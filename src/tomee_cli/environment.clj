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

(ns ^{:author "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com>,
               Hildeberto Mendonça <hildeberto.com>"}
  tomee-cli.environment
  (:require [clojure.java.shell :refer (sh)]
            [clojure.string     :refer (split)]
            [tomee-cli.utils    :refer (pretty-output)]))

(def tomee-home (let [env-var (System/getenv "TOMEE_HOME")]
                  (if (nil? env-var) "." env-var)))

(defn windows?
  "Verify if the operating system is windows or not"
  []
  (= (.toLowerCase (System/getProperty "os.name")) "windows"))

(def extension (if (windows?) ".exe" ".sh"))

(defn version
  "Prints version numbers of the environment elements"
  ([]     (version tomee-home))
  ([path] (pretty-output (get (sh (str path "/bin/version" extension)) :out))))

