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
 tomee-cli.installation
  (:require [clojure.java.shell :refer (sh)]
            [clojure.java.io :refer (copy input-stream output-stream reader writer as-file)]
            [tomee-cli.environment :refer (extension) :as env]))

(defn download-file [uri file]
  (if (.exists (as-file file))
    (as-file file)
    (with-open [in (input-stream uri)
                out (output-stream file)]
      (copy in out)
      file)))

(defn unzip-file [zip-file location]
  (let [zip-file (java.util.zip.ZipFile. zip-file)
        enum (enumeration-seq (.entries zip-file))]
    (if (zero? (count (map (fn [zip-entry]
           (let [file-name (.getName zip-entry)
                 file      (as-file file-name)
                 parent    (.getParentFile file)]
             (if (.endsWith file-name "/")
               (str " ups" (.mkdirs file))
               (do (when (not (nil? parent))
                     (.mkdirs parent))
                   (with-open [out (java.io.FileOutputStream. file)
                               in  (.getInputStream zip-file zip-entry)]
                     (let [bytes (byte-array (.getSize zip-entry))]
                       (.write out bytes 0 (.read in bytes)))))))) enum))) nil location)))

(defn grant-permission [path]
  path)

(defn install-tomee [ & {:keys [dist version location]
                         :or {dist "webprofile" version "1.7.1" location "."}}]
  (grant-permission
    (unzip-file
      (download-file
        (str "http://apache.cu.be/tomee/tomee-" version "/apache-tomee-" version "-" dist ".zip")
        (str location "/apache-tomee-" version "-" dist ".zip"))
      location)))
