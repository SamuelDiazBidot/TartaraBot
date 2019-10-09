(ns discord.core
  (:require [discord.bot :as bot]
            [clojure.string :as s])) 

(def tartara
  (atom {:username "MrMeatyHD"
         :name "Gabriel"}))

(bot/defcommand change-tartara
  [client message]
  (if-let [content (not-empty (:content message))]
    (let [tokens (s/split content #"\s")]
    (reset! tartara {:username (get tokens 0) 
                     :name (get tokens 1)}))))

(bot/defhandler get-username-handler
  [prefix client message]
  (let [user (:author message)
        user-name (:username user)]
    (if (= user-name (:username @tartara))
      (bot/say (format "%s es una tartara" (:name @tartara))))))

(defn -main 
  "Starts a discord bot."
  [& args]
  (bot/start))