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
            [clojure.java.io :as io])
  (:gen-class))

(defn download-file [uri file]
  (if (.exists (io/as-file file))
    file
    (with-open [in  (io/input-stream  uri)
                out (io/output-stream file)]
      (io/copy in out)
      file)))

(defn filename-extension [filename]
  "It receives a file name and returns the extension at the end of it. The argument
   could be a full path as long as it finishes with the file name. It returns null
   if no extension is fould."
  (let [point-pos (.lastIndexOf filename ".")]
    (when-not (neg? point-pos) (.toLowerCase (.substring filename (inc point-pos))))))

(defn filename-from-path [path]
  "Identifies and returns a file name present in a path."
  (let [point-pos (.lastIndexOf path ".")
        bar-pos   (.lastIndexOf path "/")]
    (when-not (neg? point-pos) (.substring path (inc bar-pos)))))

(defn pretty-output [text]
  "Formats a text to be beautifully printed by the repl."
  (loop [out (split text #"\n")]
    (if (empty? out)
      "--------------"
      (let [to-print (first out)]
        (println to-print)
        (recur (rest out))))))
