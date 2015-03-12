;;Copyright 2015 Daniel Cunha (soro) and/or its affiliates and other contributors
;;as indicated by the @authors tag. All rights reserved.
;;
;;Licensed under the Apache License, Version 2.0 (the "License");
;;you may not use this file except in compliance with the License.
;;You may obtain a copy of the License at
;;
;;    http://www.apache.org/licenses/LICENSE-2.0
;;
;;Unless required by applicable law or agreed to in writing, software
;;distributed under the License is distributed on an "AS IS" BASIS,
;;WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
;;See the License for the specific language governing permissions and
;;limitations under the License.
(ns ^{:author "Daniel Cunha (soro) <danielsoro@gmail.com>"}
  tomee-cli.configuration.mail-resource
  (:require [clojure.xml :refer :all :as xml]
            [clojure.java.io :refer :all :as io])
  (:gen-class))

(defn- read-tomee-xml [tomee-xml-file]
  (xml/parse (io/as-file tomee-xml-file)))

(defn- define-mail-resource
  "Define a mail resorce"
  [id host port protocol auth user password]
  (let [content (str "\nmail.smtp.host="host"\nmail.smtp.port="port"\nmail.transport.protocol="protocol"\nmail.smtp.auth="auth"\nmail.smtp.user="user"\npassword="password"\n")]
  {:tag :Resource :attrs {:id (str id) :type "javax.mail.Session"} :content [content]}))

(defn- add-resource-in-tomee-xml [resource tomee-xml]
  (assoc tomee-xml :content resource))

(defn create-mail-resource
  "Create a new mail resource in TomEE"
  [tomee-xml-file id host port protocol auth user password]
  (let [resource (define-mail-resource id host port protocol auth user password)
        tomee-xml (read-tomee-xml tomee-xml-file)]
  (add-resource-in-tomee-xml resource tomee-xml)))


