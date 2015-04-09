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
 tomee-cli.configuration.jms-resource-test
  (:require [clojure.test :refer :all]
            [tomee-cli.configuration.jms-resource :refer :all]))

(def content "BrokerXmlConfig=broker:(tcp://localhost:61616)?useJmx=false\nServerUrl=vm://localhost?async=true\nDataSource=xpto")
(def expect {:tag :Resource :attrs {:id "SuperBizJMS" :type "ActiveMQResourceAdapter"} :content [content]})

(deftest define-jms-adapter-resource-test
  (let [definition (define-jms-adapter-resource "SuperBizJMS" "broker:(tcp://localhost:61616)?useJmx=false" "vm://localhost?async=true" "xpto")]
    (testing "Should create ActiveMQResourceAdapter type"
      (is (= ((expect :attrs) :type) ((definition :attrs) :type))))
    (testing "Should have an id"
      (is (not (nil? ((expect :attrs) :id)))))
    (testing "Should has a content"
      (is (not (nil? (definition :content)))))
    (testing "Should define an JMS Adapter Resource"
      (is (= [content] (definition :content))))))