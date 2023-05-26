(ns monkey.blog.core
  (:require [com.degel.re-frame-firebase :as firebase]
            [monkey.blog.alerts]
            [monkey.blog.components :as c]
            [monkey.blog.firebase]
            [monkey.blog.journal.views]
            [monkey.blog.login]
            [monkey.blog.routing :as routing]
            [monkey.blog.subs]
            [monkey.blog.panels]
            [monkey.blog.views]
            [re-frame.core :as rf]
            [reagent.core :as r]
            [reagent.dom :as rdom]
            [reitit.frontend.easy :as rfe]))

(defonce firebase-config
  {:apiKey "AIzaSyAPFhrOyMRttPgKz5pE9nbOIf3SX6jrzs8"
   :authDomain "monkey-blog-ed71f.firebaseapp.com"
   :databaseURL "https://monkey-blog-ed71f-default-rtdb.europe-west1.firebasedatabase.app"
   :projectId "monkey-blog-ed71f"
   :storageBucket "monkey-blog-ed71f.appspot.com"
   :messagingSenderId "990992276408"
   :appId "1:990992276408:web:1afcf121fdde0382654d4d"
   :measurementId "G-122DNLVTRK"})

(defn main []
  (let [p (rf/subscribe [:panels/current])]
    [:<>
     [c/error]
     [c/notification]
     (if (nil? @p)
       [:p "No panel"]
       [@p])]))

(defn ^:dev/after-load reload! []
  (routing/start!)
  (let [root (js/document.getElementById "app")]
    (rdom/unmount-component-at-node root)
    (rdom/render [main] root)))

(defn ^:export init []
  (firebase/init :firebase-app-info firebase-config
                 :get-user-sub [:firebase/user]
                 :set-user-event [:firebase/set-user]
                 :default-error-handler [:firebase/error])
  (reload!))
