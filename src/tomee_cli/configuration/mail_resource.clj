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

(ns ^{:author "Daniel Cunha (soro) <daniel.cunha@bitmaker-software.com>"}
 tomee-cli.configuration.mail-resource
  (:require [tomee-cli.resources   :refer (add-new-resource xml-with-out-str define-resource) :as resource]
            [tomee-cli.environment :refer (tomee-home) :as environment]))


(defn define-mail-resource
  "Define a mail resorce"
  [id host port protocol auth user password]
  (let [content (str "mail.smtp.host=" host "\nmail.smtp.port=" port "\nmail.transport.protocol=" protocol "\nmail.smtp.auth=" auth "\nmail.smtp.user=" user "\npassword=" password)]
    (resource/define-resource id "javax.mail.Session" content)))

(defn add-mail-resource
  "Write mail resource in tomee.xml"
  [& {:keys [path id host port protocol auth user password]
      :or {path environment/tomee-home password "" port 25}}]
  (resource/add-new-resource path (define-mail-resource id host port protocol auth user password)))
