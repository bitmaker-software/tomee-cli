;;Copyright 2015 Daniel Cunha (soro) and/or its affiliates and other contributors
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
(ns ^{:author "Daniel Cunha (soro) <danielsoro@gmail.com>,
               Hildeberto Mendonça <me@hildeberto.com>"}
  tomee-cli.execution
  (:require [clojure.java.shell :refer (sh)])
  (:gen-class))

(defn- windows?
  "Verify if the operation system is windows or not"
  [] (= (.toLowerCase (System/getProperty "os.name")) "windows"))

(def extension (if (windows?) ".exe" ".sh"))

(defn startup
  "Startup the Apache TomEE Server"
  [path]
  (sh (str path "/bin/startup" extension)))

(defn shutdown
  "Shutdown the Apache TomEE Server"
  [path]
  (sh (str path "/bin/shutdown" extension)))
