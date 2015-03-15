(ns clojure-problems.3)

(defn factor-of? [a b]
    (zero? (mod b a)))

(def primes "Generates an infinite, lazy sequence of prime numbers"
  (let [reinsert (fn [table x prime]
                   (update-in table [(+ prime x)] conj prime))]
    (letfn [(primes-step [table d]
              (if-let [factors (get table d)]
                (recur (reduce #(reinsert %1 d %2) (dissoc table d) factors)
                       (inc d))
                (lazy-seq (cons d (primes-step (assoc table (* d d) (list d))
                                               (inc d))))))]
      (primes-step {} 2))))

(defn sieve [n]
  "Returns all the primes less than or equal to a number n"
  (take-while #(<= % n) primes))

(defn lpf
  "Find largest prime factor of a number"
  [n]
  (let [possible-primes (sieve (int (Math/sqrt n)))
        factors (filter #(factor-of? % n) possible-primes)]
    (last factors)))


;; (lpf 600851475143)

