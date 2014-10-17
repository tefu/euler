(ns clojure-problems.6)

(defn sum-of-squares [seq]
  (apply + (map #(Math/pow % 2) seq)))

(defn square-of-sum [seq]
  (Math/pow (apply + seq) 2))

(int (let [seq (range 1 101)]
    (- (square-of-sum seq) (sum-of-squares seq))))
