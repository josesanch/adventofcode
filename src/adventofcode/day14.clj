(ns adventofcode.day14)

(def input (slurp "resources/day14.txt"))

(defn update-data [data [_ ren _speed _time _rest_time]]
  (let [[speed time rest_time] (map read-string [_speed _time _rest_time])]
    (update-in data [ren] merge {:speed speed :time time :rest-time rest_time})))

(defn parse-input [input]
  (reduce update-data {}
          (->> input
               clojure.string/split-lines
               (map #(re-find #"(\w+).*?(\d+)[^\d]+(\d+)[^\d]+(\d+)" %)))))


(defn calc-interval [time distance]
  (* time distance))

(defn calc-distance [data time]
  (let [interval (+ (data :time) (data :rest-time))]
    (+
     (calc-interval (Math/floor (/ time interval)) (* (data :speed)
                                                      (data :time)))
     (* (min (data :time)
             (rem time interval))
        (data :speed)))
    ))

;; (* (data :speed)
;;    (data :time)
;;    (Math/ceil (/ time interval)))))



(defn calc-distances-at-time [data time]
  (sort-by
   second >
   (map
    (fn [r]
      [r (calc-distance (data r) time)])
    (keys data))))


(defn day14 []
  (let [data (parse-input input)
        time 2503]
    (calc-distances-at-time data time)))


(defn filter-max [distances]
  (let [max-distance (second (first distances))]
    (filter #(= (second %) max-distance) distances)))


(defn day14-2 []
  (let [data (parse-input input)]

    (sort-by
     val >
     (frequencies
      (flatten
       (map
        #(map first (filter-max (calc-distances-at-time data %)))
        (range 1 2504)))))))
