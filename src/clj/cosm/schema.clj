(ns cosm.schema)

(def types
  [{:db/ident :make}
   {:db/ident :track}
   {:db/ident :class}
   {:db/ident :rider}
   {:db/ident :race}
   {:db/ident :user}])

(def motos
  [{:db/ident :moto1}
   {:db/ident :moto2}])

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

(def race-class [{:db/ident :class/description
                  :db/valueType :db.type/string
                  :db/cardinality :db.cardinality/one
                  :db/index true}])

(def rider [{:db/ident :rider/last-name
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one
             :db/index true}

            {:db/ident :rider/first-name
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one
             :db/index true}

            ;;primary key for riders for now
            {:db/ident :rider/race-number
             :db/valueType :db.type/long
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity}

            {:db/ident :rider/home-city
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one}

            {:db/ident :rider/home-state
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one}
            
            {:db/ident :rider/bikes
             :db/valueType :db.type/ref
             :db/cardinality :db.cardinality/many
             :db/isComponent true}
            
            ;;bike subtype
            {:db/ident :bike/make
             :db/valueType :db.type/ref
             :db/cardinality :db.cardinality/one}
            
            {:db/ident :bike/model
             :db/valueType :db.type/string
             :db/cardinality :db.cardinality/one}
            
            {:db/ident :bike/year
             :db/valueType :db.type/long
             :db/cardinality :db.cardinality/one}

            {:db/ident :bike/displacement
             :db/valueType :db.type/long
             :db/cardinality :db.cardinality/one}
            ;;;;;;;

            
            ])

(def race [{:db/ident :race/date
            :db/valueType :db.type/instant
            :db/cardinality :db.cardinality/one}
           {:db/ident :race/venue
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/one}
           {:db/ident :race/result
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/many
            :db/isComponent true}
           ;;result subtype
           {:db/ident :result/class
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/one}
           {:db/ident :result/moto
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/one}
           {:db/ident :result/finish
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/many
            :db/isComponent true}
           ;;finish sub-subtype
           {:db/ident :finish/place
            :db/valueType :db.type/long
            :db/cardinality :db.cardinality/one}
           {:db/ident :finish/racer
            :db/valueType :db.type/ref
            :db/cardinality :db.cardinality/one}])

(def user
  [{:db/ident :user/username
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/password
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/rider
    :db/valueType :db.type/ref
    :db/cardinality :db.cardinality/one}
   {:db/ident :user/admin
    :db/valueType :db.type/boolean
    :db/cardinality :db.cardinality/one}])

(def full-schema
  (concat
   race
   rider
   race-class
   track
   make
   globals
   types
   motos
   user))
