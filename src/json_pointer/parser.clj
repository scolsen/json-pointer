(ns json-pointer.parser 
  "A parser for json pointers."
  (:require [instaparse.core :as i]))

(def parser (i/parser "json-pointer = *1(\"#\" (reference-token / \"\" / array-index)) *( \"/\" (reference-token / \"\" / array-index) ) / \"\"
reference-token = *( unescaped / escaped ) 
unescaped = *( *%x00-2E / *%x30-7D / *%x7F-10FFFF ) 
escaped = \"~\" ( \"0\" / \"1\" )
array-index = %x30 / ( %x31-39 *(%x30-39) )" :input-format :abnf))

(defn parse [pointer]
  "Parse a JSON pointer."
  (parser pointer))
