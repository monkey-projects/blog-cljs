(ns monkey.blog.test.journal.events-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.alerts :as a]
            [monkey.blog.journal.db :as db]
            [monkey.blog.journal.events :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(rf/clear-subscription-cache!)

(defn test-fx [fx]
  (let [a (atom [])]
    (rf/reg-fx fx
               (fn [arg]
                 (swap! a conj arg)))
    a))

(deftest load-evt
  (testing "loads from firebase"
    (let [a (test-fx :firebase/read-once)]
      (rf/dispatch-sync [::sut/load])
      (is (= 1 (count @a)))
      (is (= [:journal :items] (:path (first @a)))))))

(deftest load--success
  (testing "sets items"
    (is (empty? (reset! app-db {})))
    (rf/dispatch-sync [::sut/load--success [:test-items]])
    (is (= [:test-items] (db/items @app-db))))

  (testing "sets loaded"
    (is (empty? (reset! app-db {})))
    (rf/dispatch-sync [::sut/load--success [:test-items]])
    (is (true? (db/loaded? @app-db)))))

(deftest load--failed
  (testing "sets error"
    (is (empty? (reset! app-db {})))
    (rf/dispatch-sync [::sut/load--failed "test-error"])
    (is (= "test-error" (:message (a/error @app-db)))))
  
  (testing "allows retrying"
    (is (empty? (reset! app-db {})))
    (rf/dispatch-sync [::sut/load--failed "test-error"])
    (is (= [::sut/load] (:retry (a/error @app-db))))))
