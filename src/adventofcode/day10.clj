(ns adventofcode.day10)

(def input "3113322113")

(defn say [[rep num]] (str (count rep) num))

(defn read-aloud [number]
  (clojure.string/join (map say (re-seq #"(\d)\1*" number))))

(defn repeat-say [input times]
  (loop [pos 1
         number input]
    (println pos)
    (if (<= pos times)
      (recur (inc pos) (read-aloud number))
      (count number))))


(defn day10 []
  (repeat-say input 40))

(defn day10-2 []
  (repeat-say input 50))
