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
 tomee-cli.configuration.database-resource
  (:require [tomee-cli.resources   :refer (add-resource xml-with-out-str define-resource) :as resource]
            [tomee-cli.environment :refer (tomee-home) :as environment]))

(defn define-datasource-resource
  "Define a DataSource resource"
  [id jdbc-drive jdbc-url username password jtamanaged]
  (let [content (str "\njdbcDriver=" jdbc-drive "\njdbcUrl=" jdbc-url "\nusername=" username "\npassword=" password "\nJtaManaged=" jtamanaged "\n")]
    (resource/define-resource id "javax.sql.DataSource" content)))

(defn add-new-datasource-resource
  "Write a new DataSource Resource in tomee.xml"
  ([id jdbc-drive jdbc-url username password jtamanaged] (add-new-datasource-resource environment/tomee-home id jdbc-drive jdbc-url username password jtamanaged))
  ([path id jdbc-drive jdbc-url username password jtamanaged]
   (let [new-resource (define-datasource-resource id jdbc-drive jdbc-url username password jtamanaged)
         new-tomee-xml (resource/add-resource environment/tomee-xml-path new-resource)
         str-new-tomee-xml (resource/xml-with-out-str new-tomee-xml)]
     (spit environment/tomee-xml-path str-new-tomee-xml)
     str-new-tomee-xml)))
