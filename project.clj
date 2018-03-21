(defproject cosm "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.9.0"]
                 [ring-server "0.5.0"]
                 [reagent "0.7.0"]
                 [reagent-utils "0.3.1"]
                 [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [compojure "1.6.0"]
                 [hiccup "1.0.5"]
                 [yogthos/config "1.1"]
                 [org.clojure/clojurescript "1.9.946"
                  :scope "provided"]
                 [cljs-http "0.1.44"]
                 [secretary "1.2.3"]
                 [venantius/accountant "0.2.4"
                  :exclusions [org.clojure/tools.reader]]
                 [cljs-react-material-ui "0.2.48"]
                 [cljsjs/react "15.6.1-1"]
                 [cljsjs/react-dom "15.6.1-1"]
                 [com.datomic/datomic-pro "0.9.5661"
                  :exclusions [com.google.guava/guava]]]

  :plugins [[lein-environ "1.1.0"]
            [lein-cljsbuild "1.1.7"]
            [lein-asset-minifier "0.2.7"
             :exclusions [org.clojure/clojure]]]

  :ring {:handler cosm.handler/app
         :uberwar-name "cosm.war"}

  :min-lein-version "2.5.0"

  :uberjar-name "cosm.jar"

  :main cosm.server

  :clean-targets ^{:protect false}
  [:target-path
   [:cljsbuild :builds :app :compiler :output-dir]
   [:cljsbuild :builds :app :compiler :output-to]]

  :source-paths ["src/clj"]
  :resource-paths ["resources" "target/cljsbuild"]

  :minify-assets
  {:assets
   {"resources/public/css/site.min.css" "resources/public/css/site.css"}}

  :cljsbuild
  {:builds {:min
            {:source-paths ["src/cljs" "env/prod/cljs"]
             :compiler
             {:output-to        "target/cljsbuild/public/js/app.js"
              :output-dir       "target/cljsbuild/public/js"
              :source-map       "target/cljsbuild/public/js/app.js.map"
              :optimizations :advanced
              :pretty-print  false}}
            :app
            {:source-paths ["src/cljs" "env/dev/cljs"]
             :figwheel {:on-jsload "cosm.core/mount-root"}
             :compiler
             {:main "cosm.dev"
              :asset-path "/js/out"
              :output-to "target/cljsbuild/public/js/app.js"
              :output-dir "target/cljsbuild/public/js/out"
              :source-map true
              :optimizations :none
              :pretty-print  true}}}} 
  :figwheel
  {:http-server-root "public"
   :server-port 3449
   :nrepl-port 7002
   :nrepl-middleware ["cemerick.piggieback/wrap-cljs-repl"
                      ]
   :css-dirs ["resources/public/css"]
   :ring-handler cosm.handler/app}



  :profiles {:dev {:repl-options {:init-ns cosm.repl
                                  :nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}

                   :dependencies [[binaryage/devtools "0.9.9"]
                                  [ring/ring-mock "0.3.2"]
                                  [ring/ring-devel "1.6.3"]
                                  [prone "1.5.0"]
                                  [figwheel-sidecar "0.5.15"]
                                  [org.clojure/tools.nrepl "0.2.13"]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [pjstadig/humane-test-output "0.8.3"]
                                  
 ]

                   :source-paths ["env/dev/clj"]
                   :plugins [[lein-figwheel "0.5.15"]
]

                   :injections [(require 'pjstadig.humane-test-output)
                                (pjstadig.humane-test-output/activate!)]

                   :env {:dev true}}

             :uberjar {:hooks [minify-assets.plugin/hooks]
                       :source-paths ["env/prod/clj"]
                       :prep-tasks ["compile" ["cljsbuild" "once" "min"]]
                       :env {:production true}
                       :aot :all
                       :omit-source true}}

  :repositories {"my.datomic.com" {:url "https://my.datomic.com/repo"}})
