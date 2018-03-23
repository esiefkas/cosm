(ns cosm.rider
  (:require [ring.util.response :as ring]
            [datomic.api :as d]
            [cosm.datomic :as cd]))

(defn handler [request]
  (let [db (d/db @cd/datomic-conn)
        raw-datomic-response
        (d/q '[:find [(pull ?e [*]) ...]
               :in $
               :where
               [?e :cosm/type :rider]] db)]
    (ring/response raw-datomic-response)))
