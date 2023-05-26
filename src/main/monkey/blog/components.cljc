(ns monkey.blog.components
  (:require [monkey.blog.utils :as u]
            [re-frame.core :as rf]))

(defn public-links
  "Links available on the public site"
  []
  [:section
   [:div.title "interesting reads"]
   [:a {:href "http://www.joelonsoftware.com"} "joel on software"]
   [:a {:href "https://blogs.msdn.microsoft.com/oldnewthing/"} "the old new thing"]
   [:a {:href "http://www.martinfowler.com/bliki/"} "martin fowler's bliki"]
   [:a {:href "https://clojure.org/news/news"} "clojure news"]])

(defn private-links
  "Links only available in the secure area"
  []
  [:section
   [:div.title "navigation"]
   [:a {:href "/"} "weblog"]
   [:a {:href "/admin"} "admin"]
   [:a {:href "/journal"} "journal"]
   [:a {:href "/drafts"} "drafts"]
   [:a {:href "/upload"} "upload files"]])

(defn links
  "Shows public links, and if the user has been authenticated, also the private links"
  []
  (let [a (rf/subscribe [:authenticated?])]
    [:div.links
     [public-links]
     (when @a
       [private-links])]))

(defn evt-link [evt lbl]
  [:a {:href ""
       :on-click (u/evt-dispatch-handler evt)} lbl])

(defn error []
  (let [e (rf/subscribe [:alerts/error])]
    (when @e
      [:div.error @e])))

(defn notification []
  (let [e (rf/subscribe [:alerts/notification])]
    (when @e
      [:div.notification @e])))

(defn with-layout
  "Wraps given `p` component in the default layout, with the specified links to show"
  [links p]
  [:table {:width "100%" :cell-padding 0}
   [:tbody
    [:tr
     [:td {:valign "top" :width "100%"}
      p]
     [:td {:valign "top" :align "right" :nowrap "true"}
      [:div.links links]]]]])
