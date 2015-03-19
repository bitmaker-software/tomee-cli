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
            [clojure.java.io :refer (copy input-stream output-stream reader writer as-file)]))

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
    (if (< point-pos 0)
      nil
      (.toLowerCase (.substring filename (inc point-pos))))))

(defn filename-from-path [path]
  "Identifies and returns a file name present in a path."
  (let [point-pos (.lastIndexOf path ".")
        bar-pos   (.lastIndexOf path "/")]
    (if (< point-pos 0)
      nil
      (.substring path (inc bar-pos)))))

(defn copy-uri-to-file [uri file]
  (with-open [in (input-stream uri)
              out (output-stream file)]
    (copy in out)
    file))

(defn unzip-file [zip-file destination]
  (with-open [i (reader (java.util.zip.GZIPInputStream. (input-stream (as-file zip-file))))
              o (java.io.PrintWriter. (writer (as-file destination)))]
    (doseq [l (line-seq i)]
      (.println o l))))
