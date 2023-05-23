(ns monkey.blog.test.core-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [monkey.blog.core :as sut]))

(deftest header
  (testing "returns vector"
    (is (vector? (sut/header)))))

(deftest footer
  (testing "returns vector"
    (is (vector? (sut/footer)))))

(deftest main
  (testing "returns vector"
    (is (vector? (sut/main)))))
