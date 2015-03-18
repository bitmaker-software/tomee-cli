# Apache TomEE-CLI

[![Join the chat at https://gitter.im/bitmaker-software/tomee-cli](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/bitmaker-software/tomee-cli?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

[![Dependencies Status](http://jarkeeper.com/bitmaker-software/tomee-cli/status.png)](http://jarkeeper.com/bitmaker-software/tomee-cli)
[![Build Status](https://travis-ci.org/bitmaker-software/tomee-cli.svg)](https://travis-ci.org/bitmaker-software/tomee-cli)
[![Release](http://img.shields.io/github/release/bitmaker-software/tomee-cli.svg)](https://github.com/bitmaker-software/tomee-cli/releases/latest)

This command line tool helps system administrators and developers to manage a instance of TomEE server.

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

## Documentation

https://github.com/bitmaker-software/tomee-cli/wiki

## License

Copyright 2015 Bitmaker Software LDA and/or its affiliates and other contributors as indicated by the @authors tag. All rights reserved.

Distributed under the Apache License V2.
