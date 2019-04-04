_Now that you've got your Tapster, all that's left is some quick setup and you'll be ready to tap away to your heart's content. This shouldn't take more than 15 minutes._

# Setting Up Tapster

## Components:

![Tapsterbot](http://i.imgur.com/qOL44Vp.jpg) 

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;One pre-assembled Tapster
<br><br>
<br><br>

![Arms and stylus holder](http://i.imgur.com/FXPwi3c.jpg)

Six arms (ball bearings attached) and one end effector with stylus
<br><br>
<br><br>

![Alligator Clip](http://i.imgur.com/17VqlWr.jpg)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;One alligator clip
<br><br>
<br><br>

![USB Cable](http://i.imgur.com/IXE2F9g.jpg)

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;One USB cable
<br><br>
<br><br>

![Power supply](http://i.imgur.com/CfClC5Q.jpg)

One power supply (9V - 1A -- design may vary)

[The Arduino IDE](https://www.arduino.cc/en/Main/Software)
_______________

### Step 1: Attach the arms

Attaching the arms is simple. Connect the ball bearings on one end of the arm (it doesn't matter which end) to the arm holder mounted on top of the robot.

![Attaching the arms](http://i.imgur.com/XwKSz11.jpg)

### Step 2: Attach the end effector

The next step is to attach the end effector to the arms. Make sure that the screw in the end effector is aligned with the Tapster logo, and connect the ball bearings on the other end of the arms to the magnets in the end effector. The stylus tip should be pointing down.

![End effector attachment -- screw location in red](http://puu.sh/j1FS3/1b178fe730.jpg)

### Step 3: Attach the alligator clip

The third step is to attach the alligator clip. First, plug one end into the ground on the daughter board.

![Plugging in ground](http://puu.sh/j1Ln9/fc931b28ca.jpg)

<br><br>

Then clip the other end to the top of the stylus.

![Clipping](http://i.imgur.com/XXM0EHf.jpg)

This allows the stylus to function properly -- without it, most screens would not recognize Tapster's tapping.

### Step 4: Plug everything in

Plug the Type B USB connector (the squarer one) into the back of the Arduino motherboard. Plug the Type A connector (the rectangular one) into your computer.

![USB](http://i.imgur.com/V4qxN9L.jpg)
<br><br>

Then, plug the power supply's barrel plug into the back of the motherboard, and plug the other end into an outlet.

![Power](http://i.imgur.com/tncyHIV.jpg)

<br><br>
If everything is working properly, you should see some lights come on.

![Success](http://i.imgur.com/UaIF1zU.jpg)

### Step 5 (Optional): Double check the pin connections

The standard Tapster currently uses 3 servos. These servos are connected to pins 9, 10, and 11 on the daughterboard. If, for some reason, these come unplugged, or are plugged in incorrectly, Tapster will not work right.

Here is an image showing which servo corresponds to which pin.

![Map](http://puu.sh/j1Cld/2a917e0efc.jpg)

It may be worth double checking that they are wired to the correct pins, and the wires are in the right orientation. The black wire, which is the ground wire, should line up with the "G" and the yellow wire, which is the signal wire, should line up with the "S". 

_________________

## Loading Firmata

Each bot should come with Firmata already loaded on the motherboard; however, it's good to double check that this is the case and hopefully avoid some nasty errors.

### Step 1: Download and install the Arduino IDE

Set-up is straightforward. The IDE is found [here](https://www.arduino.cc/en/Main/Software).

### Step 2: Connect to the board

Go to Tools > Port and select the port that the board is connected to. If no port appears, the device drivers may still be installing, and you'll have to wait for them to finish.

![Ports](http://puu.sh/j1yJb/93f394adc5.png)

### Step 3: Check that the board is working

To ensure that the board is working, run a simple Blink sketch. This is found in File > Examples > 01. Basics. Load the sketch and upload it to the board (File > Upload).

![Blink](http://puu.sh/j1yQe/44846cd20b.png)

You should be able to see the yellow LED on the motherboard blinking on and off every second.

Optional step: Modify the time interval, re-upload the sketch, and check to see that the board is now blinking at a different rate. 

### Step 4: Upload Firmata

Open up the StandardFirmata sketch (File > Examples > Firmata) and upload it to the board. 

![Firmata](http://puu.sh/j1zi7/f2184ffdd2.png)

Firmata allows us to more easily communicate with the motherboard, greatly simplifying things.

_________________________

# Setting up your computer

### Step 1: Install Node

I would recommend using one of the installers found [here](https://nodejs.org/download/) (this also installs npm). If you would prefer to use another method of installation, look up a tutorial on Google.

### Step 2: Verify that npm is working

Open up the command line interface of your choosing and type `npm -v`. If npm was installed properly the version number should be spit out.

![Version check](http://puu.sh/j1A2D/c1c33537b6.png)

### Step 3: Download the Tapsterbot files from GitHub and save them wherever you'd like

### Step 4: Install the Tapsterbot dependencies

Using the command line, navigate to the Tapsterbot software folder. Then, type `npm install` to install the modules that Tapster requires to run.

![Dependencies](http://puu.sh/j1Avb/0fefe291a4.png)

_____________________

# Configuring Tapster

Tapster's configuration file is config.js, located in the software folder, which you are free to edit. Many of the values should be left alone -- these are marked as such. The rest you can modify to change the speed, accuracy, and smoothness of the Tapster's functions. 


### Do not change:
* **e**, **f**, **re**, and **rf** are the lengths of components of Tapster. Tapster is a delta robot, which uses inverse kinematics to calculate the angles to move to. e, f, re, and rf are values that are necessary for calculating these angles -- for more information on inverse kinematics and the way delta robots work, see [this](http://forums.trossenrobotics.com/tutorials/introduction-129/delta-robot-kinematics-3276/) post on TrossenRobotics.
* The **in_min** and **in_max** values for all three servos are used for mapping points. 
* The **baseWidth** and **baseHeight** are the dimensions of drawing area of the baseplate. Although the full baseplate is larger than these values would lead you to believe, Tapster can only reliably reach part of the baseplate because of the way it is designed. These values are used for mapping points, and should not be changed.


### Change as needed:
* The **out_min** and **out_max** values for all three servos are also used for mapping points; however, they change from servo to servo and so should be changed after calibrating each servo (see below). **Default**: 0, 90
* **penHeight** is the default z-level that the robot should move to. A higher value raises the pen, and a lower value lowers it. **Default**: -140
* **drawHeight** is the z-level that the robot should draw at. It is used when using the whiteboard insert and dry erase markers. Because dry erase markers lose their tip after being used frequently, this value might need to be changed over time. A higher value raises the marker and a lower value lowers it. **Default**: -152.5
* **delay** is the amount of delay to be used in SVGReader commands. Each command will take *delay* milliseconds or more to complete. A higher value of delay will result in more accurate and smoother drawings, at the expense of making it slower; a lower value will result in a less accurate and less smooth drawing, but will be much faster. **Default**: 150
* **defaultEaseType** is the default easing algorithm to be used when none is specified. For the list of easing algorithms, look at motion.js. A value of "none" means that unless specified, no easing algorithm should be used. **Default**: "linear"

It is possible to specify a configuration file other than the default config.js. When launching bot.js, input the path to the config file as a string parameter.

### Step 1: Launch bot.js

Navigate to the src folder and type `node bot.js`. This will power the robot and make it listen to any commands that you give it.

![Launch](http://puu.sh/j1AZ4/f600e9c693.png)
<br><br>
<br><br>

To specify a configuration file other than the default one, put the path to the file as a string parameter.

![Like so.](http://puu.sh/j1JPT/4e79344498.png)

### Step 2: Calibrate the servos

The Hi-Tec 311 servos, which are used in the current design of Tapster, require some calibration to be done to ensure the highest level of precision possible. To do this, first remove the arms.

Once that is done, you can begin to calibrate the servos. The goal is to figure out at what values the servo mounts are completely horizontal and completely vertical, and then input those values into the config file to be used in a mapping function.

Use the `to` command to move the servos.

![Like so](http://puu.sh/j1JGP/d985ec1495.png)
<br><br>
<br><br>

![Horizontal](http://puu.sh/j1DbV/8d67185ac7.jpg)

This is what the the mount should look like at its out_min value
<br><br>
<br><br>

![Vertical](http://puu.sh/j1DDy/529893235b.jpg)

This is what the mount should look like at its out_max value
<br><br>

The values should be put in config.js in the out_min and out_max variables for each servo. 

______________________

# Testing Tapster

### Step 1: Launch bot.js

### Step 2: Call some test functions

Tapster comes with a wide array of functions that can be used to test itself. You can tell it to move to specific points using the `go` command (ex: go([0, 0, -140])); you can tell it to draw a square with any sidelength (ex: draw.drawSquare(40)); you can tell it to draw an image from an .svg file (ex: svg.drawSVG("C:/Projects/Tapsterbot/software/src/example/tutorial.svg"); or you can try some of the other methods found in bot.js, draw.js, and SVGReader.js.

Keep in mind that if you call any functions from the latter two files you have to call them using an instantiated object of that file (ex: draw.drawCircle for draw.js and svg.hello for SVGReader.js).

For more information about using Tapster's functions, check out the other pages of this wiki.
____
![The Tapster grid](http://puu.sh/j1F3Q/5fe6b4627a.jpg)

The Tapster board's center corresponds to (0, 0), and the rest of the board is arranged like a standard graph. The upper right hand corner is the first quadrant and is +,+; the upper left hand corner is the second quadrant and is -,+; and so on. If the movement of the bot does not match up to this graph, something is probably wrong. Look at the troubleshooting section for more detail.

At this point, everything should be working properly and you're ready to use Tapster!

____________________
# Troubleshooting

### When I try to upload Firmata it fails

Double check that the port you selected is the correct one. You can try unplugging the cable and plugging it into a different one as well. If no port shows up, the device drivers are probably still being installed, and you will have to wait.

If nothing else fixes it, restarting your computer usually helps.

### When I launch bot.js the console just spits out "undefined" several times and nothing happens

Plug the USB cable into a different port and re-launch.

### When I tell the bot to move to a point, it moves in the wrong direction/to the wrong point/nowhere

The servo to pin wiring is probably messed up. Double check that the servos are connected to the correct pins, and that the wires are oriented properly (black to ground, yellow to signal).

If those are correct and the bot is still messed up, check the calibration values in the config.js file. If they are wrong they will mess up the mapping function and inaccurate values will be returned.

### The arms pop off when the bot moves

This can happen when the servos exert too much force. Easing algorithms are used to avoid this. Try specifying a different easing algorithm to use, or change the default easing algorithm. This will change the way the bot moves from one point to another, and should prevent the servos from tearing the arms off.

Side note: No lasting damage is done when the arms pop off. Magnets are used so that the arms will fall off rather than break in two when put under too much pressure.