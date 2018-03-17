(ns ^:figwheel-no-load cosm.dev
  (:require
    [cosm.core :as core]
    [devtools.core :as devtools]))

(devtools/install!)

(enable-console-print!)

(core/init!)
