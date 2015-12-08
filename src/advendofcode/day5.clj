(ns advendofcode.day5)

(defn input []
  (clojure.string/split-lines (slurp "resources/day5.txt")))


(defn contains-at-least-3-vowels [line]
  (let [vowels (set "aeiou")]
    (>= (count (filter #(contains? vowels %) line)) 3)))


(defn double-letter [line]
  (loop [current-letter nil
         current-line line]
    (if-not (empty? current-line)
      (if (= current-letter (first current-line))
        true
        (recur (first current-line) (rest current-line))))))

(defn dont-contains [line]
  (nil? (re-find #"ab|cd|pq|xy" line)))

(defn nice-string [string]
  (and
   (contains-at-least-3-vowels string)
   (double-letter string)
   (dont-contains string)))

(defn day5 []
  (count (filter nice-string (input))))

(defn repeat-letter [line]
  (re-find #"(\w)\w\1" line))

(defn pair-letters-twice [line]
  (re-find #"(\w\w).*?\1" line))


(defn nice-string2 [line]
  (and (repeat-letter line)
       (pair-letters-twice line)))

(defn day5-2 []
  (count (filter nice-string2 (input))))
