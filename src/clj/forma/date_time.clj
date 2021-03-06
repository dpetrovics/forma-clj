;; This namespace allows for conversion of dates into integer time
;; periods, as measured from some reference date. This allows for
;; proper temporal comparison of two unrelated datasets.

(ns forma.date-time
  (:use [clj-time.core :only (date-time month year)])
  (:require [clj-time.core :as time]
            [clj-time.format :as f]))

(defn parse
  "Returns a `DateTime` object generated by parsing the supplied
  string `s` using the supplied format. Options for `format` can be
  viewed with a call to `(clj-time.format/show-formatters)`."
  [s format]
  (->> s (f/parse (f/formatters format))))

(defn unparse
  "Returns a string generated by unparsing the supplied `DateTime`
  object using the supplied format. Options for `format` can be viewed
  with a call to `(clj-time.format/show-formatters)`."
  [dt format]
  (->> dt (f/unparse (f/formatters format))))

(defn convert
  "Converts the supplied string `s` between the two supplied
  formats. Options for each format keyword can be viewed with a call
  to `(clj-time.format/show-formatters)`."
  [s from-format to-format]
  (-> s
      (parse from-format)
      (unparse to-format)))

(defn within-dates?
  "Returns true if the supplied datestring `dt` falls within the dates
  described by start and start end (exclusive)`, false otherwise. the
  `:format` keyword argument can be used to specify the format of the
  datestrings. Options for `:format` can be viewed with a call
  to `(clj-time.format/show-formatters)`.

  For example:
    (within? \"2005-12-01\" \"2011-01-02\" \"2011-01-01\")
    ;=> true"
  [start end dt & {:keys [format]
                   :or {format :year-month-day}}]
  (let [[start end dt] (map #(parse % format) [start end dt])]
    (time/within? (time/interval start end) dt)))

;; ### Reference Time
;;
;; In developing the time period conversion functions, we noticed that
;; as long as the time period remained consistent from dataset to
;; dataset, the ability to 0-index each dataset became irrelevant.
;; Our choice of the [Unix epoch](http://goo.gl/KyuLr) as a common
;; reference date ensures that, regardless of the date on which the
;; specific MODIS product became active, datasets at the same temporal
;; resolution will match up.

;; ## Date -> Time Period Conversion
;;
;; For NASA's composite products, MODIS data is composited into either
;; monthly, 16-day, or 8-day intervals. Each of these scales begins
;; counting on January 1st. Monthly datasets begin counting on the
;; first of each month, while the others mark off blocks of 16 or 8
;; ordinal days from the beginning of the year.
;;
;; (It's important to note that time periods are NOT measured from the
;; activation date of a specific product. The first available NDVI
;; 16-day composite, for example, has a beginning date of February
;; 18th 2001, or ordinal day 49, the 1st day of the 4th period as
;; measured from January 1st. With our reference of January 1st, This
;; dataset will receive an index of 3, and will match up with any
;; other data that falls within that 16-day period. This provides
;; further validation for our choice of January 1st, 2000 as a
;; consistent reference point.
;;
;; Additionally, it should be noted that the final dataset is never
;; full; the 23rd 16-day dataset of the year holds data for either 13
;; or 14 days, depending on leap year.)
;;
;; So! To construct a date, the user supplies a number of date
;; "pieces", ranging from year down to microsecond. To compare two
;; times on ordinal day, we use the clj-time library to calculate the
;; interval between a given date and January 1st of the year in which
;; the date occurs.

(defn ordinal
  "Returns the ordinal day index of a given date."
  [dt]
  (time/in-days
   (time/interval (time/date-time (year dt))
                  dt)))

;; the `ordinal` function complements the other "date-piece" functions
;; supplied by the clj-time library; day, month, year, and the others
;; each allow for extraction of that particular element of a date. We
;; define the delta function to allow us to take the difference of a
;; specific date-piece between two dates.

(defn delta [f a b] (- (f b) (f a)))

;; For example,
;;
;;     (delta ordinal a b)
;;
;; returns the difference between the ordinal day values of the two
;; supplied dates. (Note that `(partial delta ordinal)` ignores the
;; year value of each of the dates. That function returns the same
;; value for a = Jan 1st, 2000, b = Feb 25th, 2000 and b = Feb 25th,
;; 2002.)

(defn per-year
  "Calculates how many periods of the given span of supplied units can
  be found in a year. Includes the final period, even if that period
  isn't full."
  [unit span]
  (let [m {ordinal 365 month 12}]
    (-> (m unit) (/ span) Math/ceil int)))

(defn delta-periods
  "Calculates the difference between the supplied start and end dates
  in span-sized groups of unit (months or ordinals). [unit span] could be
  [ordinal 16], for example."
  [unit span start end]
  (let [[years units] (map #(delta % start end) [time/year unit])]
    (+ (* years (per-year unit span))
       (quot units span))))

;; The following partial functions take care of the issue with delta
;; above, by calculating the span in periods across years. (Note that
;; these functions only work for MODIS products using regular
;; production. All terra products meet this restriction -- the aqua
;; products use phased production with 16- and 8-day temporal
;; resolution, so time periods begin on January 9th.)

(def months (partial delta-periods month 1))
(def sixteens (partial delta-periods ordinal 16))
(def eights (partial delta-periods ordinal 8))

(defn periodize
  "Converts the supplied `org.joda.time.DateTime` object into a
reference time interval at the supplied temporal
resolution. `DateTime` objects can be created with `clj-time`'s
`date-time` function."
  [temporal-res date]
  (let [converter (case temporal-res
                        "32" months
                        "16" sixteens
                        "8" eights)]
    (converter (time/epoch) date)))

(defn datetime->period
  "Converts a formatted datestring, into an integer time period at the
  supplied temporal resolution. The default format is
  `:year-month-day`, or `YYYY-MM-DD`."
  ([res datestring]
     (datetime->period res datestring :year-month-day))
  ([res datestring format]
     (let [date (parse datestring format)]
       (periodize res date))))

(defn period->datetime
  "Converts an integer time period at the supplied temporal resolution
  into a formatted datestring. The default format is
  `:year-month-day`, or `YYYY-MM-DD`."
  ([res period]
     (period->datetime res period :year-month-day))
  ([res period format]
     (let [[unit span f] (case res
                               "32" [month 1 time/months]
                               "16" [ordinal 16 time/days]
                               "8" [ordinal 8 time/days]
                               "1" [ordinal 1 time/days])
           [yrs pds] ((juxt quot mod) period (per-year unit span))]
       (-> (time/epoch)
           (time/plus (time/years yrs) (f (* span pds)))
           (unparse format)))))

(defn beginning
  "Accepts a string representation of a date-time object, and returns
a new string object representing the absolute beginning of time period
in which `string` lies (according to the supplied resolution, `res`)."
  ([res string]
     (beginning res string :year-month-day))
  ([res string format]
     (let [period (datetime->period res string format)]
       (period->datetime res period format))))

(defn diff-in-days
  "Returns the difference in ordinal days between the supplied date
  strings."
  [one-date two-date]
  (->> [one-date two-date]
       (map #(parse % :year-month-day))
       (apply time/interval)
       (time/in-days)))

(defn date-offset
  "Returns the difference in ordinal days between the beginnings of
  the two supplied periods. For example:

 (date-offset \"16\" 1 \"32\" 1) => 15"
  [from-res from-period to-res to-period]
  (diff-in-days (period->datetime from-res from-period)
                (period->datetime to-res to-period)))

(defn period-span
  "Returns the length in days of the supplied period at the supplied
  resolution. For example:

  (period-span \"32\" 11) => 31"
  [t-res pd]
  (->>  [pd (inc pd)]
        (map (partial period->datetime t-res))
        (apply diff-in-days)))

(defn shift-resolution
  "Takes a period at `from-res`, returns the corresponding period at
  `to-res`."
  [from-res to-res period]
  (->> period
       (period->datetime from-res)
       (datetime->period to-res)))

(defn current-period
  "Returns the current time period for the supplied resolution. For
  example:

    (current-period \"32\")
    => 495 ;; (on April 27, 2011, this function's birthday!)"
  [res]
  (periodize res (time/now)))

(defn relative-period
  "convert periods (string) to an integer value that is relative to
  the start period.

  Example usage:
  (relative-period \"32\" 391 [\"2005-02-01\" \"2005-03-01\"])
  => (30 31)"
  [t-res start period-seq]
  (map (comp #(long (- % start))
             (partial datetime->period t-res))
       period-seq))

(defn msecs-from-epoch
  "Total number of milliseconds passed since January 1st, 1970 (the
  Unix epoch)."
  [date]
  (time/in-msecs (time/interval (time/epoch) date)))

(defn monthly-msec-range
  "Returns a sequence of msec values corresponding to each time period
  at monthly resolution from the supplied `start-date` (inclusive) to
  the `end-date` (exclusive). Both inputs must be date-time objects
  created with `clj-time.core/date-time`."
  [start-date end-date]
  (let [total-months (inc (delta-periods month 1 start-date end-date))]
    (for [month-offset (range total-months)]
      (msecs-from-epoch (time/plus start-date
                                   (time/months month-offset))))))

(defn res->period-count
  [res]
  (apply per-year
         (case res
           "32" [month 1]
           "16" [ordinal 16]
           "8" [ordinal 8]
           "1" [ordinal 1])))

(defn convert-period-res
  "Convert a period from in-res to corresponding period at out-res.

   By converting a period to a date, we get the first date within a period. Converting date to period, we get the period in which that first date falls, at the new resolution."
  [res-in res-out period]
  (->> (period->datetime res-in period)
       (datetime->period res-out)))