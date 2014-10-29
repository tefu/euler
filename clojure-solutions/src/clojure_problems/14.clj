(ns clojure-problems.14)

(defn collatz [x]
  (take-while
   #(not= 1 %)
   (iterate (fn [n] 
              (if (even? n)
                (/ n 2)
                (inc (* 3 n)))) x)))

(def f (comp count collatz))

;; This takes like 5 minutes ;_;
(prn (reduce (fn [a b] 
               (if (< (f a) (f b))
                 b
                 a)) (range 1 1000000)))
