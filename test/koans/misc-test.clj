(ns test.koans.misc
  (:use [clojure.test])
  (:require koans.misc)
  (:refer koans.misc)
  )

(deftest test-add100
  "should always add 100 to argument"
  (is (= 100 (add100 0)))
  (is (= 0 (add100 -100)))
  (is (= 104 (add100 4))))


(deftest test-decmaker
  "should return a function that subtracts expected result"
  (is (= 10 ((decmaker 0) 10)))
  (is (= -10 ((decmaker 10) 0)))
  (is (= 1 ((decmaker 9) 10)))
  (is (= -15 ((decmaker 5) -10)))
  (is (= -5 ((decmaker -5) -10)))
  )

(deftest test-mapset
  "should return a set of func'd things"
  (is (= #{2 3} (mapset inc [1 1 2 2])))
  (is (= #{"HELLO WORLD" "DERP"} (mapset #(.toUpperCase %) ["hello world" "derp"])))
  )

(deftest test-toInfix
  "should return an expected list"
  ;(is (= '(+ 1 2) (toInfix ('(1 + 2)))))
  ;(is (= '(+ 1 2) "derp"))
  ;(is (= '(* 3 2) (toInfix ('(3 * 2)))))
  ;(is (= '(+ 1 (+ 2 (* 3 4))) (toInfix ('(1 + 2 + 3 * 4)))))

  )


(defmethod report :begin-test-ns [m]
  (with-test-out
    (if (some #(:test (meta %)) (vals (ns-interns (:ns m))))
      (println "\nTesting" (ns-name (:ns m))))))

(run-all-tests)