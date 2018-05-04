(ns json-pointer.token
  "Token transformers."
  (:require [json-pointer.escape :as esc] [clojure.string :as s]))

(defn token->seq
  "Parse a JSON pointer token and return a sequence of values."
  [& {:keys [escaped array-index unescaped]
      :or {escaped esc/unescape array-index (comp #(Integer. %) s/join) 
           unescaped second}}]
  (fn go [token]
         (case (first token) 
               :escaped (escaped token)
               :array-index (array-index token)
               :unescaped (unescaped token)
               :reference-token (map go (rest token))
               token)))

(def default (token->seq))
