(ns clojure-problems.12)

(def triangle-nums 
  (map first (iterate
              (fn [[a n]] [(+ a (inc n)) (inc n)]) [1 1])))

(defn factor-of? [a b]
    (zero? (mod b a)))

;; Doesn't account for square numbers.
(defn lower-factors [x]
  (filter #(factor-of? % x) (range 1 (Math/sqrt (inc x)))))

(prn (first (filter
             #(> (count (lower-factors %)) 250)
             triangle-nums)))
