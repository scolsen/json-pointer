(ns json-pointer.escape 
  ""
  (:require [clojure.string :as s]))

(defn unescape 
  "Unescape strings."
  [token]
  (case (last token)
        "0" "~"
        "1" "/"))

(defn escape 
  "Escape invalid characters."
  [pointerstring]
  (-> pointerstring 
      (s/replace #"~" "~0")
      (s/replace #"/" "~1")))
