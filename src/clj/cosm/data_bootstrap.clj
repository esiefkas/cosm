(ns cosm.data-bootstrap
  (:require [datomic.api :as d]))

;;Going to try and figure out the data model in here
;;helpers

(defn add-type [type m]
  (assoc m :cosm/type type))

;;TODO: add brand icons
(def bike-makes
  [{:display-name "Honda" :db/ident :honda}
   {:display-name "Yamaha" :db/ident :yamaha}
   {:display-name "Kawasaki" :db/ident :kawasaki}
   {:display-name "Suzuki" :db/ident :suzuki}
   {:display-name "KTM" :db/ident :ktm}
   {:display-name "TM" :db/ident :tm}
   {:display-name "Beta" :db/ident :beta}
   {:display-name "Husqvarna" :db/ident :husqvarna}
   {:display-name "Husaberg" :db/ident :husaberg}
   {:display-name "SWM" :db/ident :swm}
   {:display-name "Aprilia" :db/ident :aprilia}
   {:display-name "Sherco" :db/ident :sherco}])

;;TODO: add track icons
(def tracks
  [{:db/ident :imi
    :display-name "IMI Motorsports"
    :location {:loc/address "5074 Summit Blvd" :loc/city "Dacono" :loc/state "CO" :loc/zip 80514}}
   {:db/ident :cns
    :display-name "Colorado National Speedway"
    :location {:loc/address "4281 Speedway Blvd" :loc/city "Dacono" :loc/state "CO" :loc/zip 80514}}
   {:db/ident :gjms
    :display-name "Grand Junction Motor Speedway"
    :location {:loc/address "3002 N I 70 Frontage Rd" :loc/city "Grand Junction" :loc/state "CO" :loc/zip 81504}}
   {:db/ident :pmp
    :display-name "Pueblo Motorsports Park"
    :location {:loc/address "3733 N Pueblo Blvd" :loc/city "Pueblo" :loc/state "CO" :loc/zip 81008}}
   {:db/ident :sbr
    :display-name "SBR Motorsports Park"
    :location {:loc/address "21430 Spencer Rd" :loc/city "Calhan" :loc/state "CO" :loc/zip 80808}}])

(def classes
  [{:db/ident :pro :display-name "Pro" :class/description "Unlimited displacement, unlimited modifications. Open to advanced skill levels."}
   {:db/ident :intermediate :display-name "Intermediate" :class/description "Open displacement with unlimited modifications. Open to any rider that has never podiumed the Open Pro class."}
   {:db/ident :novice :display-name "Novice" :class/description " Unlimited displacement with unlimited modifications.  Open to novice level riders only. Season ending bumps will be done on a mark system (or sooner based on promoter’s discretion)."}
   {:db/ident :250-class :display-name "250cc" :class/description "Open to all riders with a bike limit of 250cc or less."}
   {:db/ident :sportsman :display-name "Sportsman" :class/description "Open to factory street legal supermotos and bikes with stock wheels sizes and brakes.  DOT tires recommended.  Open to all skill levels.  This class is intended to allow to safely and inexpensively try supermoto.  Any rider or setup that undermines the intent of this class may be excluded at the promoters discretion."}
   {:db/ident :asphalt-a :display-name "Asphalt A" :class/description "Unlimited displacement, unlimited modifications. No dirt, asphalt only. (Intended for advanced level riders)" }
   {:db/ident :asphalt-b :display-name "Asphalt B" :class/description "Unlimited displacement, unlimited modifications. No dirt, asphalt only."}
   {:db/ident :asphalt-c :display-name "Asphalt C" :class/description "Unlimited displacement, unlimited modifications. No dirt, asphalt only. Open to novice level riders only. Season ending bumps will be done on a mark system (or sooner based on promoter’s discretion)." }
   {:db/ident :open-supermoto :display-name "Open Supermoto" :class/description "Single race format open to Intermediate and Pro Riders only. Unlimited modifications and any engine size above 125 2 stroke and 250 4 stroke."}
   {:db/ident :adult-mini :display-name "Adult Mini" :class/description "This class is designed for adults of 14+ years. Unlimited modification for up to 85cc two-stroke Single, up to 160cc liquid cooled four-stroke Single, up to 230cc Air Cooled four-stroke Single. No dirt, asphalt only."}
   {:db/ident :youth-mini :display-name "Youth Mini" :class/description "This class is designed for children under the age of 14. Unlimited modification for up to 85cc two-stroke Single, up to 160cc liquid cooled four-stroke Single, up to 230cc Air Cooled four-stroke Single."}])

