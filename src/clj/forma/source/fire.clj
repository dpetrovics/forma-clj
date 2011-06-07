(ns forma.source.fire
  (:use cascalog.api
        [forma.date-time :only (datetime->period
                                convert)]
        [forma.source.modis :only (latlon->modis
                                   hv->tilestring
                                   tilestring->hv)])
  (:require [clojure.string :as s]
            [forma.hadoop.io :as io]
            [forma.hadoop.predicate :as p])
  (:import [forma.schema FireTuple]
           [java.util ArrayList])
  (:gen-class))

;; ### Thrift Manipulation
;;
;; The fires dataset is different from previous MODIS datasets in that
;; it requires us to keep track of multiple values for each MODIS
;; pixel. We must maintain a total fire count, as well as a count of
;; the subsets of the total that satisfy certain conditions, such as
;; `Temp > 330 Kelvin`, `Confidence > 50`, or both at once. We
;; abstract this complication away by wrapping up each of these into a
;; compound value, represented as a `FireTuple` thrift object. We wrap
;; up collections of `FireTuple` objects in a `TimeSeries` thrift
;; object.

;; ### Fire Predicates

(defn comma [& xs] (s/join "-" xs))

(defn daily-datestring
  "Takes a datestring from our daily fire datasets, formatted as
  `MM/DD/YYYY`, and returns a date formatted as `YYYY-MM-DD`."
  [datestring]
  (let [[month day year] (s/split datestring #"/")]
    (comma year month day)))

(defn monthly-datestring
  "Takes a datestring from our monthly fire datasets, formatted as
  `YYYYMMDD`, and returns a date formatted as `YYYY-MM-DD`."
  [datestring]
  (convert datestring :basic-date :year-month-day))

(defn strings->floats
  [& strings]
  (map #(Float. %) strings))

(def
  ^{:doc "Predicate macro that converts confidence and temperature
  into a tuple of fire characteristics."}
  fire-characteristics
  (<- [?conf ?kelvin :> ?tuple]
      (p/full-count ?conf :> ?count)
      (p/filtered-count [330] ?kelvin :> ?temp-330)
      (p/filtered-count [50] ?conf :> ?conf-50)
      (p/bi-filtered-count [330 50] ?conf ?kelvin :> ?both-preds)
      (io/fire-tuple ?temp-330 ?conf-50 ?both-preds ?count :> ?tuple)))

(defaggregateop
  ^{:doc " Aggregates a number of firetuples by adding up the values
  of each `FireTuple` property."}
  merge-firetuples
  ([] [0 0 0 0])
  ([state tuple] (map + state (io/extract-fields tuple)))
  ([state] [(apply io/fire-tuple state)]))

;; TODO: Move this to some other namespace.
(defn running-sum
  "Given an accumulator, an initial value and an addition function,
  transforms the input sequence into a new sequence of equal length,
  increasing for each value."
  [acc init add-func tseries]
  (first (reduce (fn [[coll last] new]
                   (let [last (add-func last new)]
                     [(conj coll last) last]))
                 [acc init]
                 tseries)))

;; Special case of `running-sum` for `FireTuple` thrift objects.
(defmapop running-fire-sum
  [tseries]
  (let [empty (io/fire-tuple 0 0 0 0)]
    (->> tseries
         (running-sum [] empty io/add-fires)
         io/fire-series)))

;; ## Fire Queries

(defn fire-source-monthly
  "Takes a source of monthly fire textlines from before , and returns tuples with dataset, date,
  position and value all defined. In this case, the value `?tuple` is
  a `FireTuple` thrift object containing all relevant characteristics
  of fires for that particular day."
  [src]
  (<- [?dataset ?date ?t-res ?lat ?lon ?tuple]
      (src ?line)
      (p/mangle [#"\s+"] ?line :> ?datestring _ _ ?s-lat ?s-lon ?s-kelvin _ _ _ ?s-conf)
      (not= "YYYYMMDD" ?datestring)
      (strings->floats ?s-lat ?s-lon ?s-kelvin ?s-conf :> ?lat ?lon ?kelvin ?conf)
      (p/add-fields "fire" "01" :> ?dataset ?t-res)
      (monthly-datestring ?datestring :> ?date)
      (fire-characteristics ?conf ?kelvin :> ?tuple)))

(defn fire-source-daily
  "Takes a source of textlines, and returns tuples with dataset, date,
  position and value all defined. In this case, the value `?tuple` is
  a `FireTuple` thrift object containing all relevant characteristics
  of fires for that particular day."
  [src]
  (<- [?dataset ?date ?t-res ?lat ?lon ?tuple]
      (src ?line)
      (p/mangle [#","] ?line :> ?s-lat ?s-lon ?s-kelvin _ _ ?datestring _ _ ?s-conf _ _ _)
      (strings->floats ?s-lat ?s-lon ?s-kelvin ?s-conf :> ?lat ?lon ?kelvin ?conf )
      (p/add-fields "fire" "01" :> ?dataset ?t-res)
      (daily-datestring ?datestring :> ?date)
      (fire-characteristics ?conf ?kelvin :> ?tuple)))

(defn reproject-fires
  "Aggregates fire data at the supplied path by modis pixel at the
  supplied resolution."
  [m-res src]
  (<- [?dataset ?m-res ?t-res ?tilestring ?date ?sample ?line ?tuple]
      (p/add-fields m-res :> ?m-res)
      (src ?dataset ?date ?t-res ?lat ?lon ?tuple)
      (latlon->modis m-res ?lat ?lon :> ?mod-h ?mod-v ?sample ?line)
      (hv->tilestring ?mod-h ?mod-v :> ?tilestring)))

;; #### Time Series Processing
;;
;; TODO: Split this business off into a separate job.

(defn aggregate-fires
  "Converts the datestring into a time period based on the supplied
  temporal resolution."
  [t-res src]
  (<- [?dataset ?m-res ?new-t-res ?tilestring ?tperiod ?sample ?line ?newtuple]
      (p/add-fields t-res :> ?new-t-res)
      (src ?dataset ?m-res ?t-res ?tilestring ?datestring ?sample ?line ?tuple)
      (datetime->period ?new-t-res ?datestring :> ?tperiod)
      (merge-firetuples ?tuple :> ?newtuple)))

(defn fire-series
  "Aggregates fires into timeseries."
  [t-res start end src]
  (let [[start end] (map (partial datetime->period t-res) [start end])
        length (-> end (- start) inc)
        mk-fire-tseries (p/vals->sparsevec start length (io/fire-tuple 0 0 0 0))]
    (<- [?dataset ?m-res ?t-res ?mod-h ?mod-v ?sample ?line ?t-start ?t-end ?ct-series]
        (src ?dataset ?m-res ?t-res ?tilestring ?tperiod ?sample ?line ?tuple)
        (tilestring->hv ?tilestring :> ?mod-h ?mod-v)
        (mk-fire-tseries ?tperiod ?tuple :> _ ?tseries)
        (p/add-fields start end :> ?t-start ?t-end)
        (running-fire-sum ?tseries :> ?ct-series))))

(defn -main
  [monthly-path daily-path out-dir]
  (?- (io/chunk-tap out-dir)
      (->> (cascalog.api/union (fire-source-daily (hfs-textline monthly-path))
                               (fire-source-monthly (hfs-textline daily-path)))
           (reproject-fires "1000"))))

;; TODO: Move this shit out of fires!
;;
;; (defn run-test [path out-path]
;;   (?- (hfs-seqfile out-path)
;;       (->> (hfs-textline path)
;;            fire-source-daily
;;            (reproject-fires "1000")
;;            (aggregate-fires "32")
;;            (fire-series "32" "2000-11-01" "2011-04-01"))))

;; (def some-map
;;   {:est-start "2005-12-01"
;;    :est-end "2011-04-01"
;;    :t-res "32"
;;    :long-block 15
;;    :window 5})

;; (defn run-second [path]
;;   (let [src (hfs-seqfile path)]
;;     (?- (stdout)
;;         (-> (<- [?dataset ?m-res ?t-res ?mod-h ?mod-v ?sample ?line ?est-start ?count]
;;              (src ?dataset ?m-res ?t-res ?mod-h ?mod-v ?sample ?line ?start _ ?series)
;;              (adjust-fires some-map ?start ?series :> ?est-start ?fire-series)
;;              (io/count-vals ?fire-series :> ?count))
;;             (cascalog.ops/first-n 2)))))

;; (defn grab [tuple]
;;   (def test-fires tuple)
;;   tuple)

;; (defn run-check []
;;   (let [src (hfs-seqfile "/Users/sritchie/Desktop/FireOutput/")]
;;     (?- (stdout)
;;         (-> (<- [?dataset ?m-res ?t-res ?mod-h ?mod-v ?sample ?line ?t-start ?t-end ?ct-series]
;;                 (src ?dataset ?m-res ?t-res
;;                      ?mod-h ?mod-v ?sample ?line ?t-start ?t-end ?ct-series))
;;             (cascalog.ops/first-n 2)))))
