/*
  Copyright (c) 2011-2016, Tapster Committers
  Copyright (c) 2018, Orange
  All rights reserved.

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

//Draws lines and shapes using if needed data.json file

/* ************
 * Useful tools
 * ************/

var fs = require('fs');
var motion = require("./motion");

/* ********* *
 * Variables *
 * ********* */

var objRef, defaultEaseType;
var calculated, spiralPts;

var baseHeight, baseWidth, canvasHeight, canvasWidth, heightRatio, widthRatio, halfway;

var DRAW_HEIGHT = -150;
var currentPoint = {x: 0, y: 0, z: -140};
var penHeight = DRAW_HEIGHT;

var robotReference;

/* **************************
 * Definition of super object
 * **************************/

function Draw(args, robot){

  // FIXME Hardcoded values
  this.baseWidth = 80;
  this.baseHeight = 95;
  this.drawHeight = DRAW_HEIGHT; // TODO Use calibration data instead!
  this.defaultEaseType = "linear";

  if (args){
    var keys = Object.keys(args);
    keys.forEach(function(key){
      this[key] = args[key];
    }, this)
  }

  penHeight = this.drawHeight;
  defaultEaseType = this.defaultEaseType;
  objRef = this;

  robotReference = robot;

}


/* ********************* *
 * Defintions of methods *
 * ********************* */

//Maps point from canvas to the Tapster coordinate plane
//The conversion is based on the canvas size and the size of the Tapster base
//These can be changed as needed
mapPoints = function(x, y){
  var newX = x;
  var newY = y;
  newX = (newX - halfway.x) / widthRatio;
  newY = (halfway.y - newY) / heightRatio;
  return {x:newX, y:newY};
};


//Set the penHeight from the command line
Draw.prototype.setPenHeight = function(height){
  penHeight = height;
}

//Draws a square in order to ensure that everything is working properly
//Optional args:
//sideLength: the length of each side (default: 20)
//n: draws every nth point (default: 2)
Draw.prototype.drawSquare = function(args){

  //Default values
  this.sideLength = 30;
  this.n = 2;

  if (args) {
    var keys = Object.keys(args)
    keys.forEach(function(key){
      this[key] = args[key]
    }, this)
  }

  resetTimer(); //Reset the timer so that there isn't unnecessary delay when calling the function multiple times

  var halfSide = this.sideLength / 2;
  var points = this.sideLength / this.n;

  doSetTimeout(-halfSide, halfSide, penHeight + 10, 0)
  doSetTimeout(-halfSide, halfSide, penHeight, 500); //Top left corner

  for (var i = 0; i < points; i++) { //To bottom left
    doSetTimeout(-halfSide, halfSide - (this.n * i), penHeight, i * 5);
  }

  for (var i = 0; i < points; i++) { //To bottom right
    doSetTimeout(-halfSide + (this.n * i), -halfSide, penHeight, i * 5);
  }

  for (var i = 0; i < points; i++) { //To top right
    doSetTimeout(halfSide, -halfSide + (this.n * i), penHeight, i * 5);
  }

  for (var i = 0; i < points; i++) { //To top left
    doSetTimeout(halfSide - (this.n * i), halfSide, penHeight, i * 5);
  }

  doSetTimeout(0, 0, -140, timer + 100);

};

//Draws a star to test that the Tapster bot is working properly
// Points are not in the 2D landmark of the device, but in the robot's landmark
// (= (0,0) at the center of the plate)
Draw.prototype.drawStar = function(){
  resetTimer();
  doSetTimeout(-20, -20, penHeight, 1000);
  doSetTimeout(0, 30, penHeight, 1000);
  doSetTimeout(20, -20, penHeight, 1000);
  doSetTimeout(-30, 10, penHeight, 1000);
  doSetTimeout(30, 10, penHeight, 1000);
  doSetTimeout(-20, -20, penHeight, 1000);
  doSetTimeout(0, 0, -140, timer + 100);
  //-20, -20, 0, 30, 20, -20
};

// Draws a triangle
// Points are not in the 2D landmark of the device, but in the robot's landmark
// (= (0,0) at the center of the plate)
Draw.prototype.drawTriangle = function(x, y, x1, y1, x2, y2){
  resetTimer();
  doSetTimeout(x, y, penHeight, 1000);
  doSetTimeout(x1, y1, penHeight, 1000);
  doSetTimeout(x2, y2, penHeight, 1000);
  doSetTimeout(x, y, penHeight, 1000);
  doSetTimeout(0, 0, -140, timer + 100);
}

