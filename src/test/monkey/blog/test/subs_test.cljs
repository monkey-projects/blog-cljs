(ns monkey.blog.test.subs-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.db :as db]
            [monkey.blog.subs :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(rf/clear-subscription-cache!)

(deftest panel-current
  (let [r (rf/subscribe [:panel/current])]

    (testing "exists"
      (is (some? r)))

    (testing "returns current panel from db"
      (is (map? (reset! app-db (db/set-current-panel {} :test-current))))
      (is (= :test-current @r)))))
