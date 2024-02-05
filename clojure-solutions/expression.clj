;;;;;;;;;;;
;; HW 10 ;;
;;;;;;;;;;;
(defn constant [value] (fn [_] (identity value)))
(defn variable [name] (fn [map] (get map name)))
(defn op [f] (fn [& args] (fn [map] (apply f (mapv #(% map) args)))))

(def add (op +))
(def subtract (op -))
(def multiply (op *))

(defn div [& args] (/ (double (first args)) (double (apply * (rest args)))))

(def divide (op div))
(def negate (op -))
(def exp (op #(Math/exp %)))
(def ln (op #(Math/log %)))
(def arcTan (op #(Math/atan %)))
(def arcTan2 (op #(Math/atan2 %1 %2)))
(def operationsHW10
  {'+      add
   '-      subtract
   '*      multiply
   '/      divide
   'negate negate
   'exp    exp
   'ln     ln
   'atan   arcTan
   'atan2  arcTan2})

(defn parse [expression map v c]
  (letfn
    [(parseExpression [expr]
                      (cond (list? expr) (apply (map (first expr)) (mapv parseExpression (rest expr)))
                        (symbol? expr)   (v (str expr))
                        :else            (c expr)))]
    (parseExpression (read-string expression))))
(defn parseFunction [expression]
  (parse expression operationsHW10 variable constant))

;;;;;;;;;;;
;; HW 11 ;;
;;;;;;;;;;;
(definterface Expression
  (eval [args])
  (toStr []))

(deftype ConstantType [value]
  Expression
  (eval [_ _] (identity value))
  (toStr [_] (str value)))

(defn Constant [value] (ConstantType. value))

(deftype VariableType [name]
  Expression
  (eval [_ map] (get map name))
  (toStr [_] (str name)))

(defn Variable [name] (VariableType. name))

(deftype Operation [op f args]
  Expression
  (eval [_ map] (apply f (mapv #(.eval % map) args)))
  (toStr [_]
    (str "(" op " " (clojure.string/join " " (map #(.toStr %) args)) ")")))

(defn Add [& args] (Operation. "+" + args))
(defn Subtract [& args] (Operation. "-" - args))
(defn Divide [& args] (Operation. "/" div args))
(defn Multiply [& args] (Operation. "*" * args))
(defn Negate [& args] (Operation. "negate" - args))
(defn Sin [& args] (Operation. "sin" #(Math/sin %) args))
(defn Cos [& args] (Operation. "cos" #(Math/cos %) args))
(defn Sinh [& args] (Operation. "sinh" #(Math/sinh %) args))
(defn Cosh [& args] (Operation. "cosh" #(Math/cosh %) args))

(def operationsHW11
  {'+      Add
   '-      Subtract
   '*      Multiply
   '/      Divide
   'negate Negate
   'sin    Sin
   'cos    Cos
   'sinh   Sinh
   'cosh   Cosh})

(defn evaluate [expr map] (.eval expr map))
(defn toString [expr] (.toStr expr))
(defn parseObject [expression]
  (parse expression operationsHW11 Variable Constant))

;;;;;;;;;;;
;; HW 12 ;;
;;;;;;;;;;;
; to be continued...
