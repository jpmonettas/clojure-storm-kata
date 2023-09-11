(ns clojure-storm-kata.core
  (:import (io.getunleash DefaultUnleash FakeUnleash)
           (io.getunleash.strategy Strategy)
           (io.getunleash.util UnleashConfig UnleashScheduledExecutor)
           (java.util.concurrent Executors ScheduledExecutorService TimeUnit)))

(defn scheduled-executor
  [^ScheduledExecutorService service]
  (reify UnleashScheduledExecutor
    (setInterval [_ command initialDelaySec periodSec]
      (.scheduleAtFixedRate service command initialDelaySec periodSec TimeUnit/SECONDS))
    (scheduleOnce [_ command]
      (future-call command))))

(defn unleash [_ {:keys [test? environment instance-id endpoint]}]
  (if test?
    {:unleash (FakeUnleash.)}
    (let [service (Executors/newScheduledThreadPool 1)

          config  (-> (UnleashConfig/builder)
                      (.scheduledExecutor (scheduled-executor service))
                      (.appName environment)
                      (.instanceId instance-id)
                      (.unleashAPI ^String endpoint)
                      (.build))]
      {:unleash (DefaultUnleash. config (into-array Strategy []))
       :service service})))

(defn -main []
  (unleash nil {:endpoint "http://false.false"
                :instance-id "smth"
                :environment "smth"}))
