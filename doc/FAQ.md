## Frequently Asked Questions

* Why use Appium?
  * Appium has been created by the same person who created Tapster
  * Appium is quite powerful is we want to deal with mobile native or web apps with only one script
  * Appium makes possible the retrievement of coordinates of contact points during the calibration process

* How Appium can get the contents?
  * For Android, Appium will use a _bootstrap.jar_ and the _UI Automator_
  * For iOS, Appium will use a _Web Driver Agent Runner_ which will use _XCUITest_

* How the bot and the computer can communicate?
  * The bot possesses an [Arduino Uno](https://store.arduino.cc/arduino-uno-rev3 "Arduino's homepage") board
  * The bot and the computer are linked with a USB connection
  * The [Firmata](http://firmata.org/wiki/Main_Page "Firmata's wiki") protocol is used so as to make the communication. It has been flashed in the Arduino board, and embedded with [Johnny-Five](http://johnny-five.io/ "J5's homepage") in the computer side.
