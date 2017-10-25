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

var http = require('http');

exports.client = function(address, port){

  var get = function(path, cb){
    return http.get({ host: address, port: port, path: path }, function(res){
      res.setEncoding('utf8');
      return res.on('data', function(chunk) {
        var result = JSON.parse(chunk);
        return cb(result.data);
      });
    }).on("error", function(err){
      console.log("Got error: " + err.message);
      return cb(null, err);
    });
  };

  var post = function(path, bodyData, cb){
    var req = http.request({ host: address, port: port, path: path, method: 'POST'}, function(res) {
      res.setEncoding('utf8');
      return res.on('data', function (chunk) {
        var result = JSON.parse(chunk);
        return cb(result.data);
      });
    }).on('error', function(err) {
      console.log("Got error: " + err.message);
      return cb(null, err);
    });
    req.setHeader('Content-Type', 'application/x-www-form-urlencoded');
    req.write(bodyData);
    req.write('\n');
    req.end();
  };

  return {
    address : address,
    port : port,
    url : function(uri){
      return 'http://' + address + ":" + port + uri;
    },
    angles : function(cb){
      return get('/angles', cb);
    },
    setAngles : function(theta1, theta2, theta3, cb){
      var postData = "theta1=" + theta1 + "&theta2=" +theta2 + "&theta3=" + theta3;
      return post('/setAngles', postData, cb);
    },
    position : function(cb){
      return get('/position', cb);
    },
    setPosition : function(x, y, z, cb){
      var postData = "x=" + x + "&y=" + y + "&z=" + z;
      return post('/setPosition', postData, cb);
    },
    tap : function(x, y, cb){
      var postData = "x=" + x + "&y=" + y;
      return post('/tap', postData, cb);
    },
    reset : function(cb){
      return post('/reset', '', cb);
    },
    calibrationData : function(cb){
      return get('/calibrationData', cb);
    },
    setCalibrationData : function(newData, cb){
      var postData = "newData=" + JSON.stringify(newData);
      return post('/setCalibrationData', postData, cb);
    }
  };

};
