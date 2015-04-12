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

(defproject com.bitmaker-software/tomee-cli "0.1.1-SNAPSHOT"
  :description "The fast way to configure Apache TomEE"
  :url "https://github.com/bitmaker-software/tomee-cli"
  :scm {:name "git"
        :dir  "."
        :url  "https://github.com/bitmaker-software/tomee-cli"
        :tag  "HEAD"}
  :license {:name "Apache License"
            :version "2.0"
            :url "http://www.apache.org/licenses/LICENSE-2.0"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [environ "1.0.0"]]
  :plugins [[lein-cljfmt "0.1.10"]
            [lein-environ "1.0.0"]
            [lein-release "1.0.5"]]
  :lein-release {:scm :git
                 :deploy-via :lein-install}
  :main ^:skip-aot tomee-cli.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :test {:env {:tomee-home "resources"}}
             :dev {:plugins [[lein-kibit "0.0.8"]] }})
