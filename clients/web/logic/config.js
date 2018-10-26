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
* @file config.js
* @brief Classic JavaScript file containing configuration to use for the web app
* @author pylapp
* @version 2.0.0
* @since 29/03/2018
*/

"use strict";

/* *********** *
 * ROBOT'S API *
 * *********** */

 let URL_ROBOT_API_RESET = "/reset";
 let URL_ROBOT_API_STATUS = "/status";
 let URL_ROBOT_API_GET_CONTACT_Z = "/contactZ";
 let URL_ROBOT_API_TAP = "/tap";
 let URL_ROBOT_API_N_TAP = "/nTap";
 let URL_ROBOT_API_LONG_TAP = "/longTap";
 let URL_ROBOT_API_SWIPE = "/swipe";
 let URL_ROBOT_API_N_SWIPE = "/nSwipe";
 let URL_ROBOT_API_GET_POSITION = "/position";
 let URL_ROBOT_API_SET_POSITION = "/setPosition";
 let URL_ROBOT_API_GET_ANGLES = "/angles";
 let URL_ROBOT_API_SET_ANGLES = "/setAngles";
 let URL_ROBOT_API_DANCE = "/dance";
 let URL_ROBOT_API_STOP_DANCE = "/stopDancing";
 let URL_ROBOT_API_DRAW_STAR = "/drawStar";
 let URL_ROBOT_API_DRAW_CIRCLE = "/drawCircle";
 let URL_ROBOT_API_DRAW_SPIRAL = "/drawSpiral";
 let URL_ROBOT_API_DRAW_SQUARE = "/drawSquare";

/* ******************** *
 * REGEX FOR PARAMETERS *
 * ******************** */

 let REGEX_PARAMETER_TAP = /^[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_LONG_TAP = /^[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_N_TAP = /^[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_STRESS_TAP = /^[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_SWIPE = /^[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_N_SWIPE = /^[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_STRESS_SWIPE = /^[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_SET_POSITION = /^[-]{0,1}[0-9]+\s[-]{0,1}[0-9]+\s[-]{0,1}[0-9]+$/;
 let REGEX_PARAMETER_SET_ANGLES = /^[-]{0,1}[0-9]+\s[-]{0,1}[0-9]+\s[-]{0,1}[0-9]+$/;
 let REGEX_PARAMETER_DRAW_CIRCLE = /^[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_DRAW_SPIRAL = /^[0-9]+\s[0-9]+\s[0-9]+\s[0-9]+$/;
 let REGEX_PARAMETER_DRAW_SQUARE = /^[0-9]+$/;

/* ******* *
 * STORAGE *
 * ****** */

/**
 * The key used in with local storage tor ead /w rite the server's URL
 */
 let KEY_STORAGE_SERVER_URL = "tapster-storage-robot-server-url";


 /* ******** *
  * MESSAGES *
  * ******** */

let STRING_ERROR_BAD_BROWSER = "The web app may be unstable with this web browser. Please use Chromium 37+, Chrome 37+ or Firefox 44+";
let STRING_ERROR_SERVER_URL_NOT_SAVED = "The server's URL has not been saved";
let STRING_SIMPLE_SERVER_URL_SAVED = "The server's URL has been saved";

/* ***** *
 * MISC. *
 * ***** */

/**
 * Wait this amount of time after each key-u⁻event in server's URL field
 */
 let TYPING_INTERVAL = 2000;

 /**
  * Delay in ms between each tap
  */
 let WAIT_TIME_BETWEEN_TAP = 500;

 /**
  * Delay in ms between each tap during stress taps process
  */
 let WAIT_TIME_STRESS_TAP = 250;

 /**
  * Delay in ms between each swipe
  */
 let WAIT_TIME_BETWEEN_SWIPE = 500;

/**
 * Delay in seconds between each swipe during stress swipes process
 */
 let WAIT_TIME_STRESS_SWIPE = 333;
