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

// Based on http://bl.ocks.org/cloudshapes/5661984 by cloudshapes

// The output
var output;

// The canvas
var svg;

// The logger
var logger;

// The will-be-drawn line
var line;

// The current session with drawn items
var session;

// A flag if draw operation is on going
var drawing;

/**
 * Prepares the JavaScript callback and the SVG element for the recorder.
 * Will define additional JavaScript glue so as to set up the recorder.
 */
function prepareRecorder(){

  var margin = {top: 0, right: 0, bottom: 0, left: 0};
  var width = 300 - margin.left - margin.right;
  var height = 500 - margin.top - margin.bottom;

  var ptdata = [];
  var path;

  session = [];
  drawing = false;

  logger = d3.select('#logger')
  .attr("width", width + margin.left + margin.right)
  .attr("height", height + margin.top + margin.bottom)

  output = d3.select('#output');

  line = d3.svg.line()
    .interpolate("bundle") // basis, see http://bl.ocks.org/mbostock/4342190
    .tension(1)
    .x(function(d, i) { return d.x; })
    .y(function(d, i) { return d.y; });

  svg = d3.select("#sketch")
    .append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)

  svg.append("g")
  .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

  svg
  .on("mousedown", listen)
  .on("touchstart", listen)
  .on("touchend", ignore)
  .on("touchleave", ignore)
  .on("mouseup", ignore)
  .on("mouseleave", ignore);

  // Ignore default touch behavior
  var touchEvents = ['touchstart', 'touchmove', 'touchend'];
  touchEvents.forEach(function (eventName) {
    document.body.addEventListener(eventName, function(e){
      e.preventDefault();
    });
  });

}

/**
 *
 */
function listen(){

  drawing = true;

  output.text('event: ' + d3.event.type);

  // Reset point data
  ptdata = [];

  // Start a new line
  path = svg.append("path")
    .data([ptdata])
    .attr("class", "line")
    .attr("d", line);

  if (d3.event.type === 'mousedown'){
    svg.on("mousemove", onMove);
  } else {
    svg.on("touchmove", onMove);
  }

}

/**
 *
 */
function ignore(){

  var before, after;

  output.text('event: ' + d3.event.type);

  svg.on("mousemove", null);
  svg.on("touchmove", null);

  // Skip out if we're not drawing
  if (!drawing) return;
  drawing = false;

  before = ptdata.length;
  console.group('Line Simplification');
  console.log("Before simplification:", before)

  // Simplify
  ptdata = simplify(ptdata);
  after = ptdata.length;

  console.log("After simplification:", ptdata.length)
  console.groupEnd();

  var percentage = parseInt(100 - (after/before)*100, 10);
  output.html('Points: ' + before + ' => ' + after + '. <b>' + percentage + '% simplification.</b>');

  // Add newly created line to the drawing session
  session.push(ptdata);

  exportData();

  // Redraw the line after simplification
  tick();

}

/**
 *
 */
function onMove(e){

  var type = d3.event.type;
  var point;

  if (type === 'mousemove'){
    point = d3.mouse(this);
    output.text('event: ' + type + ': ' + d3.mouse(this));
  } else {
    // only deal with a single touch input
    point = d3.touches(this)[0];
    output.text('event: ' + type + ': ' + d3.touches(this)[0]);
  }

  // push a new data point onto the back
  ptdata.push({ x: point[0], y: point[1] });
  tick();

}

/**
 * Redraws the path
 */
function tick(){
  path.attr("d", function(d) { return line(d); })
}

/**
 *
 */
function exportData(){
  for ( var i = 0; i < session.length; i++ ){
    var data = JSON.stringify(session[i], null, '\t');
    console.log(data);
    logger.text(data);
  }
}
