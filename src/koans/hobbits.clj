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

;(def hit
;  "determines which part of a hobbit has been hit"
;  [hobbit]
;  (reduce #(+ %1 (:size %2)) 0 (symmetrize-by-reduce hobbit)))