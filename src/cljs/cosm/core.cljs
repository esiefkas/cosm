(ns cosm.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [reagent.core :as r :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [cljsjs.material-ui]
              [cljs-react-material-ui.core :refer [get-mui-theme color]]
              [cljs-react-material-ui.reagent :as ui]
              [cljs-react-material-ui.icons :as ic]
              [cljs-http.client :as http]
              [cljs.core.async :refer [<!]]))


(defn call-backend-test []
  )

(defn print-a-thing []
  (println "herrroo"))
;; -------------------------
;; Views

(def co-blue "#102E82")
(def co-yellow "#F6D047")
(def co-red "#BF2C34")
(def menu-grey "#212121")

;;TODO: Fix this image 
(defn logo []
  [:div {:style {:width "223px"
                 :height "65px"
                 :background-color "#FFFFFF"}}
   [:img {:src "https://static.wixstatic.com/media/116575_cc70b86da0714afb9c37f3baac6a5f5a~mv2.png/v1/fill/w_1200,h_349,al_c,lg_1/116575_cc70b86da0714afb9c37f3baac6a5f5a~mv2.png"
          :height "65px"
          :style {:padding-top "10px"}}]])

(defn login-button []
  [ui/raised-button
   {:style
    {:margin "auto"
     :min-width "80px"
     :width "30%"
     :height "36px"}}
   [:div {:style {:display "flex"
                  :align-items "center"
                  :height "36px"}}
    [:i {:class "material-icons"} "account_circle"]
    "Log In"]])

(defn register-button []
  [ui/raised-button  {:secondary true
                      :style {:margin "auto" 
                              :min-width "80px"
                              :height "36px"
                              :width "30%"}}
   [:div {:style {:display "flex"
                  :align-items "center"
                  :height "36px"}}
    [:i {:class "material-icons"} "assignment"]
    "Sign Up"]])

(defn main-menu-button-ph [name]
  [ui/flat-button {:style {:height "100%"
                           :width "100%"}}
   name])

(defn logged-out-control-panel []
  [ui/toolbar-group {:style {:width "15%"
                             :font-size "0.66em"}}
   [:div {:style {:height "85px"
                  :width "100%" 
                  :background-color co-blue
                  :text-color "#FFFFFF"
                  :text-align "center"
                  :display "flex"
                  :padding-left "5%"
                  :padding-right "5%"}}
    [login-button]
    [register-button]]])

(defn main-nav []
  [ui/toolbar {:title "Colorado Supermoto"
               :style {:width "90%"
                       :height "85px"
                       :margin "auto"
                       :z-index "1000"
                       :background-color menu-grey
                       :padding-right 0}}
   [logo]
   [ui/toolbar-group
    {:style {:width "55%"}}
    [main-menu-button-ph "SCHEDULE"]
    [main-menu-button-ph "RULE BOOK"]
    [main-menu-button-ph "CLASSES"]
    [main-menu-button-ph "TRACKS"]
    [main-menu-button-ph "STANDINGS"]]
   [logged-out-control-panel]])

(defn stepper-example []
  (let [active-step (atom 0)]
    (fn []
      [:div [ui/stepper {:active-step @active-step}
             [ui/step {:on-click #(reset! active-step 0)} [ui/step-label "STEP1"]]
             [ui/step {:on-click #(reset! active-step 1)} [ui/step-label "STEP2"]]
             [ui/step {:on-click #(reset! active-step 2)} [ui/step-label "STEP3"]]]])))

(defn news-reel-inset []
  [ui/paper {:z-depth 5
             :style {:height "20%"
                     :width "90%"
                     :margin-bottom "20px"
                     :text-align "center" 
                     :text-color "#000000"}}
   [:div {:style {:padding-top "15px"
                   :color "#000000"}} "Round 1 Registration (Pueblo) Open"
    [ui/raised-button {:secondary true
                       :style {:margin "20px"
                               :margin-top "0px"}} "REGISTER NOW"]]])

(defn front-page-race-item [name image-url date]
  [ui/paper {:style {:color "#000000"
                     :height "200px"
                     :width "13%"
                     :display "inline-block"
                     :vertical-align "middle"
                     :margin "20px"}
             :z-depth 2}
   [:div name]
   [:img {:src image-url
          :width "90%"
          :style {:padding-top "10px"}}]
   [:div date]
   [ui/raised-button {:secondary true
                      :style {}}
    "Details"]])

(defn front-page-schedule []
  [:div
   {:style {:height "250px"
            :text-align "center"}} 
   [front-page-race-item "Round 1" "http://carovod.ru/ring/pueblomotorsportspark.jpg" "3/25"]
   [front-page-race-item "Round 2" "http://grandjunctionmotorspeedway.com/wp-content/uploads/2017/01/GJMSheaderlogo.png" "5/27"]
   [front-page-race-item "Round 3" "http://imimotorsports.com/images/banner_imimotorsports_txt.jpg" "6/17"]
   [front-page-race-item "Round 4" "http://www.coloradospeedway.com/wp-content/uploads/2014/01/CNS-Logo-Phone-Number.png" "7/15"]
   [front-page-race-item "Round 5" "http://imimotorsports.com/images/banner_imimotorsports_txt.jpg" "8/26"]
   [front-page-race-item "Round 6" "http://imimotorsports.com/images/banner_imimotorsports_txt.jpg" "9/23"]])

(defn news-reel []
  [:div
   [:img {:src "https://scontent.fapa1-1.fna.fbcdn.net/v/t31.0-8/21993034_1733862726655647_7860000715063099826_o.jpg?oh=ec21041445bb691a640a69520ea9d16a&oe=5B0B1614"
          :style {:margin-top "-300px"
                  :z-index "-10000"
                  :width "100%"
                  :position "absolute"}}]
   [:div {:style {:display "flex"
                  :align-items "flex-end"
                  :justify-content "center"
                  :height "400px"}}
    [news-reel-inset]]])

(defn footer []
  [:footer {:style {:background-color menu-grey
                    :text-align "center"
                    :color "#FFFFFF"
                    :height "50px"
                    :width "100%"}} "Copyright Lambda Technologies and Colorado Supermoto Series 2018"])

;;BACK END TEST
(def riders (atom []))

(defn update-riders! []
  (go (let [response (<! (http/get "/riders"))] 
        (reset! riders (:body response)))))

(defn back-end-data-test []
  (update-riders!)
  (fn []
    [:div (str @riders)])
  )

(defn home-page []
  [ui/mui-theme-provider
   {:mui-theme
    (get-mui-theme
     {:palette {:text-color (color :white)
                :primary-1-color co-blue
                :accent-1-color co-yellow}
      :fontFamily "Encode Sans Expanded"})} 
   [:div
    [main-nav]
    [news-reel]
    [front-page-schedule]
    ]])

(defn about-page []
  [:div "NOT HERE!"])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [:div [@page]
   [footer]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
