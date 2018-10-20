(ns cljs-name-to-path-issue.prod
  (:require [cljs-name-to-path-issue.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
