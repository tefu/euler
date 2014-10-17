(ns clojure-problems.5)

(defn factor-of? [a b]
    (zero? (mod b a)))

(defn smallest-multiple
  ([coll]
     (if (< 1 (count coll))
       (let [mults (take 2 coll)
             coll (drop 2 coll)]
         (smallest-multiple
          (conj coll (apply smallest-multiple mults))))
       coll))
  ([a b]
     (let [filters [#(factor-of? a %) #(factor-of? b %)]
           multiple? (apply every-pred filters)
           large-multiple (* a b)]
     (loop [i b]
      (if (multiple? i)
        i
        (if (> i large-multiple)
          large-multiple
          (recur (+ i b))))))))


(smallest-multiple (range 1 20))
