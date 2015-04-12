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
 tomee-cli.configuration.jms-resource
  (:require [tomee-cli.resources   :refer (add-new-resource xml-with-out-str define-resource) :as resource]
            [tomee-cli.environment :refer (tomee-home) :as env]))

(defn define-jms-adapter-resource
  "Define a ActiveMQ Resource Adapter"
  [id broker-xml-config server-url data-source]
  (let [content (str "BrokerXmlConfig=" broker-xml-config "\nServerUrl=" server-url "\nDataSource=" data-source)]
    (resource/define-resource id "ActiveMQResourceAdapter" content)))

(defn define-jms-factory-resource
  "Define JMS Factory Resource"
  [id resource-adapter transaction-support pool-max-size pool-min-size connection-max-wait connection-max-idle]
  (let [content (str "ResourceAdapter=" resource-adapter "\nTransactionSupport=" transaction-support "\nPoolMaxSize=" pool-max-size "\nPoolMinSize=" pool-min-size "\nConnectionMaxWaitMilliseconds=" connection-max-wait "\nConnectionMaxIdleMinutes=" connection-max-idle)]
    (resource/define-resource id "javax.jms.ConnectionFactory" content)))

(defn define-jms-queue-resource
  "Define JMS Queue"
  [id destination]
  (let [content (str "destination=" destination)]
    (resource/define-resource id "javax.jms.Queue" content)))

(defn define-jms-topic-resource
  "Define JMS Topic"
  [id destination]
  (let [content (str "destination=" destination)]
    (resource/define-resource id "javax.jms.Topic" content)))

(defn add-jms-adapter-resource
  "Add new JMS Adapter Resource in tomee.xml"
  [& {:keys [path id broker-xml server-url data-source]
      :or [broker-xml "broker:(tcp://localhost:61616)?useJmx=false" server-url "vm://localhost?async=true" data-source "" path env/tomee-home]}]
  (resource/add-new-resource path (define-jms-adapter-resource id broker-xml server-url data-source)))

(defn add-jms-factory-resource
  "Add new JMS Factory Resource in tomee.xml"
  [& {:keys [path id resource-adapter transaction-support pool-max-size pool-min-size connection-max-wait connection-max-idle]
      :or [transaction-support "xa" pool-max-size 10 pool-min-size 0 connection-max-wait 5000 connection-max-idle 15 path env/tomee-home]}]
  (resource/add-new-resource path (define-jms-factory-resource id resource-adapter transaction-support pool-max-size pool-min-size connection-max-wait connection-max-idle)))

(defn add-jms-queue-resource
  "Add new JMS Queue Resource in tomee.xml"
  [& {:keys [path destination]
      :or [destination "" path env/tomee-home]}]
  (resource/add-new-resource path (define-jms-queue-resource id destination)))

(defn add-jms-topic-resource
  "Add new JMS Topic Resource in tomee.xml"
  [& {:keys [path destination]
      :or [destination "" path env/tomee-home]}]
  (resource/add-new-resource path (define-jms-topic-resource id destination)))
