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
  (:require [midje.sweet :refer :all]
            [clojure.test :refer :all]
            [tomee-cli.configuration.jms-resource :refer :all]))

(fact "Should define a JMS Resource Adapter"
      (let [content-adapter "BrokerXmlConfig=broker:(tcp://localhost:61616)?useJmx=false\nServerUrl=vm://localhost?async=true\nDataSource=xpto"
            expect-adapter {:tag :Resource :attrs {:id "SuperBizJMSAdapter" :type "ActiveMQResourceAdapter"} :content [content-adapter]}
            definition (define-jms-adapter-resource "SuperBizJMSAdapter" "broker:(tcp://localhost:61616)?useJmx=false" "vm://localhost?async=true" "xpto")]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => ((expect-adapter :attrs) :type)
        ((definition :attrs) :type) => "ActiveMQResourceAdapter"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperBizJMSAdapter"
        ((definition :attrs) :id) => "SuperBizJMSAdapter"
        (nil? (definition :content)) => false
        (definition :content) => [content-adapter]))

(fact "Should define a JMS Resource Factory"
      (let [content-factory "ResourceAdapter=SuperBizJMSAdapter\nTransactionSupport=xa\nPoolMaxSize=10\nPoolMinSize=0\nConnectionMaxWaitMilliseconds=5000\nConnectionMaxIdleMinutes=15"
            expect-factory {:tag :Resource :attrs {:id "SuperBizJMSFactory" :type "javax.jms.ConnectionFactory"} :content [content-factory]}
            definition (define-jms-factory-resource "SuperBizJMSFactory" "SuperBizJMSAdapter" "xa" 10 0 5000 15)]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => ((expect-factory :attrs) :type)
        ((definition :attrs) :type) => "javax.jms.ConnectionFactory"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperBizJMSFactory"
        (nil? (definition :content)) => false
        (definition :content) => [content-factory]))

(fact "Should define a JMS Resource Queue"
      (let [content-queue "destination=superBizQueue"
            expect-queue {:tag :Resource :attrs {:id "SuperBizQueue" :type "javax.jms.Queue"} :content [content-queue]}
            definition (define-jms-queue-resource "SuperBizQueue" "superBizQueue")]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => ((expect-queue :attrs) :type)
        ((definition :attrs) :type) => "javax.jms.Queue"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperBizQueue"
        (nil? (definition :content)) => false
        (definition :content) => [content-queue]))

(fact "Should define a JMS Resource Topic"
      (let [content-topic "destination=superBizTopic"
            expect-topic {:tag :Resource :attrs {:id "SuperBizTopic" :type "javax.jms.Topic"} :content [content-topic]}
            definition (define-jms-topic-resource "SuperBizTopic" "superBizTopic")]
        (nil? ((definition :attrs) :type)) => false
        ((definition :attrs) :type) => ((expect-topic :attrs) :type)
        ((definition :attrs) :type) => "javax.jms.Topic"
        (nil? ((definition :attrs) :id)) => false
        ((definition :attrs) :id) => "SuperBizTopic"
        (nil? (definition :content)) => false
        (definition :content) => [content-topic]))
