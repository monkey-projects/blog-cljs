(ns monkey.blog.alerts
  (:require [re-frame.core :as rf]))

(defn set-notification [db n]
  (assoc db ::notification n))

(defn notification [db]
  (::notification db))

(defn clear-notification [db]
  (dissoc db ::notification))

(rf/reg-sub
 :alerts/notification
 (fn [db _]
   (notification db)))

(defn set-error [db e]
  (assoc db ::error e))

(defn clear-error [db]
  (dissoc db ::error))

(defn error [db]
  (::error db))

(defn clear-all [db]
  (-> db
      (clear-error)
      (clear-notification)))

(rf/reg-sub
 :alerts/error
 (fn [db _]
   (error db)))
