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
  tomee-cli.configuration.mail-resource-test
  (:require [clojure.test :refer :all]
            [clojure.java.io :refer :all :as io]
            [tomee-cli.configuration.mail-resource :refer :all]))

(def email-resource-expect {:tag :Resource, :attrs {:id "SuperbizMail", :type "javax.mail.Session"}, :content ["\nmail.smtp.host=mail.superbiz.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=someuser\npassword=mypassword\n"]})

(def tomee-xml-resource (io/file
                          (io/resource "tomee.xml" )))

(def create-mail (define-mail-resource "SuperbizMail" "mail.superbiz.org" "25" "smtp" "true" "someuser" "mypassword"))

(deftest create-mail-resource-test
  (testing "Should create a resource mail"
    (is (= email-resource-expect create-mail))))

(deftest read-tomee-xml-test
   (testing "tomee.xml must be not null"
    (is (not (nil? tomee-xml-resource))))

  (testing "Should read the tomee.xml"
    (is (not (nil? (read-tomee-xml tomee-xml-resource))))))
