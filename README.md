# Monkey Blog (cljs version)

This is a full [ClojureScript](https://clojurescript.org/) version of Monkey blog app.
The blog app is very simple, it allows you to create a new entry for a given date,
search the entries and attach images to it.  Backend storage is
[Firebase](https://firebase.google.com), which allows to create the app as a
ClojureScript-only application.  The intention is to make the build process fully
ClojureScript as well, using [Monkey-CI](https://monkey-ci.com) as a build app.

The application will deploy to the [Monkey Blog](https://blog.monkey-projects.be),
and of course this will be fully automated.

## Building

To build the application, run it using [shadow-cljs](https://shadow-cljs.github.io/docs/UsersGuide.html):

```bash
npx shadow-cljs compile app
```

## Testing

You can run the unit tests in the browser, or on the command line:

```bash
# Browser testing
npx shadow-cljs watch test
# You can go to http://localhost:8000 to see the results
```

Running the tests on command line is useful for the CI pipeline:

```bash
# First compile to a js file
npx shadow-cljs compile test-ci
# Now run them in node
node out/node-tests.js
```

## Contributing

You are all free to contribute, but please use test-driven development, 'cause to
paraphrase Winston Churchill, it's the least bad way to write software!
