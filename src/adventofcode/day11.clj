(ns adventofcode.day11
  (:require [clojure.string :as string]))

(def input "hxbxwxba")
(def a 97)
(def z 122)

(defn increasing? [chunk]
  (every? #(= % -1) (map #(apply - %)
                         (partition 2 1 (map byte chunk)))))
(defn contain-inc? [pw]
  (some true? (map increasing? (partition 3 1 pw))))

(defn not-contain-iol? [pw]
  (every? #(not (.contains "iol" (str %))) pw))

(defn two-same-letter? [pw]
  (re-find #"(\w)\1.*(\w)\2" pw))


(defn valid-pasword [pw]
  (and (contain-inc? pw)
       (not-contain-iol? pw)
       (two-same-letter? pw)))

(defn next-password [pw]
  (let [car (byte (last pw))
        is-z? (>= car z)
        next (char (if is-z? a (inc car)))
        rest-pw (string/join (drop-last pw))]

    (str (if (and is-z? (not-empty rest-pw))
           (next-password rest-pw)
           rest-pw)
         next)))



(defn gen-next-password [pw]
  (loop [next-pw (next-password pw)]

    (if (valid-pasword next-pw)
      next-pw
      (recur (next-password  next-pw)))
    ))


(defn day11 []
  (gen-next-password input))

(defn day11-2 []
  (gen-next-password (day11)))