//Draws a circle
//Optional args:
//centerX: the x coordinate of the center (default: 0)
//centerY: the y coordinate of the center (default: 0)
//radius: the radius of the center (default: 20)
Draw.prototype.drawCircle = function(args){

  resetTimer();

  this.centerX = 0;
  this.centerY = 0;
  this.radius = 20;

  if (args) {
    var keys = Object.keys(args)
    keys.forEach(function(key){
      this[key] = args[key]
    }, this)
  }

  // an array to save your points
  var points = [];

  // populate array with points along a circle
  //Goes slightly over so that the circle is actually completed
  for (var degree=0; degree < 395; degree++) {
    var radians = (degree + 90) * Math.PI/180;
    var x = this.centerX + this.radius * Math.cos(radians);
    var y = this.centerY + this.radius * Math.sin(radians);
    points.push({x:x,y:y});
  }

  circle = function() {
    doSetTimeout(0, 0, -120, 50, "none");
    doSetTimeout(points[0].x, points[0].y, penHeight + 10, 150, "none");
    doSetTimeout(points[0].x, points[0].y, penHeight, 150, "none");
    for (var i=0; i<points.length; i+=1) {
      point = points[i];
      doSetTimeout(point.x, point.y, penHeight, 2, "none");
    }
  }

  circle();
  doSetTimeout(0, 0, -140, timer + 100);

};

//Draws an arbitrary amount of spirals to test that the Tapster bot is working properly
//Optional args:
//startX: the x coordinate of the spiral's starting point (default: 0)
//startY: the y coordinate of the spiral's starting point (default: 0)
//spirals: amount of spirals to draw (default: 3)
//radius: radius of largest spiral (default: 30)
//zLevel: height at which to draw the spiral (default: penHeight)
//ptArray: an array of points to draw instead of calculating the spiral again (default: null)
Draw.prototype.drawSpiral = function(args){

  this.startX = 0;
  this.startY = 0;
  this.spirals = 3;
  this.radius = 30;
  this.zLevel = penHeight;
  this.ptArray = null;

  if (args) {
    var keys = Object.keys(args)
    keys.forEach(function(key){
      this[key] = args[key]
    }, this)
  }

  resetTimer();

  var x1 = 0;
  var y1 = 0;
  var points = [];

  if (this.ptArray) {
    points = this.ptArray;
  //Draws additional points, mainly for use with the erase function
  //The extra points allow the eraser to end up in its resting position
  } else {
    for (var degree = 0; degree < this.spirals * 360 + 95; degree++) {
      x1 = x1 + this.radius/(this.spirals * 360);
      y1 = y1 + this.radius/(this.spirals * 360);
      var radians = degree * Math.PI/180;
      var x = this.startX + x1 * Math.cos(radians);
      var y = this.startY + y1 * Math.sin(radians);
      points.push({x:x, y:y});
    }
    //doSetTimeout(points[0].x, points[0].y, penHeight + 10, 150);
    //doSetTimeout(points[0].x, points[0].y, penHeight, 150, "none");
  }

  spiral = function() {
    for (var z = 0; z < points.length; z++) {
      point = points[z];
      doSetTimeout(point.x, point.y, objRef.zLevel, 5, "none");
    }
  }

  spiral();
  return points;

};

/* ****************************************************
 * Inner functions and variables picked from demo files
 * ****************************************************/

var defaultEaseType = "linear";
var timer = 0;
var current = [0, 0, DRAW_HEIGHT];
var steps = 5;
var delay = 200 / steps;


 //A separate setTimeout method so that delays work properly
doSetTimeout = function(x, y, z, timeDelay, easing){
  if (!easing) easing = defaultEaseType;
  setTimeout(function() { go(x, y, z, easing) }, timer);
  timer = timer + timeDelay;
};

// Makes a moves with an ease to this point
go = function(x, y, z, easeType){

  var pointB = [x, y, z];

  if (easeType == "none") {
    current = [pointB[0], pointB[1], pointB[2]];
    robotReference.setPosition(pointB[0], pointB[1], pointB[2]);
    return; //Ensures that it doesn't move twice
  } else if (!easeType) {
    easeType = defaultEaseType; 
  }

  var points = motion.getPoints(current, pointB, steps, easeType);

  for (var i = 0; i < points.length; i++) {
    setTimeout(
      function(point){
        current = [point[0], point[1], point[2]];
        robotReference.setPosition(point[0], point[1], point[2])
      },
      i * delay,
      points[i]
    );
    //setTimeout( function(point) { moveServosTo(point[0], point[1], point[2]) }, i * delay, points[i]);
  }

}

resetTimer = function(){
  timer = 0;
}

module.exports.Draw = Draw;
