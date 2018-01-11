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
It matches the URL and payload format used in Python client using [hapi.js](https://hapijs.com/ "Hapi website") and [Requests](http://docs.python-requests.org/en/master/ "Requests webpage") libraries.


| Feature       			    | Method        | Path                  			| Body data           							| Comment             															|
| ------------------------------------------|:-------------:|:-----------------------------------------:|:---------------------------------------------------------------------:|:-------------------------------------------------------------------------------------------------------------------------------------:|
| Status	    			    | GET           | /status               			| _not defined_       							| Returns the status of the robot's server, in JSON format										|
| Reset		    			    | POST          | /reset	      				| {}		    							| Resets the robot to its initial state (i.e. moves its arms to their initial states)							|
| Dance		    			    | POST          | /dance	            			| {}		    							| Makes the robot dance! \(-ㅂ-)/ ♥ ♥ ♥												|
| Stop dancing	    			    | POST          | /stopDancing          			| {}		    							| Stops the dance of the robot :(													|
| Set arms' angles (t1, t2, t3)		    | POST          | /setAngles            			| {'theta1': t1, 'theta2': t2, 'theta3': t3}		    	 	| Sets the angles of the arms of the bot to t1, t2 and t3										|
| Set position (x,y,z)    		    | POST          | /setPosition          			| {'x': x, 'y': y, 'z': z}       					| Sets the robot to the 3D position (x, y, z)												|
| Get angles    			    | GET           | /angles               			| _not defined_       							| Gets the arms angles of the bot													|
| Get position    			    | GET           | /position             			| _not defined_       							| Gets the 3D position of the robot													|
| Angles for position (a, b, c)		    | GET           | /anglesForPosition/x/a/y/b/z/c		| _not defined_       							| Gets the angles of the arms of the bot for the 3D coordinates (a, b, c) where a in X axis, b in Y axis and c in Z axis		|
| Position for screen coordinates (x, y)    | GET           | /positionForScreenCoordinates/x/a/y/b	| _not defined_       							| Gets the positions of the robot according to (x, y) coordinates in the 2D device landmark						|
| Tap to a point at (x,y)    		    | POST          | /tap                 			| {'x': x, 'y': y}      						| Makes the robot tap on (x, y)														|
| Swipe from (a,b) to (c,d)    		    | POST          | /swipe                 			| {'startX': a, 'startY': b, 'endX': c, 'endY', d} 			| Makes the robot swipe from (a, b) to (c, d)												|
| Send keys		    		    | POST          | /sendKeys               			| 						 			| _Not implemented yet_ 														|
| Get calibration data   		    | GET           | /calibrationData      			| _not defined_       							| Gets the claibration data used by the bot												|
| Set calibration data    		    | POST          | /setCalibrationData   			| _JSON format of calibration data_					| Defines the claibration data the robot should use											|
