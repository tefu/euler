(ns clojure-problems.19)

(defn leap-year? [year]
  (or (and (= (mod year 4) 0)
           (not (= (mod year 100) 0)))
      (= (mod year 400) 0)))

(defn month-days [year] [31
                         (if (leap-year? year) 29 28)
                         31
                         30
                         31
                         30
                         31
                         31
                         30
                         31
                         30
                         31])


(defn inc-week [[day month year]]
  (let [days-in-month (nth (month-days year) month)
        month-rolled? (> (+ 7 day) days-in-month)
        year-rolled? (and month-rolled? (> (inc month) 11))]
    [(+ day 7 (if month-rolled? (- days-in-month) 0))
     (if month-rolled? 
       (mod (inc month) 12)
       month)
     (if year-rolled? (inc year) year)]))

(defn date< [[day1 month1 year1] [day2 month2 year2]]
  (or (< year1 year2)
      (and (= year1 year2) (< month1 month2))
      (and (= year1 year2) (= month1 month2) (< day1 day2))))

(defn num-on-first-of-month [start end]
  (letfn [(aux [start end count]
            (if-not (date< start end)
              count
              (if (= (first start) 1)
                (recur (inc-week start) end (inc count))
                (recur (inc-week start) end count))))]
    (aux start end 0)))

(num-on-first-of-month [1 0 1901] [31 11 2000])
