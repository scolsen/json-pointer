(ns json-pointer.tree
  "Transform parsed trees."
  (:require [json-pointer.token :as token]))

(defn parse-tree->seq
  "Process a parsed pointer pair functions for each type of token."
  [f token-transformer]
  (fn [parse-tree]
      (->> (f parse-tree)
           (map token-transformer))))

;;this is subtle, we need to use partial because filter is a transducer.
(def default (parse-tree->seq (partial filter vector?) token/default))
