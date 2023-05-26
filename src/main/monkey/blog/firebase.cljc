(ns monkey.blog.firebase
  "Firebase binding events and subs"
  (:require [re-frame.core :as rf]))

(rf/reg-event-db
 :firebase/set-user
 (fn [db [_ user]]
   (println "Setting user:" user)
   (assoc db :firebase/user user)))

(rf/reg-sub
 :firebase/user
 (fn [db _]
   (:firebase/user db)))

(rf/reg-sub
 :authenticated?
 :<- [:firebase/user]
 (fn [u _]
   (some? u)))
