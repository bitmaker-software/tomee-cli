(ns tomee-cli.core
  (:require [clojure.java.shell :refer (sh)])
  (:gen-class))

(defn startup []
  (sh "../bin/startup.sh"))

(defn shutdown []
  (sh "../bin/shutdown.sh"))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
