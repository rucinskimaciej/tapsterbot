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
* @file storage.js
* @brief Classic JavaScript file which handles with storage things
* @author pylapp
* @version 2.0.0
* @since 29/03/2018
*/

"use strict";


/**
* Save in the <b>local storage</b> the URL of the server which will be used
* @param url - The URL to save
^ @return boolean - True if added, false otherwise or if local storage not available
*/
function setRobotServerUrl(url){
  if (typeof(Storage) !== "undefined") {
    console.log("Storage - save value of server's URL - "+url);
    url = url.trim();
    localStorage.setItem(KEY_STORAGE_SERVER_URL, url);
    return true;
  } else {
    return false;
  }
}

/**
* Read in the <b>local storage</b> the URL of the server which will be used
^ @return string - The URL or null if local storage not supported or empty string is never defined
*/
function getRobotServerUrl(){
  let result = null;
  if (typeof(Storage) !== "undefined") {
    result = localStorage.getItem(KEY_STORAGE_SERVER_URL);
    if (result == null) result = ""
    else result = result.trim();
  }
  return result
}
