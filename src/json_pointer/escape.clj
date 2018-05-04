(ns json-pointer.escape 
  "Escape and unescape JSON pointer strings."
  (:require [clojure.string :as s]))

(defn unescape 
  "Unescape JSON pointer strings."
  [token]
  (case (last token)
        "0" "~"
        "1" "/"))

(defn escape 
  "Escape invalid JSON pointer characters."
  [string]
  (-> string
      (s/replace #"~" "~0")
      (s/replace #"/" "~1")))

(defn ->escaped-string 
  "Takes a value and converts it to an escaped JSON pointer string."
  [k]
  (cond (keyword? k) (escape (s/replace-first (str k) #":" ""))
        (string? k) (escape k)
        :else k))

