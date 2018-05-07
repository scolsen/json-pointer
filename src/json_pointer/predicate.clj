(ns works.olsen.json-pointer.predicate
  "Predicates over json pointers."
  (:require [instaparse.core :as insta]))

(defn token? 
  "Determine if arg is a token."
  [token] (and (= (first token) :reference-token) vector?))

(defn pointer? 
  "Returns true if pointer is a valid, parseable json pointer."
  [pointer] ((complement insta/failure?) pointer))

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
