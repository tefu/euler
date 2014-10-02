(ns clojure-problems.1)

(apply +
       (filter #(or
                 (zero? (mod % 3))
                 (zero? (mod % 5))) (range 1000)))
