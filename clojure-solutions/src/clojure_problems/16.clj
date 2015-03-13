(ns clojure-problems.16)

(defn exp [x n]
  (reduce * (repeat n x)))

(defn get-digits [x]
  (map #(Character/digit % 10) (str x)))

(comment (apply + (get-digits (exp (bigint 2) (bigint 1000)))))
