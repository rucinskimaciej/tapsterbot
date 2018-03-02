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
Version....: 1.2.0
Since......: 10/01/2018
"""

# *******
# IMPORTS
# *******

import requests # You must install previously this module
import json
import time
from config import *

# *********
# FUNCTIONS
# *********

# Function: checkRobotConnection()
def checkRobotConnection():
    """
        Checks the robot server's connection, i.e. if the server is running.
        Returns the True if status code is 200, False otherwise
    """
    # FIXME Make a better management
    try:
        r = requests.head(ROBOT_URL+"/status")
        print "Connection made. Status code: " + str(r.status_code)
        return r.status_code == 200
    except requests.exceptions.ConnectionError:
        print "ERROR: Connection error"
        return False
# End of Function: checkRobotConnection()

# Function: robot_getAngles()
def robot_getAngles():
        """
            Sends to the robot's server an HTTP request so as to get the arms' angles values.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending get-angles request..."
        request = requests.get(ROBOT_URL + ROBOT_URL_GET_ANGLES )
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
        request = requests.post(ROBOT_URL + ROBOT_URL_SET_ANGLES, data=payload)
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
        request = requests.get(ROBOT_URL + ROBOT_URL_GET_POSITION )
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
        request = requests.post(ROBOT_URL + ROBOT_URL_SET_POSITION, data=payload)
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
        request = requests.post(ROBOT_URL + ROBOT_URL_TAP, data=payload)
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
        request = requests.post(ROBOT_URL + ROBOT_URL_RESET, data=payload)
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
        request = requests.get(ROBOT_URL + ROBOT_URL_GET_CALIBRATION )
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
        print "Sending set-calibration request..."
        payload = json.loads(jsonData)
        request = requests.post(ROBOT_URL + ROBOT_URL_SET_CALIBRATION, data=payload)
        print request.text
        return request.json()
# End of Function: robot_setCalibration( jsonData )

# Function: robot_status()
def robot_status():
        """
            Sends to the robot's server an HTTP request so as to get its status.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending get-status request..."
        request = requests.get(ROBOT_URL + ROBOT_URL_STATUS )
        print request.text
        return request.json()
# End of Function: robot_status()

# Function: robot_dance()
def robot_dance():
        """
            Sends to the robot's server an HTTP request so as to make it dance.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending dance request..."
        payload = {}
        request = requests.post(ROBOT_URL + ROBOT_URL_DANCE )
        print request.text
        return request.json()
# End of Function: robot_dance()

# Function: robot_stopDance()
def robot_stopDance():
        """
            Sends to the robot's server an HTTP request so as to make it stop dancing.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending stop-dance request..."
        payload = {}
        request = requests.post(ROBOT_URL + ROBOT_URL_STOP_DANCE )
        print request.text
        return request.json()
# End of Function: robot_stopDance()

# Function: robot_swipe( startX, startY, endX, endY )
def robot_swipe( startX, startY, endX, endY ):
        """
            Sends to the robot's server an HTTP request so as to swipe from (startX, startY) to (endX, endY).
            Parameters: startX, startY, endX, endY as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending swipe request..."
        payload = {'startX': startX, 'startY': startY, 'endX': endX, 'endY': endY}
        request = requests.post(ROBOT_URL + ROBOT_URL_SWIPE, data=payload)
        print request.text
        return request.json()
# End of Function: robot_swipe( x, y, z )

# Function: robot_ntap( n, x, y )
def robot_ntap( n, x, y ):
        """
            Sends to the robot's server n HTTP requests so as to tap n times on (x,y).
            Parameters: n, x, y as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the last executed command.
        """
        print "Sending "+ n + " tap requests..."
        payload = {'x': x, 'y': y}
        for i in range(0, int(n)):
            request = requests.post(ROBOT_URL + ROBOT_URL_TAP, data=payload)
            print request.text
            time.sleep(WAIT_TIME_BETWEEN_TAP)
        return request.json()
