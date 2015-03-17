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
 tomee-cli.configuration.mail-resource-test
  (:require [clojure.test                          :refer :all]
            [tomee-cli.configuration.mail-resource :refer :all]))

(def expect {:tag :Resource :attrs {:id "SuperbizMail" :type "javax.mail.Session"} :content ["\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n"]})
(def expect-new-tomee-xml "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.mail.Session'>\n\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n\n</Resource>\n</tomee>\n")
(def expect-new-tomee-xml-with-resource "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.mail.Session'>\n\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n\n</Resource>\n\n<Resource id='SuperbizMail' type='javax.mail.Session'>\n\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n\n</Resource>\n</tomee>\n")

(defn before [])

(defn after []
  (spit "resources/conf/tomee.xml" "<?xml version='1.0' encoding='UTF-8'?>\n<tomee/>"))

(defn each-fixture [f]
  (before)
  (f)
  (after))

(use-fixtures :each each-fixture)

(deftest define-mail-resource-test
  (testing "Should create mail resource"
    (is (= expect (define-mail-resource "SuperbizMail" "tomee.apache.org" "25" "smtp" "true" "email@apache.org" "123456")))))

(deftest add-new-mail-resource-test
  (testing "Should add new mail resource in tomee.xml without TOMEE_HOME env"
    (is (= expect-new-tomee-xml (add-new-mail-resource "resources" "SuperbizMail" "tomee.apache.org" "25" "smtp" "true" "email@apache.org" "123456")))))

(deftest add-new-mail-resource-with-env-test
  (testing "Should add new mail resource in tomee.xml with TOMEE_HOME env"
    (System/setProperty "TOMEE_HOME" "resources")
    (is (= expect-new-tomee-xml (add-new-mail-resource "SuperbizMail" "tomee.apache.org" "25" "smtp" "true" "email@apache.org" "123456")))))

(deftest add-new-mail-resource-in-tomee-with-resource-test
  (spit "resources/conf/tomee-with-resource.xml" expect-new-tomee-xml-with-resource)
  (testing "Should not replace resources/conf/tomee-with-resource.xml"
    (is (= expect-new-tomee-xml-with-resource))))

