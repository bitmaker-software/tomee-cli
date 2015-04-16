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
            [midje.sweet                           :refer :all]
            [tomee-cli.configuration.mail-resource :refer :all]))

(background (after :facts (spit "resources/conf/tomee.xml" "<?xml version='1.0' encoding='UTF-8'?>\n<tomee/>")))

(fact "Should define a mail resource"
      (let [midje ""
            expect {:tag :Resource :attrs {:id "SuperbizMail" :type "javax.mail.Session"} :content ["mail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456"]}]
       (define-mail-resource "SuperbizMail" "tomee.apache.org" 25 "smtp" true "email@apache.org" 123456) => expect))

(fact "Should add new mail resource in tomee.xml without TOMEE_HOME env"
      (let [expect-new-tomee-xml "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.mail.Session'>\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n</Resource>\n</tomee>\n"]
      (add-mail-resource :path "resources" :id "SuperbizMail" :host "tomee.apache.org" :protocol "smtp" :auth true :user "email@apache.org" :password 123456) => expect-new-tomee-xml))

(fact "Should add new mail resource in tomee.xml with TOMEE_HOME env"
      (let [expect-new-tomee-xml "<?xml version='1.0' encoding='UTF-8'?>\n<tomee>\n<Resource id='SuperbizMail' type='javax.mail.Session'>\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n</Resource>\n</tomee>\n"]
        (add-mail-resource :id "SuperbizMail" :host "tomee.apache.org" :protocol "smtp" :auth true :user "email@apache.org" :password 123456) => expect-new-tomee-xml))
