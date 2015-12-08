(ns adventofcode.day1)

(defn input []
  (seq (slurp "resources/day1.txt")))

(defn day1 []
  (reduce  #(+ % (condp = %2
                   \( 1
                   -1))
           0 (input)))


(defn day1-2 []
  (first (filter #(= -1 (second %))
                 (map-indexed vector
                              (reductions #(+ % (condp = %2
                                                  \( 1
                                                  -1))
                                          0 (input))))))
