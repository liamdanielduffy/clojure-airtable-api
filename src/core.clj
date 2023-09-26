(ns clojure-airtable-orm.core
  (:require [clj-http.client :as client]
            [cheshire.core :as json]
            [environ.core :refer [env]]))

;; --- fetching the base schema --- 

(def base-id (env :base-id))
(def personal-access-token (env :personal-access-token))
(def auth-headers { "Authorization" (str "Bearer " personal-access-token)})
(def base-schema-endpoint (str "https://api.airtable.com/v0/meta/bases/" base-id "/tables"))

(defn base-schema-request []
  (client/get base-schema-endpoint {:headers auth-headers}))

(defn base-schema []
  (let [response (base-schema-request)
        body (:body response)]
   (json/parse-string body true)))

(comment 
  (base-schema)
)

;; --- table utils ---

(defn table-schemas [] (:tables (base-schema)))

(defn table-names [] (map :name (table-schemas)))

(defn table-schema [table-name]
  (let [tables (:tables (base-schema))
        table (first (filter #(= (:name %) table-name) tables))]
    table))

(comment 
  (table-schemas)
  (table-names)
  (table-schema "Short Term Stay"))

;; --- field utils ---

(defn fields [table-name] (:fields (table-schema table-name)))

(defn field-names [table-name] (map :name (fields table-name)))

(defn field-types [table-name] (map :type (fields table-name)))

(defn field-types-by-name [table-name] 
  (zipmap (field-names table-name) (field-types table-name)))

(defn field-names-by-type [table-name]
  (zipmap (field-types table-name) (field-names table-name)))

(comment 
  (fields "Short Term Stay")
  (field-types "Short Term Stay") 
  (field-names "Short Term Stay")
  (field-types-by-name "Short Term Stay")
  (field-names-by-type "Short Term Stay")
  )