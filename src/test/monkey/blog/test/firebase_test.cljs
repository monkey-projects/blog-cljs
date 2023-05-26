(ns monkey.blog.test.firebase-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.firebase :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))
  
(rf/clear-subscription-cache!)

(deftest set-user-evt
  (testing "sets user in db"
    (is (empty? (reset! app-db {})))
    (rf/dispatch-sync [:firebase/set-user :test-user])
    (is (= :test-user (:firebase/user @app-db)))))

(deftest user-sub
  (let [u (rf/subscribe [:firebase/user])]
    (testing "exists"
      (is (some? u)))
    
    (testing "gets user from db"
      (is (map? (reset! app-db {:firebase/user :test-user})))
      (is (= :test-user @u)))))

(deftest authenticated?-sub
  (let [a (rf/subscribe [:authenticated?])]
    (testing "exists"
      (is (some? a)))

    (testing "true if there is a firebase user"
      (is (empty? (reset! app-db {})))
      (is (false? @a))
      (is (not-empty (reset! app-db {:firebase/user "some user"})))
      (is (true? @a)))))
      
