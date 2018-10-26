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
* @file network.js
* @brief JavaScript file containing the source code of code which deals with HTTP requests
* @author pylapp
* @version 1.0.0
* @since 05/04/2018
*/

  "use strict";

/**
 * Sends a GET HTTP request using the url in parameter
 * @param url - The host to reach
 * @return Promise - The promise which will send the request, with the returned data if resolved
 */
function sendGetRequest(url){

  return new Promise(function(resolve, reject){

    if ( ! checkIfUrlOfServerDefined() ){
      reject("Undefined server");
      return;
    }

    let xhr = new XMLHttpRequest();

    try {
      xhr.open("GET", url, true);
      xhr.onreadystatechange = function(){
        if ( xhr.readyState == XMLHttpRequest.DONE ){
          if ( xhr.status == 200 ){
            let data = xhr.responseText;
            addSimpleMessage("[Request] Sent.");
            addResultMessage("[Response] "+data);
            resolve(data);
          } else {
            addErrorMessage("[Request] Error with the server: "+xhr.status);
            reject(xhr.status);
          }
        }
      }
      xhr.send(null);
    } catch ( error ){
      console.error("Error during request sending: "+error);
      addErrorMessage("[Request] Error with the server: "+error);
      reject(error);
    }

  });

}

/**
 * Sends a POST HTTP request using the url in parameter
 * @param url - The host to reach
 * @param body - The body of the request to send
 * @return Promise - The promise which will send the request, with the returned data if resolved
 */
function sendPostRequest(url, body){

  return new Promise(function(resolve, reject){

    if ( ! checkIfUrlOfServerDefined() ){
      reject("Undefined server");
      return;
    }

    let xhr = new XMLHttpRequest();

    try {
      xhr.open("POST", url, true);
      xhr.setRequestHeader("Content-type", "application/json");
      xhr.onreadystatechange = function(){
        if ( xhr.readyState == XMLHttpRequest.DONE ){
          if ( xhr.status == 200 ){
            let data = xhr.responseText;
            addSimpleMessage("[Request] Sent.");
            addResultMessage("[Response] "+data);
            resolve(data);
          } else {
            addErrorMessage("[Request] Error with the server: "+xhr.status);
            reject(xhr.status);
          }
        }
      }
      xhr.send(body);
    } catch ( error ){
      console.error("Error during request sending: "+error);
      addErrorMessage("[Request] Error with the server: "+error);
      reject(error);
    }

  });

}
