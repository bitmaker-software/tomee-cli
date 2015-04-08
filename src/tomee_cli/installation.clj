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
  (:require [clojure.java.io                                   :as io]
            [clojure.java.shell    :refer (sh)]
            [tomee-cli.environment :refer (extension)          :as env]
            [tomee-cli.utils       :refer (filename-extension) :as utils]))

(defn download-file [uri file]
  (if (.exists (io/as-file file))
    file
    (with-open [in  (io/input-stream  uri)
                out (io/output-stream file)]
      (io/copy in out)
      file)))

(defn unzip-file [file]
  (let [zip-file (java.util.zip.ZipFile. file)
        enum (enumeration-seq (.entries zip-file))]
    (loop [entries enum
           location nil]
      (if (empty? entries)
        location
        (let [zip-entry (first entries)
              file-name (.getName zip-entry)
              file      (io/as-file file-name)
              parent    (.getParentFile file)]
          
          (if (.endsWith file-name "/")
            (.mkdirs file)
            (do
              (when (not (nil? parent))
                (.mkdirs parent))
              (with-open [out (java.io.FileOutputStream. file)
                          in  (.getInputStream zip-file zip-entry)]
                (let [bytes (byte-array (.getSize zip-entry))]
                  (.write out bytes 0 (.read in bytes))))))
          (recur (rest entries) 
                 (if (nil? location) (.getPath file) location)))))))

(defn grant-permission [location-install]
  "Scans the bin directory, looking for .sh files, and gives execution permission for them."
  (if (nil? location-install)
    nil
    (let [files (.listFiles (java.io.File. (str location-install "/bin")))]
      (map #(.setExecutable % true)
           (filter #(and (.isFile %)
                         (= (str "." (utils/filename-extension (.getName %))) env/extension))
                   files)))))

(defn install-tomee [ & {:keys [dist version location]
                         :or {dist "webprofile" version "1.7.1" location "."}}]
  (grant-permission
    (unzip-file
      (download-file
        (str "http://apache.belnet.be/tomee/tomee-" version "/apache-tomee-" version "-" dist ".zip")
        (str location "/apache-tomee-" version "-" dist ".zip")))))
