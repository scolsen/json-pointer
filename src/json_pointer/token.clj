(ns json-pointer.token
  "Token transformers."
  (:require [json-pointer.escape :as esc] [clojure.string :as s]))

(defn- handle-escape 
  "Handle escaped tokens."
  [token]
  (if (= (first token) :escaped)
    (esc/unescape token)
    token))

(defn- ->string 
  "Given a token, flatten it to a string."
  [token]
  (->> token
       rest
       (map handle-escape) ;;this is suboptimal for array-index.
       flatten
       (filter (complement keyword?))
       s/join))

(defn token->
  "Parse a JSON pointer token and return a sequence of values."
  [f]
  (fn [token]
      (case (first token) 
            :array-index (Integer. (->string token))
            :reference-token (f (->string token))
            token)))

(def token->keys (token-> keyword))
(def token->strings (token-> identity))


