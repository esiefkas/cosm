(ns cosm.rider
  (:require [ring.util.response :as ring]))

(defn handler [request]
  (ring/response "hello world"))