# End of Function: robot_ntap( n, x, y )

# Function: robot_posForScreen( x, y )
def robot_posForScreen( x, y ):
        """
            Sends to the robot's server an HTTP request so as to get the 3D coordinates according to the (x,y) 2D coordinates.
            Parameters: x, y as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending posForScreen request..."
        request = requests.get(ROBOT_URL + ROBOT_URL_POSITION_FOR_SCREEN_COORD+"/x/"+x+"/y/"+y)
        print request.text
        return request.json()
# End of Function: robot_posForScreen( x, y )

# Function: robot_angForPos( x, y, z )
def robot_angForPos( x, y, z ):
        """
            Sends to the robot's server an HTTP request so as to get the angles of the arms for the (x,y,z) 3D coordinates
            Parameters: x, y, z as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending angForPos request..."
        request = requests.get(ROBOT_URL + ROBOT_URL_ANGLES_FOR_POSITION+"/x/"+x+"/y/"+y+"/z/"+z)
        print request.text
        return request.json()
# End of Function: robot_angForPos( x, y, z )

# Function: robot_stressSwipe( n, startX, startY, endX, endY )
def robot_stressSwipe( n, startX, startY, endX, endY ):
        """
            Sends to the robot's server n HTTP requests for swipes from (startX, startY) to (endX, endY) with WAIT_TIME_STRESS_SWIPE duration between each swipe to as to stress the app.
            Parameters:  n, startX, startY, endX, endY as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending "+ n + " stress swipe requests..."
        payload = {'startX': startX, 'startY': startY, 'endX': endX, 'endY': endY}
        for i in range(0, int(n)):
            request = requests.post(ROBOT_URL + ROBOT_URL_SWIPE, data=payload)
            print request.text
            time.sleep(WAIT_TIME_STRESS_SWIPE)
        return request.json()
# End of Function: robot_stressSwipe( n, startX, startY, endX, endY )

# Function: robot_stressTap( n, x, y )
def robot_stressTap( n, x, y  ):
        """
            Sends to the robot's server n HTTP requests for taps to (x, y) with WAIT_TIME_STRESS_TAP duration between each tap to as to stress the app.
            Parameters:  n, x, y as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending "+ n + " stress tap requests..."
        payload = {'x': x, 'y': y}
        for i in range(0, int(n)):
            request = requests.post(ROBOT_URL + ROBOT_URL_TAP, data=payload)
            print request.text
            time.sleep(WAIT_TIME_STRESS_TAP)
        return request.json()
# End of Function: robot_stressTap( n, x, y )

# Function: robot_getContactZ()
def robot_getContactZ():
        """
            Sends to the robot's server an HTTP request so as to get its Z contact value.
            Displays a text and readable results of the command.
            Returns the results of the command.
        """
        print "Sending contact-z request..."
        request = requests.get(ROBOT_URL + ROBOT_URL_CONTACT_Z )
        print request.text
        return request.json()
# End of Function: robot_getContactZ()

# Function: robot_nswipe( n, startX, startY, endX, endY )
def robot_nswipe( n, startX, startY, endX, endY ):
        """
            Sends to the robot's server n HTTP requests so as to make n swipes from (startX, startY) to (endX, endY).
            Parameters:  n, startX, startY, endX, endY as integer numbers.
            Displays a text and readable results of the command.
            Returns the results of the last executed command.
        """
        print "Sending "+ n + " swipe requests..."
        payload = {'startX': startX, 'startY': startY, 'endX': endX, 'endY': endY}
        for i in range(0, int(n)):
            request = requests.post(ROBOT_URL + ROBOT_URL_SWIPE, data=payload)
            print request.text
            time.sleep(WAIT_TIME_BETWEEN_SWIPE)
        return request.json()
# End of Function: robot_nswipe( n, startX, startY, endX, endY )