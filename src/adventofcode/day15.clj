(ns adventofcode.day15
  (:require [clojure.math.combinatorics :as combo]))



(def ingredients
  {:sugar {:capacity 3, :durability 0, :flavor 0, :texture -3, :calories 2}
   :sprinkles  {:capacity -3, :durability 3, :flavor 0, :texture 0, :calories 9}
   :candy  {:capacity -1, :durability 0, :flavor 4, :texture 0, :calories 1}
   :chocolate  {:capacity 0, :durability 0, :flavor -2, :texture 2, :calories 8}
   })

(defn combinations [size groups]
  (let [num_groups (count groups)
        mask (repeat num_groups 0)]
    (map
     #(zipmap groups %)
     (mapcat
      combo/permutations
      (map
       #(take num_groups (apply conj mask (map count %)))
       (combo/partitions
        (repeat size "*") :max num_groups))))))


(defn calc-quality-by-amount [properties spoons]
  (reduce
   (fn [prev [p v]] (assoc prev  p (* v spoons)))
   {}
   properties))

(defn sum-properties [ingredients mix]
  (reduce
   (fn [prev [ingredient spoons]]
     (merge-with + prev (calc-quality-by-amount (ingredients ingredient) spoons)))
   {}
   mix))


(defn multiply [values]
  (reduce #(* % %2)
          (map
           #(if (pos? %) % 0)
           values)))

(defn quality [ingredients mix]
  (multiply
   (take 4 (vals (sum-properties ingredients mix)))))


(defn get-best-mix [fn-quality]
  (first
   (sort-by
    second >
    (map
     (fn [mix] [mix (fn-quality ingredients mix)])
     (combinations 100 (keys ingredients))))))

(defn day15 []
  (get-best-mix quality))


(defn quality-with-500-calc [ingredients mix]
  (let [properties (sum-properties ingredients mix)
        calories (properties :calories)]
    (if (= calories 500)
      (multiply (take 4 (vals properties)))
      0)))


(defn day15-2 []
  (get-best-mix quality-with-500-calc))
