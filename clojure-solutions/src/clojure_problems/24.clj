(ns clojure-problems.23
  (:require [clojure.math.combinatorics :as combo]))

(prn (nth (combo/permutations (range 0 10)) 999999))
