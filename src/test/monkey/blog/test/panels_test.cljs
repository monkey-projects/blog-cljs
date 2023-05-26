(ns monkey.blog.test.panels-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.panels :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(rf/clear-subscription-cache!)

(deftest panels-all
  (let [p (rf/subscribe [:panels/all])]
    
    (testing "exists"
      (is (some? p)))

    (testing "returns all panels"
      (is (empty? (reset! app-db {})))
      (sut/reg-panel ::test :test-panel)
      (is (= {::test :test-panel} @p)))))

(deftest panels-current
  (let [c (rf/subscribe [:panels/current])]
    
    (testing "exists"
      (is (some? c)))

    (testing "nil if no current panel"
      (is (empty? (reset! app-db {})))
      (is (nil? @c)))

    (testing "returns current panel symbol for route"
      (is (some? (reset! app-db {:route/current {:data {:name ::test}}})))
      (sut/reg-panel ::test :test-panel)
      (is (= :test-panel @c)))))
