(ns cosm.data-bootstrap)

;;Going to try and figure out the data model in here

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
  [{:name {:first "Eric"
           :last "Siefkas"}
    :race-number 955
    :bikes [{:make :tm
             :model "SMX 450Fi"
             :year 2014}]
    :home {:city "Lafayette"
           :state "CO"}}
   
   {:name {:first "Tim"
           :last "Velasquez"}
    :race-number 838
    :bikes [{:make :husqvarna
             :model "FS 450"}]
    :home {:state "CO"}}
   
   {:name {:first "Dawson"
           :last "Schieffer"}
    :race-number 28
    :bikes [{:make :suzuki}]
    :home {:city "Sturgis"
           :state "SD"}}
   
   {:name {:first "Chuck"
           :last "Hopper"}
    :race-number 151
    :bikes [{:make :husqvarna
             :model "FC 450"}]
    :home {:state "CO"}}

   {:name {:first "Randy"
           :last "Hopper"}
    :race-number 141
    :bikes [{:make :husqvarna
             :model "FC 450"}]
    :home {:state "CO"}}

   {:name {:first "Michael"
           :last "Henao"}
    :race-number 001
    :bikes [{:make :ktm}]
    :home {:state "CO"}}

   {:name {:first "Flint"
           :last "Velasquez"}
    :race-number 868
    :bikes [{:make :honda
             :model "CRF450R"}]
    :home {:state "CO"}}

   {:name {:last "Grace"}
    :race-number 97}

   {:name {:first "Mike"
           :last "Eller"}
    :race-number 47
    :bikes [{:make :yamaha
             :model "YZ450F"}]
    :home {:state "CO"}}

   {:name {:first "Sean"
           :last "Butterman"}
    :race-number 109
    :bikes [{:make :husqvarna
             :model "FC 450"}]
    :home {:state "NM"}}

   {:name {:first "Thomas"
           :last "Harrison"}
    :race-number 101
    :bikes [{:make :yamaha
             :model "YZ 450"}]
    :home {:state "YZ"}}

   {:name {:first "Richie"
           :last "Soroka"}
    :race-number 19
    :bikes [{:make :kawasaki
             :model "KX 450F"}]}

   {:name {:last "Brancom"}
    :race-numer 20}

   {:name {:last "Hines"}
    :race-numer 130}

   {:name {:last "Beck"}
    :race-numer 222}

   {:name {:first "Koy"
           :last "Baker"}
    :race-number 156
    :bikes [{:make :husaberg
             :model "Badass custom 600"}]}

   {:name {:first "Sean"
           :last "Colleary"}
    :race-number 46
    :bikes [{:make :yamaha
             :model "YZ450F"}]}

   {:name {:first "David"
           :last "Hetzler"}
    :race-number 24}

   {:name {:first "Mark"
           :last "Applegate"}
    :race-number 49}

   {:name {:last "Refvem"}
    :race-number 250}

   {:name {:first "Grant"
           :last "Fuller"}
    :race-number 48
    :bikes [{:make :honda
             :model "CRF450R"}]}

   {:name {:first "Justin"
           :last "Monnett"}
    :race-number 63
    :bikes [{:make :suzuki}]}

   {:name {:first "Brandon"
           :last "Rost"}
    :race-number 88
    :bikes [{:make :honda}]}

   {:name {:first "CJ"
           :last "Aguilera"}
    :race-number 35
    :bikes [{:make :kawasaki}]}

   {:name {:first "Matt"
           :last "Miner"}
    :race-number 424
    :bikes [{:make :honda}]}

   {:name {:first "Brandon"
           :last "Kosut"}
    :race-number 100
    :bikes [{:make :ktm}]}

   {:name {:last "Pagel"}
    :race-number 27}

   {:name {:last "Drake"}
    :race-number 137}

   {:name {:last "Bandel"}
    :race-number 15}

   {:name {:first "Joe"
           :last "Brady"}
    :race-number 502
    :bikes [{:make :ktm}]}

   {:name {:last "Holland"}
    :race-number 444}

   {:name {:last "Slavik"}
    :race-number 427}

   {:name {:first "Lindsey"
           :last "Richter"}
    :race-number 48
    :bike {:make :ktm}}

   {:name {:first "Stevo"
           :last "Weaver"}
    :race-number 512
    :bike {:make :ktm}}

   {:name {:first "Tyler"
           :last "Givens"}
    :race-number 72
    :bike {:make :honda}}

   {:name {:first "Max"
           :last "Lassiter"}
    :race-number 121
    :bike {:make :honda}}

   {:name {:first "Dan"
           :last "Raygor"}
    :race-number 121
    :bike {:make :honda}}

   {:name {:first "Jason"
           :last "Marx"}
    :race-number 0
    :bike {:make :honda}}

   {:name {:first "Shane"
           :last "Brisendine"}
    :race-number 325
    :bike {:make :yamaha}}

   {:name {:last "Ross"}
    :race-number 124
    :bike {:make :yamaha}}

   {:name {:last "Brittenham"}
    :race-number 176
    :bike {:make :yamaha}}

   {:name {:last "Zieg"}
    :race-number 124
    :bike {:make :yamaha}}

   {:name {:last "Pope"}
    :race-number 35
    :bike {:make :kawasaki}}

   {:name {:first "Josh"
           :last "Bourque"}
    :race-number 444
    :bike {:make :yamaha}}

   {:name {:first "Sebstien"
           :last "Graf"}
    :race-number 94
    :bike {:make :ktm}}

   {:name {:last "Minor"}
    :race-number 242
    :bike {:make :yamaha}}   
;; TODO: add youth mini
])

