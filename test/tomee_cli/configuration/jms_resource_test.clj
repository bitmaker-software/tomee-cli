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

(def content-adapter "BrokerXmlConfig=broker:(tcp://localhost:61616)?useJmx=false\nServerUrl=vm://localhost?async=true\nDataSource=xpto")
(def expect-adapter {:tag :Resource :attrs {:id "SuperBizJMSAdapter" :type "ActiveMQResourceAdapter"} :content [content-adapter]})

(deftest define-jms-adapter-resource-test
  (let [definition (define-jms-adapter-resource "SuperBizJMSAdapter" "broker:(tcp://localhost:61616)?useJmx=false" "vm://localhost?async=true" "xpto")]
    (testing "Should create ActiveMQResourceAdapter type"
      (is (= ((expect-adapter :attrs) :type) ((definition :attrs) :type))))
    (testing "Should have an id"
      (is (not (nil? ((expect-adapter :attrs) :id)))))
    (testing "Should has a content"
      (is (not (nil? (definition :content)))))
    (testing "Should define a JMS Adapter Resource"
      (is (= [content-adapter] (definition :content))))))


(def content-factory "ResourceAdapter=SuperBizJMSAdapter\nTransactionSupport=xa\nPoolMaxSize=10\nPoolMinSize=0\nConnectionMaxWaitMilliseconds=5000\nConnectionMaxIdleMinutes=15")
(def expect-factory {:tag :Resource :attrs {:id "SuperBizFactory" :type "javax.jms.ConnectionFactory"} :content [content-factory]})

(deftest define-jms-factory-resource-test
  (let [definition (define-jms-factory-resource "SuperBizJMSFactory" "SuperBizJMSAdapter" "xa" 10 0 5000 15)]
    (testing "Should create Connection Factory type"
      (is (= ((expect-factory :attrs) :type) ((definition :attrs) :type))))
    (testing "Should have an id"
      (is (not (nil? ((expect-factory :attrs) :id)))))
    (testing "Should has a content"
      (is (not (nil? (definition :content)))))
    (testing "Should define a JMS Factory Resource"
      (is (= [content-factory] (definition :content))))))


(def content-queue "destination=superBizQueue")
(def expect-queue {:tag :Resource :attrs {:id "SuperBizQueue" :type "javax.jms.Queue"} :content [content-queue]})

(deftest define-jms-factory-resource-test
  (let [definition (define-jms-queue-resource "SuperBizQueue" "superBizQueue")]
    (testing "Should create Queue type"
      (is (= ((expect-queue :attrs) :type) ((definition :attrs) :type))))
    (testing "Should have an id"
      (is (not (nil? ((expect-queue :attrs) :id)))))
    (testing "Should has a content"
      (is (not (nil? (definition :content)))))
    (testing "Should define a Queue Resource"
      (is (= [content-queue] (definition :content))))))

(def content-topic "destination=superBizTopic")
(def expect-topic {:tag :Resource :attrs {:id "SuperBizTopic" :type "javax.jms.Topic"} :content [content-topic]})

(deftest define-jms-factory-resource-test
  (let [definition (define-jms-topic-resource "SuperBizTopic" "superBizTopic")]
    (testing "Should create Topic type"
      (is (= ((expect-topic :attrs) :type) ((definition :attrs) :type))))
    (testing "Should have an id"
      (is (not (nil? ((expect-queue :attrs) :id)))))
    (testing "Should has a content"
      (is (not (nil? (definition :content)))))
    (testing "Should define a Topic Resource"
      (is (= [content-topic] (definition :content))))))
