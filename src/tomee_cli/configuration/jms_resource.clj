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
  (:require [tomee-cli.resources   :refer (add-resource xml-with-out-str define-resource) :as resource]
            [tomee-cli.environment :refer (tomee-home) :as env]))

(defn define-jms-adapter-resource
  "Define a ActiveMQ Resource Adapter"
  [id broker-xml-config server-url data-source]
  (let [content (str "BrokerXmlConfig=" broker-xml-config "\nServerUrl=" server-url "\nDataSource=" data-source)]
    (resource/define-resource id "ActiveMQResourceAdapter" content)))

(defn define-jms-factory-resource
  ([] (define-jms-factory-resource env/tomee-home))
  ([tomee-path] (println "Add JMS Factory Resource")))

(defn define-jms-container-resource
  ([] (define-jms-container-resource env/tomee-home))
  ([tomee-path] (println "Add JMS Container Resource")))

(defn define-jms-queue-resource
  ([] (define-jms-queue-resource env/tomee-home))
  ([tomee-path] (println "Add JMS Queue Resource")))

(defn define-jms-topic-resource
  ([] (define-jms-topic-resource env/tomee-home))
  ([tomee-path] (println "Add JMS Topic Resource")))
