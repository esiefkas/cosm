(ns cosm.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [prone.middleware :refer [wrap-exceptions]]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.format-response :as format-response]
            [ring.middleware.format-params :as format-params]
            [ring.middleware.session :as session]))

(defn wrap-middleware [handler]
  (-> handler
      format-response/wrap-transit-json-response
      format-params/wrap-transit-json-params
      session/wrap-session
      #_(wrap-defaults site-defaults)
      wrap-exceptions
      wrap-reload))
