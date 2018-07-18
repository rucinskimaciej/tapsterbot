# Test the robot through its HTTP REST API commands sent with CURL

**Get status of the robot's server**
_Command:_ curl http://127.0.0.1:4242/status  
_Exemple of result:_ {"status":0,"data":"\"OK\""}


**Reset arms of the bot to their initial state**  
_Command:_ curl --data "" http://127.0.0.1:4242/reset  
_Exemple of result:_ {"status":0,"data":[7.38704364563208,2.3870436456320796,7.38704364563208]}d


**Make the robot dance**
_Command:_ curl --data "" http://127.0.0.1:4242/dance  


**Make the robot stop dancing**
_Command:_ curl --data "" http://127.0.0.1:4242/stopDancing


**Define values (16, 16, 16) for angles of servomotors**  
_Command:_ curl --data "theta1=16&theta2=16&theta3=16" http://127.0.0.1:4242/setAngles  
_Exemple of result:_ {"status":0,"data":[16,16,16]}


**Define 3D position (10, 20, -140)**  
_Command:_ curl --data "x=10&y=20&z=-140" http://127.0.0.1:4242/setPosition  
_Exemple of result:_ {"status":0,"data":"\"OK\""}


**Get values of angles of servomotors**  
_Command:_ curl http://127.0.0.1:4242/angles  
_Exemple of result:_ {"status":0,"data":[16.447287034628303,16.447287034628303,16.447287034628303]}


**Get 3D position**  
_Command:_ curl http://127.0.0.1:4242/position  
_Exemple of result:_ {"status":0,"data":[0,0,0,-150.19971782711525]}


**Angles of arms for a 3D position**
_Command:_ curl http://127.0.0.1:4242/anglesForPosition/x/20/y/-10/z/-155  
_Exemple of result:_ {"status":0,"data":[17.77777607858807,16.571235547940283,33.79900615110225]}


**Position of the 3D robot_landmark point according to a 2D device-landmark point**
_Command:_ curl http://127.0.0.1:4242/positionForScreenCoordinates/x/300/y/300
_Exemple of result:_ {"status":0,"data":{"x":[2.0928902362294797],"y":[-1.1868672424641078]}}


**Tap on (100,100)**  
_Command:_ curl --data "x=100&y=100" http://127.0.0.1:4242/tap  
_Exemple of result:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}
_Note:_ You can use this request only if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Tap on (100,100) n times**  
_Command:_ curl --data "n=10&x=100&y=100" http://127.0.0.1:4242/nTap  
_Exemple of result:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}
_Note:_ You can use this request only if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Swipe from (100, 100) to (300, 450)**
_Command:_ curl --data "startX=100&startY=100&endX=300&endY=450" http://127.0.0.1:4242/swipe  
_Exemple of result:_ {"status":0,"data":[0,-23.52644491452315,8.617498307198526,-146.5704748734171]}  
_Note:_ You can use this request if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Swipe from (100, 100) to (300, 450) 10 times**
_Command:_ curl --data "n=10&startX=100&startY=100&endX=300&endY=450" http://127.0.0.1:4242/nSwipe  
_Results:_ {"status":0,"data":[0,20.099039333895835,9.35021825602261,-144.863474995738]}  
_Note:_ You can use this request if you have started your server with the calibration data you got during the calibration workflow. Otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


**Get calibration data**  
_Command:_ curl http://127.0.0.1:4242/calibrationData  
_Exemple of result:_ {"status":0,"data":{"restPoint":{"x":0,"y":0,"z":-130},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}


**Define calibration data**  
_Command:_ curl --data "newData={\"status\":0,\"data\":{\"restPoint\":{\"x\":30,\"y\":30,\"z\":-140},\"servo1\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo2\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo3\":{\"minimumAngle\":20,\"maximumAngle\":90}}}" http://127.0.0.1:4242/setCalibrationData  
_Exemple of result:_ {"status":0,"data":{"status":0,"data":{"restPoint":{"x":30,"y":30,"z":-140},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}}


**Get contact Z**  
_Command:_ curl http://127.0.0.1:4242/contactZ  
_Exemple of result:_{"status":0,"data":{"z":-151.5}}


**Draw a square**  
_Command:_ curl --data "n=1&length=30" http://127.0.0.1:4242/drawSquare  
_Exemple of result:_ {"status":0}  
_Note:_ Draws a square, where n is a divider of the length of the square to define the number of points to draw, using points in the robot's landmark


**Draw a star**  
_Command:_ curl --data "" http://127.0.0.1:4242/drawStar  
_Exemple of result:_ {"status":0}  
_Note:_ Draws a raw star


**Draw a triangle**  
_Command:_ curl --data "x1=100&y1=100&x2=100&y2=500&x3=300&y3=250" http://127.0.0.1:4242/drawTriangle
_Exemple of result:_ {"status":0}  
_Note:_ Draws a triangle using points in 2D device landmark


**Draw a circle**  
_Command:_ curl --data "x=200&y=200&r=15" http://127.0.0.1:4242/drawCircle
_Exemple of result:_ {"status":0}  
_Note:_ Draws a circle using points in 2D device landmark


**Draw a cross**  
_Command:_  curl --data "x1=100&y1=100&x2=300&y2=100&x3=300&y3=300&x4=100&y4=300" http://127.0.0.1:4242/drawCross
_Exemple of result:_ {"status":0}  
_Note:_ Draws a cross, using points in 2D device landmark, with two strokes: (x1,y1) -> (x3, y3) and (x2,y2) -> (x4, y4)


**Draw a spiral**  
_Command:_  curl --data "x=300&y=300&r=20&n=10" http://127.0.0.1:4242/drawSpiral
_Exemple of result:_ {"status":0}  
_Note:_ Draws a spiral, using points in 2D device landmark, center on (x,y) with the r radius and n levels


**Draw an SVG picture**  
_Command:_  curl --data "rawContent=..." http://127.0.0.1:4242/drawSVG
_Exemple of result:_ {"status":0}  
_Note:_ Draws an SVG picture, where '...' is the XML content of the SVG file


**Draw a random pattern (swipe-based)**  
_Command:_   curl --data "n=10&minWidth=100&minHeight=100&maxWidth=350&maxHeight=500" http://127.0.0.1:4242/drawRandomPattern
_Exemple of result:_ {"status":0}  
_Note:_ Draws a random pattern using continuous strokes with n points in a defined area (based on device's 2D landmark)


**Tap on random points**  
_Command:_   curl --data "n=10&minWidth=100&minHeight=100&maxWidth=400&maxHeight=500" http://127.0.0.1:4242/tapRandomPoints
_Exemple of result:_ {"status":0}  
_Note:_ Tap on n random points in a defined area (based on device's 2D landmark)