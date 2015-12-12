(ns adventofcode.day12
  (:require [cheshire.core :refer [parse-string]]))

(def input
  (slurp "resources/day12.txt"))

(defn day12 []
  (reduce +
          (map
           read-string
           (re-seq #"\-?\d+" input))))


(defn sum-numbers [prev json]
  (+ prev
     (cond
       (or (seq? json) (vector? json)) (reduce sum-numbers 0 json)
       (map? json) (if-not (contains? (set (vals json)) "red")
                     (reduce sum-numbers 0 (vals json))
                     0)
       (number? json) json
       :else 0)))

(defn day12-2 []
  (let [json (parse-string input)]
    (reduce sum-numbers 0 json)))
