(ns monkey.blog.journal.views
  (:require [monkey.blog.components :as c]
            [monkey.blog.login :as l]
            [monkey.blog.panels :as p]
            [monkey.blog.routing :as r]
            [monkey.blog.journal.events :as e]
            [monkey.blog.journal.subs :as s]
            [re-frame.core :as rf]))

(defn load-items []
  (rf/dispatch [::e/load])
  [:p "Loading journal items, hold on..."])

(defn show-items []
  (let [items (rf/subscribe [::s/items])]
    [:p (count @items) " journal entries loaded."]))

(defn main []
  (let [a? (rf/subscribe [:authenticated?])
        loaded? (rf/subscribe [::s/loaded?])]
    (if @a?
      [:<>
       [:div.content
        (if @loaded?
          [show-items]
          [load-items])]
       [c/links]]
      [l/login-panel])))

(p/reg-panel ::r/journal main)
