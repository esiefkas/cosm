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
            [cljs.core.async :refer [<!]]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [cosm.theme :as theme]
            [cosm.pages.home :as home]
            [cosm.pages.riders :as riders]
            [cosm.components.nav :as nav])
  (:import goog.History))

(defn footer []
  [:footer {:style {:background-color theme/menu-grey
                    :text-align "center"
                    :color "#FFFFFF"
                    :height "50px"
                    :width "100%"}}
   "Copyright Lambda Technologies and Colorado Supermoto Series 2018"])

(defn about-page []
  [:div "NOT HERE!!!!!!BALLS"])

;; -------------------------
;; Routes

(defonce page (atom #'home/page))

(defn current-page []
  [:div [ui/mui-theme-provider
         {:mui-theme
          (get-mui-theme
           {:palette {:primary-1-color theme/co-blue
                      :accent-1-color theme/co-yellow}
            :fontFamily "Encode Sans Expanded"})} 
         [:div
            [nav/main-nav]
            [@page]
            [footer]]]])

(secretary/defroute "/" []
  (reset! page #'home/page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

(secretary/defroute "/riders" []
  (reset! page #'riders/page))
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
