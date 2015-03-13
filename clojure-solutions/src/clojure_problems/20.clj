(ns clojure-problems.20
  (:use [clojure-problems.16]))

(defn fact [x]
  (loop [n x f 1]
    (if (= n 1)
      f
      (recur (dec n) (* f n)))))

(apply + (get-digits (fact (bigint 100))))
