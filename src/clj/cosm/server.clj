(ns cosm.server
  (:require [cosm.handler :refer [app]]
            [config.core :refer [env]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.string :as string]
            [cosm.datomic :as datomic])
  (:gen-class))

 (defn -main [& args]
   (let [port (Integer/parseInt (or (env :port) "3000"))]
     (datomic/start-datomic)
     (run-jetty app {:port port :join? false})))


   
