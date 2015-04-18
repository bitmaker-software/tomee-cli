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

(ns ^{:author "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com"}
 tomee-cli.configuration.database-resource-test
  (:require [midje.sweet                              :refer :all]
            [tomee-cli.configuration.database-resource :refer :all]))

(background (after :facts (spit "resources/conf/tomee.xml" "<?xml version='1.0' encoding='UTF-8'?>\n<tomee/>")))

(fact "Should create database resource"
      (let [midej ""
       expect {:tag :Resource :attrs {:id "SuperBizDataSource" :type "javax.sql.DataSource"} :content ["jdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true"]}]
       (define-datasource-resource "SuperBizDataSource" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" 123456 true) => expect))

(fact "Should add new database resource in tomee.xml without TOMEE_HOME env"
    (let [midje ""
          expect "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperBizDataSource' type='javax.sql.DataSource'>\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n</Resource>\n</tomee>\n"]
      (add-datasource-resource :id "SuperBizDataSource" :jdbc-drive "com.mysql.Jdbc.Driver" :jdbc-url "jdbc:mysql://localhost/db" :username "soro" :password 123456) => expect))

(fact "Should add new database resource in tomee.xml with TOMEE_HOME env"
    (let [midje ""
          expect "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperBizDataSource' type='javax.sql.DataSource'>\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n</Resource>\n</tomee>\n"]
      (add-datasource-resource :path "resources" :id "SuperBizDataSource" :jdbc-drive "com.mysql.Jdbc.Driver" :jdbc-url "jdbc:mysql://localhost/db" :username "soro" :password 123456) => expect))
