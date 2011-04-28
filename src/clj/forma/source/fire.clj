(ns forma.source.fire
  (:use cascalog.api
        [forma.date-time :only (datetime->period current-period)]
        [clojure.string :only (split)]
        [forma.matrix.utils :only (sparse-expander)]
        [forma.source.modis :only (latlon->modis
                                   hv->tilestring)])
  (:require [forma.hadoop.predicate :as p]
            [cascalog.ops :as c]
            [cascalog.vars :as v])
  (:import [forma.schema FireTuple TimeSeries]
           [java.util ArrayList]))

(def prepath "/Users/sritchie/Desktop/FORMA/FIRE/")
(def testfile (str prepath "MCD14DL.2011074.txt"))

(def new-fire-tap
  (memory-source-tap
   [["-4.214,152.190,319.9,1.6,1.2,01/15/2011,0035,T,0,5.0,301.3,27.8"]
   ["-26.464,148.237,312.2,1.7,1.3,01/15/2011,0040,T,30,5.0,288.3,16.9"]
   ["-28.314,150.342,329.3,2.5,1.5,01/15/2011,0040,T,82,5.0,301.9,83.4"]
   ["-27.766,140.252,317.3,1.1,1.0,01/15/2011,0040,T,63,5.0,305.8,15.4"]
   ["-29.059,150.453,331.3,2.6,1.6,01/15/2011,0040,T,84,5.0,302.4,91.2"]
   ["-29.059,150.453,331.3,2.6,1.6,01/15/2011,0040,T,84,5.0,302.4,91.2"]
   ["-29.059,150.453,331.3,2.6,1.6,02/15/2011,0040,T,84,5.0,302.4,91.2"]
   ["-29.059,150.453,331.3,2.6,1.6,03/15/2011,0040,T,84,5.0,302.4,91.2"]
   ["-29.063,150.447,327.3,2.6,1.6,04/15/2011,0040,T,80,5.0,301.9,67.0"]
   ["-28.963,148.843,322.2,2.0,1.4,01/15/2011,0040,T,75,5.0,302.9,26.5"]
   ["-28.971,148.801,329.4,2.0,1.4,01/15/2011,0040,T,82,5.0,303.2,51.0"]
   ["-28.975,148.842,328.7,2.0,1.4,01/15/2011,0040,T,82,5.0,303.2,50.5"]
   ["-29.262,150.233,328.2,2.5,1.5,01/15/2011,0040,T,81,5.0,301.1,64.9"]]))

;; ### Fire Predicates

(defn format-datestring
  [datestring]
  (let [[month day year] (split datestring #"/")]
    (format "%s-%s-%s" year month day)))

(defmacro defpredsummer
  "Generates cascalog defaggregateops for counting items that satisfy
  some custom predicate. Defaggregateops don't allow anonymous
  functions, so we went this route instead."
  [name vals pred]
  `(defaggregateop ~name
     ([] 0)
     ([count# ~@vals] (if (~pred ~@vals)
                      (inc count#)
                      count#))
     ([count#] [count#])))

(defpredsummer fires-above-330
  [val] #(> % 330))

(defpredsummer conf-above-50
  [val] #(> % 50))

(defpredsummer per-day
  [val] identity)

(defpredsummer both-preds
  [conf temp]
  (fn [c t] (and (> t 330)
                (> c 50))))

(defn tupleize
  [t-above-330 c-above-50 both-preds count]
  (FireTuple. t-above-330 c-above-50 both-preds count))

(def
  ^{:doc "Generates a tuple of fire characteristics from confidence
  and temperature."}
  fire-characteristics
  (<- [?conf ?kelvin :> ?tuple]
      ((c/juxt #'conf-above-50 #'per-day) ?conf :> ?conf-50 ?count)
      (fires-above-330 ?kelvin :> ?temp-330)
      (both-preds ?conf ?kelvin :> ?both-preds)
      (tupleize ?temp-330 ?conf-50 ?both-preds ?count :> ?tuple)))

;; ## Fire Queries


(defn mk-ts
  "TODO: DOCS"
  [start end ts-seq]
  (doto (TimeSeries.)
    (.setStartPeriod start)
    (.setEndPeriod end)
    (.setValues (ArrayList. ts-seq))))

(defbufferop [sparse-expansion [t-res start-date end-date missing-val]]
  {:doc "Receives 2-tuple pairs of the form `<idx, val>`, and inserts
  each `val` into a sparse vector of the supplied length at the
  corresponding `idx`. `missing-val` will be substituted for any
  missing value."}
  [tuples]
  (let [one (datetime->period t-res start-date)
        two (datetime->period t-res end-date)
        length (inc (- two one))
        ts (mk-ts one
                  two
                  (sparse-expander missing-val tuples
                                   :start one
                                   :length length))]
    
    [[ts]]))

(defn fire-source
  "Takes a source of textlines, and returns 2-tuples with latitude and
  longitude."
  [source]
  (let [vs (v/gen-non-nullable-vars 5)]
    (<- [?dataset ?datestring ?t-res ?lat ?lon ?tuple]
        (source ?line)
        (identity "fire" :> ?dataset)
        (identity "01" :> ?t-res)
        (format-datestring ?date :> ?datestring)
        (p/mangle ?line :> ?lat ?lon ?kelvin _ _ ?date _ _ ?conf _ _ _)
        (fire-characteristics ?conf ?kelvin :> ?tuple))))

(defn rip-fires
  "Aggregates fire data at the supplied path by modis pixel at the
  supplied resolution."
  [m-res source]
  (let [fires (fire-source source)]
    (<- [?dataset ?m-res ?t-res ?tilestring ?datestring ?sample ?line ?tuple]
        (fires ?dataset ?datestring ?t-res ?lat ?lon ?tuple)
        (latlon->modis m-res ?lat ?lon :> ?mod-h ?mod-v ?sample ?line)
        (hv->tilestring ?mod-h ?mod-v :> ?tilestring)
        (identity m-res :> ?m-res))))

(defn fire-series
  [t-res start end src]
  (let [empty (FireTuple. 0 0 0 0)]
    (<- [?dataset ?m-res ?new-t-res ?tilestring ?sample ?line ?tseries]
        (datetime->period ?new-t-res ?datestring :> ?tperiod)
        (identity t-res :> ?new-t-res)
        (src ?dataset ?m-res ?t-res ?tilestring ?datestring ?sample ?line ?tuple)
        (:sort ?tperiod)
        (sparse-expansion [t-res start end empty] ?tperiod ?tuple :> ?tseries))))

(defn run-rip
  "Rips apart fires!"
  [t-res start end]
  (?- (stdout)
      (fire-series t-res start end (rip-fires "1000" new-fire-tap))))
