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

(ns ^{:author "Hildeberto Mendonça <hildeberto.com>"
              "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com>"}
 tomee-cli.utils-test
  (:require [midje.sweet     :refer :all]
            [tomee-cli.utils :refer :all]))

(fact "Should not find file extension"
      (nil? (filename-extension "")) => true
      (nil? (filename-extension "tomee")) => true)

(fact "Should find file extension"
      (filename-extension "tomee.xml") => "xml"
      (filename-extension "tomee-1.7.1.zip") => "zip")

(fact "Should return extension in lower case"
      (filename-extension "tomee.XML") => "xml")

(fact "Should not find file withot extension"
      (nil? (filename-from-path "")) => true
      (nil? (filename-from-path "tomee")) => true
      (nil? (filename-from-path "resources/conf/tomee")) => true)

(fact "Should find file with extension"
      (filename-from-path "tomee.xml") => "tomee.xml"
      (filename-from-path "resources/conf/tomee.xml") => "tomee.xml")
