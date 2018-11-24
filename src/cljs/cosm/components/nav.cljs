(ns cosm.components.nav
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [accountant.core :as accountant]
            [cljs-react-material-ui.reagent :as ui]
            [cljsjs.material-ui]
            [cosm.theme :as theme]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<!]]
            [reagent.core :as r :refer [atom]]))

(defn logo []
  [:div {:style {:width "223px"
                 :height "65px"
                 :background-color "#FFFFFF"
                 :cursor "pointer"}
         :on-click (fn [_] (accountant/navigate! "/"))}
   [:img {:src "https://static.wixstatic.com/media/116575_cc70b86da0714afb9c37f3baac6a5f5a~mv2.png/v1/fill/w_1200,h_349,al_c,lg_1/116575_cc70b86da0714afb9c37f3baac6a5f5a~mv2.png"
          :height "65px"
          :style {:padding-top "10px"}}]])

(defonce login-dialog-open? (atom false))

(defn login-button []
  [ui/raised-button
   {:on-click (fn [_] (reset! login-dialog-open? true))
    :style
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



(defn riders-main-menu-button []
  [ui/flat-button {:style {:color "#FFFFFF"
                           :height "100%"
                           :width "100%"}
                   :on-click (fn [_] (accountant/navigate! "/riders"))}
   "RIDERS"])

(defn schedule-main-menu-button []
  [ui/flat-button {:style {:color "#FFFFFF"
                           :height "100%"
                           :width "100%"}
                   :on-click (fn [_] (accountant/navigate! "/schedule"))}
   "SCHEDULE"])

(defonce current-user (atom nil))

(defn get-current-user! []
  (go (let [response (<! (http/get "/api/user/current"))]
        (if (= 200 (:status response))
          (reset! current-user (:body response))
          (do (println response)
              (reset! current-user nil))))))

(defn logout! []
  (go
    (let [response (<! (http/post "/api/logout"))]
      (if (= 200 (:status response))
          (do (js/alert "logout success")
              (get-current-user!))
          (js/alert "logout fail")))))

(defn log-in! [username password]
  (go
    (let [response (<! (http/post "/api/login"
                                  {:transit-params {:username username
                                                    :password password}}))]
      (println response)
        (if (= 200 (:status response))
          (do (js/alert "login success")
              (get-current-user!)
              (reset! login-dialog-open? false))
          (js/alert "login fail")))))

(defn log-out-button []
  [ui/raised-button
   {:on-click (fn [_]
                (logout!)
                (get-current-user!))
    :style
    {:margin "auto"
     :min-width "80px"
     :width "30%"
     :height "36px"}}
   [:div {:style {:display "flex"
                  :align-items "center"
                  :height "36px"}}
    [:i {:class "material-icons"} "account_circle"]
    "Log Out"]])


(defn login-dialog []
  (let [username (atom "")
        password (atom "")] 
    [ui/dialog {:open @login-dialog-open?
                :title "Sign In"
                :on-request-close (fn [_] (reset! login-dialog-open? false))
                :content-style {:width "35%"}}
     [:div
      [:div [ui/text-field {:hint-text "Username"
                            :on-change #(reset! username (-> % .-target .-value))}]]
      [:div [ui/text-field {:hint-text "Password"
                            :type "password"
                            :on-change #(reset! password (-> % .-target .-value))}]]
      [:div [ui/raised-button {:primary true
                               :on-click (fn [_] (log-in! @username @password))} [:span {:style {:color "#FFFFFF"}} "Sign In"]]
       [ui/raised-button {:style {:margin-left "10px"}
                          :on-click (fn [_] (reset! login-dialog-open? false))} "Cancel"]]]]))

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

(defn control-panel [current-user]
  [ui/toolbar-group {:style {:width "15%"
                             :font-size "0.66em"}}
   [:div {:style {:height "85px"
                  :width "100%" 
                  :background-color theme/co-blue
                  :color "#FFFFFF"
                  :text-align "center"
                  :display "flex"
                  :padding-left "5%"
                  :padding-right "5%"}}
    

    [log-out-button]
    [:div (str "Welcome" (:username current-user))]]])

(defn main-nav []
  (get-current-user!)
  (fn []
    [:div [ui/toolbar {:title "Colorado Supermoto"
                       :style {:width "90%"
                               :height "85px"
                               :margin "auto"
                               :z-index "1000"
                               :background-color theme/menu-grey
                               :padding-right 0}}
           [logo]
           [ui/toolbar-group
            {:style {:width "55%"}}
            [schedule-main-menu-button]
            [main-menu-button-ph "RESULTS"]
            [main-menu-button-ph "CLASSES"]
            [main-menu-button-ph "TRACKS"]
            [main-menu-button-ph "ABOUT"]
            [riders-main-menu-button]
            ]
           (if-let [cu @current-user]
             [control-panel cu]
             [logged-out-control-panel])]
     [login-dialog]]))
