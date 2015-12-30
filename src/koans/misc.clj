(ns koans.misc)

(defn add100
  "a function that takes a number and adds 100 to it"
  [x]
  (+ 100 x))

(defn decmaker
  "returns a function that takes one argument and deducts y from it"
  [y]
  (fn [x] (- x y))
  )

(defn mapset
  "returns a set of the result of mapping func over the list"
  [fnc lst]
  ((comp set #(map fnc %)) lst))

(defn toInfix
  "Takes a list like (1 + 3 * 4 - 5) and transforms it into lists that Clojure needs"
  [[f s & l]]
  (list s f (if (= 1 (count l)) (first l) (toInfix l))))