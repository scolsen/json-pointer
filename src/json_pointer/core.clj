(ns works.olsen.json-pointer
  (:require [works.olsen.json-pointer.pointer :as pointer] 
            [works.olsen.json-pointer.parser :as parser]
            [works.olsen.json-pointer.predicate :as predicate]
            [works.olsen.json-pointer.escape :as esc]))

(defn pointer->keys 
  "Transform a JSON pointer string to a sequence of keys."
  [s] (as-> s s*
            (parser/parse s*)
            (when (predicate/pointer?) 
              (pointer/pointer->keys s*))))

(defn pointer->strings 
  "Transform a JSON pointer string to a sequence of strings."
  [s] (as-> s s*
            (parser/parse s*)
            (when (predicate/pointer? s*) 
              (pointer/pointer->strings s*))))

(def escape "Escape a JSON pointer string. Alias to esc/escape" esc/escape)
(def parse "Parse a JSON pointer string. Aliare to parser/parse" parser/parse)
