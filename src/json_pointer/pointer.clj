(ns json-pointer.pointer 
  "Pointer functions."
  (:require [json-pointer.predicate :as pred] [json-pointer.parser :as parser]
            [json-pointer.tree :as pt] [json-pointer.transform :as t]
            [clojure.string :as s]))

(defn pointer->seq 
  "Transform a parsed json pointer to a seq using a token transformer."
  ([tree-transformer seq-transformer] 
   (fn [pointer] (->> pointer 
                      (parser/parse)
                      (tree-transformer)
                      (map seq-transformer)
                      (flatten)))))

(defn seq->pointer 
  "Transform a sequence to a json pointer using some function f."
  [f] (fn [xs]
          (->> (f xs)
               (interpose \/)
               (s/join)
               (str "#"))))

(def pointer->keys (pointer->seq pt/default t/->keys))
(def pointer->strings (pointer->seq pt/default t/->strings))

(def strings->pointer (seq->pointer identity))
