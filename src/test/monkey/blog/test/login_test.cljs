(ns monkey.blog.test.login-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.login :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(rf/clear-subscription-cache!)

(deftest login-panel
  (testing "returns hiccup vector"
    (is (vector? (sut/login-panel)))))

(deftest credentials-sub
  (let [c (rf/subscribe [:login/credentials])]
    (testing "exists"
      (is (some? c)))

    (testing "returns credentials from db"
      (is (some? (reset! app-db {::sut/credentials :test-credentials})))
      (is (= :test-credentials @c)))))

(deftest username-evt
  (testing "stores username in credentials"
    (reset! app-db {})
    (rf/dispatch-sync [:login/username "test-user"])
    (is (= "test-user" (get-in @app-db [::sut/credentials :username])))))

(deftest password-evt
  (testing "stores password in credentials"
    (reset! app-db {})
    (rf/dispatch-sync [:login/password "test-user"])
    (is (= "test-user" (get-in @app-db [::sut/credentials :password])))))
