;;Copyright 2015 Daniel Cunha (soro) and/or its affiliates and other contributors
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
(ns ^{:author "Daniel Cunha (soro) <danielsoro@gmail.com>"}
  tomee-cli.configuration.mail-resource
  (:gen-class))

(defn create-mail-resource "Create a new mail resource in TomEE"
  [id host port protocol is-auth user password]
  (str "<Resource id=\""id "\" type=\"javax.mail.Session\">"
       "\nmail.smtp.host="host
       "\nmail.smtp.port="port
       "\nmail.transport.protocol="protocol
       "\nmail.smtp.auth="is-auth
       "\nmail.smtp.user="user
       "\npassword="password
       "\n</Resource>"))
