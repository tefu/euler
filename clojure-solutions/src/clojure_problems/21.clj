(ns clojure-problems.21
  (:use [clojure.set]
        [clojure-problems.3]))

(defn powerset [coll]
  (if (empty? coll) #{'()}
      (let [half-way-there (powerset (rest coll))]
        (union half-way-there
               (map #(conj % (first coll)) half-way-there)))))

(defn find-prime-factors [x]
  (let [prime-set (filter #(factor-of? % x) (sieve x))
        prime? (fn [num] (contains? (set prime-set) num))
        aux (fn aux [factor]
              (let [divisors (filter #(factor-of? % factor) prime-set)]
                (if (empty? divisors)
                  []
                  (conj (aux (/ factor (first divisors))) 
                        (first divisors)))))]
    (aux x)))

(defn sum-divisors [x]
  (-  (apply + (map #(apply * %) (powerset (find-prime-factors x)))) x))

(defn amicable? [a]
  (let [b (sum-divisors a)]
    (and (not (= a b))
         (= a (sum-divisors b)))))

; (apply + (filter amicable? (range 2 10001)))
