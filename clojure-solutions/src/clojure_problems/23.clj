(ns clojure-problems.23
  (:use [clojure-problems.21]
        [clojure.set]))

(def known-limit 28123)

(def abundant-nums (filter #(< % (sum-divisors %))
                                 (range 1 (inc known-limit))))

(def abundant-sums (set (filter #(< % known-limit) 
                                (for [x abundant-nums
                                      y abundant-nums]
                                  (+ x y)))))

(def cant-sum-them-nums
  (difference (set (range 1 known-limit)) abundant-sums))

; (prn (time (apply + cant-sum-them-nums)))

