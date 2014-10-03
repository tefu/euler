(ns clojure-problems.7)

(defn factor-of? [a b]
    (zero? (mod b a)))

(defn sieve [n]
  "Returns all the primes less than a number n"
  (let [limit (Math/floor (Math/sqrt n))]
    (loop [primes []
           i 2
           coll (range 2 n)]
      (if (> i limit)
        (concat primes coll)
        (let [sieved-coll (filter #(not (factor-of? i %)) coll)]
          (recur (conj primes i) (first sieved-coll) sieved-coll))))))

(nth (sieve 10000000) 10000)
