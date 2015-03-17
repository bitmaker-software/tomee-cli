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
  tomee-cli.deployment
  (:require [clojure.java.io       :refer (copy file delete-file)]
            [tomee-cli.environment :refer (tomee-home)]
            [tomee-cli.utils       :refer (filename-extension filename-from-path)]))

(defn copy-file [source-path dest-path]
  (copy (file source-path) (file dest-path)))

(defn deploy
  ([app-file-path] (deploy tomee-home app-file-path))
  ([tomee-path app-file-path]
     (let [extension (filename-extension (filename-from-path app-file-path))]
       (cond
         (= extension "war") (copy-file app-file-path
                                        (str tomee-path "/webapps/" (filename-from-path app-file-path)))
         (= extension "ear") (copy-file app-file-path
                                        (str tomee-path "/apps/" (filename-from-path app-file-path)))
         :else (str "Error deploying application. File " (filename-from-path app-file-path) " invalid.")))))

(defn undeploy
  ([app-filename] (undeploy tomee-home app-filename))
  ([tomee-path app-filename]
     (let [extension (filename-extension app-filename)]
       (cond
         (= extension "war") (delete-file (str tomee-path "/webapps/" app-filename))
         (= extension "ear") (delete-file (str tomee-path "/apps/" app-filename))
         :else (str "Error undeploying application. File " (filename-from-path app-filename) " doesn't exist.")))))
