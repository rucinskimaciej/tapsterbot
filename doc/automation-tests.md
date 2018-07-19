## Automation of tests

### For what things?

So as to improve quality of sources or products, you may apply principles of Test-Driven-Development (TDD), Behaviour-Driven-Development (BDD) or DevOps with continous integration (#buzzwords ^_^)

You may use _Robot Framework_, a powerful tool which provides API to describe test cases.  
_Robot Framework_ works well with other tools like _Appium_ if you want to process tests in mobile or web apps.  

It is also possible to use the Tapster robot in such tests: **you just have to include the wrapper**: it provides high level _Robot Framework_ keywords which gather Tapster-dedicated and Appium-dedicated keywords. It will avoid you to know on which (X,Y) coordinates you have to tap by allowing you to tap on "this button" or "the widget with that id".

### Set up

If you want to use the _Tapster_ robot in automated tests processes, you have to follow the steps below.

1. Install the dependencies for _Robot Framework_
```shell
  pip install -U robotframework-appiumlibrary
  pip install -U requests
  pip install -U robotframework-requests
```

2. Calibrate the robot for the tested device. You can save the output calibration file to use it later

3. Start the _Appium_ server which will look in the tested app to get widgets, locations, etc
```shell
   appium
```

4. Start the Node.js server of the robot, which will receive the HTTP requests
```shell
   node server.js --calibration path-to-calibration-file
```

### Use of keywords

You will be able to use the keywords of the _tapster_wrapper.robot_ file. You just have to include as a _Resource_ the file in your tests cases script. Be aware this library uses the _config.robot_ and the_tapster_keywords.robot_ files, keep on eye on their paths.


| Keywords									| Parameters    																| Description																							|
| ------------------------------------------|:-----------------------------------------------------------------------------:|:-----------------------------------------------------------------------------------------------------:|
| Tap To Element With Id					| id    offset_x (opt,=0)    offset_y (opt,=0)									| Tap to the widget which have this id, using or not offsets, at the center of the widget 				|
| Tap To Element With Text					| text    offset_x (opt,=0)    offset_y (opt,=0)								| Tap to the widget which have this text, using or not offsets, at the center of the widget  			|
| Long Tap To Element With Id				| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a long to the widget which have this id, using or not offsets, at the center of the widget  		|
| Long Tap To Element With Text				| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a long to the widget which have this text, using or not offsets, at the center of the widget  	|
| Long Tap To Element With Xpath			| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a long to the widget which have this XPath locator, using or not offsets, at the center of the widget|
| Double Tap To Element With Id				| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a double to the widget which have this id, using or not offsets, at the center of the widget  	|
| Double Tap To Element With Text			| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a double to the widget which have this text, using or not offsets, at the center of the widget  	|
| Double Tap To Element With Xpath			| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a double to the widget which have this XPath locator, using or not offsets, at the center of the widget|
| Triple Tap To Element With Id				| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a triple to the widget which have this id, using or not offsets, at the center of the widget  	|
| Triple Tap To Element With Text			| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a triple to the widget which have this text, using or not offsets, at the center of the widget  	|
| Triple Tap To Element With Xpath			| text    offset_x (opt,=0)    offset_y (opt,=0)								| Make a triple to the widget which have this XPath locator, using or not offsets, at the center of the widget|
| Reset										| 																				| Reset position of the robot's arms																	|
| Swipe										| a    b    c    d    offset_x (opt,=0)    offset_y (opt,=0)                    | Swipe from (a,b) to (c,d) using or not offsets														|
| Tap To Element N Times With Id			| N    id    offset_x (opt,=0)    offset_y (opt,=0)								| Tap N times to the widget which have this id, using or not offsets, at the center of the widget    	|
| Tap To Element N Times With Text			| text    id    offset_x (opt,=0)    offset_y (opt,=0)							| Tap N times to the widget which have this text, using or not offsets, at the center of the widget  	|
| Stresstap To Element With Id				| N    id    offset_x (opt,=0)    offset_y (opt,=0)								| Tap N times very quickly to the widget which have this id, using or not offsets    					|
| Stresstap To Element With Text			| N    id    offset_x (opt,=0)    offset_y (opt,=0)								| Tap N times very quickly to the widget which have this text, using or not offsets    					|
| Swipe N Times								| N    a    b    c    d    offset_x (opt,=0)    offset_y (opt,=0)               | Swipe N times from (a,b) to (c,d) using or not offsets												|
| Stress swipes								| N    a    b    c    d    offset_x (opt,=0)    offset_y (opt,=0)               | Swipe very quickly N times from (a,b) to (c,d) using or not offsets									|
| Tap Somewhere To Element With Id			| id    offset_x (opt,=0)    offset_y (opt,=0)									| Tap somewhere to the widget which have this id, using or not offsets, using a random position    		|
| Tap Somewhere To Element With text		| text    offset_x (opt,=0)    offset_y (opt,=0)								| Tap somewhere to the widget which have this text, using or not offsets, using a random position  		|
| Swipe using elements id 					| source_id    destination_id    offset_x (opt,=0)    offset_y (opt,=0)			| Swipe from an element to another using their ids 														|
| Swipe using elements text					| source_text    destination_text    offset_x (opt,=0)    offset_y (opt,=0)		| Swipe from an element to another using their text contents											|
| Swipe using elements xpath				| source_xpath    destination_xpath    offset_x (opt,=0)    offset_y (opt,=0)	| Swipe from an element to another using XPath locators													|
| Draw Random Pattern						| n    minWidth    minHeight    maxWidth    maxHeight							| Draws a random pattern with n points, thus n-1 strokes, in a defined area 							|
| Draw Star 								|																				| Draws a lovely star 																					|
| Draw Circle 								| x    y    r																	| Draws a circle centered on (x,y) with r radius														|
| Draw Cross 								| x1    y1    x2    y2    x3    y3    x4    y4									| Draws a cross with two strokes: one from 1 to 4, the other from 2 to 3								|
| Draw Square								| n (opt, =2)    length (opt, =30)												| Draws a square with a dedicated length and a ratio of points to draw 									|
| Draw Triangle								| x1    y1    x2    y2    x3    y3												| Draws a triangle using 3 points																		|
| Draw Spiral								| x    y    n    r 																| Draws a spiral center on (x,y) point with n loops and an r radius										|

### Notes

1. Offsets are optional parameters with a default value defined to 0
2. Widgets in landscape mode do not have the same coordinates as in portrait mode, but robot cannot make the difference
 Thus we need to convert coordinates if we are in landscape mode. _AppiumLibrary_ for _Robot Framework_ does not provide
 the suitable keywords. So we need today to hardcode the coordinates to use for landscape-displayed widgets.
3. Some devices have Hall effect sensors, thus the finger of the robot can disturb magnetic field and make screen closed...
4. If we want to tap on a widget according to a text or an id, if we have several matching occurences, only the first will be used.

**Missing some keywords? Feel free to make pull requests!**
