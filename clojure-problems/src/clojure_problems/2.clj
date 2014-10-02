(ns clojure-problems.2)

(defn fibo [a b]
  (let [fib-iter (fn [[a b]] [b (+ a b)])]
    (map first (iterate fib-iter [a b]))))

(apply + (filter #(even? %) (take-while
                             #(< % 4000000)
                             (fibo 0 1))))
