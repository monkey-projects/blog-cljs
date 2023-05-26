(ns monkey.blog.login
  (:require [re-frame.core :as rf]
            [monkey.blog.components :as c]
            [monkey.blog.panels :as p]
            [monkey.blog.routing :as r]
            [monkey.blog.utils :as u]))

(rf/reg-sub
 :login/credentials
 (fn [db _]
   (::credentials db)))

(rf/reg-event-db
 :login/username
 (fn [db [_ user]]
   (assoc-in db [::credentials :username] user)))

(rf/reg-event-db
 :login/password
 (fn [db [_ user]]
   (assoc-in db [::credentials :password] user)))

(rf/reg-event-fx
 :login
 (fn [{:keys [db]} _]
   (let [{:keys [username password]} (::credentials db)]
     {:firebase/email-sign-in {:email username
                               :password password}})))

(defn login-panel []
  (let [creds (rf/subscribe [:login/credentials])]
    [:div.login
     [:p "Please login to access my private parts."]
     [:div.card
      [:div.title "authenticate"]
      [:form
       [:div.form-entry
        [:div "username"]
        [:input {:type :text
                 :id :username
                 :value (or (:username @creds) "")
                 :on-change (u/value-handler [:login/username])}]]
       [:div.form-entry
        [:div "password"]
        [:input {:type :password
                 :id :password
                 :value (or (:password @creds) "")
                 :on-change (u/value-handler [:login/password])}]]
       [:div.form-entry
        ;; use a button that looks like a link, this allows submitting the form by pressing enter
        [:button.link-btn
         {:type :submit
          :on-click (u/evt-dispatch-handler [:login])} "login"]
        " | "
        [:a {:href "/"} "cancel"]]
       [c/error]]]]))

(p/reg-panel ::r/login login-panel)
