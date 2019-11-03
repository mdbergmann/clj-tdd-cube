(ns clj-tdd-cube.ui
  (:use seesaw.core))

(def *state (atom :red))
(def *components (atom {:phase-box nil}))

(defn new-phase [phase]
  (println "old phase: "phase)
  (case phase
    :red :green
    :green :yellow
    :yellow :red))

(defn phase-to-col [phase]
  (case phase
    :red java.awt.Color/RED
    :green java.awt.Color/GREEN
    :yellow java.awt.Color/YELLOW))

(defn update-phase-box [phase component]
  (config! component :background (phase-to-col phase))
  (repaint! component))

(defn content []
  (let [phase-button (button :text "Next" :border 10)
        phase-box (canvas :size [200 :by 200] :background (phase-to-col @*state))
        panel (border-panel
               :center (vertical-panel
                        :items [phase-box])
               :south (horizontal-panel
                       :items [phase-button]))]

    (swap! *components #(assoc % :phase-box phase-box))
    
    (listen phase-button :action
            (fn [e]
              (println "button")
              (swap! *state new-phase)
              (println "new phase: " @*state)
              (update-phase-box @*state phase-box)))
    panel))

(defn start-ui []
  (invoke-later
   (let [f (frame :title "TDD cube",
              :width 300,
              :height 100,
              :resizable? false,
              :content (content),
              :on-close :exit)]
     (.setAlwaysOnTop f true)
     (-> f
         pack!
         show!))))
