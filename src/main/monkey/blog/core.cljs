(ns monkey.blog.core
  (:require [monkey.blog.routing :as routing]
            [monkey.blog.subs]
            [monkey.blog.views]
            [reagent.core :as r]
            [reagent.dom :as rdom]
            [reitit.frontend.easy :as rfe]))

(defn header []
  [:div.header
   [:div.site_title
    [:a {:href ""} "fighting the food fight"]]
   "uh, " [:i "good"] " fight, I mean good fight."])

(defn footer []
  [:div.footer
   [:p "Time for a change, " [:a {:href "https://www.mozilla.org/firefox"} "try firefox"] "."]
   [:p "Mail me at " [:a {:href "mailto:wout@neirynck.com"} "wout@neirynck.com"] "."]])

(defn main []
  [:div.main
   [header]
   [:p "Loading..."]
   [footer]])

(defn ^:dev/after-load reload! []
  (routing/start!)
  (let [root (js/document.getElementById "app")]
    (rdom/unmount-component-at-node root)
    (rdom/render [main] root)))

(defn ^:export init []
  (reload!))
