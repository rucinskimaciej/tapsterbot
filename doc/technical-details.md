# Technical details you should know

## If you are using Tapster with Appium
- Do not install Appium or Node.js with sudo
- Since massive refactoring (1.4 -> 1.5), only Appium 1.4 supports Tapster bot but this version is deprecated
- Work is in progress to add drivers to Appium
- You can read the following thread: https://discuss.appium.io/t/integration-of-tapster-2-to-appium/19010
- You can read the following issue: https://github.com/appium/appium/issues/9367
- Feel free to help the community behind Tapster so as to bring fun, automation and bot on your desk!

## Dependencies for bot Node.js server
- Node.js (8.6.0) (required)
- npm (5.3.0) (required)
- nvm (0.33.4)
- one Tapster robot (OpenSCAD, STL, JavaScript and other files available in this repo)
- hapi.js (>= v17)

## Dependencies for Python client
 - requests (2.18.4)

## Dependencies for Robot Framework keywords client
 - robotframework-requests (0.4.7)

## Some commands to run to install Node.js, third-party libraries and other dependencies
```shell
  npm install argparse;                       # Version 1.0.9
  npm install hapi;                           # Version 17.2.0
  npm install johnny-five;                    # Version 0.13.0
  npm install sylvester;                      # Version 0.0.21
  npm install prompt;                         # Version 1.0.0
  npm install wd;                             # Version 1.5.0
  npm install fs;                             # version 0.0.2
  npm install os;                             # Version 0.1.1
  npm install path;                           # Version 0.12.7
  npm install svg-path-parser;                # Version 1.1.0
  npm install xml2js;                         # Version 0.4.19
  npm install express;                        # Version 4.16.2
  npm install body-parser;                    # Version 1.18.2
  pip install -U requests;                    # Version 2.18.4
  pip install -U robotframework-requests;     # Version 0.4.7
```
You can also run
```shell
  npm install
```
in folders having a _package.json_ file.

## Warning
The [hapi](https://hapijs.com/ "Go to hapi") dependence had a major evolution with its [v17](https://github.com/hapijs/hapi/issues/3658 "Breaking changes"), and the "legacy" server.js in the orignal tapsterbot repository won't work.
So be sure you have installed at least v17 for hapi. For more details see those 2 issues on GitHub ([here](https://github.com/hapijs/discuss/issues/567) and [here](https://github.com/hapijs/hapi/issues/3697)).