(def races
  [;;2017 races
   {:date #inst "2017-04-23"
    :venue :imi
    :result {:pro {:moto1 [{:place 1 :racer [:race-number 838]}
                           {:place 2 :racer [:race-number 28]}
                           {:place 3 :racer [:race-number 151]}
                           {:place 4 :racer [:race-number 001]}
                           {:place 5 :racer [:race-number 868]}
                           {:place 6 :racer [:race-number 97]}
                           {:place 7 :racer [:race-number 141]}
                           {:place 8 :racer [:race-number 101]}
                           {:place 9 :racer [:race-number 955]}
                           {:place 10 :racer [:race-number 19]}
                           {:place 11 :racer [:race-number 222]}
                           {:place 12 :racer [:race-numer 130]}]
                   :moto2 [{:place 1 :racer [:race-number 838]}
                           {:place 2 :racer [:race-number 28]}
                           {:place 3 :racer [:race-number 001]}
                           {:place 4 :racer [:race-number 151]}
                           {:place 5 :racer [:race-number 868]}
                           {:place 6 :racer [:race-number 97]}
                           {:place 7 :racer [:race-number 141]}
                           {:place 8 :racer [:race-number 101]}
                           {:place 9 :racer [:race-number 19]}
                           {:place 10 :racer [:race-number 955]}
                           {:place 11 :racer [:race-number 222]}
                           {:place 12 :racer [:race-numer 130]}]}
             :asphalt-c {:moto1 [{:place 1 :racer [:race-number 273]}
                                 {:place 2 :racer [:race-number 35]}
                                 {:place 3 :racer [:race-number 15]}
                                 {:place 4 :racer [:race-number 137]}
                                 {:place 5 :racer [:race-number 63]}
                                 {:place 6 :racer [:race-number 88]}
                                 {:place 7 :racer [:race-number 27]}
                                 {:place 8 :racer [:race-number 444]}
                                 {:place 9 :racer [:race-number 124]}
                                 {:place 10 :racer [:race-number 19]}
                                 {:place 11 :racer [:race-number 222]}]
                         :moto2 [{:place 1 :racer [:race-number 273]}
                                 {:place 2 :racer [:race-number 137]}
                                 {:place 3 :racer [:race-number 63]}
                                 {:place 4 :racer [:race-number 35]}
                                 {:place 5 :racer [:race-number 15]}
                                 {:place 6 :racer [:race-number 27]}
                                 {:place 7 :racer [:race-number 124]}
                                 {:place 8 :racer [:race-number 101]}
                                 {:place 9 :racer [:race-number 19]}
                                 {:place 10 :racer [:race-number 88]}
                                 {:place 11 :racer [:race-number 444]}]}
             :}}
   {:date #inst "2017-05-28"
    :venue :imi}
   {:date #inst "2017-06-18"
    :venue :imi}
   {:date #inst "2017-07-02"
    :venue :gjms}
   {:date #inst "2017-08-13"
    :venue :sbr}
   {:date #inst "2017-09-24"
    :venue :imi}
   ;;upcoming 2018 races
   {:date #inst "2018-03-25"
    :venue :pmp}
   {:date #inst "2018-05-27"
    :venue :gjms}
   {:date #inst "2018-06-17"
    :venue :imi}
   {:date #inst "2018-07-15"
    :venue :cns}
   {:date #inst "2018-08-26"
    :venue :imi}
   {:date #inst "2018-09-23"
    :Venue :imi}])
