(ns json-pointer.token
  "Token transformers."
  (:require [json-pointer.escape :as esc] [clojure.string :as s]))

(defn ->sequence
  "Parse a JSON pointer token and return a sequence of values."
  [token]
  (case (first token) 
        :escaped (esc/unescape token)
        :array-index (Integer. (s/join (rest token)))
        :unescaped (second token)
        :reference-token (map ->sequence (rest token))
        token))

(defn token? 
  "Determine if arg is a token."
  [token] (and (= (first token) :reference-token) vector?))
