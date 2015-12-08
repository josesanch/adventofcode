(ns adventofcode.day6)

(defn input []
  (clojure.string/split-lines (slurp "resources/day6.txt")))


(def length 1000)
(def lights (into [] (take (* length length) (repeat 0))))
(def mlights  (into [] (take (* 3 3) (repeat 0))))


(defn translate [[x y]]
  (+ x (* y length)))


(defn select-square [x y to_x to_y]
  (for [_x (range x (+ to_x 1))
        _y (range y (+ to_y 1))]
    [_x _y]))

(defn fn-action [action]
  (condp = action
    "turn on" (fn [i] 1)
    "turn off" (fn [i] 0)
    "toggle" #(if (zero? %) 1 0)))

(defn brightness [action]
  (condp = action
    "turn on" (fn [i] (inc i))
    "turn off" (fn [i] (if (and i (pos? i)) (dec i) i))
    "toggle" (fn [i] (+ i 2))))

(defn perform-action [action positions lights]
  (reduce #(update-in %1 [(translate %2)] action) lights positions))


(defn parse [lights line method]
  (let [[_ action x y to_x to_y] (re-find #"([\w\s]+)\s+(\d+),(\d+)\s+\w+\s+(\d+),(\d+)" line)
        coordinates (apply select-square (map read-string [x y to_x to_y]))]
    (->> lights
         (perform-action (method action) coordinates))))

(defn day6 []
  (count
   (filter #(pos? %)
           (reduce #(parse % %2 fn-action) lights (input)))))


(defn day6-2 []
  (reduce +
          (filter #(pos? %)
                  (reduce #(parse % %2 brightness) lights (input)))))
