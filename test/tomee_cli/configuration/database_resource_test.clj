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
      (let [midje ""
            content "jdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true"
            definition (define-datasource-resource "SuperBizDataSource" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" 123456 true)]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => "javax.sql.DataSource"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperBizDataSource"
        (nil? (definition :content)) => false
        (definition :content) => [content]))
