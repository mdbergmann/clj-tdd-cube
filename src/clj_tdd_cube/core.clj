(ns clj-tdd-cube.core
  (:gen-class)
  (:require clj-tdd-cube.ui))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!")
  (clj-tdd-cube.ui/start-ui))
