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

# IP address where the Tapster2 bot is reachable
ROBOT_IP_ADDRESS = "127.0.0.1"

# Port to use to as to reach the robot
ROBOT_PORT = "4242"

# The protocol to use for the robot
ROBOT_PROTOCOL = "http"

# The URL to use to as to reach the robot
ROBOT_URL = ROBOT_PROTOCOL + "://" + ROBOT_IP_ADDRESS + ":" + ROBOT_PORT

# Regular expressions for commands
PATTERN_GET_ANGLES = re.compile("^get-angles$")
PATTERN_SET_ANGLES = re.compile("^set-angles (\d+(\.\d+)?) (\d+(\.\d+)?) (\d+(\.\d+)?)$")
PATTERN_GET_POSITION = re.compile("^get-position$")
PATTERN_SET_POSITION = re.compile("^set-position (\d+(\.\d+)?) (\d+(\.\d+)?) (\d+(\.\d+)?)$")
PATTERN_TAP = re.compile("^tap (\d+) (\d+)$")
PATTERN_RESET = re.compile("^reset$")
PATTERN_GET_CALIBRATION = re.compile("^get-calibration$")
PATTERN_SET_CALIBRATION = re.compile("^set-calibration .+$") #FIXME Improve the regex to make it match to JSON calibration data string
PATTERN_CONFIG = re.compile("^config$")
PATTERN_HELP = re.compile("^help$")
PATTERN_BYE = re.compile("^bye$")
