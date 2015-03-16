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

(ns ^{:author "Hildeberto Mendonça <hildeberto.com>"}
  tomee-cli.utils-test
  (:require [clojure.test    :refer :all]
            [tomee-cli.utils :refer :all]))

(deftest filename-from-path-test
  (testing "Testing file name not found"
    (is (nil? (filename-from-path "")))
    (is (nil? (filename-from-path "tomee")))
    (is (nil? (filename-from-path "resources/conf/tomee"))))
  (testing "Testing file name found"
    (is (= "tomee.xml" (filename-from-path "tomee.xml")))
    (is (= "tomee.xml" (filename-from-path "resources/conf/tomee.xml")))))

(run-all-tests)
