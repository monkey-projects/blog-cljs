(ns monkey.blog.views
  "Main views"
  (:require [monkey.blog.panels :as p]
            [monkey.blog.routing :as r]))

(defn home []
  [:div.content
   [:p "Welcome."]
   [:div.entry
    [:div.title "test entry"]
    [:p "This is a test entry"]]])

(defn journal []
  [:p "This is the journal panel"])

(p/reg-panel ::r/root home)
(p/reg-panel ::r/journal journal)
