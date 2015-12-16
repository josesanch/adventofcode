(ns adventofcode.day16
  (:require [clojure.string :as string]))

(defn parse-line [line]
  (let [[_ name things] (re-find #"([^:]+):(.*)" line)]
    (reduce
     (fn [prev [_ key value]]
       (let [v (read-string value)
             k (keyword key)]
         (assoc-in prev [name k] v)))
     {name {}}
     (re-seq #"(\w+):\s(\d+)" things))))


(def data {:children 3
           :cats 7
           :samoyeds 2
           :pomeranians 3
           :akitas 0
           :vizslas 0
           :goldfish 5
           :trees 3
           :cars 2
           :perfumes 1})


(def input (->> "resources/day16.txt"
                slurp
                string/split-lines
                (map parse-line)))



(defn is-she? [compare aunt]
  (->> aunt
       vals
       first
       (reduce-kv #(and ((compare %2) %3 (data %2)) %1) true)))


(defn filter-ants [input comp]
  (->> input
       (filter (partial is-she? comp))
       first))

(defn compare-equal-fn [attr] =)

(defn day16 []
  (filter-ants input compare-equal-fn))

(defn compare-fn [attr]
  (condp = attr
    :cats >
    :trees >
    :pomeranians <
    :goldfish <
    =))

(defn day16-2 []
  (filter-ants input compare-fn))
