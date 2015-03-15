(ns clojure-problems.26)

(defn sep-num-and-denom [xs points]
  {:integer (drop-last points xs)
   :decimal (take-last points xs)})

(defn get-decimal [a b]
  (loop [a a
         b b
         nums-after-dec 0
         nums []
         seen []]
    (cond (some #{a} seen) (assoc (sep-num-and-denom nums nums-after-dec)
                                  :repeats-at (.indexOf seen a)
                                  :d b
                                  )
          (< (/ a b) 1) (recur (* a 10) b (inc nums-after-dec) nums seen)
          (zero? (mod a b)) (assoc (sep-num-and-denom (conj nums (quot a b)) nums-after-dec)
                                   :repeats-at nil
                                   :d b)
          :else (recur (mod a b) 
                       b 
                       nums-after-dec 
                       (conj nums (quot a b)) 
                       (conj seen a)))))

#_(prn (last 
      (sort-by 
       #(- (count (:decimal %)) (:repeats-at %))
                    (filter :repeats-at 
                            (map 
                             (partial get-decimal 1) 
                             (range 1 1000))))))
