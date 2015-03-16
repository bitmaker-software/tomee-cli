# Apache TomEE-CLI

[![Dependencies Status](http://jarkeeper.com/bitmaker-software/tomee-cli/status.png)](http://jarkeeper.com/bitmaker-software/tomee-cli)
[![Build Status](https://travis-ci.org/bitmaker-software/tomee-cli.svg)](https://travis-ci.org/bitmaker-software/tomee-cli)
[![Release](http://img.shields.io/github/release/bitmaker-software/tomee-cli.svg)](https://github.com/bitmaker-software/tomee-cli/releases/latest)

This command line tool helps system administrators and developers to manage a instance of TomEE server.

## Installation

Follow the steps below to install TomEE-CLI:

1. Install and configure [JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

2. Install and configure [Git](http://git-scm.com/downloads).

3. Install and configure [Leiningen](https://leiningen.org).

2. Create the environment variable `TOMEE_HOME` pointing to the directory where TomEE is installed. It's optional, but it simplifies a lot the use of TomEE-CLI. Without it, you will have to inform the installation path everytime you call a function.

3. Clone the repository locally:


    $ git clone https://github.com/bitmaker-software/tomee-cli.git

It will create the directory `tomee-cli` in the current location.

## Checkstyle
To check code style in TomEE-CLI, enter in the directory and run:

    $ lein cljfmt test

To fix code style run:

    $ lein cljfmt fix

## Test

To run test in TomEE-CLI, enter in the directory and run:

    $ lein test

## Execution

To run TomEE-CLI, enter in the directory and start the REPL:

    $ cd tomee-cli
    $ lein repl

The REPL starts with all utility functions available by default in the namespace. You can simply type the functions or use the autocomplete (Tab) to find the one that fits your needs.

## Usage

Start server:

    tomee-cli.core=> (start "[path-to-tomee]")    // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (start)                      // With TOMEE_HOME variable defined.

Stop server:

    tomee-cli.core=> (stop "[path-to-tomee]")     // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (stop)                       // With TOMEE_HOME variable defined.

Restart server:

    tomee-cli.core=> (restart "[path-to-tomee]")  // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (restart)                    // With TOMEE_HOME variable defined.

Print versions of the environment:

    tomee-cli.core=> (version "[path-to-tomee]")  // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (version)                    // With TOMEE_HOME variable defined.

## License

Copyright 2015 Bitmaker Software LDA and/or its affiliates and other contributors as indicated by the @authors tag. All rights reserved.

Distributed under the Apache License V2.
