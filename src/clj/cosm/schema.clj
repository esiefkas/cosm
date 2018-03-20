(ns cosm.schema)

;;global fields

;;display name
(def types
  [{:db/ident :make}
   {:db/ident :track}
   {:db/ident :class}
   {:db/ident :rider}
   {:db/ident :race}])

(def globals
  [{:db/ident :display-name
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true
    :db/doc "Primary display string for any item in the db"}
   {:db/ident :image-url
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc "Image url for displaying item on the front end"}
   {:db/ident :cosm/type
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc "type enum for marking entities"}])

(def make [])

(def track
  [{:db/ident :location
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/isComponent true
    :db/index true
    :db/doc "ref to location entity"}
   {:db/ident :loc/address
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true}
   {:db/ident :loc/address
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true}
   {:db/ident :loc/city
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true}
   {:db/ident :loc/state
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true}
   {:db/ident :loc/zip
    :db/valueType :db.type/long
    :db/cardinality :db.cardinality/one
    :db/index true}])

(def class [{:db/ident :class/description
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one
             :db/index true}])

(def rider [])

(def race [])

