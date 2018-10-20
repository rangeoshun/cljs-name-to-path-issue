# cljs-name-to-path-issue

A repro repo for clojurescript handling required directories incorrectly.

To run just

```
lein figwheel
```

Open brower: `http://0.0.0.0:3449`

Open up console and behold:

```
base.js:1357 Uncaught Error: Undefined nameToPath for module$Users$range$Documents$cljs_name_to_path_issue$node_modules$deat_mui_core$colors
    at visitNode (base.js:1357)
    at visitNode (base.js:1355)
    at visitNode (base.js:1355)
    at Object.goog.writeScripts_ (base.js:1369)
    at Object.goog.require (base.js:706)
    at (index):2
```
