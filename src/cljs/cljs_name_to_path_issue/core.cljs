(ns cljs-name-to-path-issue.core
    (:require [reagent.core :as r :refer [atom]]
              [secretary.core :as secretary :include-macros true]
              [accountant.core :as accountant]
              [deat-mui-core :as mui]))
;; -------------------------
;; Reagent wrappers

(def create-theme (.-createMuiTheme mui))
(def theme-provider (r/adapt-react-class (.-MuiThemeProvider mui)))
(def css-baseline (r/adapt-react-class (.-CssBaseline mui)))
(def t (r/adapt-react-class (.-Typography mui)))

(def theme-map (clj->js {:palette    {:primary   {:light         "#ffffff"
                                                  :main          "#eeeeee"
                                                  :dark          "#bcbcbc"
                                                  :contrast-text "#000000"}
                                      :secondary {:ligh          "#ff7961"
                                                  :main          "#f44336"
                                                  :dark          "#ba000d"
                                                  :contrast-text "#000000"}}
                         :typography {:useNextVariants true}}))

(def theme (mui/create-theme theme-map))

;; -------------------------
;; Views

(defn home-page []
  [:div
   [t {:variant 'h1} "Welcome to cljs-name-to-path-issue"]
   [:div [:a {:href "/about"} "go to about page"]]])

(defn about-page []
  [:div
   [t {:variant 'h1} "About cljs-name-to-path-issue"]
   [:div [:a {:href "/"} "go to the home page"]]])

;; -------------------------
;; Routes

(defonce page (atom #'home-page))

(defn current-page []
  [css-baseline
   theme-provider
   [@page]])

(secretary/defroute "/" []
  (reset! page #'home-page))

(secretary/defroute "/about" []
  (reset! page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
