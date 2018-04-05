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

  }

  /**
   * Initializes the logic of the widget which sends a "reset robot" request
   */
  function initWidgetButtonReset(){

    let sendRequest = function(){
      if ( ! checkIfUrlOfServerDefined() ) return;
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"reset\" request...")
      let xhr = new XMLHttpRequest();
      try {
        xhr.open("POST", baseUrl+  URL_ROBOT_API_RESET, true);
        xhr.onreadystatechange = function(){
          if (xhr.readyState == XMLHttpRequest.DONE){
            if (xhr.status == 200){
              addSimpleMessage("[Request] Sent.");
              addResultMessage("[Response] "+xhr.responseText);
            } else {
              addErrorMessage("[Request] Error with the server: "+xhr.status);
            }
          }
        }
        xhr.send("{}");
      } catch (error){
        console.error("Error during request sending: "+error);
        addErrorMessage("[Request] Error with the server: "+error);
      }
    }

    document.getElementById("buttonRequestReset").addEventListener("click", sendRequest);

  }

  /**
   * Initializes the logic of the widget which sends a "status" request
   */
  function initWidgetButtonStatus(){

    let sendRequest = function(){
      if ( ! checkIfUrlOfServerDefined() ) return;
      let baseUrl = getRobotServerUrl();
      addSimpleMessage("[Request] Sending \"status\" request...")
      let xhr = new XMLHttpRequest();
      try {
        xhr.open("GET", baseUrl+  URL_ROBOT_API_STATUS, true);
        xhr.onreadystatechange = function(){
          if (xhr.readyState == XMLHttpRequest.DONE){
            if (xhr.status == 200){
              addSimpleMessage("[Request] Sent.");
              addResultMessage("[Response] "+xhr.responseText);
            } else {
              addErrorMessage("[Request] Error with the server: "+xhr.status);
            }
          }
        }
        xhr.send(null);
      } catch (error){
        console.error("Error during request sending: "+error);
        addErrorMessage("[Request] Error with the server: "+error);
      }
    }

    document.getElementById("buttonRequestStatus").addEventListener("click", sendRequest);

  }
