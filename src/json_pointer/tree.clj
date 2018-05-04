(ns json-pointer.tree
  "Transform parsed trees."
  (:require [json-pointer.token :as token]))

(defn ->tokens 
  "Transform a parsed tree into a coll of token vectors."
  [tree] (filter vector? tree))

(defn ->token-sequence
  "Process a parsed pointer pair functions for each type of token."
  [tree] (map token/->sequence (->tokens tree)))

(defn ->prefixes
  "Convert a parsed tree to a list of terminal values only."
  [tree] (filter #(= "/" %) tree))

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
