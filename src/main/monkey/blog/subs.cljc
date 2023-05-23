(ns monkey.blog.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :panel/current
 (fn [db _]
   (:panel/current db)))
