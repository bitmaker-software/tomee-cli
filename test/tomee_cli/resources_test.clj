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
 tomee-cli.resources-test
  (:require [midje.sweet :refer :all]
            [tomee-cli.resources :refer :all]))

(fact "Should parse xml"
      (nil? (parse-xml "resources/conf/tomee.xml")) => false)

(fact "Should add new resource"
      (let [midje ""
            expect {:tag :Resource :attrs {:id "SuperbizMail" :type "javax.mail.Session"} :content ["\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n"]}
            expect-new-tomee-xml {:tag :tomee :attrs nil :content [{:tag :Resource :attrs {:id "SuperbizMail" :type "javax.mail.Session"} :content ["\nmail.smtp.host=tomee.apache.org\nmail.smtp.port=25\nmail.transport.protocol=smtp\nmail.smtp.auth=true\nmail.smtp.user=email@apache.org\npassword=123456\n"]}]}]
        (add-resource "resources/conf/tomee.xml" expect) => expect-new-tomee-xml))

