(ns adventofcode.core
  (:gen-class)
  (:require
   [adventofcode.day1 :refer [day1 day1-2]]
   [adventofcode.day2 :refer [day2 day2-2]]
   [adventofcode.day3 :refer [day3 day3-2]]
   [adventofcode.day4 :refer [day4 day4-2]]
   [adventofcode.day5 :refer [day5 day5-2]]
   [adventofcode.day6 :refer [day6 day6-2]]
   [adventofcode.day7 :refer [day7 day7-2]]
   [adventofcode.day8 :refer [day8 day8-2]]
   [adventofcode.day9 :refer [day9 day9-2]]
   [adventofcode.day10 :refer [day10 day10-2]]
   [adventofcode.day11 :refer [day11 day11-2]]
   [adventofcode.day12 :refer [day12 day12-2]]
   [adventofcode.day13 :refer [day13 day13-2]]
   [adventofcode.day14 :refer [day14 day14-2]]
   [adventofcode.day15 :refer [day15 day15-2]]
   [adventofcode.day16 :refer [day16 day16-2]]
   ))

(defn -main
  "Advent of code"
  [& args]
  (doseq [day (if-not (nil? args) args (range 1 14))
          part ["" "-2"]]

    (let [prog (str "adventofcode.day" day "/day" day part)]
      (println "\nExecuting " prog)
      (time (println ((eval (read-string prog))))))))
