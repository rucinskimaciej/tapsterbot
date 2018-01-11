# Technical details you should know

## If you are using Tapster with Appium
- Do not install Appium or Node.js with sudo
- Since massive refactoring (1.4 -> 1.5), only Appium 1.4 supports Tapster bot
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
 - requests

## Some commands to run to install Node.js libs
```shell
  npm install argparse;
  npm install hapi;
  npm install johnny-five;
  npm install sylvester;
  npm install prompt;
  npm install wd;
```

## Warning
The [hapi](https://hapijs.com/ "Go to hapi") dependence had a major evolution with its [v17](https://github.com/hapijs/hapi/issues/3658 "Breaking changes"), and the "legacy" server.js in the orignal tapsterbot repository won't work.
So be sure you have installed at least v17 for hapi. For more details see those 2 issues on GitHub ([here](https://github.com/hapijs/discuss/issues/567) and [here](https://github.com/hapijs/hapi/issues/3697)).
