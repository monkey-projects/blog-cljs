(ns monkey.blog.core
  (:require [monkey.blog.routing :as routing]
            [monkey.blog.subs]
            [monkey.blog.panels]
            [monkey.blog.views]
            [re-frame.core :as rf]
            [reagent.core :as r]
            [reagent.dom :as rdom]
            [reitit.frontend.easy :as rfe]))

(defn main []
  (let [p (rf/subscribe [:panels/current])]
    (if (nil? @p)
      [:p "No panel"]
      [@p])))

(defn ^:dev/after-load reload! []
  (routing/start!)
  (let [root (js/document.getElementById "app")]
    (rdom/unmount-component-at-node root)
    (rdom/render [main] root)))

(defn ^:export init []
  (reload!))
