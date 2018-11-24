(ns cosm.pages.schedule
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [reagent.core :as r :refer [atom]]
   [cljs.core.async :refer [<!]]
   [cljs-react-material-ui.reagent :as ui]
   [cljs-http.client :as http]
   [cljsjs.material-ui]))

(defonce races (atom []))

(defn update-schedule! []
  (go (let [response (<! (http/get "/api/races"
                                   {:query-params
                                    {:year 2018}}))] 
        (reset! races (:body response)))))

(defn schedule-row [round-num race]
  [ui/table-row
   [ui/table-row-column {:style {:width "10%"}} (inc round-num)]
   [ui/table-row-column [:span {:style {:color "#000000"}}
                         (str (:race/date race))]]
   [ui/table-row-column {} (get-in race [:race/venue :display-name])]])

(defn schedule-table []
  (update-schedule!)
  (fn []
    [:div {:style {:width "100%"
                   :color "#000000"}}
     [ui/table {:style {}}
      [ui/table-header {}
       [ui/table-row {}
        [ui/table-header-column {:style {:width "10%"}} "Round"]
        [ui/table-header-column {} "Date"]
        [ui/table-header-column {} "Venue"]]]
      [ui/table-body {}
       (doall (map-indexed schedule-row @races))]]]))

(defn page []
  [:div {}
   [schedule-table]])
