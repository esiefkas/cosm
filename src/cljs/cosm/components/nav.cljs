(ns cosm.components.nav
  (:require [accountant.core :as accountant]
            [cljs-react-material-ui.reagent :as ui]
            [cljsjs.material-ui]
            [cosm.theme :as theme]))

(defn logo []
  [:div {:style {:width "223px"
                 :height "65px"
                 :background-color "#FFFFFF"
                 :cursor "pointer"}
         :on-click (fn [_] (accountant/navigate! "/"))}
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
  [ui/flat-button {:style {:color "#FFFFFF"
                           :height "100%"
                           :width "100%"}}
   name])

(defn logged-out-control-panel []
  [ui/toolbar-group {:style {:width "15%"
                             :font-size "0.66em"}}
   [:div {:style {:height "85px"
                  :width "100%" 
                  :background-color theme/co-blue
                  :text-color "#FFFFFF"
                  :text-align "center"
                  :display "flex"
                  :padding-left "5%"
                  :padding-right "5%"}}
    [login-button]
    [register-button]]])

(defn riders-main-menu-button []
  [ui/flat-button {:style {:color "#FFFFFF"
                           :height "100%"
                           :width "100%"}
                   :on-click (fn [_] (accountant/navigate! "/riders"))}
   "RIDERS"])

(defn main-nav []
  [ui/toolbar {:title "Colorado Supermoto"
               :style {:width "90%"
                       :height "85px"
                       :margin "auto"
                       :z-index "1000"
                       :background-color theme/menu-grey
                       :padding-right 0}}
   [logo]
   [ui/toolbar-group
    {:style {:width "55%"}}
    [main-menu-button-ph "RACES"]
    [main-menu-button-ph "RULE BOOK"]
    [main-menu-button-ph "CLASSES"]
    [main-menu-button-ph "TRACKS"]
    [riders-main-menu-button]]
   [logged-out-control-panel]])
