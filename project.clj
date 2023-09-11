(defproject clojure-storm-kata "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :profiles {:dev {:jvm-opts ["-Djdk.attach.allowAttachSelf"
                              "-XX:+UnlockDiagnosticVMOptions" "-XX:+DebugNonSafepoints" "-XX:-OmitStackTraceInFastThrow"
                              "-Dflowstorm.startRecording=false"
                              "-Dclojure.storm.instrumentEnable=true"
                              "-Dclojure.storm.instrumentOnlyPrefixes=backend-api.,bimsystems."]
                   :exclusions [org.clojure/clojure]
                   :dependencies [[com.github.jpmonettas/clojure "1.12.0-master-SNAPSHOT"]]}}
  :dependencies [[io.getunleash/unleash-client-java "6.0.1"]]
  :repl-options {:init-ns clojure-storm-kata.core})