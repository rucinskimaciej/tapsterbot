# -*- coding: utf_8 -*-

"""
MIT License
Copyright (c) 2016-2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
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
"""
# ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一

"""
File.......: config.py
Brief......: File including configuration to use and to apply on the client
Author.....: pylapp
Version....: 1.0.0
Since......: 10/01/2018
"""

import re

# Version of this Tapster2 Python client
CLIENT_VERSION = "v1.0.0"

# ************
# Robot things
# ************

# IP address where the Tapster2 bot is reachable
ROBOT_IP_ADDRESS = "127.0.0.1"

# Port to use to as to reach the robot
ROBOT_PORT = "4242"

# The protocol to use for the robot
ROBOT_PROTOCOL = "http"

# The URL to use to as to reach the robot
ROBOT_URL = ROBOT_PROTOCOL + "://" + ROBOT_IP_ADDRESS + ":" + ROBOT_PORT

# Delay in seconds between each tap
WAIT_TIME_BETWEEN_TAP=0.3

# ****************
# URL of Robot API
# ****************

ROBOT_URL_GET_ANGLES = "/angles"
ROBOT_URL_SET_ANGLES = "/setAngles"
ROBOT_URL_GET_POSITION = "/position"
ROBOT_URL_SET_POSITION = "/setPosition"
ROBOT_URL_TAP = "/tap"
ROBOT_URL_RESET = "/reset"
ROBOT_URL_GET_CALIBRATION = "/calibrationData"
ROBOT_URL_SET_CALIBRATION = "/setCalibrationData"
ROBOT_URL_STATUS = "/status"
ROBOT_URL_DANCE = "/dance"
ROBOT_URL_STOP_DANCE = "/stopDancing"
ROBOT_URL_SWIPE = "/swipe"
ROBOT_URL_POSITION_FOR_SCREEN_COORD = "/positionForScreenCoordinates"

# ********************************
# Regular expressions for commands
# ********************************

ROBOT_PATTERN_GET_ANGLES = re.compile("^get-angles$")
ROBOT_PATTERN_SET_ANGLES = re.compile("^set-angles (\d+(\.\d+)?) (\d+(\.\d+)?) (\d+(\.\d+)?)$")
ROBOT_PATTERN_GET_POSITION = re.compile("^get-position$")
ROBOT_PATTERN_SET_POSITION = re.compile("^set-position (\d+(\.\d+)?) (\d+(\.\d+)?) (\d+(\.\d+)?)$")
ROBOT_PATTERN_TAP = re.compile("^tap (\d+) (\d+)$")
ROBOT_PATTERN_RESET = re.compile("^reset$")
ROBOT_PATTERN_GET_CALIBRATION = re.compile("^get-calibration$")
ROBOT_PATTERN_SET_CALIBRATION = re.compile("^set-calibration .+$") #FIXME Improve the regex to make it match to JSON calibration data string
ROBOT_PATTERN_CONFIG = re.compile("^config$")
ROBOT_PATTERN_HELP = re.compile("^help$")
ROBOT_PATTERN_BYE = re.compile("^bye$")
ROBOT_PATTERN_STATUS = re.compile("^status$")
ROBOT_PATTERN_DANCE = re.compile("^dance$")
ROBOT_PATTERN_STOP_DANCE = re.compile("^stop-dance$")
ROBOT_PATTERN_SWIPE = re.compile("^swipe (\d+) (\d+) (\d+) (\d+)$")
ROBOT_PATTERN_N_TAP = re.compile("^n-tap (\d+) (\d+) (\d+)$")
ROBOT_PATTERN_POSITION_FOR_SCREEN_COORD = re.compile("^pos3d (\d+) (\d+)$")
