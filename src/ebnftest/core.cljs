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
(defn- failure->string 
  "Convert an instaparse failure object to a string."
  [result]
  (with-out-str (fail/pprint-failure result)))

(defn set-html! 
  "Set the text content of an element."
  [el content]
  (set! (.-innerText el) content))

(defn set-error! 
  "Sets the output area class to invalid, then sets the content."
  [content]
  (let [textout (dom/getElement "output")]
    (classes/set textout "invalid")
    (set-html! textout content)))

(defn set-result! 
  "Sets the output area class to valid, then sets the content."
  [content]
  (let [textout (dom/getElement "output")]
    (classes/set textout "valid")
    (set-html! textout content)))
  
(defn grammar 
  "Get the current grammar text."
  [] 
  (.-value (dom/getElement "grammar")))
  
(defn testtext
  "Get the current input text."
  [] 
  (.-value (dom/getElement "input")))

(defn show-result
  "Show the current parse result in the output area."
  [result]
  (if (insta/failure? result)
    (set-error! (failure->string result))
    (set-result! "The test input is valid.")))
    
(defn newtext
  "Process new user input in either grammar or test text values."
  [evt]
  (try
    (let [aparser (insta/parser (grammar))]
      (show-result (aparser (testtext))))
    (catch :default err (set-error! err))))
    
; Set event listeners on the input areas.
; The output is updated every time there's a change in input.
(events/listen (dom/getElement "grammar") (.-INPUT events/EventType) newtext)
(events/listen (dom/getElement "input") (.-INPUT events/EventType) newtext)

; Process the initial text.
(newtext nil)




      


