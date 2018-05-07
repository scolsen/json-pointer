(ns json-pointer.pointer-test
  (:require [clojure.test :refer :all] [json-pointer.pointer :as p]))

(def jpointer "#definitions/Pet")
(def with-array "#definitions/Pet/5")
(def with-escape "#defini~1tions/P~0et")

(defn pk-round [x] (-> x p/pointer->keys p/keys->pointer))
(defn ps-round [x] (-> x p/pointer->strings p/strings->pointer))

(deftest pointer-keys-round-trip
  (testing "pointer->keys inverses." 
    (is (= jpointer (pk-round jpointer)))
    (is (= with-array (pk-round with-array)))
    (is (= with-escape (pk-round with-escape)))))

(deftest pointer-strings-round-trip
  (testing "pointer->strings inverses."
    (is (= jpointer (ps-round jpointer)))
    (is (= with-array (ps-round with-array)))
    (is (= with-escape (ps-round with-escape)))))
