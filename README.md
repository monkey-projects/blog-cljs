# Monkey Blog (cljs version)

This is a full [ClojureScript](https://clojurescript.org/) version of Monkey blog app.
The blog app is very simple, it allows you to create a new entry for a given date,
search the entries and attach images to it.  Backend storage is
[Firebase](https://firebase.google.com), which allows to create the app as a
ClojureScript-only application.  The intention is to make the build process fully
ClojureScript as well, using [Monkey-CI](https://monkey-ci.com) as a build app.

The application will deploy to the [Monkey Blog](https://blog.monkey-projects.be),
and of course this will be fully automated.