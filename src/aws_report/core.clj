(ns aws-report.core
  (:require [amazonica.aws.ec2 :as ec2]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [postal.core :as postal]))

(defn report
  []
  (let [instances (flatten (map :instances (:reservations (ec2/describe-instances))))
        stopped-instances (filter #(= "stopped" (get-in % [:state :name])) instances)]
    (str "There are currently " (count instances)
         " EC2 instances, " (count stopped-instances)
         " of which are stopped:\n\n"
         (str "<pre>"
          (str/join "\n" (map #(vector
                                (:tags %)
                                (get-in % [:state :name]))
                              instances))
          "</pre>"))))

(defn email
  [from-address to-addresses]
  (let [smtp-creds (read-string
                    (slurp (io/file (System/getenv "HOME")
                                    ".secrets/smtp.clj")))]
    (doseq [to-address to-addresses]
      (postal/send-message
       smtp-creds
       {:from from-address
        :to to-address
        :subject "AWS EC2 Status Update"
        :body [{:type "text/html"
                :content (report)}]}))))

(defn -main
  [& addresses]
  (email (first addresses)
         (rest addresses)))
