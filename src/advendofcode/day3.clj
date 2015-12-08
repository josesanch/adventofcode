(ns advendofcode.day3)


(defn input []
  (seq (clojure.string/split (slurp "resources/day3.txt") #"")))


(defn sum-vector [v1 v2]
  (reduce #(vector (+ (first %) (first %2))
                   (+ (second %) (second %2))) [v1 v2]))


(defn direction [dir]
  (condp = dir
    "^" [0 -1]
    "v" [0 1]
    "<" [-1 0]
    ">" [1 0]))

(defn positions [data]
  (set (conj
        (->> data
             (map direction)
             (reductions sum-vector))
        [0 0])))

(defn day3 []
  (count (positions (input))))

(defn day3-2 []
  (let [data (partition 2 (input))
        santa (map first data)
        robot (map second data)]

    (count (set (concat (positions santa)
                        (positions robot))))))
