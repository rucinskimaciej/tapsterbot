# MIT License
# Copyright (c) 2016-2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
# Copyright (c) 2018  Orange
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
# The above copyright notice and this permission notice shall be included in all
# copies or substantial portions of the Software.
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
# SOFTWARE.
#
# ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一

#
# File.......: config.robot
# Brief......: File contaning configuration to apply to kewyrods
# Author.....: pylapp
# Version....: 2.0.0
# Since......: 17/01/2018

*** Settings ***

Documentation
...    Here are the variables which will be used by the keywords.
...    You can define here the IP adress of the robot, the port in use or other things.

*** Variables ***

# Version of the Robot Framework "client"
${CLIENT_VERSION}    v2.0.0

# ************
# Robot things
# ************

# Default IP address where the Tapster2 bot is reachable
${DEFAULT_ROBOT_IP_ADDRESS}    127.0.0.1

# Default port to use to as to reach the robot
${DEFAULT_ROBOT_PORT}    4242

# Default protocol to use for the robot
${DEFAULT_ROBOT_PROTOCOL}    http

# Default URL to use to as to reach the robot
${DEFAULT_ROBOT_URL}    ${DEFAULT_ROBOT_PROTOCOL}://${DEFAULT_ROBOT_IP_ADDRESS}:${DEFAULT_ROBOT_PORT}

# Delay in seconds between each tap
${WAIT_TIME_BETWEEN_TAP}    0.5s

# Delay in seconds between each swipe
${WAIT_TIME_BETWEEN_SWIPE}    0.5s

# Delay in seconds between each swipe during stress swipes process
${WAIT_TIME_STRESS_SWIPE}    0.33s

# Delay in seconds between each tap during stress taps process
${WAIT_TIME_STRESS_TAP}    0.25s

# The default duration for a long tap, in milliseconds
${DEFAULT_DURATION_LONG_TAP}    1500

# The default duration for a double or triple tap, duration between each tap, in milliseconds
${DEFAULT_DURATION_MULTI_TAP}    100

# Deal in seconds between each operation picked and triggered from a commands file
${WAIT_BETWEEN_CASCADED_OPERATION}    0.5s

# The escape symbol to commnt a command store din the commands files
${ESCAPE_SYMBOL}    #

# ****************
# URL of Robot API
# ****************

${ROBOT_URL_GET_ANGLES}    /angles
${ROBOT_URL_SET_ANGLES}    /setAngles
${ROBOT_URL_GET_POSITION}    /position
${ROBOT_URL_SET_POSITION}    /setPosition
${ROBOT_URL_TAP}    /tap
${ROBOT_URL_DOUBLE_TAP}    /doubleTap
${ROBOT_URL_TRIPLE_TAP}    /tripleTap
${ROBOT_URL_LONG_TAP}    /longTap
${ROBOT_URL_RESET}    /reset
${ROBOT_URL_GET_CALIBRATION}    /calibrationData
${ROBOT_URL_SET_CALIBRATION}    /setCalibrationData
${ROBOT_URL_STATUS}    /status
${ROBOT_URL_DANCE}    /dance
${ROBOT_URL_STOP_DANCE}    /stopDancing
${ROBOT_URL_SWIPE}    /swipe
${ROBOT_URL_POSITION_FOR_SCREEN_COORD}    /positionForScreenCoordinates
${ROBOT_URL_ANGLES_FOR_POSITION}    /anglesForPosition
${ROBOT_URL_CONTACT_Z}    /contactZ
${ROBOT_URL_DRAW_RANDOM}    /drawRandomPattern
