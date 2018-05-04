(ns json-pointer.pointer 
  "Pointer functions."
  (:require [json-pointer.predicate :as pred] [json-pointer.parser :as parser]
            [json-pointer.tree :as pt] [json-pointer.transform :as t]
            [clojure.string :as s] [json-pointer.escape :as esc]))

(defn pointer->seq 
  "Transform a parsed json pointer to a seq using a token transformer."
  ([tree-transformer seq-transformer] 
   (fn [pointer] (->> pointer 
                      (parser/parse)
                      (tree-transformer)
                      (map seq-transformer)
                      (flatten)))))

(defn ->pointer 
  "Transform a sequence to a json pointer using some function f."
  [f] (fn [xs]
          (->> xs
               (map f)
               (interpose \/)
               (s/join)
               (str "#"))))

(defn pointer-> 
  "Transform a JSON pointer."
  [f] (fn [pointer] 
          (->> pointer
               parser/parse
               tree/->token-sequence
               (map f)
               (flatten))))

(defn ->keys 
  "Transform a JSON pointer to a sequence of keys."
  [pointer] ())

(defn ->strings 
  "Transform a JSON pointer to a sequence of strings."
  [pointer] ())

(def pointer->keys (pointer->seq pt/default t/->keys))
(def pointer->strings (pointer->seq pt/default t/->strings))

(def keys->pointer (seq->pointer esc/->escaped-string))
(def strings->pointer (seq->pointer esc/escape))
