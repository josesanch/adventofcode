(ns adventofcode.day7)

(defn input []
  (clojure.string/split-lines (slurp "resources/day7.txt")))

(defn regexp [line]
  (filter (comp not nil?) (reverse  (rest (re-find #"(?:(\w+)\s)?(?:(\w+)\s)?(?:(\w+)\s)->\s(.*)" line)))))

(defn is-numeric? [n]
  (and (not (nil? n))
       (Character/isDigit (first n))))

(defn bit-operation [op wire1 wire2]
  (condp = op
    "AND" (bit-and wire1 wire2)
    "OR" (bit-or wire1 wire2)
    "RSHIFT" (bit-shift-right wire1 wire2)
    "LSHIFT" (bit-shift-left wire1 wire2)
    "NOT" (- 65535 wire2)
    nil wire2))

(defn get-value [wire state]
  (if (is-numeric? wire)
    (read-string wire)
    (state wire 0)))

(defn perform-logic [wire1 op wire2 to_wire state]
  (assoc state to_wire
         (bit-operation op (get-value wire1 state) (get-value wire2 state))))

(defn parse [state line]
  (let [[to_wire wire2 op wire1] (regexp line)]
    (perform-logic wire1 op wire2 to_wire state)))


(defn perform-loop [data initial-state]
  (loop [state initial-state]
    (let [new-state (reduce #(parse %1 %2) state data)]
      (if-not (= state new-state)
        (recur new-state)
        new-state))))

(defn day7 []
  (perform-loop (input) {}))

(defn mutate-b [input new-value]
  (map (fn [line] (if (= (take 2 (reverse line)) (seq "b "))
                   (str new-value " -> b")
                   line))
       input))

(defn day7-2 []
  (perform-loop
   (mutate-b (input) (get (day7) "a"))
   {}))
