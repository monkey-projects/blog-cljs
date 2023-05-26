(ns monkey.blog.journal.subs
  (:require [monkey.blog.journal.db :as db]
            [re-frame.core :as rf]))

(rf/reg-sub
 ::loaded?
 (fn [db _]
   (db/loaded? db)))

(rf/reg-sub
 ::items
 (fn [db _]
   (db/items db)))
