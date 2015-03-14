;;Copyright 2015 Bitmaker Software LDA and/or its affiliates and other contributors
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

(ns ^{:author "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com>"}
  tomee-cli.configuration.mail-resource
  (:require [tomee-cli.resources :refer (add-resource xml-with-out-str) :as resource]))

(defn define-mail-resource
  "Define a mail resorce"
  [id host port protocol auth user password]
  (let [content (str "\nmail.smtp.host="host"\nmail.smtp.port="port"\nmail.transport.protocol="protocol"\nmail.smtp.auth="auth"\nmail.smtp.user="user"\npassword="password"\n")]
  {:tag :Resource :attrs {:id (str id) :type "javax.mail.Session"} :content [content]}))

(defn add-new-mail-resource
  "Add a new mail resource in tomee.xml"
  [path id host port protocol auth user password]
  (let [tomee-xml-path (str path "/conf/tomee.xml")
        new-resource (define-mail-resource id host port protocol auth user password)
        new-tomee-xml (resource/add-resource tomee-xml-path new-resource)
        str-new-tomee-xml (xml-with-out-str new-tomee-xml)]
    (spit tomee-xml-path str-new-tomee-xml)
    str-new-tomee-xml))
