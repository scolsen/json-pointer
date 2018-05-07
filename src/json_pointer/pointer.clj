(ns json-pointer.pointer 
  "Pointer functions."
  (:require [json-pointer.predicate :as pred] 
            [json-pointer.token :as token]
            [json-pointer.escape :as esc]
            [clojure.string :as s]))

(defn- ->pointer 
  "Transform a sequence to a JSON pointer using some function f."
  [f] (fn [xs]
          (->> xs
               (map f)
               (interpose \/)
               (s/join)
               (str "#"))))

(defn- pointer-> 
  "Transform a JSON pointer into a sequence using a token transformation."
  [f] (fn [pointer] 
          (->> pointer
               (filter vector?)
               (map f))))

(def pointer->strings
  "Transform a JSON pointer to a sequence of strings."
  (pointer-> token/token->strings))

(def pointer->keys 
  "Transform a JSON pointer to a sequence of keys."
  (pointer-> token/token->keys))

(def keys->pointer
  "Transform a sequence of keys into a JSON pointer."
  (->pointer #(if (keyword? %) (esc/key->escaped-string %) %)))

(def strings->pointer
  "Transform a sequence of strings into a JSON pointer."
  (->pointer #(if (string? %) (esc/escape %) %)))
