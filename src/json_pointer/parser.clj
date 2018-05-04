(ns json-pointer.parser 
  "A parser for json pointers."
  (:require [instaparse.core :as i]))

(def parser (i/parser "src/json_pointer/data/abnf.txt" :input-format :abnf))

(defn parse [pointer]
  "Parse a JSON pointer."
  (parser pointer))
