# How to calibrate the bot

## Run the following commands

1 - Start the server for the bot
```shell
	node server.js
```

2 - Then start Appium with the calibration app and the desired capabilities.

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
    "autoLaunch": "true",
    "appPackage": "pylapp.tapster.calibration.android",
    "appActivity": "pylapp.tapster.calibration.android.MainActivity"
  }'
```

Be sure you have adb in your PATH
```shell
  PATH=$PATH:path-to-Android/sdk/platform-tools
```

3 - Run the calibration process
```shell
	node calibrate.js -o calibration.json
```

Be sure you have previously filled the JSON capabilities file available in the _capabilities_ folder.


## I didn't all understand

The calibration workflow is necessary because the bot cannot see where is the device to click on.
If the screen is not aligned to the plate, or if it is too big, the bot will not be warned.
So it will be quite possible to have the bot click outside the screen.  

The server run in step 1 is used to drive the robot. It listens HTTP requests sent by the calibration script.
You can see it as the "brain" of the bot.  

The calibration script run in step 2 drives the calibration workflow.
Basically it will set up the positions of the arms, and then will calibrate the bot to the device.
Indeed the robot needs to make a match between its 3D landmark (Tapster is based on delta-bots) and the 2D landmark of the device.
Calibration is needed because bot's 3D coordinates (X, Y, Z) can match to (x1, y1) on a small screen and to (x2, y2) on a wider screen with x1 != x2 and y1 != y2.  

The last part (step 3) is about Appium. The bot cannot see the device, there is no camera. And the calibration workflow makes the bot move its arms on the screen.
So Appium is here so as to manage a calibration app which will displays the 2D effectice coordinates, i.e. the coordinates of the contact point.
Then the calibration script will get through Appium the coordinates displayed by the app.  

Thus the calibration workflow will make the match between (x, y) theorical/wanted 2D coordinates, the bot's (X, Y, Z) 3D coordinates, and the final real (x', y') 2D coordinates touched by the bot.
Be aware that (x, y) and (x', y') might not be equals. Do you click always on the same pixel ? ;-D
