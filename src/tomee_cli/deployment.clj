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
  (:require [clojure.java.io       :refer (as-file copy file delete-file) :as io]
            [tomee-cli.environment :refer (tomee-home) :as env]
            [tomee-cli.utils       :refer (filename-extension filename-from-path) :as utils])
  (:gen-class))

(defn copy-file [source-path dest-path]
  (io/copy (io/file source-path) (io/file dest-path)))

(defn deploy
  "Deploys a war/ear file on the server, by copying it to the folder /webapps
   if the file has the extension .war or to the folder /apps if the file has the extention .ear."
  ([app-file-path] (deploy env/tomee-home app-file-path))
  ([tomee-path app-file-path]
   (let [filename    (utils/filename-from-path app-file-path)
         extension   (utils/filename-extension (utils/filename-from-path app-file-path))]
     (cond
       (= extension "war") (let [deploy-path (str tomee-path "/webapps/")]
                             (copy-file app-file-path
                                        (str deploy-path filename))
                             (str "Deployed " filename " at " deploy-path))

       (= extension "ear") (let [deploy-path (str tomee-path "/apps/")
                                 filename    (filename-from-path app-file-path)]
                             (when-not (.exists (as-file deploy-path))
                               (.mkdir (io/as-file deploy-path)))
                             (copy-file app-file-path
                                        (str deploy-path filename))
                             (str "Deployed " filename " at " deploy-path))

       :else (str "Error deploying application. File " filename " invalid.")))))

(defn undeploy
  "Undeploys a war/ear file from the server by deleting the war file from the folder /webapps
   or the ear file from the folder /apps."
  ([app-filename] (undeploy env/tomee-home app-filename))
  ([tomee-path app-filename]
   (let [extension (utils/filename-extension app-filename)]
     (cond
       (= extension "war") (io/delete-file (str tomee-path "/webapps/" app-filename))
       (= extension "ear") (io/delete-file (str tomee-path "/apps/" app-filename))
       :else (str "Error undeploying application. File " (utils/filename-from-path app-filename) " doesn't exist.")))))
