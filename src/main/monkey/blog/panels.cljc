(ns monkey.blog.panels
  (:require [re-frame.core :as rf]
            [re-frame.db :as rdb]))

(defonce panels (atom {}))

(defn reg-panel [route-name panel]
  (swap! rdb/app-db assoc-in [::panels route-name] panel))

(rf/reg-sub
 :panels/all
 (fn [db _]
   (::panels db)))
 
(rf/reg-sub
 :panels/current
 :<- [:panels/all]
 :<- [:route/current]
 (fn [[all r] _]
   (get all (get-in r [:data :name]))))
