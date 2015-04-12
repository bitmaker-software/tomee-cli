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
  (:require [tomee-cli.resources   :refer (add-new-resource xml-with-out-str define-resource) :as resource]
            [tomee-cli.environment :refer (tomee-home) :as environment]))

(defn define-datasource-resource
  "Define a DataSource resource"
  [id jdbc-drive jdbc-url username password jta-managed]
  (let [content (str "jdbcDriver=" jdbc-drive "\njdbcUrl=" jdbc-url "\nusername=" username "\npassword=" password "\nJtaManaged=" jta-managed)]
    (resource/define-resource id "javax.sql.DataSource" content)))

(defn add-datasource-resource
  "Write a new DataSource Resource in tomee.xml"
  [& {:keys [path id jdbc-drive jdbc-url username password jta-managed]
      :or {jdbc-drive "org.hsqldb.jdbcDriver" jdbc-url "jdbc:hsqldb:file:data/hsqldb/hsqldb" username "sa" password "" jta-managed true path environment/tomee-home}}]
  (resource/add-new-resource path (define-datasource-resource id jdbc-drive jdbc-url username password jta-managed)))
