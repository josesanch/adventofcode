(ns adventofcode.day9
  (:require [clojure.math.combinatorics :refer [permutations]]))

(defn input []
  (clojure.string/split-lines (slurp "resources/day9.txt")))

(defn parse-input [data]
  (loop [cities {}
         data data]

    (let [line (first data)]
      (if-not (nil? line)
        (let [[from to _distance] (clojure.string/split line #" to | = ")
              distance (read-string _distance)]
          (recur
           (-> cities
               (assoc-in [from to] distance)
               (assoc-in [to from] distance)
               )
           (rest data)))
        cities))))


(defn get-distance [cities from to]
  (get-in cities [from to] (get-in cities [to from] 0)))

(defn find-distance [cities path]
  [(clojure.string/join "->" path)
   (reduce
    +
    (map
     #(get-distance cities (first %) (last %))
     (partition 2 1 path)))])

(defn distances [cities]
  (sort-by last (map
                 (partial find-distance cities)
                 (permutations (set (keys cities))))))

(def cities (parse-input (input)))



(defn day9 []
  (first (distances cities)))

(defn day9-2 []
  (last (distances cities)))
