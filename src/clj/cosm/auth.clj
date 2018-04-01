(ns cosm.auth
  (:require
   [buddy.hashers :as hashers]
   [cosm.datomic :as datomic]
   [ring.util.response :as ring]))

;;maybe send back user here as well?
(defn login-handler [request]
  (spit "login-log.txt" request)
  (let [uname (get-in request [:body-params :username])
        input-pw (get-in request [:body-params :password])
        enc-pw (datomic/uname->encrypted-pw uname)]
    (if (hashers/check input-pw enc-pw)
      (-> (ring/response {:message "Success!"})
          (assoc-in [:session :identity] uname))
      (-> (ring/response {:message "Failure!"})
          (ring/status 401)))))

(defn logout-handler [request]
  (spit "logout-log.txt" request)
  (-> (ring/response {:message "Success!"})
      (assoc-in [:session :identity] nil)))

(defn current-user-handler [request]
  (if-let [username (get-in request [:session :identity])]
    (ring/response {:username username})
    (-> (ring/response {:message "Not Authenticated"})
        (ring/status 204))))

(defn test-login [username password]
  (login-handler {:body {:username username
                         :password password}}))
