(ns monkey.blog.journal.events
  (:require [monkey.blog.alerts :as a]
            [monkey.blog.journal.db :as db]
            [monkey.blog.utils :as u]
            [re-frame.core :as rf]))

(rf/reg-event-fx
 ::load
 (fn [_ _]
   {:firebase/read-once {:path [:journal :items]
                         :on-success [::load--success]
                         :on-failure [::load--failed]}}))

(rf/reg-event-db
 ::load--success
 (fn [db [_ items]]
   (-> db
       (db/set-items items)
       (db/set-loaded true))))

(rf/reg-event-db
 ::load--failed
 (fn [db [_ err]]
   (a/set-error db {:message (u/extract-error err)
                    :retry [::load]})))
