# Apache TomEE-CLI

[![Join the chat at https://gitter.im/bitmaker-software/tomee-cli](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/bitmaker-software/tomee-cli?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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

    $ lein cljfmt check

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

### Start server:

    tomee-cli.core=> (start "[path-to-tomee]")    // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (start)                      // With TOMEE_HOME variable defined.

### Stop server:

    tomee-cli.core=> (stop "[path-to-tomee]")     // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (stop)                       // With TOMEE_HOME variable defined.

### Restart server:

    tomee-cli.core=> (restart "[path-to-tomee]")  // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (restart)                    // With TOMEE_HOME variable defined.

### Print versions of the environment:

    tomee-cli.core=> (version "[path-to-tomee]")  // Without TOMEE_HOME variable defined.
    tomee-cli.core=> (version)                    // With TOMEE_HOME variable defined.

### Deploy application:

    tomee-cli.core=> (deploy "[path-to-tomee]" "[path-to-war/ear-file]")
    tomee-cli.core=> (deploy "[path-to-war/ear-file]")

### Undeploy application:

    tomee-cli.core=> (undeploy "[path-to-tomee]" "[war/ear-file]")
    tomee-cli.core=> (undeploy "[war/ear-file]")

### Add new mail resource in tomee.xml:

    tomee-cli.core=> (add-new-mail-resource :path "[TOMEE_HOME]" :id "[id-to-resource]" :host "[host-to-email-server]" :port "[port-to-email-server]" :protocol "[protocol-to-email-server]" :auth "[true/false]" :user "[username]" :password "[password]")

The parameters don't have ordenation. You need only specify the key, like the sample.

:port has 25 as default value.

:password has "" as default value.

:path has TOMEE\_HOME as default value.

### Add new datasource in tomee.xml:

    tomee-cli.core=> (add-new-datasource-resource :path "[TOMEE_HOME]" :id "[id-to-resource]" :jdbc-drive "[jdbc-drive]" :jdbc-url "[jdbc-url]" :username "[username]" :password "[password]" :jta-managed "[jta-managed]"])

The parameters don't have ordenation. You need only specify the key, like the sample.

:jta-managed has true as default value.

:path has TOMEE\_HOME as default value.

## License

Copyright 2015 Bitmaker Software LDA and/or its affiliates and other contributors as indicated by the @authors tag. All rights reserved.

Distributed under the Apache License V2.
