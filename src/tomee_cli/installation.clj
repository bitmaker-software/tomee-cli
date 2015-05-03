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
  (:require [clojure.java.io                          :as io]
            [clojure.java.shell    :refer (sh)]
            [tomee-cli.environment :refer (extension) :as env]
            [tomee-cli.utils                          :as utils])
  (:gen-class))

(def default-dist     "webprofile")
(def default-version  "1.7.1")
(def default-location ".")

(defn discover-download-uri [& {:keys [dist version]
                                :or {dist default-dist version default-version}}]
  (let [content     (utils/fetch-uri (str "http://www.apache.org/dyn/closer.cgi/tomee/tomee-" version "/apache-tomee-" version "-" dist ".zip"))
        pos-version (.indexOf content version)]
    (loop [link      version
           pos-left  (dec pos-version)
           pos-right (+ pos-version (.length version))]
      ; The loop stops when double quotes are found at the beginning and at the end of the link.
      (if (and (= (.indexOf link "\"") 0)
               (= (.lastIndexOf link "\"") (dec (.length link))))
        (.replace link (str (char 34)) "")
        (let [char-left  (.charAt content pos-left)
              char-right (.charAt content pos-right)]
          (recur (str char-left link char-right)
                 (if (= (int char-left) 34)  pos-left  (dec pos-left))
                 (if (= (int char-right) 34) pos-right (inc pos-right))))))))

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
                (loop [bytes  (byte-array 1024)]
                  (let [length (.read in bytes)]
                    (when (>= length 0)
                      (.write out bytes 0 length)
                      (recur (byte-array 1024))))))))
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

(defn install-tomee [& {:keys [dist version location]
                        :or {dist default-dist version default-version location default-location}}]
  (grant-permission
   (unzip-file
    (utils/download-file
     (discover-download-uri :dist dist :version version)
     (str location "/apache-tomee-" version "-" dist ".zip")))))
