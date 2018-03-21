(ns cosm.rider
  (:require [ring.util.response :as ring]))

(defn handler [request]
  (println "HERE!!!!")
  (ring/response {:message "hello world"}))
