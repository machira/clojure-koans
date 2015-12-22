(ns test.koans.hobbits
  (:use [clojure.test])
  (:require koans.hobbits)
  (:refer koans.hobbits)
  )


;;
;; Tests for all hobbits!
;;

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(def sym-body-parts [{:name "head", :size 3}
                     {:name "left-eye", :size 1}
                     {:name "right-eye", :size 1}
                     {:name "left-ear", :size 1}
                     {:name "right-ear", :size 1}
                     {:name "mouth", :size 1}
                     {:name "nose", :size 1}
                     {:name "neck", :size 2}
                     {:name "left-shoulder", :size 3}
                     {:name "right-shoulder", :size 3}
                     {:name "right-upper-arm", :size 3}
                     {:name "left-upper-arm", :size 3}
                     {:name "chest", :size 10}
                     {:name "back", :size 10}
                     {:name "left-forearm", :size 3}
                     {:name "right-forearm", :size 3}
                     {:name "abdomen", :size 6}
                     {:name "left-kidney", :size 1}
                     {:name "right-kidney", :size 1}
                     {:name "left-hand", :size 2}
                     {:name "right-hand", :size 2}
                     {:name "right-knee", :size 2}
                     {:name "left-knee", :size 2}
                     {:name "right-thigh", :size 4}
                     {:name "left-thigh", :size 4}
                     {:name "right-lower-leg", :size 3}
                     {:name "left-lower-leg", :size 3}
                     {:name "right-achilles", :size 1}
                     {:name "left-achilles", :size 1}
                     {:name "right-foot", :size 2}
                     {:name "left-foot", :size 2}])


(deftest test-matching-part
  "should return symmetrically opposite part"
  (is ( = {:name "right-knee" :size 2} (matching-part {:name "left-knee" :size 2})))

  "should return the same result for a part that isn't symmetrical"
  (is ( = {:name "nose" :size 2} (matching-part {:name "nose" :size 2})))
  (is ( = {:name "cleft-lip" :size 2} (matching-part {:name "cleft-lip" :size 2}))))


(deftest test-unique-parts-only
  "should return only unique body parts after execution"
  (is ( = #{{:name "right-knee" :size 2} {:name "left-knee" :size 2}}  (unique-parts-only {:name "left-knee" :size 2})))
  (is ( = #{{:name "right-eye" :size 14} {:name "left-eye" :size 14}}  (unique-parts-only {:name "left-eye" :size 14})))
  (is ( = #{ {:name "nose" :size 4}}  (unique-parts-only {:name "nose" :size 4}))))

(deftest test-symmetrize-by-reduce
  "should symmetrize whole body"
  (is (= [{:name "right-lower-leg", :size 3} {:name "left-lower-leg", :size 3}] (symmetrize-by-reduce [{:name "left-lower-leg", :size 3}])))
  (is (= [{:name "nose", :size 7}] (symmetrize-by-reduce [{:name "nose", :size 7}])))
  (is (= sym-body-parts (symmetrize-by-reduce asym-hobbit-body-parts)))
  )

(deftest test-hobbit-weight
  "should return total hobbit weight"
  (is (= 1 (total-hobbit-size [{:name "head", :size 1}])))
  (is (= 4 (total-hobbit-size [{:name "bauch", :size 4}])))
  (is (= 2 (total-hobbit-size [{:name "head", :size 1} {:name "nose", :size 1}]))))
  (is (= 10 (total-hobbit-size [{:name "chin", :size 1}{:name "left-eye", :size 1}{:name "nose", :size 3}{:name "bauch", :size 4}])))

(deftest test-find-part
  "should return the part of a hobbit that matches a given cummulative weight"
  (is (= {:name "left-eye" :size 3} (find-part 5 [{:name "right-eye" :size 3} {:name "left-eye" :size 3}])))

  "should return first part for a negative, or 0"
  (is (= {:name "left-eye" :size 3} (find-part 0 [{:name "left-eye" :size 3}])))
  (is (= {:name "left-eye" :size 3} (find-part -1 [{:name "left-eye" :size 3}])))

  "should return empty map for a weight that exceeds weight of hobbit"
  (is (= {} (find-part 4 [{:name "blah" :size 3}])))
  )

(deftest test-sort-body-parts-by-size
  "should return a sorted list"
  (is (= [{:name "head", :size 3}{:name "left-eye", :size 2}{:name "left-ear", :size 1}
          {:name "mouth", :size -1}{:name "nose", :size -3}] (sort-body-parts-by-size [{:name "head" :size 3} {:name "left-eye" :size 2}
                                                                                      {:name "left-ear" :size 1} {:name "mouth" :size -1}
                                                                                      {:name "nose" :size -3}])))
  )

(deftest test-hit-a-hobbit
  "should returns parts of passed in hobbit"
  (is true? (some #(= "head" (:name %)) (hit-a-hobbit [{:name "head" :size 3} {:name "head" :size 2}]))))

(defmethod report :begin-test-ns [m]
  (with-test-out
    (if (some #(:test (meta %)) (vals (ns-interns (:ns m))))
      (println "\nTesting" (ns-name (:ns m))))))

(run-all-tests)