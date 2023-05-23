(ns monkey.blog.routing
  (:require [re-frame.core :as rf]
            [reitit.frontend :as f]
            [reitit.frontend.easy :as rfe]))

(rf/reg-event-db
 :route/goto
 (fn [db [_ match]]
   (assoc db :route/current match)))

(def router (f/router [["/" ::root]
                       ["/about" ::about]]))

(defn on-route-change [match history]
  (println "Route changed:" match)
  (rf/dispatch [:route/goto match]))

(defn start! []
  (rfe/start! router on-route-change {:use-fragment false}))
