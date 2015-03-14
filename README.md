# Apache TomEE-CLI

[![Build Status](https://travis-ci.org/bitmaker-software/tomee-cli.svg)](https://travis-ci.org/bitmaker-software/tomee-cli)
[![Release](http://img.shields.io/github/release/bitmaker-software/tomee-cli.svg)](https://github.com/bitmaker-software/tomee-cli/releases/latest)

This command line tool helps system administrators and developers to manage a instance of TomEE server.

## Usage

We assume you already have JDK 8, Git and [Leiningen](https://leiningen.org) installed and configured. Follow the steps below to use the tool:

Clone the repository locally:

    $ git clone https://github.com/bitmaker-software/tomee-cli.git

It will create the directory `tomee-cli` in the current location. Enter in the directory and start the REPL:

    #> cd tomee-cli
    #> lein repl

The REPL starts with all utility functions available by default in the namespace. You can simply type the functions or use the autocomplete (Tab) to find the one that fits your needs. Example:

    tomee-cli.core=> (start "[TOMEE_HOME]")

## Features

- Start Apache TomEE   -> (start   "[TOMEE_HOME]")
- Stop Apache TomEE    -> (stop    "[TOMEE_HOME]")
- Restart Apache TomEE -> (restart "[TOMEE_HOME]")

## License

Copyright 2015 Bitmaker Software LDA and/or its affiliates and other contributors as indicated by the @authors tag. All rights reserved.

Distributed under the Apache License V2.
