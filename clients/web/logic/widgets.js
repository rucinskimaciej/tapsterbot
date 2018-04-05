/*
MIT License
Copyright (c) 2016-2018 Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
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
* @version 1.0.0
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
      if ( state == "none" ) target.style.display = "block";
      else if ( state == "block" ) target.style.display = "none";
      else target.style.display = "block";
    }
    document.getElementById("configUrlOfServer").addEventListener("click", toggleServerUrlField);

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
    document.getElementById("setServerUrl").addEventListener("keyup", setUrlOfServerInBase);

    // Buttons for requests
    initWidgetButtonReset();
    initWidgetButtonStatus();
    initWidgetButtonTap();

  }

  /**
   * Initializes the logic of the widget which sends a "reset robot" request
   */
  function initWidgetButtonReset(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"reset\" request...")
      sendPostRequest(baseUrl+  URL_ROBOT_API_RESET, "{}");
    }
    document.getElementById("buttonRequestReset").addEventListener("click", sendRequest);
  }

  /**
   * Initializes the logic of the widget which sends a "status" request
   */
  function initWidgetButtonStatus(){
    let sendRequest = function(){
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"status\" request...");
      sendGetRequest(baseUrl + URL_ROBOT_API_STATUS);
    }
    document.getElementById("buttonRequestStatus").addEventListener("click", sendRequest);
  }

  /**
   * Initializes the logic of the widget which sends a "tap" request
   */
  function initWidgetButtonTap(){
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
  }

 /**
  * Returns the parameters for the tap request, or null if the format is not correct
  * @return String -
  */
  function getRequestTapParameters(){
    let parameters = document.getElementById("tap-parameters").value;
    if ( ! REGEX_PARAMETER_TAP.test(parameters) ){
      return null;
    }
    parameters = parameters.split(" ");
    return '{"x": "' + parameters[0] +'", "y": "'+parameters[1]+'"}';
  }