;; TODO: add facebook link and profile pic
;;Machines wfill be created implicitly with each rider
(def riders
  [{:rider/first-name "Eric"
    :rider/last-name "Siefkas"
    :rider/race-number 955
    :rider/home-city "Lafayette"
    :rider/home-state "CO"
    :rider/bikes [{:bike/make :tm
                   :bike/model "SMX 450Fi"
                   :bike/year 2014}]}
   
   {:rider/first-name "Tim"
    :rider/last-name "Velasquez"
    :rider/race-number 838
    :rider/bikes [{:bike/make :husqvarna
                   :bike/model "FS 450"}]
    :rider/home-state "CO"}
   
   {:rider/first-name "Dawson"
    :rider/last-name "Schieffer"
    :rider/race-number 28
    :rider/bikes [{:bike/make :suzuki}]
    :rider/home-city "Sturgis"
    :rider/home-state "SD"}
   
   {:rider/first-name "Chuck"
    :rider/last-name "Hopper"
    :rider/race-number 151
    :rider/bikes [{:bike/make :husqvarna
                   :bike/model "FC 450"}]
    :rider/home-state "CO"}

   {:rider/first-name "Randy"
    :rider/last-name "Hopper"
    :rider/race-number 141
    :rider/bikes [{:bike/make :husqvarna
                   :bike/model "FC 450"}]
    :rider/home-state "CO"}

   {:rider/first-name "Michael"
    :rider/last-name "Henao"
    :rider/race-number 001
    :rider/bikes [{:bike/make :ktm}]
    :rider/home-state "CO"}

   {:rider/first-name "Flint"
    :rider/last-name "Velasquez"
    :rider/race-number 868
    :rider/bikes [{:bike/make :honda
                   :bike/model "CRF450R"}]
    :rider/home-state "CO"}

   {:rider/last-name "Grace"
    :rider/race-number 97}

   {:rider/first-name "Mike"
    :rider/last-name "Eller"
    :rider/race-number 47
    :rider/bikes [{:bike/make :yamaha
                   :bike/model "YZ450F"}]
    :rider/home-state "CO"}

   {:rider/first-name "Sean"
    :rider/last-name "Butterman"
    :rider/race-number 109
    :rider/bikes [{:bike/make :husqvarna
                   :bike/model "FC 450"}]
    :rider/home-state "NM"}

   {:rider/first-name "Thomas"
    :rider/last-name "Harrison"
    :rider/race-number 101
    :rider/bikes [{:bike/make :yamaha
                   :bike/model "YZ 450"}]
    :rider/home-state "YZ"}

   {:rider/first-name "Richie"
    :rider/last-name "Soroka"
    :rider/race-number 19
    :rider/bikes [{:bike/make :kawasaki
                   :bike/model "KX 450F"}]}

   {:rider/last-name "Brancom"
    :rider/race-number 20}

   {:rider/last-name "Hines"
    :rider/race-number 130}

   {:rider/last-name "Beck"
    :rider/race-number 222}
   
   {:rider/first-name "Koy"
    :rider/last-name "Baker"
    :rider/race-number 156
    :rider/bikes [{:bike/make :husaberg
                   :bike/model "Badass custom 600"}]}

   {:rider/first-name "Sean"
    :rider/last-name "Colleary"
    :rider/race-number 46
    :rider/bikes [{:bike/make :yamaha
                   :bike/model "YZ450F"}]}

   {:rider/first-name "David"
    :rider/last-name "Hetzler"
    :rider/race-number 24}

   {:rider/first-name "Mark"
    :rider/last-name "Applegate"
    :rider/race-number 49}

   {:rider/last-name "Refvem"
    :rider/race-number 250}

   {:rider/first-name "Grant"
    :rider/last-name "Fuller"
    :rider/race-number 48
    :rider/bikes [{:bike/make :honda
                   :bike/model "CRF450R"}]}

   {:rider/first-name "Justin"
    :rider/last-name "Monnett"
    :rider/race-number 63
    :rider/bikes [{:bike/make :suzuki}]}

   {:rider/first-name "Brandon"
    :rider/last-name "Rost"
    :rider/race-number 88
    :rider/bikes [{:bike/make :honda}]}

   {:rider/first-name "CJ"
    :rider/last-name "Aguilera"
    :rider/race-number 35
    :rider/bikes [{:bike/make :kawasaki}]}

   {:rider/first-name "Matt"
    :rider/last-name "Miner"
    :rider/race-number 424
    :rider/bikes [{:bike/make :honda}]}

   {:rider/first-name "Brandon"
    :rider/last-name "Kosut"
    :rider/race-number 100
    :rider/bikes [{:bike/make :ktm}]}

   {:rider/last-name "Pagel"
    :rider/race-number 27}

   {:rider/last-name "Drake"
    :rider/race-number 137}

   {:rider/last-name "Bandel"
    :rider/race-number 15}

   {:rider/first-name "Joe"
    :rider/last-name "Brady"
    :rider/race-number 502
    :rider/bikes [{:bike/make :ktm}]}

   {:rider/last-name "Holland"
    :rider/race-number 445}

   {:rider/last-name "Slavik"
    :rider/race-number 427}

   {:rider/first-name "Lindsey"
    :rider/last-name "Richter"
    :rider/race-number 13
    :rider/bikes [{:bike/make :ktm}]}

   {:rider/first-name "Stevo"
    :rider/last-name "Weaver"
    :rider/race-number 512
    :rider/bikes [{:bike/make :ktm}]}

   {:rider/first-name "Tyler"
    :rider/last-name "Givens"
    :rider/race-number 72
    :rider/bikes [{:bike/make :honda}]}

   {:rider/first-name "Max"
    :rider/last-name "Lassiter"
    :rider/race-number 121
    :rider/bikes [{:bike/make :honda}]}

   ;;bumped number by one
   {:rider/first-name "Dan"
    :rider/last-name "Raygor"
    :rider/race-number 122
    :rider/bikes [{:bike/make :honda}]}

   {:rider/first-name "Jason"
    :rider/last-name "Marx"
    :rider/race-number 0
    :rider/bikes [{:bike/make :honda}]}

   {:rider/last-name "Barfoot"
    :rider/race-number 273
    :rider/bikes [{:bike/make :yamaha}]}

   {:rider/first-name "Shane"
    :rider/last-name "Brisendine"
    :rider/race-number 325
    :rider/bikes [{:bike/make :yamaha}]}

   {:rider/last-name "Ross"
    :rider/race-number 124
    :rider/bikes [{:bike/make :yamaha}]}

   {:rider/last-name "Brittenham"
    :rider/race-number 176
    :rider/bikes [{:bike/make :yamaha}]}

   ;;bumped by 1 
   {:rider/last-name "Zieg"
    :rider/race-number 125
    :rider/bikes [{:bike/make :yamaha}]}

   ;;bumped by 1
   {:rider/last-name "Pope"
    :rider/race-number 36
    :rider/bikes [{:bike/make :kawasaki}]}

   {:rider/first-name "Josh"
    :rider/last-name "Bourque"
    :rider/race-number 444
    :rider/bikes [{:bike/make :yamaha}]}

   {:rider/first-name "Sebstien"
    :rider/last-name "Graf"
    :rider/race-number 94
    :rider/bikes [{:bike/make :ktm}]}

   {:rider/last-name "Minor"
    :rider/race-number 242
    :rider/bikes [{:bike/make :yamaha}]}   
   ;; TODO: add youth mini
   ])

