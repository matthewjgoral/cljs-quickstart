(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources/public"}

  :dependencies '[[org.clojure/clojure "1.8.0"]
  [org.clojure/clojurescript "1.9.542"]
  [adzerk/boot-cljs "2.0.0"]
  [pandeiro/boot-http "0.8.3"]
  [org.clojure/tools.nrepl "0.2.12"] ;;required in order to make boot-http works
  [adzerk/boot-reload "0.5.1"]
  [adzerk/boot-cljs-repl "0.3.3"]
  [com.cemerick/piggieback "0.2.1"]
  [weasel "0.7.0"]])

(require '[adzerk.boot-cljs :refer [cljs]]
'[pandeiro.boot-http :refer [serve]]    ;; make serve task visible
'[adzerk.boot-reload :refer [reload]]
'[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])


;; define dev task as composition of subtasks
(deftask dev
  "Launch dev environment"
  []
  (comp
    (serve :dir "target" :httpkit true)
    (watch)
    (reload)
    (cljs-repl) ;; before cljs task
    (cljs)
    (target :dir #{"target"})))
