(ns clojure-problems.25
  (:use [clojure-problems.2]))

#_(prn (+ 2 (count (take-while
                  #(< (count (str %)) 1000)
                  (fib (bigint 1) 2)))))
