(ns cosm.pages.home
  (:require [reagent.core :as r :refer [atom]]
            [cljs-react-material-ui.reagent :as ui]
            [cljsjs.material-ui]))

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
   [:img {:src "https://scontent.fapa1-2.fna.fbcdn.net/v/t31.0-8/21993034_1733862726655647_7860000715063099826_o.jpg?_nc_cat=0&_nc_eui2=AeFL1CYg5qB0lnAWCJXEahGzYGiXKEH1K-cuonjrIKkSVgfHBLfvGqGBirflGvSPere5c3M2eMH6HOHEwUQsBxtAnSidQ8haVT2mO7ox1PUcXQ&oh=5c9f29908888ba74389e946d93992932&oe=5BD0D714"
          :style {:margin-top "-300px"
                  :z-index "-10000"
                  :width "100%"
                  :position "absolute"}}]
   [:div {:style {:display "flex"
                  :align-items "flex-end"
                  :justify-content "center"
                  :height "400px"}}
    [news-reel-inset]]])

(defn page []
  [:div
   [news-reel]
   [front-page-schedule]])
