(ns json-pointer.transform
  "Higher-order functions for generating pointer transformations."
  (:require [clojure.string :as s] [json-pointer.predicate :as pred]))
  
(defn seq-> 
  "Transform a parsed token using some function f.
   Takes an optional predicate."
  ([f] (fn [x] (f x)))
  ([f pred] (fn [x] (if (pred x) (f x) x))))

(def ->keys (seq-> (comp keyword s/join) pred/no-integers?))
(def ->strings (seq-> s/join pred/no-integers?))






