(ns monkey.blog.test.components-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.components :as sut]))

(deftest public-links
  (testing "returns hiccup vector"
    (is (vector? (sut/public-links)))))

(deftest private-links
  (testing "returns hiccup vector"
    (is (vector? (sut/private-links)))))

(deftest links
  (testing "returns hiccup vector"
    (is (vector? (sut/links)))))

(deftest evt-link
  (testing "renders link with empty href"
    (let [l (sut/evt-link [:test-event] "test-link")]
      (is (= :a (first l)))
      (is (empty? (-> l second :href))))))
