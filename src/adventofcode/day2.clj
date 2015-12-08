(ns adventofcode.day2)

(defn input []
  (clojure.string/split-lines (slurp "resources/day2.txt")))


(defn paper [size]
  (let [[l w h] (map read-string (clojure.string/split size #"x"))
        [a b c] [(* 2 l w)
                 (* 2 w h)
                 (* 2 h l)]]
    (+ a b c
       (/ (min a b c) 2))))



(defn day2 []
  (->> (input)
       (map paper)
       (reduce +)))


(defn paper2 [size]
  (let [[l w h] (map read-string (clojure.string/split size #"x"))
        [ribbon wrap] [(+ l l w  w)
                       (* l w h)]]
    (+ ribbon wrap)))



(defn day2-2 []
  (->> (input)
       (map paper2)
       (reduce +)))
