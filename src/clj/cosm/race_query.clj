(ns cosm.race-query
  (:require [ring.util.response :as ring]
            [datomic.api :as d]
            [cosm.datomic :as cd]
            [clj-time.core :as t]))

(defn get-races-for-year [year]
  (let [db (d/db @cd/datomic-conn)
        raw-datomic-response
        (d/q '[:find [(pull ?e [:race/date {:race/venue [{:location [*]} :display-name]}]) ...]
               :in $ ?year
               :where
               [?e :cosm/type :race]
               [?e :race/date ?d]
               [((fn [^java.util.Date dt] (+ (.getYear dt) 1900)) ?d) ?ryear]
               [(= ?ryear ?year)]]
             db year)]
    raw-datomic-response))

(defn get-current-year []
  (+ 1900 (.getYear (java.util.Date.))))

(defn query-handler [year]
  (println "IN RACE-QUERY HANDLER")
  (let [;;req-year (get-in request [:params :year])
        ;;year (if req-year (Long/parseLong req-year) (get-current-year))
        _ (println "YEAR IS" year)
        races (get-races-for-year (Long/parseLong year))
        _ (println "race count" (count races))]
    (println "RESULT IS:")
    (clojure.pprint/pprint races)
    (ring/response races)))

