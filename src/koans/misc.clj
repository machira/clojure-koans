(ns koans.misc)

(defn add100
  "a function that takes a number and adds 100 to it"
  [x]
  (+ 100 x))

(defn decmaker
  "returns a function that takes one argument and deducts y from it"
  [y]
  (partial - y))

(defn mapset
  "returns a set of the result of mapping func over the list"
  [fnc lst]
  (reduce #(into %1 (fnc %2)) #{} lst))