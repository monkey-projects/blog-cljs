(ns monkey.blog.utils
  (:require [re-frame.core :as rf]))

(defn evt-dispatch-handler [evt]
  (fn [e]
    #?(:cljs (.preventDefault e))
    (rf/dispatch evt)))

(defn- get-value [e]
  #?(:cljs (.-value (.-target e))
     :clj (get-in e [:target :value])))

(defn value-handler
  "Returns an event handler that dispatches to `evt` with the value
   of the event attached."
  [evt]
  (fn [e]
    (rf/dispatch-sync (vec (conj evt (get-value e))))))

(defn extract-error [e]
  (or (:status-text e)
      (str e)))
