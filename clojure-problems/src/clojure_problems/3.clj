(ns clojure-problems.3)

(defn factor-of? [a b]
    (zero? (mod b a)))

(defn sieve [n]
  "Returns all the primes less than a number n"
  (let [limit (floor (Math/sqrt n))]
    (loop [primes []
           i 2
           coll (range 2 n)]
      (if (> i limit)
        (concat primes coll)
        (let [sieved-coll (filter #(not (factor-of? i %)) coll)]
          (recur (conj primes i) (first sieved-coll) sieved-coll))))))

(defn lpf
  "Find largest prime factor of a number"
  [n]
  (let [possible-primes (sieve (int (Math/sqrt n)))
        factors (filter #(factor-of? % n) possible-primes)]
    (last factors)))


(lpf 600851475143)
