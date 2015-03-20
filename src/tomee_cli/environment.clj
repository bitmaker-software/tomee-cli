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
            [tomee-cli.utils    :refer (copy-uri-to-file pretty-output unzip-file)]
            [environ.core       :refer (env)]))

(def tomee-home (let [env-var (env :tomee-home)]
                  (if (nil? env-var) "." env-var)))

(def tomee-xml-path (str tomee-home "/conf/tomee.xml"))

(defn windows?
  "Verify if the operating system is windows"
  []
  (= (.toLowerCase (System/getProperty "os.name")) "windows"))

(def extension (if (windows?) ".exe" ".sh"))

(defn version
  "Prints version numbers of the environment elements"
  ([]     (version tomee-home))
  ([path] (pretty-output (get (sh (str path "/bin/version" extension)) :out))))

(defn install-tomee [dist version]
  (if (not (empty? tomee-home))
    (unzip-file (copy-uri-to-file (str "http://apache.cu.be/tomee/tomee-" version "/apache-tomee-" version "-" dist ".zip")
                                  (str "apache-tomee-" version "-" dist ".zip")))
    "You can't do that because tomee-cli points to an existing installation."))

