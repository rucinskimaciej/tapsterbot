# Test the bot with HTTP commands sent with CURL

**Get values of angles of servomotors**  
_Command:_ curl http://127.0.0.1:4242/angles  
_Results:_ {"status":0,"data":[16.447287034628303,16.447287034628303,16.447287034628303]}


**Define values (16, 16, 16) for angles of servomotors**  
_Command:_ curl --data "theta1=16&theta2=16&theta3=16" http://127.0.0.1:4242/setAngles  
_Results:_ {"status":0,"data":[16,16,16]}


**Get 3D position**  
_Command:_ curl http://127.0.0.1:4242/position  
_Results:_ {"status":0,"data":[0,0,0,-150.19971782711525]}


**Define 3D position (10, 20, -140)**  
_Command:_ curl --data "x=10&y=20&z=-140" http://127.0.0.1:4242/setPosition  
_Results:_ {"status":0,"data":"\"OK\""}


**Reset arms of the bot**  
_Command:_ curl --data "" http://127.0.0.1:4242/reset  
_Results:_ {"status":0,"data":[16.447287034628303,16.447287034628303,16.447287034628303]}


**Tap on (100,100)**  
_Command:_ curl --data "x=100&y=100" http://127.0.0.1:4242/tap  
_Results:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}
_Note:_ You can use this request only if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Tap on (100,100) n times**  
_Command:_ curl --data "n=10&x=100&y=100" http://127.0.0.1:4242/nTap  
_Results:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}
_Note:_ You can use this request only if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Swipe from (100, 100) to (300, 450)**
_Command:_ curl --data "startX=100&startY=100&endX=300&endY=450" http://127.0.0.1:4242/swipe  
_Results:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}  
_Note:_ You can use this request if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Swipe from (100, 100) to (300, 450) 10 times**
_Command:_ curl --data "n=10&startX=100&startY=100&endX=300&endY=450" http://127.0.0.1:4242/nSwipe  
_Results:_ {"status":0,"data":[0,20.099039333895835,9.35021825602261,-144.863474995738]}  
_Note:_ You can use this request if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Get calibration data**  
_Command:_ curl http://127.0.0.1:4242/calibrationData  
_Results:_ {"status":0,"data":{"restPoint":{"x":0,"y":0,"z":-130},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}


**Define calibration data**  
_Command:_ curl --data "newData={\"status\":0,\"data\":{\"restPoint\":{\"x\":30,\"y\":30,\"z\":-140},\"servo1\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo2\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo3\":{\"minimumAngle\":20,\"maximumAngle\":90}}}" http://127.0.0.1:4242/setCalibrationData  
_Results:_ {"status":0,"data":{"status":0,"data":{"restPoint":{"x":30,"y":30,"z":-140},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}}


**Get contact Z**  
_Command:_ curl http://127.0.0.1:4242/contactZ  
_Results:_
{"status":0,"data":{"z":-151.5}}


**Draw a square**  
_Command:_ curl --data "n=1&length=30" http://127.0.0.1:4242/drawSquare  
_Results:_ {"status":0}  
_Note:_ Draws a square, where n is a divider of the length of the square to define the number of points to draw, using points in the robot's landmark


**Draw a star**  
_Command:_ curl --data "" http://127.0.0.1:4242/drawStar  
_Results:_ {"status":0}  
_Note:_ Draws a raw star


**Draw a triangle**  
_Command:_ curl --data "x1=0&y1=0&x2=-10&y2=20&x3=20&y3=-10" http://127.0.0.1:4242/drawTriangle
_Results:_ {"status":0}  
_Note:_ Draws a triangle using points in the robot's landmark


**Draw a circle**  
_Command:_ curl --data "x=0&y=0&r=15" http://127.0.0.1:4242/drawCircle
_Results:_ {"status":0}  
_Note:_ Draws a circle using points in the robot's landmark


**Draw a cross**  
_Command:_  curl --data "x1=-10&y1=10&x2=10&y2=10&x3=10&y3=-10&x4=-10&y4=-10" http://127.0.0.1:4242/drawCross
_Results:_ {"status":0}  
_Note:_ Draws a cross, using points in the robot's landmark, with two strokes: (x1,y1) -> (x3, y3) and (x2,y2) -> (x4, y4)


**Draw a spiral**  
_Command:_  curl --data "x=0&y=0&r=20&n=6" http://127.0.0.1:4242/drawSpiral
_Results:_ {"status":0}  
_Note:_ Draws a spiral, using points in the robot's landmark, center on (x,y) with the r radius and n levels


**Draw an SVG picture**  
_Command:_  curl --data "rawContent=..." http://127.0.0.1:4242/drawSVG
_Results:_ {"status":0}  
_Note:_ Draws an SVG picture, where '...' is the XML content of the SVG file


**Draw a random pattern (swipe-based)**  
_Command:_   curl --data "n=10&minWidth=-35&minHeight=-35&maxWidth=30&maxHeight=30" http://127.0.0.1:4242/drawRandomPattern
_Results:_ {"status":0}  
_Note:_ Draws a random pattern using continuous strokes with n points in a defined area (based on robot's 3D landmark)