(def races
  [;;2017 races
   {:race/date #inst "2017-04-23"
    :race/venue :imi
    :race/result [{:result/class :pro
                    :result/moto :moto1
                    :result/finish [{:finish/place 1 :finish/racer [:rider/race-number 838]}
                                    {:finish/place 2 :finish/racer [:rider/race-number 28]}
                                    {:finish/place 3 :finish/racer [:rider/race-number 151]}
                                    {:finish/place 4 :finish/racer [:rider/race-number 001]}
                                    {:finish/place 5 :finish/racer [:rider/race-number 868]}
                                    {:finish/place 6 :finish/racer [:rider/race-number 97]}
                                    {:finish/place 7 :finish/racer [:rider/race-number 141]}
                                    {:finish/place 8 :finish/racer [:rider/race-number 101]}
                                    {:finish/place 9 :finish/racer [:rider/race-number 955]}
                                    {:finish/place 10 :finish/racer [:rider/race-number 19]}
                                    {:finish/place 11 :finish/racer [:rider/race-number 222]}
                                    {:finish/place 12 :finish/racer [:rider/race-number 130]}]}
                  {:result/class :pro
                   :result/moto :moto2
                   :result/finish [{:finish/place 1 :finish/racer [:rider/race-number 838]}
                                   {:finish/place 2 :finish/racer [:rider/race-number 28]}
                                   {:finish/place 3 :finish/racer [:rider/race-number 001]}
                                   {:finish/place 4 :finish/racer [:rider/race-number 151]}
                                   {:finish/place 5 :finish/racer [:rider/race-number 868]}
                                   {:finish/place 6 :finish/racer [:rider/race-number 97]}
                                   {:finish/place 7 :finish/racer [:rider/race-number 141]}
                                   {:finish/place 8 :finish/racer [:rider/race-number 101]}
                                   {:finish/place 9 :finish/racer [:rider/race-number 19]}
                                   {:finish/place 10 :finish/racer [:rider/race-number 955]}
                                   {:finish/place 11 :finish/racer [:rider/race-number 222]}
                                   {:finish/place 12 :finish/racer [:rider/race-number 130]}]}
                  {:result/class :asphalt-c
                   :result/moto :moto1
                   :result/finish [{:finish/place 1 :finish/racer [:rider/race-number 273]}
                                   {:finish/place 2 :finish/racer [:rider/race-number 35]}
                                   {:finish/place 3 :finish/racer [:rider/race-number 15]}
                                   {:finish/place 4 :finish/racer [:rider/race-number 137]}
                                   {:finish/place 5 :finish/racer [:rider/race-number 63]}
                                   {:finish/place 6 :finish/racer [:rider/race-number 88]}
                                   {:finish/place 7 :finish/racer [:rider/race-number 27]}
                                   {:finish/place 8 :finish/racer [:rider/race-number 444]}
                                   {:finish/place 9 :finish/racer [:rider/race-number 124]}
                                   {:finish/place 10 :finish/racer [:rider/race-number 19]}
                                   {:finish/place 11 :finish/racer [:rider/race-number 222]}]}
                  {:result/class :asphalt-c
                   :result/moto :moto2
                   :result/finish [{:finish/place 1 :finish/racer [:rider/race-number 273]}
                                   {:finish/place 2 :finish/racer [:rider/race-number 137]}
                                   {:finish/place 3 :finish/racer [:rider/race-number 63]}
                                   {:finish/place 4 :finish/racer [:rider/race-number 35]}
                                   {:finish/place 5 :finish/racer [:rider/race-number 15]}
                                   {:finish/place 6 :finish/racer [:rider/race-number 27]}
                                   {:finish/place 7 :finish/racer [:rider/race-number 124]}
                                   {:finish/place 8 :finish/racer [:rider/race-number 101]}
                                   {:finish/place 9 :finish/racer [:rider/race-number 19]}
                                   {:finish/place 10 :finish/racer [:rider/race-number 88]}
                                   {:finish/place 11 :finish/racer [:rider/race-number 444]}]}]}
   {:race/date #inst "2017-05-28"
    :race/venue :imi}
   {:race/date #inst "2017-06-18"
    :race/venue :imi}
   {:race/date #inst "2017-07-02"
    :race/venue :gjms}
   {:race/date #inst "2017-08-13"
    :race/venue :sbr}
   {:race/date #inst "2017-09-24"
    :race/venue :imi}
   ;;upcoming 2018 races
   {:race/date #inst "2018-03-25"
    :race/venue :pmp}
   {:race/date #inst "2018-05-27"
    :race/venue :gjms}
   {:race/date #inst "2018-06-17"
    :race/venue :imi}
   {:race/date #inst "2018-07-15"
    :race/venue :cns}
   {:race/date #inst "2018-08-26"
    :race/venue :imi}
   {:race/date #inst "2018-09-23"
    :race/venue :imi}])

(def users
  [{:user/username "channelzero"
    :user/password "bcrypt+sha512$26ad41d2ed71eadfc388cf145f21a8f0$12$b6219bfa72ac6f0fcce770e06d105d239c820e740f1bfdfd"
    :user/admin true
    :user/rider [:rider/race-number 955]}
   {:user/username "userMcUserFace"
    :user/password "bcrypt+sha512$aeb3bc27719d311a4fca36968aa8c30c$12$7d7fdbaeca012ca3c2ea268ff98f3dc0a46716a382e85c62"}])
