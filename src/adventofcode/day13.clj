(ns adventofcode.day13
  (:require [clojure.string :as string]
            [clojure.math.combinatorics :as compo]))


(def input (slurp "resources/day13.txt"))


(defn update-data [data [_ person pos happiness person2]]
  (let [h (read-string happiness)
        value (if (= pos "lose") (* -1 h) h)]
    (update-in data [person] merge {person2 value})))


(defn parse-input [input]
  (reduce update-data {}
          (->> input
               clojure.string/split-lines
               (map #(re-find #"^(\w+).*\s(gain|lose)[^\d]+(\d+)\s.*\s(\w+).$" %)))))


(defn calc-happiness [data prev [p1 p2]]
  (+ prev
     (+ (get-in data [p1 p2] 0)
        (get-in data [p2 p1] 0))))

(defn happiness [data people]
  (reduce
   (partial calc-happiness data)
   0
   (partition 2 1 (conj (vec people) (first people)))))


(defn optimal-position [data]
  (first
   (sort-by
    second >
    (reduce
     #(assoc % %2 (happiness data %2))
     {}
     (compo/permutations (keys data))))))

(defn day13 []
  (optimal-position (parse-input input)))

(defn day13-2 []
  (optimal-position
   (assoc (parse-input input) "Me" {})))
