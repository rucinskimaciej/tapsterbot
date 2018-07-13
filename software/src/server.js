#! /usr/local/bin/node

/*
Copyright (c) 2011-2016, Tapster Committers
Copyright (c) 2016-2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:

1. Redistributions of source code must retain the above copyright notice, this
   list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright notice,
   this list of conditions and the following disclaimer in the documentation
   and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

[This is the BSD 2-Clause License, http://opensource.org/licenses/BSD-2-Clause]
*/

/* ***************************
 * Load third-party librairies
* ****************************/

var parser = require("./lib/server/parser"),
  Hapi = require("hapi"),
  path = require("path"),
  five = require("johnny-five"),
  calibration = require("./lib/server/calibration"),
  Robot = require("./lib/server/robot").Robot;

var args = parser.parseArgs();
var robot, servo1, servo2, servo3;

var board = new five.Board({ debug: false});

board.on("ready", function(){

  /* **************************************************
   * Step 1: Get the servomotors from the Arduino board
   * **************************************************/

  servo1 = five.Servo({
    pin: 9,
    range: [0,120]
  });

  servo2 = five.Servo({
    pin: 10,
    range: [0,120]
  });

  servo3 = five.Servo({
    pin: 11,
    range: [0,120]
  });

  servo1.on("error", function() {
    console.log(arguments);
  });

  servo2.on("error", function() {
    console.log(arguments);
  });

  servo3.on("error", function() {
    console.log(arguments);
  });

 /* ***********************
  * Strep 2: Prepare config
  * ***********************/

  // Initialize objects
  var calibrationData = calibration.getDataFromFilePath(args.calibration);
  calibrationData = calibrationData == null ? calibration.defaultData : calibrationData;
  robot = new Robot(servo1, servo2, servo3, calibrationData);

  // Move to starting point
  robot.resetPosition();

  // Create a server with a host and port
  var server = new Hapi.Server({
    host: args.address,
    port: args.port
  });

  var getCommonReponseObject = function(err, data){
    if (err) {
      return { status:err.code, data: err };
    } else {
      return { status: 0, data: data };
    }
  };

  /* *********************
   * Step 3: Define routes
   * *********************/

  // Status of the robot's server
  server.route({
    method: 'GET',
    path:'/status',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, '"OK"');
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Reset the position of the robot's arms
  server.route({
    method: 'POST',
    path:'/reset',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.resetPosition();
      return getCommonReponseObject(null, robot.getAngles());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Make the robot dance
  server.route({
    method: 'POST',
    path:'/dance',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.startDancing();
      return getCommonReponseObject(null, '"Dancing!"');
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Make the robot stop dancing
  server.route({
    method: 'POST',
    path:'/stopDancing',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.stopDancing();
      return getCommonReponseObject(null, '"No more dancing."');
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Define the gnales the robot's arms must have (angles of servomotors)
  server.route({
    method: 'POST',
    path:'/setAngles',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var theta1 = parseFloat(request.payload.theta1);
      var theta2 = parseFloat(request.payload.theta2);
      var theta3 = parseFloat(request.payload.theta3);
      robot.setAngles(theta1, theta2, theta3);
      return getCommonReponseObject(null, robot.getAngles());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Define the position in 3D for the robot's finger
  server.route({
    method: 'POST',
    path:'/setPosition',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var x = parseFloat(request.payload.x);
      var y = parseFloat(request.payload.y);
      var z = parseFloat(request.payload.z);
      robot.setPosition(x, y, z);
      return getCommonReponseObject(null, '"OK"');
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Get the angles the robot's arms have
  server.route({
    method: 'GET',
    path:'/angles',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, robot.getAngles());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Get the position in 3D of the robot's finger
  server.route({
    method: 'GET',
    path:'/position',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      return getCommonReponseObject(null, robot.getPosition());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Return the angles of the servomotors for a dedicated 3D point
  server.route({
    method: 'GET',
    path:'/anglesForPosition/x/{x}/y/{y}/z/{z}',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      var x = parseFloat(request.params.x);
      var y = parseFloat(request.params.y);
      var z = parseFloat(request.params.z);
      return getCommonReponseObject(null,robot.getAnglesForPosition(x,y,z));
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Return the 3D position of the finger for a dedicated 2D point using the device landmark
  server.route({
    method: 'GET',
    path:'/positionForScreenCoordinates/x/{x}/y/{y}',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      var x = parseFloat(request.params.x);
      var y = parseFloat(request.params.y);
      return getCommonReponseObject(null,robot.getPositionForScreenCoordinates(x,y));
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Tap to a 2D point using the device landmark
  server.route({
    method: 'POST',
    path:'/tap',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var x = parseFloat(request.payload.x);
      var y = parseFloat(request.payload.y);
      robot.tap( x, y, function(){return getCommonReponseObject(null, '"OK"')} );
      return getCommonReponseObject( null, robot.getPosition());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Swipe from a point to another point, these points are 2D device-landmark-based
  server.route({
    method: 'POST',
    path:'/swipe',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var startX = parseFloat(request.payload.startX);
      var startY = parseFloat(request.payload.startY);
      var endX = parseFloat(request.payload.endX);
      var endY = parseFloat(request.payload.endY);
      robot.swipe( startX, startY, endX, endY, function(){return getCommonReponseObject(null, '"OK"')} );
      return getCommonReponseObject( null, robot.getPosition());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Tape to a keyboard using already defined kies values, for old iPhone (legacy feature)
  // WARNING: Deprecated
  server.route({
    method: 'POST',
    path:'/sendKeys',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var keys = decodeURIComponent(request.payload.keys);
      return robot.sendKeys(keys, function() {
        return getCommonReponseObject(null, '"OK"');
      });
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Get the calibration data used to calibrate the nrobot for a dedicated device
  server.route({
    method: 'GET',
    path:'/calibrationData',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, robot.getCalibrationData());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Set the calibration data used to calibrate the nrobot for a dedicated device
  server.route({
    method: 'POST',
    path:'/setCalibrationData',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var newData = JSON.parse(request.payload.newData);
      robot.setCalibrationData(newData);
      return getCommonReponseObject(null, robot.getCalibrationData());
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  // Return the Z value of the 3D point (robot landmark) where the robot's finger hits the device's screen
  server.route({
    method: 'GET',
    path:'/contactZ',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, {z: robot.getContactZ()} );
    },
    config: {
      cors: {
        origin: ['*'],
        additionalHeaders: ['cache-control', 'x-requested-with']
      }
    }
  });

  server.start();
  console.log("Robot listening on port " + args.port);

}); // End of board.on("ready", function()
