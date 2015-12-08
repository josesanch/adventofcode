(ns advendofcode.day4)

(import 'java.security.MessageDigest
        'java.math.BigInteger
        )

(defn md5 [s]
  (let [algorithm (MessageDigest/getInstance "MD5")
        size 32;; (* 2 (.getDigestLength algorithm))
        raw (.digest algorithm (.getBytes s))
        sig (.toString (BigInteger. 1 raw) 16)
        padding (apply str (repeat (- size (count sig)) "0"))]
    (str padding sig)))

(def secret-key "bgvyzdsv")


(defn zeros [number string]
  (every? #(= % \0) (take number string)))

(defn day4 [check]
  (loop [num 0]
    (let [hexdigest (md5 (str secret-key num))]
      (if (check hexdigest)
        num
        (recur (+ 1 num))))))
