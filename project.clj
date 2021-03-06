(defproject adventofcode "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.170"]
                 [org.clojure/math.combinatorics "0.1.1"]
                 [cheshire "5.5.0"]
                 [com.taoensso/timbre "4.1.4"]
                 ]
  :main ^:skip-aot adventofcode.core
  :plugins [[lein-cljsbuild "1.1.1-SNAPSHOT"]
            ]
  :jvm-opts ["-XX:+TieredCompilation" "-XX:TieredStopAtLevel=1"]
  :bootclasspath true
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src"]

                        :compiler {:main adventofcode.day10
                                   :output-to "target/app.js"
                                   :output-dir "target/out"
                                   :target :nodejs
                                   :optimizations :advanced
                                   :source-map-timestamp true}}]}
  )
