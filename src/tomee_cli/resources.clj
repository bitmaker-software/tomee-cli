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
  (:require [clojure.xml     :refer (parse)   :as xml]
            [clojure.java.io :refer (as-file) :as io]))

(defn parse-xml
  [path]
  (xml/parse
   (io/as-file path)))

(defn add-resource
  [path resource]
  (assoc (parse-xml path) :content [resource]))

(defn xml-with-out-str
  [xml-file]
  (with-out-str
    (xml/emit xml-file)))
