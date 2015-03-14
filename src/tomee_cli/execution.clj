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
               Hildeberto Mendonça <me@hildeberto.com>"}
  tomee-cli.execution
  (:require [clojure.java.shell :refer (sh)]
            [tomee-cli.environment :refer (tomee-home)]))

(defn windows?
  "Verify if the operation system is windows or not"
  []
  (= (.toLowerCase (System/getProperty "os.name")) "windows"))

(def extension (if (windows?) ".exe" ".sh"))

(defn start
  "Startup the Apache TomEE Server"
  ([]     (start tomee-home))
  ([path] (sh (str path "/bin/startup" extension))))

(defn stop
  "Shutdown the Apache TomEE Server"
  ([]     (stop tomee-home))
  ([path] (sh (str path "/bin/shutdown" extension))))

(defn restart
  "Shutdown and start the Apache TomEE Server"
  ([]     (restart tomee-home))
  ([path] (do (stop path)
              (start path))))
