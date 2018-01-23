You may want to send quickly commands to the robot's server without using third-party glue. You can find here some curl commands to use to make the robot move. The robot's URL in use here is _http://127.0.0.1:4242_

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


**Tap on (0,0)**  
_Command:_ curl --data "x=0&y=0" http://127.0.0.1:4242/tap  
_Results:_ {"status":0,"data":"\"OK\""}  
_Note:_ You can use this request if you have started your server with the calibration data you got during the calibration workflow. otherwise you may have an error like the one displayed in file 'captures/screen-capture_interla-error-with-tap-curl.png'


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


**Welcome**  
_Command:_ curl http://127.0.0.1:4242/  
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}  
_Note:_ Command available with bot.js and not server.js (and its HTTP client)


**Go to (20,25,10)**  
_Command:_ curl --data "{\"x\":20,\"y\":25,\"z\":10}"  http://127.0.0.1:4242/go  
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}  
_Note:_ Command available with bot.js and not server.js (and its HTTP client)


**Draw a circle**  
_Command:_ curl http://127.0.0.1:4242/circle  
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}  
_Note:_ Command available with bot.js and not server.js (and its HTTP client)


**Draw an arc**  
_Command:_ curl http://127.0.0.1:4242/arc  
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}  
_Note:_ Command available with bot.js and not server.js (and its HTTP client)
