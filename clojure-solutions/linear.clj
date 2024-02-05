(defn op [f]
  (fn [& args]
    (if
      (every? number? args)
      (apply f args)
      (apply mapv (op f) args))))

(def v+ (op +))
(def v- (op -))
(def v* (op *))
(def vd (op /))

(defn scalar [v1, v2] (reduce + (v* v1, v2)))
(defn vect [v1, v2]
  [(- (* (nth v1 1) (nth v2 2)) (* (nth v1 2) (nth v2 1))),
   (- (* (nth v1 2) (nth v2 0)) (* (nth v1 0) (nth v2 2))),
   (- (* (nth v1 0) (nth v2 1)) (* (nth v1 1) (nth v2 0)))])
(defn v*s [v, s] (mapv * v (iterate partial s)))

(def m+ (op v+))
(def m- (op v-))
(def m* (op v*))
(def md (op vd))

(defn transpose [m] (apply mapv vector m))
(defn m*s [m, s] (mapv v*s m (iterate partial s)))
(defn m*v [m, v] (mapv scalar m (iterate partial v)))
(defn m*m [m1, m2] (mapv (partial m*v (transpose m2)) m1))

(def s+ (op +))
(def s- (op -))
(def s* (op *))
(def sd (op /))