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

(defn modify-pointer 
  "Modify a pointer string, then recast to a new pointer string."
  [s f]
  (-> s
      pointer->strings
      f
      pointer/strings->pointer))

(defn resolve-pointer 
  "Given a JSON object and a JSON pointer, return a portion of the JSON object."
  [json pointer & {:keys [resolver] 
                   :or {resolver :strings}}]
  (case resolver
        :strings (get-in json (pointer->strings pointer))
        :keywords (get-in json (pointer->keys pointer))))

(def escape "Escape a JSON pointer string. Alias to esc/escape" esc/escape)
(def parse "Parse a JSON pointer string. Alias to parser/parse" parser/parse)
