(ns monkey.blog.test.alerts-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.alerts :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(deftest notification-sub
  (let [n (rf/subscribe [:alerts/notification])]
    (testing "exists"
      (is (some? n)))

    (testing "provides notification from db"
      (is (map? (swap! app-db sut/clear-notification)))
      (is (nil? @n))
      (is (map? (swap! app-db sut/set-notification "test notification")))
      (is (= "test notification" @n)))))

(deftest error-sub
  (let [e (rf/subscribe [:alerts/error])]
    (testing "exists"
      (is (some? e)))

    (testing "provides error from db"
      (is (map? (swap! app-db sut/clear-error)))
      (is (nil? @e))
      (is (map? (swap! app-db sut/set-error "test error")))
      (is (= "test error" @e)))))

(deftest clear-all
  (testing "clears both error and notification"
    (is (empty? (-> {}
                    (sut/set-error "the house is on fire")
                    (sut/set-notification "dinner is served")
                    (sut/clear-all))))))
