(ns clojure-problems.2)

(defn fib [a b]
  (let [fib-iter (fn [[a b]] [b (+ a b)])]
    (map first (iterate fib-iter [a b]))))

#_(apply + (filter #(even? %) (take-while
                             #(<= % 4000000)
                             (fib 1 2))))
