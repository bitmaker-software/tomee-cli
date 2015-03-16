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
            [clojure.java.shell    :refer (sh)]
            [tomee-cli.environment :refer (tomee-home)]
            [tomee-cli.utils       :refer (filename-from-path)]))

(defn copy-file [source-path dest-path]
  (copy (file source-path) (file dest-path)))

(defn deploy-war
  ([war-file-path]      (deploy-war tomee-home war-file-path))
  ([path war-file-path] (copy-file war-file-path
                                   (str path "/webapps/" (filename-from-path war-file-path)))))
(defn undeploy-war
  ([war-filename]      (undeploy-war tomee-home war-filename))
  ([path war-filename] (delete-file (str path "/webapps/" war-filename))))
