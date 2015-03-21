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
  (:require [clojure.test                              :refer :all]
            [tomee-cli.configuration.database-resource :refer :all]))

(def expect {:tag :Resource :attrs {:id "SuperbizMail" :type "javax.sql.DataSource"} :content ["\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n"]})
(def expect-new-tomee-xml "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.sql.DataSource'>\n\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n\n</Resource>\n</tomee>\n")
(def expect-new-tomee-xml-with-resource "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.sql.DataSource'>\n\n\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n\n\n</Resource>\n<Resource id='SuperbizMail' type='javax.sql.DataSource'>\n\njdbcDriver=com.mysql.Jdbc.Driver\njdbcUrl=jdbc:mysql://localhost/db\nusername=soro\npassword=123456\nJtaManaged=true\n\n</Resource>\n</tomee>\n")

(defn before [])

(defn after []
  (spit "resources/conf/tomee.xml" "<?xml version='1.0' encoding='UTF-8'?>\n<tomee/>"))

(defn each-fixture [f]
  (before)
  (f)
  (after))

(use-fixtures :each each-fixture)

(deftest define-datasource-resource-test
  (testing "Should create database resource"
    (is (= expect (define-datasource-resource "SuperbizMail" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" "123456" true)))))

(deftest add-new-datasource-resource-test
  (testing "Should add new database resource in tomee.xml without TOMEE_HOME env"
    (is (= expect-new-tomee-xml (add-new-datasource-resource "resources" "SuperbizMail" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" "123456" true)))))

(deftest add-new-datasource-resource-with-env-test
  (testing "Should add new database resource in tomee.xml with TOMEE_HOME env"
    (is (= expect-new-tomee-xml (add-new-datasource-resource "resources" "SuperbizMail" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" "123456" true)))))

(deftest add-new-datasource-resource-in-tomee-with-resource-test
  (testing "Should not replace resources/conf/tomee.xml with resource"
    (spit "resources/conf/tomee.xml" expect-new-tomee-xml)
    (is (= expect-new-tomee-xml-with-resource (add-new-datasource-resource "resources" "SuperbizMail" "com.mysql.Jdbc.Driver" "jdbc:mysql://localhost/db" "soro" "123456" true)))))
