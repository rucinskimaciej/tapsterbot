## Frequently Asked Questions

* Who has created the Tapster bot ?
  * Have a look on [Tapster ](http://tapster.io/ "Tapster.io website")

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

* I sent a "tap" command to the bot: it moves to (x,y) but did not hit the screen, why?
  * Check the Z value defined in you calibraiton used fiven as a parameter to the server
  * The system will use the lowest value between the Z of the contact point, the Z of the point1 and the Z on the point2, so you can make contactPoint.z lower in this JSON file

* Where are placed the servomotors on the bot's top plate?
  * You can have a look on [this picture](https://raw.githubusercontent.com/pylapp/tapsterbot/bb8/.dev/captures/robot-servomotors.jpg "Picture of top plate")

* What kind of things should display the calibration app?
  * You can have a look on [this picture](https://raw.githubusercontent.com/pylapp/tapsterbot/bb8/.dev/captures/robot-calibration_app.jpg "Screen capture")

* How can I be sure I have calibrated the bot to a device?
  * You may have some outputs on your terminal [like these](https://github.com/pylapp/tapsterbot/raw/bb8/.dev/captures/robot-calibration-device-samsung_galaxy_a3.png "Screen capture")

* How can I be sure I have calibrated the arms of the bot?
  * You may have some outputs on your terminal [like these](https://raw.githubusercontent.com/pylapp/tapsterbot/bb8/.dev/captures/robot-calibration-arms-samsung_galaxy_a3.png "Screen capture")

* I think my calibration data have been fucked up, what should be the format of these values?
  * You can have a look on [this file](https://raw.githubusercontent.com/pylapp/tapsterbot/bb8/.dev/calibration-samsung-galaxy-a3.json "JSON calibration data")

* How can I test quickly the HTTP requests the robot can deal?
  * You can have a look on [this file](https://github.com/pylapp/tapsterbot/blob/bb8/doc/curl-commands.md "CURL commands and results")

* How can I reach my bot available at xxx.xxx.xxx.xxx:yyyy?
  * Define the IP address and the port in the client you use
  * Run the bot's server with the dedicated parameters (--address and --port)

* I cannot start the server.js script, an exception related to johnny-five or the board occurs.
  * Be sure you can use the port on which the robot has been plugged.
  * You can run the Arduino IDE, and get the port in use. For example on Ubuntu it may be _dev/ttyACM0_
  * Then according to this [post](http://arduino-er.blogspot.fr/2014/08/arduino-ide-error-avrdude-seropen-cant.html), you may allow the use of this port using the commands:
  ```shell
    sudo usermod -a -G dialout <username>;
    sudo chmod a+rw <port>;
  ```

* And if my Tapster robot screams "Exterminate!"?
  * Call The Doctor and Run You Clever Boy!
