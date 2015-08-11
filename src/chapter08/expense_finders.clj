(ns chapter08.expense-finders
  (:require [clojure.string :as string]))

(defstruct expense :amount :date)

(defn log-call [id & args]
  "Do logging to some audit data-store"
  (println "Audit - called" id "with:" (string/join ", " args)))

(defn fetch-all-expenses [username start-date end-date]
  "Find in date-store, return list of expense structs"
  (log-call "fetch-all" username start-date end-date))

(defn expenses-greater-than [expenses threshold]
  (log-call "expenses-greater-than" threshold)
  (filter #(> (:amount %) threshold) expenses))

(defn fetch-expenses-greater-than [username start-date end-date threshold]
  (let [all (fetch-all-expenses username start-date end-date)]
    (expenses-greater-than all threshold)))
