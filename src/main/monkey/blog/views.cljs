(ns monkey.blog.views
  "Main views"
  (:require [monkey.blog.components :as c]
            [monkey.blog.login :as l]
            [monkey.blog.panels :as p]
            [monkey.blog.routing :as r]
            [re-frame.core :as rf]))

(defn home []
  (let [u (rf/subscribe [:firebase/user])]
    [:<>
     [:div.content
      (if @u
        [:p "Welcome, " (or (:display-name @u) (:email @u))]
        [:p "Welcome."])
      [:div.card
       [:div.title "test entry"]
       [:p "This is a test entry"]]]
     [c/links]]))

(p/reg-panel ::r/root home)
