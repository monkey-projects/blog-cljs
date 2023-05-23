(ns monkey.blog.test.routing-test
  (:require [cljs.test :refer-macros [deftest is testing]]
            [day8.re-frame.test :as rf-test]
            [monkey.blog.routing :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(deftest on-route-change
  (testing "dispatches `route/goto` event"
    (rf-test/run-test-sync
     (is (nil? (sut/on-route-change :test-match :test-history)))
     (is (= :test-match (:route/current @app-db))))))

