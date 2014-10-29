(ns clojure-problems.15)

;; Lattice paths
(defn naive-lattice [n]
  (letfn [(tree-probe [x y]
                     (cond
                         (= x n) 1
                         (= y n) 1
                         (= x y) (* 2 (tree-probe (inc x) y))
                         :else (+ (tree-probe (inc x) y) (tree-probe x (inc y)))))]
    (tree-probe 0 0)))

(comment
  (map naive-lattice (range 15)) ;; => (1 2 6 20 70 252 924 3432 ...)
)

(defn fact [x] (reduce * (range 1 (inc x))))

(defn cbc [n]
  "Calculate the central binomial coefficient of n"
  (let [numer (fact (* 2 n))
        denom (fact n)]
    (/ numer (* denom denom)))
  )


;; Kept getting integer overflow so I gave up and put the equation into my calculator lol
