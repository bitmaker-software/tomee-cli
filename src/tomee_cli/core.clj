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

(ns ^{:author "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com>,
               Hildeberto Mendonça <hildeberto.com>"}
 tomee-cli.core
  (:require [tomee-cli.configuration.mail-resource     :refer (add-new-mail-resource)]
            [tomee-cli.configuration.database-resource :refer (add-new-datasource-resource)]
            [tomee-cli.deployment                      :refer (deploy undeploy)]
            [tomee-cli.environment                     :refer (version)]
            [tomee-cli.execution                       :refer (start stop restart)])
  (:gen-class))
