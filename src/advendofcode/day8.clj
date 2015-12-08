(ns advendofcode.day8
  (:require [clojure.string :as str]))


(defn input []
  (clojure.string/split-lines (slurp "resources/day8.txt")))

(defn drop-quotes [line]
  (clojure.string/join (drop-last (drop 1 (apply vector line)))))

(defn replace-escapes [line]
  (clojure.string/replace line #"\\\"|\\x[0-9a-f][0-9a-f]|\\\\" "-"))

(def in-memory-count
  (reduce #(+ % (count (replace-escapes (drop-quotes %2)))) 0 (input)))

(def in-disk
  (reduce #(+ % (count %2)) 0 (input)))

(defn day8 []
  (-
   in-disk
   in-memory-count))

(defn day8-2 []
  (-
   (reduce #(+ 2 % (count (replace-escapes-2 %2))) 0 (input))
   in-disk))
