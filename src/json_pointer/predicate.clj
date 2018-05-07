(ns json-pointer.predicate
  "Predicates over json pointers."
  (:require [instaparse.core :as insta]))

(defn pointer? 
  "Returns true if pointer is a valid, parseable json pointer."
  [pointer] ((complement insta/failure?) pointer))

(defn no-integers? [xs]
  "Returns true if the sequence contains no integers."
  (partial (complement some) integer?))

(defn uri? 
  "Determines if the parsed string is a URI fragment."
  [tree] (contains? "#"))

(defn root? 
  "Determines if the parsed string points to the root document"
  [tree] (case tree
               [:json-pointer "/"] true
               [:json-pointer "#"] true
               [:json-pointer] true
               false))
