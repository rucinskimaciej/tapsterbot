  /*
  MIT License
  Copyright (c) 2016-2018 Pierre-Yves Lapersonne (Mail: dev@pylapersonne.info)
  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:
  The above copyright notice and this permission notice shall be included in all
  copies or substantial portions of the Software.
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
  SOFTWARE.
  */
  /*
  ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一
  */

  /**
  * @file widgets.js
  * @brief JavaScript file containing the source code of the logic behing the widgets
  * @author pylapp
  * @version 2.0.0
  * @since 05/04/2018
  */

  "use strict";

  /**
  * Adds listeners to widgets in the view
  */
  function initWidgets(){

    // Fill the input text containing the URL of the server

    let serverUrl = getRobotServerUrl();
    document.getElementById("setServerUrl").value = serverUrl;

    // The button to click on to define the server's URL

    let toggleServerUrlField = function(){
      let target = document.getElementById("configField");
      let state = target.style.display;
      if ( state == "none" ){
        target.style.display = "block";
        document.getElementById("setServerUrl").focus();
      } else if ( state == "block" ){
        target.style.display = "none";
      } else {
        target.style.display = "block";
        document.getElementById("setServerUrl").focus();
      }
    }
    document.getElementById("configUrlOfServer").addEventListener("click", toggleServerUrlField);

    // The button to click on to clear the console

    let clearConsole = function(){
      let consoleBoard = document.getElementById("console");
      while (consoleBoard.childNodes.length > 3) {
        consoleBoard.removeChild(consoleBoard.lastChild);
      }
    }
    document.getElementById("clearConsole").addEventListener("click", clearConsole);

    // The field with the server's URL

    let registerTimer = null;
    let interval = TYPING_INTERVAL; // in ms
    let readyToSave = function(){
      let valueToSave = document.getElementById("setServerUrl").value;
      if ( setRobotServerUrl(valueToSave) ){
        addSimpleMessage(STRING_SIMPLE_SERVER_URL_SAVED +": "+valueToSave);
      } else {
        addErrorMessage(STRING_ERROR_SERVER_URL_NOT_SAVED +": "+valueToSave);
      }
    }
    let setUrlOfServerInBase = function(){
      if (registerTimer != null) clearTimeout(registerTimer);
      registerTimer = setTimeout(readyToSave, interval)
    }

    document.getElementById("setServerUrl").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ){
        readyToSave();
      } else {
        setUrlOfServerInBase();
      }
    }

    // Widgets for requests

    initWidgetReset();
    initWidgetStatus();
    initWidgetContactZ();
    initWidgetsTap();
    initWidgetsLongTap();
    initWidgetsNTap();
    initWidgetsStressTap();
    initWidgetsSwipe();
    initWidgetsNSwipe();
    initWidgetsStressSwipe();
    initWidgetGetPosition();
    initWidgetsSetPosition();
    initWidgetGetAngles();
    initWidgetsSetAngles();
    initWidgetDance();
    initWidgetStopDance();
    initWidgetDrawStar();
    initWidgetDrawCircle();
    initWidgetDrawSpiral();
    initWidgetDrawSquare();

  }

  /**
  * Initializes the logic of the widget which sends a "reset robot" request
  */
  function initWidgetReset(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"reset\" request...")
      sendPostRequest(baseUrl+  URL_ROBOT_API_RESET, "{}");
    }
    document.getElementById("buttonRequestReset").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widget which sends a "get contact z" request
  */
  function initWidgetContactZ(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"get contact z\" request...")
      sendGetRequest(baseUrl+  URL_ROBOT_API_GET_CONTACT_Z);
    }
    document.getElementById("buttonRequestContactZ").addEventListener("click", sendRequest);
  }


  /**
  * Initializes the logic of the widget which sends a "status" request
  */
  function initWidgetStatus(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"status\" request...");
      sendGetRequest(baseUrl + URL_ROBOT_API_STATUS);
    }
    document.getElementById("buttonRequestStatus").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widgets which sends a "tap" request
  */
  function initWidgetsTap(){
    let sendRequest = function(){
      let body = getRequestTapParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("tap-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"tap\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_TAP, body);
      }
    }
    document.getElementById("buttonRequestTap").addEventListener("click", sendRequest);
    document.getElementById("tap-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends a "long tap" request
  */
  function initWidgetsLongTap(){
    let sendRequest = function(){
      let body = getRequestLongTapParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("long-tap-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"long tap\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_LONG_TAP, body);
      }
    }
    document.getElementById("buttonRequestLongTap").addEventListener("click", sendRequest);
    document.getElementById("long-tap-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends n times a "tap" request
  */
  function initWidgetsNTap(){
    let sendRequest = function(){
      let params = getRequestNTapParameters();
      if ( params == null || params.length != 3 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("n-tap-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        let body = '{"n": "'+params[0]+'", "x": "' + params[1] +'", "y": "'+params[2]+'"}';
        addSimpleMessage("[Request] Sending \"n-tap\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_N_TAP, body);
      }
    }
    document.getElementById("buttonRequestNTap").addEventListener("click", sendRequest);
    document.getElementById("n-tap-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends n times a "stress tap"
  */
  function initWidgetsStressTap(){
    let sendRequest = function(){
      let params = getRequestStressTapParameters();
      if ( params == null || params.length != 3 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("stress-tap-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        let i = 1;
        let max = params[0];
        let toTrigger = function(){
          if ( i >= max ) clearInterval(interval);
          let body = '{"x": "' + params[1] +'", "y": "' + params[2] + '"}';
          addSimpleMessage("[Request] Sending \"stress-tap\" ("+i+"/"+max+") request with parameters \""+body+"\"");
          sendPostRequest(baseUrl + URL_ROBOT_API_TAP, body);
          i++;
        }
        let interval = setInterval(function(){toTrigger()}, WAIT_TIME_STRESS_TAP);
      }
    }
    document.getElementById("buttonRequestStressTap").addEventListener("click", sendRequest);
    document.getElementById("stress-tap-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends a "swipe"
  */
  function initWidgetsSwipe(){
    let sendRequest = function(){
      let body = getRequestSwipeParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("swipe-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"swipe\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_SWIPE, body);
      }
    }
    document.getElementById("buttonRequestSwipe").addEventListener("click", sendRequest);
    document.getElementById("swipe-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends n times a "swipe" request
  */
  function initWidgetsNSwipe(){
    let sendRequest = function(){
      let params = getRequestNSwipeParameters();
      if ( params == null || params.length != 5 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("n-swipe-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        let body = '{"n": "'+params[0]+'", "startX": "' + params[1] + '", "startY": "' + params[2] +'", "endX": "'+params[3] +'", "endY": "' + params[4] + '"}';
        addSimpleMessage("[Request] Sending \"n-swipe\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_N_SWIPE, body);
      }
    }
    document.getElementById("buttonRequestNSwipe").addEventListener("click", sendRequest);
    document.getElementById("n-swipe-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widgets which sends n times a "swipe" request very quickly
  */
  function initWidgetsStressSwipe(){
    let sendRequest = function(){
      let params = getRequestStressSwipeParameters();
      if ( params == null || params.length != 5 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("stress-swipe-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        let i = 1;
        let max = params[0];
        let toTrigger = function(){
          if ( i >= max ) clearInterval(interval);
          let body = '{"startX": "' + params[1] + '", "startY": "' + params[2] +'", "endX": "'+params[3] +'", "endY": "' + params[4] + '"}';
          addSimpleMessage("[Request] Sending \"stress-swipe\" ("+i+"/"+max+") request with parameters \""+body+"\"");
          sendPostRequest(baseUrl + URL_ROBOT_API_SWIPE, body);
          i++;
        }
        let interval = setInterval(function(){toTrigger()}, WAIT_TIME_STRESS_SWIPE);
      }
    }
    document.getElementById("buttonRequestStressSwipe").addEventListener("click", sendRequest);
    document.getElementById("stress-swipe-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widget which sends a "get position" request
  */
  function initWidgetGetPosition(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"get position\" request...");
      sendGetRequest(baseUrl + URL_ROBOT_API_GET_POSITION);
    }
    document.getElementById("buttonRequestGetPosition").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widgets which sends a "set position" request
  */
  function initWidgetsSetPosition(){
    let sendRequest = function(){
      let body = getRequestSetPositionParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("set-position-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"set-position\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_SET_POSITION, body);
      }
    }
    document.getElementById("buttonRequestSetPosition").addEventListener("click", sendRequest);
    document.getElementById("set-position-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widget which sends a "get angles" request
  */
  function initWidgetGetAngles(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"get angles\" request...");
      sendGetRequest(baseUrl + URL_ROBOT_API_GET_ANGLES);
    }
    document.getElementById("buttonRequestGetAngles").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widgets which sends a "set angles" request
  */
  function initWidgetsSetAngles(){
    let sendRequest = function(){
      let body = getRequestSetAnglesParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("set-position-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"set-angles\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_SET_ANGLES, body);
      }
    }
    document.getElementById("buttonRequestSetAngles").addEventListener("click", sendRequest);
    document.getElementById("set-angles-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widget which sends a "dance" request
  */
  function initWidgetDance(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"dance\" request...")
      sendPostRequest(baseUrl+  URL_ROBOT_API_DANCE, "{}");
    }
    document.getElementById("buttonRequestDance").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widget which sends a "dance" request
  */
  function initWidgetStopDance(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"stop dance\" request...")
      sendPostRequest(baseUrl+  URL_ROBOT_API_STOP_DANCE, "{}");
    }
    document.getElementById("buttonRequestStopDancing").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widget which sends a "draw star" request
  */
  function initWidgetDrawStar(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"draw star\" request...")
      sendPostRequest(baseUrl+  URL_ROBOT_API_DRAW_STAR, "{}");
    }
    document.getElementById("buttonRequestDrawStar").addEventListener("click", sendRequest);
  }

  /**
  * Initializes the logic of the widget which sends a "draw a circle" request
  */
  function initWidgetDrawCircle(){
    let sendRequest = function(){
      let body = getRequestDrawCircleParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("draw-circle-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"draw circle\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_DRAW_CIRCLE, body);
      }
    }
    document.getElementById("buttonRequestDrawCircle").addEventListener("click", sendRequest);
    document.getElementById("draw-circle-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widget which sends a "draw a spiral" request
  */
  function initWidgetDrawSpiral(){
    let sendRequest = function(){
      let body = getRequestDrawSpiralParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("draw-spiral-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"draw spiral\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_DRAW_SPIRAL, body);
      }
    }
    document.getElementById("buttonRequestDrawSpiral").addEventListener("click", sendRequest);
    document.getElementById("draw-spiral-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Initializes the logic of the widget which sends a "draw a square" request
  */
  function initWidgetDrawSquare(){
    let sendRequest = function(){
      let body = getRequestDrawSquareParameters();
      if ( body == null || body.length <= 0 ){
        addErrorMessage("[Parameters] Not suitable with '"+document.getElementById("draw-square-parameters").value+"'");
      } else {
        let baseUrl = getRobotServerUrl();
        addSimpleMessage("[Request] Sending \"draw square\" request with parameters \""+body+"\"");
        sendPostRequest(baseUrl + URL_ROBOT_API_DRAW_SQUARE, body);
      }
    }
    document.getElementById("buttonRequestDrawSquare").addEventListener("click", sendRequest);
    document.getElementById("draw-square-parameters").onkeydown = function(e){
      if ( e.which == 13 /*ENTER key*/ ) sendRequest();
    }
  }

  /**
  * Returns the parameters for the tap request, or null if the format is not correct
  * @return String -
  */
  function getRequestTapParameters(){
    let parameters = document.getElementById("tap-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_TAP.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "'+parameters[1]+'"}';
  }

 /**
  * Returns the parameters for long-tap request, or null if the format is not correct
  * @return array -
  */
  function getRequestLongTapParameters(){
    let parameters = document.getElementById("long-tap-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_LONG_TAP.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "'+parameters[1]+'", "duration": "'+parameters[2]+'"}';
  }

  /**
  * Returns the parameters for n-tap request, or null if the format is not correct
  * @return array -
  */
  function getRequestNTapParameters(){
    let parameters = document.getElementById("n-tap-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_N_TAP.test(parameters) ){
      return null;
    }
    return parameters.split(" ");
  }

  /**
  * Returns the parameters for stress-tap request, or null if the format is not correct
  * @return array -
  */
  function getRequestStressTapParameters(){
    let parameters = document.getElementById("stress-tap-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_STRESS_TAP.test(parameters) ){
      return null;
    }
    return parameters.split(" ");
  }

  /**
  * Returns the parameters for the swipe request, or null if the format is not correct
  * @return String -
  */
  function getRequestSwipeParameters(){
    let parameters = document.getElementById("swipe-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_SWIPE.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"startX": "' + parameters[0] + '", "startY": "' + parameters[1] +
    '", "endX": "' + parameters[2] + '", "endY": "' + parameters[3] + '"}';
  }

  /**
  * Returns the parameters for n-swipe command, or null if the format is not correct
  * @return array -
  */
  function getRequestNSwipeParameters(){
    let parameters = document.getElementById("n-swipe-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_N_SWIPE.test(parameters) ){
      return null;
    }
    return parameters.split(" ");
  }

  /**
  * Returns the parameters for stress-swipe command, or null if the format is not correct
  * @return array -
  */
  function getRequestStressSwipeParameters(){
    let parameters = document.getElementById("stress-swipe-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_STRESS_SWIPE.test(parameters) ){
      return null;
    }
    return parameters.split(" ");
  }

  /**
  * Returns the parameters for the set-position request, or null if the format is not correct
  * @return String -
  */
  function getRequestSetPositionParameters(){
    let parameters = document.getElementById("set-position-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_SET_POSITION.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "' + parameters[1] + '", "z": "' + parameters[2] + '"}';
  }

  /**
  * Returns the parameters for the set-angles request, or null if the format is not correct
  * @return String -
  */
  function getRequestSetAnglesParameters(){
    let parameters = document.getElementById("set-angles-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_SET_ANGLES.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"theta1": "' + parameters[0] +'", "theta2": "' + parameters[1] + '", "theta3": "' + parameters[2] + '"}';
  }

  /**
  * Returns the parameters for the draw circle request, or null if the format is not correct
  * @return String -
  */
  function getRequestDrawCircleParameters(){
    let parameters = document.getElementById("draw-circle-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_DRAW_CIRCLE.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "' + parameters[1] + '", "r": "' + parameters[2] + '"}';
  }

  /**
  * Returns the parameters for the draw spiral request, or null if the format is not correct
  * @return String -
  */
  function getRequestDrawSpiralParameters(){
    let parameters = document.getElementById("draw-spiral-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_DRAW_SPIRAL.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "' + parameters[1] + '", "r": "' 
      + parameters[2] + '", "n": "'+parameters[3]+'"}';
  }

  /**
  * Returns the parameters for the draw square request, or null if the format is not correct
  * @return String -
  */
  function getRequestDrawSquareParameters(){
    let parameters = document.getElementById("draw-square-parameters").value;
    parameters = parameters.trim();
    if ( ! REGEX_PARAMETER_DRAW_SQUARE.test(parameters) ){
      return null;
    }
    let PRECISION = 1;
    return '{"n": "' + PRECISION +'", "length": "' + parameters +'"}';
  }
