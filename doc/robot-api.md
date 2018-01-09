# API of Tapser robot

### About files

Discovered files:
- calibrate.js
	.. see HTTP client
- robot_http_client.js
	.. definitions of URL for agnles, positions, claibration data, tap...
- bot.js
	.. definitionss of URL for arc, circle, welcome and go
- json-api.md
	.. details about methods of bot.js

### HTTP methods in use

HTTP methods in use: GET, POST

| Method        	| Parameters    		| Encoding for results  | Format for results  |
| ----------------------|:-----------------------------:|:---------------------:|:-------------------:|
| GET           	| host, port, path 		| UTF-8              	| JSON                |
| POST           	| host, port, path, method=POST | UTF-8              	| JSON                |


### List of features

This base URL matches the following pattern: https://_robot-address_:_robot-port_ _uri_ (e.g. http://127.0.0.1:4242)


| Feature       			    | Method        | Path                  | Body data           							| Comment             															|
| ------------------------------------------|:-------------:|:---------------------:|:-------------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|
| Get angles    			    | GET           | /angles               | _not defined_       							| Defined in robot_http_client.js, available with server.js            									|
| Set angles theta1, theta 2 and theta 3    | POST          | /setAngles            | "theta1=" + theta1 + "&theta2=" +theta2 + "&theta3=" + theta3    	 	| Defined in robot_http_client.js, available with server.js            									|
| Get position    			    | GET           | /position             | _not defined_       							| Defined in robot_http_client.js, available with server.js            									|
| Set position to (x,y,z)    		    | POST          | /setPosition          | "x="+x+"&y="+y+"&z="+z       						| Defined in robot_http_client.js, available with server.js            									|
| Tap to a point at (x,y)    		    | POST          | /tap                  | "x=" + x + "&y=" + y      						| Defined in robot_http_client.js, available with server.js. Server must be started with calibration data to reach contact point.       |
| Reset the arms of the bot    		    | POST          | /reset                | ""             								| Defined in robot_http_client.js, available with server.js            									|
| Get calibration data   		    | GET           | /calibrationData      | _not defined_       							| Defined in robot_http_client.js, available with server.js            									|
| Set calibration data    		    | POST          | /setCalibrationData   | "newData=" + JSON of new data    						| Defined in robot_http_client.js, available with server.js            									|
| Welcome  				    | GET           | /                     |  _not defined_      							| Defined in bot.js                    													|
| Go to (x, y, z)  			    | POST          | /go                   |  JSON with x, y and z      						| Defined in bot.js. x, y, z, radius, startAngle = float                    								|
| Draw a circle  			    | POST          | /circle               | JSON with x, y, z, radius, startAngle, anticlockwise, delay and rotations.| Defined in bot.js. x, y, z, radius, startAngle = float, anticlockwise = boolean, delay, rotations = integer           		|
| Draw an arc  				    | POST          | /arc                  |  JSON with x, y, z, radius, startAngle, endAngle, anticlockwise and delay | Defined in bot.js. x, y, z, radius, startAngle, endAngle = float, anticlockwise = boolean, 	delay = integer         		|
