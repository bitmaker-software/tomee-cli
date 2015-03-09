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
            [tomee-cli.configuration.mail-resource :refer :all]))

(defn- create-mail [] (create-mail-resource "SuperbizMail" "mail.superbiz.org" "25" "smtp" "true" "someuser" "mypassword"))

(deftest create-mail-resource-test
  (testing "Should create a resource mail not null"
    (is (not (nil? (create-mail)))))

  (testing "Should be instance of the String"
    (is (instance? String (create-mail))))

  (testing "Should create a resource mail"
    (is (= "<Resource id=\"SuperbizMail\" type=\"javax.mail.Session\">\nmail.smtp.host=mail.superbiz.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=someuser\npassword=mypassword\n</Resource>" (create-mail))))

)
