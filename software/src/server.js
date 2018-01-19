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

  // Initialize objects
  var calibrationData = calibration.getDataFromFilePath(args.calibration);
  calibrationData = calibrationData == null ? calibration.defaultData : calibrationData;
  robot = new Robot(servo1,servo2,servo3,calibrationData);

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

  server.route({
    method: 'GET',
    path:'/status',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, '"OK"');
    }
  });

  server.route({
    method: 'POST',
    path:'/reset',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.resetPosition();
      return getCommonReponseObject(null, robot.getAngles());
    }
  });

  server.route({
    method: 'POST',
    path:'/dance',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.startDancing();
      return getCommonReponseObject(null, '"Dancing!"');
    }
  });

  server.route({
    method: 'POST',
    path:'/stopDancing',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      robot.stopDancing();
      return getCommonReponseObject(null, '"No more dancing."');
    }
  });

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
    }
  });

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
    }
  });

  server.route({
    method: 'GET',
    path:'/angles',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, robot.getAngles());
    }
  });

  server.route({
    method: 'GET',
    path:'/position',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      return getCommonReponseObject(null, robot.getPosition());
    }
  });

  server.route({
    method: 'GET',
    path:'/anglesForPosition/x/{x}/y/{y}/z/{z}',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      var x = parseFloat(request.params.x);
      var y = parseFloat(request.params.y);
      var z = parseFloat(request.params.z);
      return getCommonReponseObject(null,robot.getAnglesForPosition(x,y,z));
    }
  });

  server.route({
    method: 'GET',
    path:'/positionForScreenCoordinates/x/{x}/y/{y}',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      var x = parseFloat(request.params.x);
      var y = parseFloat(request.params.y);
      return getCommonReponseObject(null,robot.getPositionForScreenCoordinates(x,y));
    }
  });

  server.route({
    method: 'POST',
    path:'/tap',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var x = parseFloat(request.payload.x);
      var y = parseFloat(request.payload.y);
      robot.tap( x, y, function(){return getCommonReponseObject(null, '"OK"')} );
      return getCommonReponseObject( null, robot.getPosition());
    }
  });

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
    }
  });

  server.route({
    method: 'POST',
    path:'/sendKeys',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var keys = decodeURIComponent(request.payload.keys);
      return robot.sendKeys(keys, function() {
        return getCommonReponseObject(null, '"OK"');
      });
    }
  });

  server.route({
    method: 'GET',
    path:'/calibrationData',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, robot.getCalibrationData());
    }
  });

  server.route({
    method: 'POST',
    path:'/setCalibrationData',
    handler: function (request, h) {
      console.log("POST " + request.path + ": ");
      var newData = JSON.parse(request.payload.newData);
      robot.setCalibrationData(newData);
      return getCommonReponseObject(null, robot.getCalibrationData());
    }
  });

  server.route({
    method: 'GET',
    path:'/contactZ',
    handler: function (request, h) {
      console.log("GET " + request.path + ": ");
      return getCommonReponseObject(null, {z: robot.getContactZ()} );
    }
  });

  server.start();
  console.log("Robot listening on port " + args.port);

}); // End of board.on("ready", function()
