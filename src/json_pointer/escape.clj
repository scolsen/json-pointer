(ns works.olsen.json-pointer.escape 
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

(defn key->escaped-string 
  "Takes a value and converts it to an escaped JSON pointer string."
  [k] (escape (if (not= nil (namespace k)) 
                  (str (namespace k) "/" (name k)) 
                  (name k)))) ;;Note that name works on keys *and* strings.

