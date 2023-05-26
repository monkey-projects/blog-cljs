(ns monkey.blog.test.journal.subs-test
  (:require [cljs.test :refer-macros [deftest testing is]]
            [monkey.blog.journal.db :as db]
            [monkey.blog.journal.subs :as sut]
            [re-frame.core :as rf]
            [re-frame.db :refer [app-db]]))

(rf/clear-subscription-cache!)

(deftest loaded?-sub
  (let [l (rf/subscribe [::sut/loaded?])]
    
    (testing "exists"
      (is (some? l)))

    (testing "returns true if journal entries have been loaded"
      (is (empty? (reset! app-db {})))
      (is (false? @l))
      (is (not-empty (reset! app-db (db/set-loaded {} true))))
      (is (true? @l)))))

(deftest items-sub
  (let [i (rf/subscribe [::sut/items])]

    (testing "exists"
      (is (some? i)))

    (testing "returns items from db"
      (is (not-empty (reset! app-db (db/set-items {} :test-items))))
      (is (= :test-items @i)))))
