(ns cosm.handler
  (:require [compojure.core :refer [GET POST defroutes]]
            [compojure.route :refer [not-found resources]]
            [hiccup.page :refer [include-js include-css html5]]
            [cosm.middleware :refer [wrap-middleware]]
            [config.core :refer [env]]
            [cosm.rider :as rider]
            [cosm.auth :as auth]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(defn head []
  [:head
   [:meta {:charset "utf-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1"}]
   (include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
   [:link {:href "https://fonts.googleapis.com/css?family=Encode Sans Expanded"
           :rel "stylesheet"}]
   [:link {:href "https://fonts.googleapis.com/icon?family=Material+Icons"
           :rel "stylesheet"}]])

(defn loading-page []
  (html5
    (head)
    [:body {:class "body-container"}
     mount-target
     (include-js "/js/app.js")]))



(defroutes routes
  ;;Front end routes
  (GET "/" [] (loading-page))
  (GET "/about" [] (loading-page))
  ;;Back end routes
  (GET "/api/riders" request (rider/handler request))
  (GET "/api/user/current" request (auth/current-user-handler request))
  (POST "/api/login" request (auth/login-handler request))
  (POST "/api/logout" request (auth/logout-handler request))
  
  (resources "/")
  (not-found "Not Found"))

(def app (wrap-middleware #'routes))
