(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'ebnftest.core
   :output-to "out/ebnftest.js"
   :output-dir "out"})
