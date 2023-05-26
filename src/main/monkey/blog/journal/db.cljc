(ns monkey.blog.journal.db)

(defn set-loaded [db l?]
  (assoc db ::loaded true))

(def loaded? (comp true? ::loaded))

(defn set-items [db items]
  (assoc db ::items items))

(def items ::items)
