(ns clojure-problems.9)

(defn square [x] (* x x))
(defn triplet? [[a b c]]
  (= (+ (square a) (square b)) (square c)))

(def trips
  (filter triplet?
                (for [
                      x (range 1 1001)
                      y (range 1 1001)
                      z (range 1 1001)
                      ]
                  [x y z])))

(apply apply * (filter #(= 1000 (apply + %) ) trips))
