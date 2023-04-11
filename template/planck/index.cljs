#!/usr/bin/env planck

(require '[fn.handler :as function]
         '[planck.core :refer [read-line]])

(defn read-lines
  [acc]
  (let [line (read-line)]
    (if line
      (recur (str acc line))
      acc)))

(defn main []
  (let [input (read-lines "")
        output (function/handler input)]
    (println output)))

(main)
