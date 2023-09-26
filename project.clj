(defproject clojure-airtable-api "0.1.0-clojure-airtable-api"
  :description "Playing with the Airtable API using Clojure"
  :url ""
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.11.1"] 
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.0.0"]
                 [cheshire "5.10.1"]
                 [environ "1.2.0"]]
  :repl-options {:init-ns clojure-airtable-orm.core})