(ns adventofcode.day18
  (:require [clojure.string :as string]))

(def size 100)

(defn prepare [input]
  (->> input
       (map #(string/split % #""))
       flatten
       (map #(= % "#"))
       (apply vector)))


;; (def input
;;   (->>  ["##.#.#"
;;          "...##."
;;          "#....#"
;;          "..#..."
;;          "#.#..#"
;;          "####.#"]
;;         prepare)
;;   )

(def input
  (->> "resources/day18.txt"
       slurp
       string/split-lines
       prepare
       ))

(defn translate-to-coords [pos]
  (if (pos? pos)
    (let [x (rem pos size)
          y (quot pos size)]
      [x y])
    [0 0]))

(defn translate-to-pos [[x y]]
  (+ (* size y) x))

(defn out-of-bounds [x y]
  (let [bound (- size 1)]
    (or
     (> x bound)
     (> y bound)
     (> (translate-to-pos [x y]) (- (* size size) 1)))))

(defn neighbors [pos]
  (let [[posx posy] (translate pos)]
    (for [x [-1 0 1]
          y [-1 0 1]

          :let [nposx (+ posx x)
                nposy (+ posy y)]

          :when (and
                 (not (out-of-bounds nposx nposy))
                 (not (neg? nposx))
                 (not (neg? nposy))
                 (not (and (= nposx posx)
                           (= nposy posy))))
          ]
      [nposx nposy])))

(defn is-corner? [pos]
  (or (= pos 0)
      (= pos (- (* size size) 1))
      (= pos (- size 1))
      (= pos (- (* size size) size))))


(defn turn-the-lights [[val count pos]]
  (if (true? val)
    (or (= 2 count)
        (= 3 count))
    (= 3 count)))

(defn turn-corners [[val count pos]]
  (if (is-corner? pos)
    true
    (turn-the-lights [val count pos])))

(defn print-map [input]
  (->> input
       (map #(if (true? %) "#" "."))
       (partition size)
       (map string/join)
       (map println)))

(defn get-on [input pos]
  (->> pos
       neighbors
       (map translate-to-pos)
       (mapv input)
       (filter true?)
       count))

(defn step [input func]
  (->> input
       (map vector (iterate inc 0))
       (map (fn [[pos state]] [state (get-on input pos) pos]))
       (map func)
       (apply vector)
       ))



(defn make-steps [func]
  (count
   (filter true?
           (reduce
            (fn [i pos] (step i func))
            input
            (range 100)))))

(defn day18 []
  (make-steps turn-the-lights))


(defn day18-2 []
  (make-steps turn-corners))
