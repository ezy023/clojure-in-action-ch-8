(ns chapter08.date-operations
  (:import (java.text SimpleDateFormat)
           (java.util Calendar GregorianCalendar))
  (:require [clojure.string]))

(defn date [date-string]
  (let [f (SimpleDateFormat. "yyyy-MM-dd")
        d (.parse f date-string)]
    (doto (GregorianCalendar.)
      (.setTime d))))

(defn day-from [d]
  (.get d Calendar/DAY_OF_MONTH))

(defn month-from [d]
  (inc (.get d Calendar/MONTH)))

(defn year-from [d]
  (.get d Calendar/YEAR))

(defn pad [n]
  (if (< n 10)
    (str "0" n)
    (str n)))

(defn as-string [date]
  (let [y (year-from date)
        m (pad (month-from date))
        d (pad (day-from date))]
    (clojure.string/join "-" [y m d])))

(defn increment-day [d]
  (doto (.clone d)
    (.add GregorianCalendar/DAY_OF_MONTH 1)))

(defn increment-month [d]
  (doto (.clone d)
    (.add GregorianCalendar/MONTH 1)))

(defn increment-year [d]
  (doto (.clone d)
    (.add GregorianCalendar/YEAR 1)))

(defn decrement-day [d]
  (let [c-d (.clone d)]
    (do
      (.add c-d GregorianCalendar/DAY_OF_MONTH -1)
      c-d)))

(defn decrement-month [d]
  (let [c-d (.clone d)]
    (do
      (.add c-d GregorianCalendar/MONTH -1)
      c-d)))

(defn decrement-year [d]
  (let [c-d (.clone d)]
    (do
      (.add c-d GregorianCalendar/YEAR -1)
      c-d)))
