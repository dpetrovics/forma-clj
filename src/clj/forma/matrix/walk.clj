(ns forma.matrix.walk
  "Functions that move along a 2D array, collecting features of the
moving window."
  (:use cascalog.api
        [forma.utils :only (nth-in)]
        [forma.matrix.utils :only (insert-into-val
                                   insert-at)]))

(defn walk-matrix
  "Returns a sequence of matrices, after walking along the rows and
  columns of the given matrix a the specified window size and
  returning all window-by-window snapshots.

  Example usage:
    (def mat [[0 1 2] [3 4 5]])
    (walk-matrix mat 2) => [[[0 1]
                             [3 4]]
                            [[1 2]
                             [4 5]]]"
  [m window]
  (mapcat (comp (partial apply map vector)
                (partial map (partial partition window 1)))
          (partition window 1 m)))

(defn buffer-matrix
  "For the supplied matrix `mat`, returns a new matrix generated by
  padding the beginning and end of each row and column with the
  supplied value, repeated `buffer-size` times.

  Example usage:
    (def test-matrix [[0 1 2]
                      [3 4 5]])
    (buffer-matrix 2 1 test-matrix) => [[1 1 1 1 1 1 1]
                                        [1 1 1 1 1 1 1]
                                        [1 1 0 1 2 1 1]
                                        [1 1 3 4 5 1 1]
                                        [1 1 1 1 1 1 1]
                                        [1 1 1 1 1 1 1]]"
  [buffer-size val [row :as mat]]
  (let [n (-> buffer-size (* 2) (+ (count row)))
        buf-row (repeat n val)]
    (insert-at buffer-size
               (for [row mat]
                 (insert-into-val val buffer-size n row))
               (repeat (* 2 buffer-size) buf-row))))

(defn wipe-out
  "Accepts a nested collection and a sequence of keys, and returns a new
  collection generated by removing the value located at the position
  indicated by the supplied key sequence.

  Example usage:
    (def test-matrix [[0 1 2] [3 4 5]])
    (wipe-out test-matrix [1 2]) => [[0 1 2] [3 4]]"
  [coll [k & ks]]
  {:pre [(or (nil? k) (coll? coll))]}
  (if-not k
    coll
    (concat (take k coll)
            (when ks [(wipe-out (nth coll k) ks)])
            (drop (inc k) coll))))

(defn neighbor-scan
  "Iterates across the supplied two dimensional matrix, returning a
  lazy sequence of 2-element vectors of the form `[matrix entry,
  neighbor-seq]`. Neighbors outside the matrix are reported as
  nil. 

  Example usage:
    (def test-matrix [[:0 :1 :2 :3]
                      [:4 :5 :6 :7]
                      [:8 :9 :a :b]
                      [:c :d :e :f]])

    (first (neighbor-scan 1 test-matrix))
       => [:0 ((nil nil nil) (nil :1) (nil :4 :5))]"
  [num-neighbors mat]
  {:pre [(> (count mat)
            (inc (* 2 num-neighbors)))]}
  (let [window (inc (* 2 num-neighbors))
        new-mat (buffer-matrix num-neighbors nil mat)]
    (for [sub-win (walk-matrix new-mat window)]
      ((juxt nth-in wipe-out) sub-win [num-neighbors num-neighbors]))))

(defn windowed-function
  "Returns a sequence of the results of calling the supplied function
  `f` on each sub-matrix of matrix `mat`. Each submatrix is a series
  of `(inc (* 2 num-neighbors))` nested vectors; each element sits at
  the middle of a single submatrix, and is suqrrounded by its
  neighbors (or `nil` if it sits on an edge.)"
  [num-neighbors f mat]
  {:pre [(> (count mat)
            (inc (* 2 num-neighbors)))]}
  (let [window  (+ 1 (* 2 num-neighbors))
        new-mat (buffer-matrix num-neighbors nil mat)]
    (for [sub-win (walk-matrix new-mat window)]
      (->> sub-win
           (apply concat)
           (filter (complement nil?))
           (apply f)))))
