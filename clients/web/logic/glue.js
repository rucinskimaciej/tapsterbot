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
* @file glue.js
* @brief Classic JavaScript file which makes the glue between the GUUI and other components
* @author pylapp
* @version 1.0.0
* @since 29/03/2018
*/

  "use strict";

  /**
  * Checks if the browser is suitable or not.
  * A suitable web browser must be able to deal with local storage, manifest for offline apps, service workers and ES6 Promise
  * @return boolean- True if suitable, false otherwise
  */
  function isWebBrowserSuitable(){

    // Assuming the world of web browsers is perfect
    let isSuitable = true;

    // No local storage? Get out.
    if (typeof(Storage) == "undefined") isSuitable = false;

    let magicString  = navigator.sayswho= (function(){
      var ua= navigator.userAgent, tem,
      M= ua.match(/(opera|chrome|safari|firefox|msie|trident(?=\/))\/?\s*(\d+)/i) || [];
      if(/trident/i.test(M[1])){
        tem=  /\brv[ :]+(\d+)/g.exec(ua) || [];
        return 'IE '+(tem[1] || '');
      }
      if(M[1]=== 'Chrome'){
        tem= ua.match(/\b(OPR|Edge)\/(\d+)/);
        if(tem!= null) return tem.slice(1).join(' ').replace('OPR', 'Opera');
      }
      M= M[2]? [M[1], M[2]]: [navigator.appName, navigator.appVersion, '-?'];
      if((tem= ua.match(/version\/(\d+)/i))!= null) M.splice(1, 1, tem[1]);
      return M.join(' ');
    })();
    let browserName = magicString.split(" ")[0];
    let browserVersion = magicString.split(" ")[1];

    // Case of Firefox
    if ( browserName.includes("Firefox") && (browserVersion <= 44) ){
      isSuitable = false;
    }

    // Case of Chrome
    if ( (browserName.includes("Chromium") || browserName.includes("Chrome"))
    && (browserVersion <= 37) ){
      isSuitable = false;
    }

    return isSuitable;

  }

  /**
   * Check if the URL of the server to reach has been defined.
   * If not, displays an error message and return false.
   * If defined,r eturnt rue.
   * @return boolean -
   */
  function checkIfUrlOfServerDefined(){
      let serverUrl = document.getElementById("setServerUrl").value;
      if (serverUrl == null || serverUrl.length <= 0){
        addErrorMessage("The URL of the server cannot be '"+serverUrl+"'");
        return false;
      } else {
        return true;
      }
  }

  /**
  * Adds in the web app's console an error message
  * @param message - The message to add
  */
  function addErrorMessage(message){
    let node = document.createElement("p");
    node.textContent = message;
    node.className = "errorMessage";
    let console = document.getElementById("console");
    console.appendChild(node);
    console.scrollTop = console.scrollHeight;
  }

  /**
  * Adds in the web app's console a simple message
  * @param message - The message to add
  */
  function addSimpleMessage(message){
    let node = document.createElement("p");
    node.textContent = message;
    node.className = "simpleMessage";
    let console = document.getElementById("console");
    console.appendChild(node);
    console.scrollTop = console.scrollHeight;
  }

  /**
  * Adds in the web app's console a  message containing the resukts of a request
  * @param message - The message to add
  */
  function addResultMessage(message){
    let node = document.createElement("p");
    node.textContent = message;
    node.className = "resultMessage";
    let console = document.getElementById("console");
    console.appendChild(node);
    console.scrollTop = console.scrollHeight;
  }
