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
File.......: robot-api.py
Brief......: File including the API of the robot, using HTTP requests
Author.....: pylapp
Version....: 1.0.0
Since......: 10/01/2018
"""

# *******
# IMPORTS
# *******

import requests # You must install previously this module
from config import *

# *********
# FUNCTIONS
# *********

# Function: robot_getAngles()
def robot_getAngles():
        """
            Sends to the robot's server an HTTP request so as to get the arms' angles values.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending get-angles request..."
        request = requests.get( ROBOT_URL + ROBOT_URL_GET_ANGLES )
        print request.text
        return request.json()
# End of Function: robot_getAngles()

# Function robot_setAngles( theta1, theta1, theta3 )
def robot_setAngles( theta1, theta2, theta3 ):
        """
            Sends to the robot's server an HTTP request so as to set the arms' angles values.
            Parameters: theta1, theta2, theta3 as float numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending set-angles request..."
        payload = {'theta1': theta1, 'theta2': theta2, 'theta3': theta3}
        request = requests.post( ROBOT_URL + ROBOT_URL_SET_ANGLES, data=payload)
        print request.text
        return request.json()
# End of Function: robot_setAngles( theta1, theta1, theta3 )

# Function: robot_getPosition()
def robot_getPosition():
        """
            Sends to the robot's server an HTTP request so as to get its position in 3D landmark
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending get-position request..."
        request = requests.get( ROBOT_URL + ROBOT_URL_GET_POSITION )
        print request.text
        return request.json()
# End of Function: robot_getPosition()

# Function: robot_setPosition( x, y, z )
def robot_setPosition( x, y, z ):
        """
            Sends to the robot's server an HTTP request so as to set the position of its arms in 3D landmark.
            Parameters: x, y, z as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending set-position request..."
        payload = {'x': x, 'y': y, 'z': z}
        request = requests.post( ROBOT_URL + ROBOT_URL_SET_POSITION, data=payload)
        print request.text
        return request.json()
# End of Function: robot_setPosition( x, y, z )

# Function: robot_tap( x, y )
def robot_tap( x, y ):
        """
            Sends to the robot's server an HTTP request so as to make it tp at (x, y) using the device's 2D landmark.
            Parameters: x, y as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending tap request..."
        payload = {'x': x, 'y': y}
        request = requests.post( ROBOT_URL + ROBOT_URL_TAP, data=payload)
        print request.text
        return request.json()
# End of Function: robot_tap( x, y )

# Function: robot_reset()
def robot_reset():
        """
            Sends to the robot's server an HTTP request so as to reset it.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending reset request..."
        payload = {}
        request = requests.post( ROBOT_URL + ROBOT_URL_RESET, data=payload)
        print request.text
        return request.json()
# End of Function: robot_reset()

# Function: robot_getCalibration()
def robot_getCalibration():
        """
            Sends to the robot's server an HTTP request so as to get its calibration data.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending get-calibration request..."
        request = requests.get( ROBOT_URL + ROBOT_URL_GET_CALIBRATION )
        print request.text
        return request.json()
# End of Function: robot_getCalibration()

# Function: robot_setCalibration( jsonData )
def robot_setCalibration( jsonData ):
        """
            Sends to the robot's server an HTTP request so as to define its calibration data to use.
            Parameter: the calibration data in JSON format.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        # TODO
        print "ERROR: Not implemented"
        print "robot_setCalibration " + jsonData
# End of Function: robot_setCalibration( jsonData )
