(ns cosm.pages.riders
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require
   [reagent.core :as r :refer [atom]]
   [cljs.core.async :refer [<!]]
   [cljs-react-material-ui.reagent :as ui]
   [cljs-http.client :as http]
   [cljsjs.material-ui]))

(def riders (atom []))

(defn update-riders! []
  (go (let [response (<! (http/get "/api/riders"))] 
        (reset! riders (:body response)))))

(defn rider->display-name [rider]
  (str (when (:rider/first-name rider)
         (str (:rider/first-name rider) " "))
       (:rider/last-name rider)))

(defn rider-row [rider]
  [ui/table-row
   [ui/table-row-column [:span {:style {:color "#000000"}} (:rider/race-number rider)]]
   [ui/table-row-column {} (rider->display-name rider)]])

(defn rider-table []
  (update-riders!)
  (fn []
    [:div {:style {:width "50%"
                   :color "#000000"}}
     [ui/table {:style {}}
      [ui/table-header {}
       [ui/table-row {}
        [ui/table-header-column {} "Race Number"]
        [ui/table-header-column {} "Name"]]]
      [ui/table-body {}
       (doall (map rider-row @riders))]]]))

(defn page []
  [:div {}
   [rider-table]])
