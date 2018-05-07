(ns json-pointer.core
  (:require [json-pointer.pointer :as pointer] 
            [json-pointer.parser :as parser]
            [json-pointer.predicate :as predicate]
            [json-pointer.escape :as esc]))

(defn pointer->keys 
  "Transform a JSON pointer string to a sequence of keys."
  [s] (as-> s s*
            (parser/parse s*)
            (when (predicate/pointer? s*) 
              (pointer/pointer->keys s*))))

(defn pointer->strings 
  "Transform a JSON pointer string to a sequence of strings."
  [s] (as-> s s*
            (parser/parse s*)
            (when (predicate/pointer? s*) 
              (pointer/pointer->strings s*))))

(def escape "Escape a JSON pointer string. Alias to esc/escape" esc/escape)
(def parse "Parse a JSON pointer string. Aliare to parser/parse" parser/parse)
