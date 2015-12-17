(ns adventofcode.day17
  (:require [clojure.string :as string]
            [clojure.math.combinatorics :as combo]))

(def input
  (->> "resources/day17.txt"
       slurp
       string/split-lines
       (reduce conj [])
       (map read-string)
       (apply vector)))

(defn combinations [recipients litres]
  (let [ran (range (count recipients))]
    (for [s ran]
      (->> (combo/combinations ran s)
           (map #(map (partial get recipients) %))
           (filter #(= (reduce + %) litres))
           (map count)))))

(defn day17 []
  (->> (combinations input 150)
       flatten
       count))


(defn day17-2 []
  (->> (combinations input 150)
       flatten
       (group-by identity)
       (map #(hash-map :size (first %) :count (count (second %))))
       (sort-by :size)
       first))
