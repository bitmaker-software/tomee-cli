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
 tomee-cli.utils
  (:require [clojure.string  :refer (split)]
            [clojure.java.io :refer (copy input-stream output-stream reader writer as-file)])
  (:import (java.io FileOutputStream)))

(defn pretty-output [text]
  "Formats a text to be beautifully printed by the repl."
  (loop [out (split text #"\n")]
    (if (empty? out)
      "--------------"
      (let [to-print (first out)]
        (println to-print)
        (recur (rest out))))))

(defn filename-extension [filename]
  (let [point-pos (.lastIndexOf filename ".")]
    (when-not (neg? point-pos) (.toLowerCase (.substring filename (inc point-pos))))))

(defn filename-from-path [path]
  "Identifies and returns a file name present in a path."
  (let [point-pos (.lastIndexOf path ".")
        bar-pos   (.lastIndexOf path "/")]
    (when-not (neg? point-pos) (.substring path (inc bar-pos)))))

(defn copy-uri-to-file [uri file]
  (if (.exists (as-file file))
    (as-file file)
    (with-open [in (input-stream uri)
                out (output-stream file)]
      (copy in out)
      file)))

(defn unzip-file [zip-file location]
  (let [zip-file (java.util.zip.ZipFile. zip-file)
        enum (enumeration-seq (.entries zip-file))]
    (str (count (map (fn [zip-entry]
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
                       (.write out bytes 0 (.read in bytes)))))))) enum)) " files uncompressed at " location)))
