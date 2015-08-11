(ns chapter08.expense-finders-spec
  (:require [chapter08.expense-finders :refer :all]
            [clojure.test :refer :all]))

(deftest test-filter-greater-than
  (let [fetched [(struct-map expense :amount 10.0 :date "2010-02-28")
                 (struct-map expense :amount 20.0 :date "2010-02-25")
                 (struct-map expense :amount 30.0 :date "2010-02-21")]
        filtered (expenses-greater-than fetched 15.0)]
    (is (= (count filtered) 2))
    (is (= (:amount (first filtered)) 20.0))
    (is (= (:amount (last filtered)) 30.0))))
