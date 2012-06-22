(ns forma.schema
  (:require [forma.date-time :as date]
            [forma.reproject :as r]
            [forma.thrift :as thrift]            
            [forma.utils :as u]
            [forma.thrift :as thrift]
            [clojure.string :as s]))

(defn create-timeseries
  "Create a TimeSeries from a period start index and a collection of timeseries
   values. The period end index is calculated by adding the size of the
   collection to the period start index."
  ([start-idx series]
     {:pre [(> start-idx 0) (coll? series)]}
     (when series
       (let [elems (count series)]
         (create-timeseries start-idx (dec (+ start-idx elems)) series))))
  ([start-idx end-idx series]
     {:pre [(> start-idx 0) (<= start-idx end-idx) (coll? series)]}
     (when series
       (thrift/TimeSeries* start-idx end-idx series))))

(defn get-vals-wrap
  "Wrap get-vals so that returned vector is sufficiently nested for use in a
   Cascalog query"
  [array-obj]
  [(get-vals array-obj)])

(defn boundaries
  "Accepts a sequence of pairs of <initial time period, collection>
  and returns the maximum start period and the minimum end period. For
  example:

    (boundaries [0 [1 2 3 4] 1 [2 3 4 5]]) => [1 4]"
  [pair-seq]
  {:pre [(even? (count pair-seq))]}
  (reduce (fn [[lo hi] [x0 ct]]
            [(max lo x0) (min hi ct)])
          (for [[x0 seq] (partition 2 pair-seq)]
            [x0 (+ x0 (count seq))])))

(defn adjust
  "Appropriately truncates the incoming timeseries values (paired with
  the initial integer period), and outputs a new start and both
  truncated series. For example:

    (adjust 0 [1 2 3 4] 1 [2 3 4 5])
    ;=> (1 [2 3 4] [2 3 4])"
  [& pairs]
  {:pre [(even? (count pairs))]}
  (let [[bottom top] (boundaries pairs)]
    (cons bottom
          (for [[x0 seq] (partition 2 pairs)]
            (into [] (u/trim-seq bottom top x0 seq))))))

(defn fire-series
  "Creates a `FireSeries` object from the supplied sequence of
  `FireValue` objects."
  ([start xs]
     (fire-series start
                  (dec (+ start (count xs)))
                  xs))
  ([start end xs]
     (thrift/TimeSeries* start end xs)))

(defn adjust-fires
  "Returns the section of fires data found appropriate based on the
  information in the estimation parameter map."
  [{:keys [est-start est-end t-res]} f-series]
  (let [[f-start f-end arr-val] (thrift/unpack f-series)
        series (thrift/unpack arr-val)
        [start end] (for [pd [est-start est-end]]
                      (date/datetime->period t-res pd))]
    [(->> series
          (u/trim-seq start (inc end) f-start)
          (thrift/TimeSeries* start))]))

(defn add-fires
  "Returns a new `FireValue` object generated by summing up the fields
  of each of the supplied `FireValue` objects."
  [& f-tuples]
  (->> f-tuples
       (map thrift/unpack)
       (apply map +)
       (apply thrift/FireValue*)))

(defn neighbor-value
  "Accepts either a forma value or a sequence of sub-values."
  ([[fire short param long t-stat]]
     (thrift/NeighborValue* fire 1 short short long long t-stat t-stat param param))
  ([fire neighbors avg-short
    min-short avg-long min-long avg-stat min-stat avg-param min-param]
     (thrift/NeighborValue* fire neighbors avg-short min-short avg-long min-long
                            avg-stat min-stat avg-param min-param)))

(defn merge-neighbors
  "Merges the supplied instance of `FormaValue` into the existing
  aggregate collection of `FormaValue`s represented by
  `neighbor-val`. (`neighbor-val` must be an instance of
  `NeighborValue`."
  [neighbor-val forma-val]
  (let [[fire short param long t] forma-val        
        [n-fire ncount avg-short min-short avg-long min-long avg-stat min-stat
         ave-break min-break] (thrift/unpack neighbor-val)]
    (thrift/NeighborValue* (add-fires n-fire fire)
                           (inc ncount)
                           (u/weighted-mean avg-short ncount short 1)
                           (min min-short short)
                           (u/weighted-mean avg-long ncount long 1)
                           (min min-long long)
                           (u/weighted-mean avg-stat ncount t 1)
                           (min min-stat t)
                           (u/weighted-mean ave-break ncount param 1)
                           (min min-break param))))

(defn combine-neighbors
  "Returns a new forma neighbor value generated by merging together
   each entry in the supplied sequence of forma values."
  ;; TODO: come up with an example
  [[x & more]]
  (if x
    (reduce merge-neighbors (neighbor-value x) more)
    (thrift/NeighborValue* (thrift/FireValue* 0 0 0 0) 0 0 0 0 0 0 0 0 0)))

(defn forma-value
  "Returns a vector containing a FireValue, short-term drop,
  parametrized break, long-term drop and t-stat of the short-term
  drop."
  [fire short param-break long t-stat]
  (let [fire (or fire (thrift/FireValue* 0 0 0 0))]
    [fire short param-break long t-stat]))

(defn forma-seq
  "Accepts a number of timeseries of equal length and starting
  position, and uses the first entry in each timeseries to create a forma
  value, for all first values and on up the sequence. Series must be
  supplied as specified by the arguments for `forma-value`. For
  example:

    (forma-seq fire-series short-series long-series t-stat-series)

  `fire-series` gets special treatment because it could come into `forma-seq` as
   nil (i.e. no fires for a given pixel) per the forma-tap query in forma.clj."
  [fire-series short long t-stat break]
  (let [unpack-series (comp thrift/unpack thrift/get-series)
        length (count short)
        fire-series (if fire-series
                      (unpack-series fire-series)
                      (vec (repeat length (thrift/FireValue* 0 0 0 0))))]
    [(->> (concat (vector fire-series) [short long t-stat break])
         (map #(or % (repeat %)))
         (apply map forma-value)
         (vec))]))

(defn fires-cleanup
  "If the fire-series is nil, leave it be - it'll be handled in
   `forma-seq-non-thrift`. Else, unpack it"
  [fire-series]
  (if (nil? fire-series)
    fire-series
    (thrift/unpack (thrift/get-series fire-series))))

(defn forma-seq-non-thrift
  "Accepts a number of timeseries of equal length and starting
  position, and uses the first entry in each timeseries to create a forma
  value, for all first values and on up the sequence. Series must be
  supplied as specified by the arguments for `forma-value`. For
  example:

    (forma-seq fire-series short-series long-series t-stat-series break)

  This works as written because we are not currently using FormaValue objects
  here. Instead, the `forma-value` function merely creates a vector of values.

  `fire-series` gets special treatment because it could come into `forma-seq` as
   nil (i.e. no fires for a given pixel) per the forma-tap query in forma.clj."
  [fire-series short-series long-series t-stat-series break-series]
  (let [fires (fires-cleanup fire-series)
        shorts (vec (reductions min short-series))
        breaks (vec (reductions max break-series))]
    [(->> (concat [fire-series] [shorts] [long-series] [t-stat-series] [breaks])
          (map #(or % (repeat %)))
          (apply map forma-value)
          (vec))]))
