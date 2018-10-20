(ns ^:figwheel-no-load cljs-name-to-path-issue.dev
  (:require
    [cljs-name-to-path-issue.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
