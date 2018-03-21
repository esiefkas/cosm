(ns cosm.middleware
  (:require [ring.middleware.defaults :refer [site-defaults wrap-defaults]]
            [ring.middleware.format-response :as format-response]
            [ring.middleware.format-params :as format-params]))

(defn wrap-middleware [handler]
  (-> handler
      format-response/wrap-transit-json-response
      format-params/wrap-transit-json-params
      (wrap-defaults site-defaults)))
