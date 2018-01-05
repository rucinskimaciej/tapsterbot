# Tapster - Mobile Automation Robot

![A Taspter2 bot](desktop.jpeg)

# Why using such bot?
- Bring fun in your office
- Gring automation four your tests
- Extend your software-based instrumented tests (UI Automator, Espresso, Kakao, Appium or whatever) with hardware-based tests

# If you are using Tapster with Appium
- Do not install Appium or Node.js with sudo
- Since massive refactoring (1.4 -> 1.5), only Appium 1.4 supports Tapster bot
- Work is in progress to add drivers to Appium
- You can read the following thread: https://discuss.appium.io/t/integration-of-tapster-2-to-appium/19010
- You can read the following issue: https://github.com/appium/appium/issues/9367
- Feel free to help the community behind Tapster so as to bring fun, automation and bot on your desk!

# Some references and things to see
- http://www.tapster.io (the creator of the tapster bots)
- https://twitter.com/tapsterbot
- https://www.tindie.com/products/hugs/tapster/
- https://github.com/hugs/tapsterbot
- https://github.com/tapsterbot/tapsterbot
- https://github.com/appium/appium
- https://github.com/appium/robots
- https://github.com/penguinho/tapsterbot
- https://github.com/jackskalitzky/tapsterbot
- https://speakerdeck.com/pylapp/why-not-tapster

# Dependencies
- Node.js (8.6.0) (required)
- npm (5.3.0) (required)
- nvm (0.33.4)
- one Tapster robot (OpenSCAD, STL, JavaScript and other files available in this repo)

# Some commands to run to install Node.js libs
```shell
  npm install argparse;
  npm install hapi;
  npm install johnny-five;
  npm install sylvester;
  npm install prompt;
  npm install wd;
```

# Some commands to run to calibrate the bot
1 - Start the bot server
```shell
	node server.js
```

2 - Then start Appium with the calibration app and the desired capabilities

For iOS, keep in mind you have to build the app with certificates, purchased Apple developper account...
```shell
  appium --pre-launch --default-capabilities '{
    "udid": "UDID of your iOS device",
    "app": "pylapp.tapster.calibration.ios.RobotCalibration",
    "platformName": "iOS",
    "deviceName": "the model of your device",
    "platformVersion": "the version of the OS",
    "xcodeSigningId": "iPhone Developer (or something else you use)",
    "xcodeOrgId": "the TeamId you can get from your purchased Apple developper account"
  }'
```

For Android
```shell
  appium --pre-launch --default-capabilities '{
    "app": "path to the APK of the Android app",
    "platformName": "Android",
    "platformVersion": "the version of the OS",
    "deviceName": "a name for your device",
    "autoLaunch": "true"
    "appPackage": "pylapp.tapster.calibration.android",
    "appActivity": "pylapp.tapster.calibration.android.MainActivity"
  }'
```

3 - Run the calibration process
```shell
	node calibrate.js -o calibration.json
```

# Getting Started
1. Download and install the Arduino Client: [http://arduino.cc/en/Guide/HomePage](http://arduino.cc/en/Guide/HomePage)
2. Follow the setup procedure, and use the "blink" test script to verify your Arduino is working.
3. From the Arduino IDE upload the "Firmata" script: File -> Open -> Examples > Firmata > StandardFirmata
4. Install the dependencies: `cd software; npm install`
5. Start'er up: `node src/bot.js`
6. Start moving: `go(0,0,-140)`
