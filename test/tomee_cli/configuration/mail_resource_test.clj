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


(def tomee-xml-file (io/file
                          (io/resource "tomee.xml" )))

(def expect {:tag :tomee, :attrs nil, :content {:tag :Resource, :attrs {:id "SuperbizMail", :type "javax.mail.Session"}, :content ["\nmail.smtp.host=host\nmail.smtp.port=port\nmail.transport.protocol=protocol\nmail.smtp.auth=auth\nmail.smtp.user=user\npassword=password\n"]}})


(deftest create-mail-resource-test
  (testing "Should create mail resource in tomee.xml"
    (is (= expect (create-mail-resource tomee-xml-file "id" "host" "port" "protocol" "auth" "user" "password")))))
