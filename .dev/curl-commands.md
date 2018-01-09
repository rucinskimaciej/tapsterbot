** Get values of angles of servomotors **
_Command:_ curl http://127.0.0.1:4242/angles
_Results:_ {"status":0,"data":[16.447287034628303,16.447287034628303,16.447287034628303]}


** Define values of angles of servomotors **
_Command:_ curl --data "theta1=16&theta2=16&theta3=16" http://127.0.0.1:4242/setAngles
_Results:_ {"status":0,"data":[16,16,16]}


** Get poisition in 3D **
_Command:_ curl http://127.0.0.1:4242/position
_Results:_ {"status":0,"data":[0,0,0,-150.19971782711525]}


** Define 3D position **
_Command:_ curl --data "x=10&y=20&z=-140" http://127.0.0.1:4242/setPosition
_Results:_ {"status":0,"data":"\"OK\""}


** Reset arms of the bot **
_Command:_ curl --data "" http://127.0.0.1:4242/reset
_Results:_ {"status":0,"data":[16.447287034628303,16.447287034628303,16.447287034628303]}


** Tap on (x,y) **
_Command:_ curl --data "x=0&y=0" http://127.0.0.1:4242/tap
_Results:_ {"message":"An internal server error occurred","statusCode":500,"error":"Internal Server Error"}
_Note:_ See screen capture, maybe because the calibration has not been previously made?


** Get calibration data **
_Command:_ curl http://127.0.0.1:4242/calibrationData
_Results:_ {"status":0,"data":{"restPoint":{"x":0,"y":0,"z":-130},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}


** Define calibraiton data **
_Command:_ curl --data "newData={\"status\":0,\"data\":{\"restPoint\":{\"x\":30,\"y\":30,\"z\":-140},\"servo1\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo2\":{\"minimumAngle\":20,\"maximumAngle\":90},\"servo3\":{\"minimumAngle\":20,\"maximumAngle\":90}}}" http://127.0.0.1:4242/setCalibrationData
_Results:_ {"status":0,"data":{"status":0,"data":{"restPoint":{"x":30,"y":30,"z":-140},"servo1":{"minimumAngle":20,"maximumAngle":90},"servo2":{"minimumAngle":20,"maximumAngle":90},"servo3":{"minimumAngle":20,"maximumAngle":90}}}}


** Welcome **
_Command:_ curl http://127.0.0.1:4242/
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}


** Go to (x,y,z) **
_Command:_ curl --data "{\"x\":20,\"y\":25,\"z\":10}"  http://127.0.0.1:4242/go
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}


** Draw a circle **
_Command:_ curl http://127.0.0.1:4242/circle
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}


** Draw an arc **
_Command:_ curl http://127.0.0.1:4242/arc
_Results:_ {"statusCode":404,"error":"Not Found","message":"Not Found"}
