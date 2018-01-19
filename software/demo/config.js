/*
  Copyright (c) 2011-2016, Tapster Committers
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

var config = {}

//Side of end effector
//~~Do not touch~~
config.e = 34.64101615137754;  // Math.sqrt(3) * 10 * 2

//Side of top triangle
//~~Do not touch~~
config.f = 110.85125168440814; // Math.sqrt(3) * 32 * 2

//Length of parallelogram joint
//~~Do not touch~~
config.re = 153.5;             // 145 + 8.5

//Length of upper joint
//~~Do not touch~~
config.rf = 52.690131903421914; // Math.sqrt(52**2 + 8.5**2)

//Input ranges for servos
//~~Do not touch~~
config.servo1 = {in_min: 0, in_max: 90};
config.servo2 = {in_min: 0, in_max: 90};
config.servo3 = {in_min: 0, in_max: 90};

//Default output ranges for servos
//CHANGE THESE
config.servo1.out_min = 12;
config.servo1.out_max = 93;
config.servo2.out_min = 8;
config.servo2.out_max = 90;
config.servo3.out_min = 14;
config.servo3.out_max = 96;

//Dimensions of the base plate
config.baseHeight = 95;
config.baseWidth = 80;

//Default Z-Level of the pen
config.penHeight = -140;

//Default drawing height of the pen
config.drawHeight = -152.75;

//Delay for commands in SVGReader
//Note that some commands will take longer than this
//Default value is 150
config.delay = 200;

//The default easing type to be used
//When no easing is specified, this is the type that will be used
//"none" means that if no easing is specified, do not ease
//For a list of possible easing types, look in motion.js
config.defaultEaseType = "linear";

module.exports = config;
