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
 tomee-cli.resources
  (:require [clojure.xml           :refer (parse)   :as xml]
            [clojure.java.io       :refer (as-file) :as io]
            [tomee-cli.environment :refer (tomee-xml-path) :as environment])
  (:gen-class))

(defn parse-xml
  [path]
  (xml/parse
   (io/as-file path)))

(defn xml-with-out-str
  [xml-file]
  (with-out-str
    (xml/emit xml-file)))

(defn add-resource
  [path resource]
  (let [xml-file (parse-xml path)
        content  (xml-file :content)]
    (cond
      (nil? content) (assoc xml-file :content [resource])
      :else (assoc xml-file :content (concat content [resource])))))

(defn add-new-resource
  ([path resource-defined]
   (let [new-tomee-xml (add-resource (environment/tomee-xml-path path) resource-defined)
         str-new-tomee-xml (xml-with-out-str new-tomee-xml)]
     (spit (environment/tomee-xml-path path) str-new-tomee-xml)
     str-new-tomee-xml)))

(defn define-resource
  [id resource-type content]
  {:tag :Resource :attrs {:id (str id) :type (str resource-type)} :content [content]})
