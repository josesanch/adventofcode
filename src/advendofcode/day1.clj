(ns advendofcode.day1)

(defn input []
  (seq (slurp "resources/day1.txt")))

(defn day1 [data]
  (reduce  #(+ % (condp = %2
                   \( 1
                   -1))
           0 data))


(defn day1-2 [data]
  (first (filter #(= -1 (second %))
                 (map-indexed vector
                              (reductions #(+ % (condp = %2
                                                  \( 1
                                                  -1))
                                          0 data)))))
