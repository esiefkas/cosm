(ns cosm.datomic
  (:require [datomic.api :as d]
            [cosm.schema :as schema]
            [cosm.data-bootstrap :as bs]))

(defonce datomic-conn (atom nil))
(def test-db-uri "datomic:mem://cosm")

(defn start-datomic []
  (d/create-database test-db-uri)
  (reset! datomic-conn (d/connect test-db-uri))
  ;;transact schema
  (d/transact @datomic-conn schema/full-schema)
  ;;transact boostrap-data
  (d/transact @datomic-conn (map (partial bs/add-type :make) bs/bike-makes))
  (d/transact @datomic-conn (map (partial bs/add-type :rider) bs/riders))
  (d/transact @datomic-conn (map (partial bs/add-type :class) bs/classes))
  (d/transact @datomic-conn (map (partial bs/add-type :track) bs/tracks))
  (d/transact @datomic-conn (map (partial bs/add-type :race) bs/races)))

(defn get-all-of-type [type]
  (let [db (d/db @datomic-conn)]
    (d/q '[:find (pull ?e [*])
           :in $ ?type
           :where
           [?e :cosm/type ?type]] db type)))

(defn get-all-moto-winners []
  (let [db (d/db @datomic-conn)]
    (d/q '[:find (pull ?r [:rider/first-name :rider/last-name])
           :in $
           :where
           [?f :finish/place 1]
           [?f :finish/racer ?r]] db)))


