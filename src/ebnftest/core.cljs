(ns ebnftest.core
  (:require [clojure.browser.repl :as repl]
            [instaparse.core :as insta]
            [instaparse.failure :as fail]
            [goog.dom :as dom]
            [goog.events :as events]
            [goog.dom.classes :as classes]))

;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))

(enable-console-print!)
  
; pretty-print a failure as a string
(defn- failure->string [result]
  (with-out-str (fail/pprint-failure result)))

(defn set-html! [el content]
  (set! (.-innerHTML el) content))
  
(defn set-elname! [nm content]
  (set-html! (dom/getElement nm) content))

(defn set-error! [content]
  (let [textout (dom/getElement "output")]
    (classes/set textout "invalid")
    (set-html! textout content)))

(defn set-result! [content]
  (let [textout (dom/getElement "output")]
    (classes/set textout "valid")
    (set-html! textout content)))
  
(defn grammar [] 
  (.-value (dom/getElement "grammar")))
  
(defn testtext [] 
  (.-value (dom/getElement "input")))

(defn show-result [result]
  (if (insta/failure? result)
    (set-error! (failure->string result))
    (set-result! "The test input is valid.")))
    
(defn newtext [evt]
  (try
    (let [aparser (insta/parser (grammar))]
      (show-result (aparser (testtext))))
    (catch :default err (set-error! err))))
    
(events/listen (dom/getElement "grammar") (.-INPUT events/EventType) newtext)
(events/listen (dom/getElement "input") (.-INPUT events/EventType) newtext)

(newtext nil)




      


