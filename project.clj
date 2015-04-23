(defproject aws-report "0.1.0-SNAPSHOT"
  :description "Sends an e-mail report describing AWS service statuses."
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [amazonica "0.3.19"]
                 [com.draines/postal "1.11.3"]]
  :main aws-report.core)
