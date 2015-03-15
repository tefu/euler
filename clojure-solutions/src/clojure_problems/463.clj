(ns clojure-problems.463
  (:use [incanter.core]
        [incanter.charts])
  (:require [clojure.core.reducers :as r]))

(def weird-recurse 
  (memoize
   (fn [x]
     (cond
       (or (= x 1) (= x 3)) x
       (even? x) (weird-recurse (/ x 2))
       (zero? (mod (- x 1) 4)) (let [n (/ (- x 1) 4)] 
                                 (- (* 2 (weird-recurse (inc (* 2 n))))
                                    (weird-recurse n)))
       :else (let [n (/ (- x 3) 4)]
               (- (* 3 (weird-recurse (inc (* 2 n)))) (* 2 (weird-recurse n))))))))

(defn n-weird-calls [n]
  (map weird-recurse (range 1 (inc n))))

(defn expo [x n]
  (reduce * (repeat n x)))

(defn weird-sum [n]
  (apply + (n-weird-calls n)))

;; I can't get any larger than this ;_;
;; (time (weird-sum (expo (bigint 3) 10)))

;; Guess I should plot the data...
(def x (range 1 10001))
(def y (n-weird-calls 10000))

;; Oh my-
(view (xy-plot x y))
;; http://i.imgur.com/axzL4Zw.png 

(defn take-nth-from [step start coll]
  (take-nth step (drop start coll)))

(defn plot-only [step start x y]
  (xy-plot (take-nth-from step start x)
           (take-nth-from step start y)
           :title (str "Step: " step " From: " start)))

;; ま、まさかー！？
(view (plot-only 32 0 x y))
(view (plot-only 32 1 x y))

(defn weird-mean [start end]
  (mean (map weird-recurse (range start end))))

(defn weird-seq [start end until]
  (loop [start start
         end end
         coll []
         count 0]
    (cond (> count until) coll
          (<= end start) coll
          :else (recur start 
                       (/ (+ start end) 2)
                       (conj coll (weird-mean start end))
                       (inc count)))))

(def test-area (weird-seq 1024 2048 10))
(def test-area2 (weird-seq 1536 2048 9))
(def test-area3 (weird-seq 1024 1536 9))
(def test-area4 (weird-seq 1536 1792 8))

(defn intervals [coll]
  (map - (butlast coll) (rest coll)))

(intervals test-area)
(intervals test-area2)
(intervals test-area3)
(intervals test-area4)

;; S(2^(k+1)) = S(2^k) + 2^(2k)
(defn weird-sum-power-twos [k]
  (loop [n k
         sum (bigint 0)]
    (if (zero? n)
      (+ sum (weird-sum 1))
      (recur (dec n) (+ sum (expo (bigint 2) (* 2 (dec n))))))))

(defn power-of-two-until [x]
  (count
   (take-while 
    #(not (= 1 %)) 
    (iterate #(quot % 2) x))))

(defn weird-sum-interval [start until]
  (letfn [(aux [start end mean depth]
            (cond (= end until) (* mean (- end start))
                  (> end until) (aux start (/ (+ start end) 2) 
                                     (- mean depth) (* depth 2))
                  :else (+ (* mean (- end start))
                           (aux end
                                (+ end (- end start))
                                (+ mean depth)
                                depth))))]
    (aux start (* 2 start) start 1)))

(defn the-true-sum [n]
  (let [k (power-of-two-until n)
        most-of-it (weird-sum-power-twos k)
        rest-of-it (weird-sum-interval (expo (bigint 2) k) (inc n))]
    (+ most-of-it rest-of-it (- 1))))

(prn (the-true-sum (expo 3 37)))
