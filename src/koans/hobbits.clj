(ns koans.hobbits)


;; This is an example from http://www.braveclojure.com/do-things/#The_Shire_s_Next_Top_Model
;;
(defn matching-part
  [part]
  (let [{:keys [name size]} part]
  {:name (clojure.string/replace name #"^left-" "right-")
   :size size}))

(defn unique-parts-only
  [part]
  (set [part (matching-part part)]))

(defn symmetrize-by-reduce
  "Returns a symmetric hobbit using reduce operation"
  [hobbit-body]
  (reduce (fn [result part] (into result (unique-parts-only part))) [] hobbit-body))

(defn total-hobbit-size
  "determines total hobbit size"
  [hobbit]
  (reduce #(+ %1 (:size %2)) 0 (symmetrize-by-reduce hobbit)))

(defn find-part
  "returns the part of the hobbits body that corresponds to a given number, based on total size"
  [cummulative_target, symmetric_hobbit]
  (loop [cumlative_size 0 [part1 & other_parts] symmetric_hobbit]
    (if (nil? part1) {}
      (if (>= (+ cumlative_size (:size part1)) cummulative_target)
      part1
      (recur (+ cumlative_size (:size part1)) other_parts)))))

(defn sort-body-parts-by-size
  "returns a list of body parts sorted descending by size"
  [body-parts]
  (sort-by :size #(compare %2 %1) body-parts))

(defn hit-a-hobbit
  "hits a hobbit in a random place"
  [hobbit]
  (:name (find-part (rand-int (total-hobbit-size hobbit)) (sort-body-parts-by-size (symmetrize-by-reduce hobbit)))))
