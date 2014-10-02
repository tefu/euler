(ns clojure-problems.4)


(defn is-palindrome?
  "Returns true if x is a palindrome"
  [x]
  (let [x (str x)]
    (= (clojure.string/reverse x) x)))

(last (sort (filter is-palindrome?
                    (for [x (range 100 1000)
                          y (range 100 1000)]
                      (* x y)))))
