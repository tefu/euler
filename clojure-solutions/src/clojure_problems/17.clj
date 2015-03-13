(ns clojure-problems.17)

(def first-nums [""
                 "one"
                 "two"
                 "three"
                 "four"
                 "five"
                 "six"
                 "seven"
                 "eight"
                 "nine"
                 "ten"
                 "eleven"
                 "twelve"
                 "thirteen"
                 "fourteen"
                 "fifteen"
                 "sixteen"
                 "seventeen"
                 "eighteen"
                 "nineteen"])

(def second-nums [""
                  "ten"
                  "twenty"
                  "thirty"
                  "forty"
                  "fifty"
                  "sixty"
                  "seventy"
                  "eighty"
                  "ninety"])


(defn get-digits [x]
  (map #(Character/digit % 10) (str x)))

(defn concat-digits [coll]
  (Integer/parseInt (clojure.string/join coll)))

(defn num-to-string [n]
  (let [digits (get-digits n)]
    (cond
      (< n 20)  (first-nums n)
      (< n 100) (str
                 (second-nums (first digits))
                 (num-to-string (concat-digits (rest digits))))
      (< n 1000) (str
                  (num-to-string (first digits)) "hundred"
                  (let [the-rest
                        (num-to-string (concat-digits (rest digits)))]
                    (if (= the-rest "") "" (str "and" the-rest))))
      :else "onethousand")))

(prn (count (mapcat num-to-string (range 1 1001))))
