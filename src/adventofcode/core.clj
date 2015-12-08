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
   ))

(defn -main
  "Advent of code"
  [& args]
  (println "Day 1" (day1))
  (println "Day 1 - part2" (day1-2))

  (println "Day 2" (day2))
  (println "Day 2 - part2" (day2-2))


  (println "Day 3" (day3))
  (println "Day 3 - part3" (day3-2))

  (println "Day 4" (day4))
  (println "Day 4 - part4" (day4-2))

  (println "Day 5" (day5))
  (println "Day 5 - part5" (day5-2))

  (println "Day 6" (day6))
  (println "Day 6 - part6" (day6-2))

  (println "Day 7" (day7))
  (println "Day 7 - part7" (day7-2))

  (println "Day 8" (day8))
  (println "Day 8 - part8" (day8-2))

  )
