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
  (:require [midje.sweet                           :refer :all]
            [tomee-cli.configuration.mail-resource :refer :all]))

(background (after :facts (spit "resources/conf/tomee.xml" "<?xml version='1.0' encoding='UTF-8'?>\n<tomee/>")))

(fact "Should define a mail resource"
      (let [midje ""
            content "mail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456"
            definition (define-mail-resource "SuperbizMail" "tomee.apache.org" 25 "smtp" true "email@apache.org" 123456)]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => "javax.mail.Session"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperbizMail"
        (nil? (definition :content)) => false
        (definition :content) => [content]))
