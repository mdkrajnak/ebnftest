# EBNF Test

EBNF Test is a simple web page that lets you enter an EBNF grammar,
and some text to test, and it evaluates the test text against the given 
grammar.


## Overview

A self contained web page that wraps the clojurescript port of 
instaparse that allows you to enter and evaluate EBNF grammers.

This project was inspired by online PEG evaluation page for [PEG.js](http://pegjs.org/online), 
which is much nicer than this project, but I had a requirement for EBNF.

This project uses the [clojurescipt port](https://github.com/lbradstreet/instaparse-cljs)
of [instaparse](https://github.com/Engelberg/instaparse) which provides
an excellent EBNF parser with one quirk: regexs must be quoted as clojure
regex literals. For example the regex for any character A-Z, \[A-Z\], is
entered as #'\[A-Z\]'.

## Setup

This page was developed using clojurescript and starting with the mies
template. 

The rest of the README comes from the default template and covers 
developing with this project.

Most of the following scripts require [rlwrap](http://utopia.knoware.nl/~hlub/uck/rlwrap/) (on OS X installable via brew).

Build your project once in dev mode with the following script and then open `index.html` in your browser.

    ./scripts/build

To auto build your project in dev mode:

    ./scripts/watch

To start an auto-building Node REPL:

    ./scripts/repl

To get source map support in the Node REPL:

    lein npm install
    
To start a browser REPL:
    
1. Uncomment the following lines in src/ebnftest/core.cljs:
```clojure
;; (defonce conn
;;   (repl/connect "http://localhost:9000/repl"))
```
2. Run `./scripts/brepl`
3. Browse to `http://localhost:9000` (you should see `Hello world!` in the web console)
4. (back to step 3) you should now see the REPL prompt: `cljs.user=>`
5. You may now evaluate ClojureScript statements in the browser context.
    
For more info using the browser as a REPL environment, see
[this](https://github.com/clojure/clojurescript/wiki/The-REPL-and-Evaluation-Environments#browser-as-evaluation-environment).
    
Clean project specific out:

    lein clean
     
Build a single release artifact with the following script and then open `index_release.html` in your browser.

    ./scripts/release

## License

Copyright © 2016 Michael Krajnak

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
