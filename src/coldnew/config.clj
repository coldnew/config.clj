(ns coldnew.config
  (:require [coldnew.config.impl :as impl]))

(defn enable-eval!
  "Set *enable-eval* to true.
  When set this, your config.edn will be evaluate after read."
  []
  (reset! impl/*enable-eval* true)
  ;; refresh variables
  (def env (impl/load-env))
  (def conf (impl/load-conf)))

(def ^{:doc "A map of configuration and environment variables.
This variable is resolved in the following order, the variables found in later weill replace tholse declared eariler:

1. `config.edn` on the classpath.
2. EDN file specified using the `config` environment variable.
3. `.lein-env` file in the project directory.
4. `.boot-env` file in the project directory.
5. Environment vairables.
6. Java system propertirs."
       :doc/format :markdown}
  env
  (impl/load-env))

(def ^{:doc "A map of environment and configuration variables.
This variable is resolved in the following order, the variables found in later weill replace tholse declared eariler:

1. `.lein-env` file in the project directory.
2. `.boot-env` file in the project directory.
3. Environment vairables.
4. Java system propertirs.
5. `config.edn` on the classpath.
6. EDN file specified using the `config` environment variable."
       :doc/format :markdown}
  conf
  (impl/load-conf))