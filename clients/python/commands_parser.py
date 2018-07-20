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
File.......: commands_parser.py
Brief......: File including features to use to as to parse commands
Author.....: pylapp
Version....: 1.3.0
Since......: 11/01/2018
"""

# *******
# IMPORTS
# *******

from glue import *
from config import *
from robot_api import *


# *********
# FUNCTIONS
# *********

# Function: isRobotCommand( command )
def isRobotCommand( command ):
    """
        Checks if the command is suitable, i.e. if it matches one of the managed commands.
        Returns a boolean value, true if the command is good, false otherwise.
    """

    # get-angles
    result = bool(ROBOT_PATTERN_GET_ANGLES.match(command))
    if result:
        return True

    # set-angles a b c
    result = bool(ROBOT_PATTERN_SET_ANGLES.match(command))
    if result:
        return True

    # get-position
    result = bool(ROBOT_PATTERN_GET_POSITION.match(command))
    if result:
        return True

    # set-position x y z
    result = bool(ROBOT_PATTERN_SET_POSITION.match(command))
    if result:
        return True

    # tap x y
    result = bool(ROBOT_PATTERN_TAP.match(command))
    if result:
        return True

    # long tap x y
    result = bool(ROBOT_PATTERN_LONG_TAP.match(command))
    if result:
        return True

    # Double tap x y
    result = bool(ROBOT_PATTERN_DOUBLE_TAP.match(command))
    if result:
        return True

    # reset
    result = bool(ROBOT_PATTERN_RESET.match(command))
    if result:
        return True

    # get-calibration
    result = bool(ROBOT_PATTERN_GET_CALIBRATION.match(command))
    if result:
        return True

    # set-calibration JSON
    result = bool(ROBOT_PATTERN_SET_CALIBRATION.match(command))
    if result:
        return True

    # status
    result = bool(ROBOT_PATTERN_STATUS.match(command))
    if result:
        return True

    # dance
    result = bool(ROBOT_PATTERN_DANCE.match(command))
    if result:
        return True

    # stop dancing
    result = bool(ROBOT_PATTERN_STOP_DANCE.match(command))
    if result:
        return True

    # swipe
    result = bool(ROBOT_PATTERN_SWIPE.match(command))
    if result:
        return True

    # n-click
    result = bool(ROBOT_PATTERN_N_TAP.match(command))
    if result:
        return True

    # posForScreen
    result = bool(ROBOT_PATTERN_POSITION_FOR_SCREEN_COORD.match(command))
    if result:
        return True

    # angForPos
    result = bool(ROBOT_PATTERN_ANGLES_FOR_POSITION.match(command))
    if result:
        return True

    # robot's connection
    result = bool(ROBOT_PATTERN_CHECK.match(command))
    if result:
        return True

    # stress-swipe
    result = bool(ROBOT_PATTERN_STRESS_SWIPE.match(command))
    if result:
        return True

    # stress-tap
    result = bool(ROBOT_PATTERN_STRESS_TAP.match(command))
    if result:
        return True

    # contact-z
    result = bool(ROBOT_PATTERN_CONTACT_Z.match(command))
    if result:
        return True

    # n-swipe
    result = bool(ROBOT_PATTERN_N_SWIPE.match(command))
    if result:
        return True

    # draw square
    result = bool(ROBOT_PATTERN_DRAW_SQUARE.match(command))
    if result:
        return True

    # draw star
    result = bool(ROBOT_PATTERN_DRAW_STAR.match(command))
    if result:
        return True

    # draw triangle
    result = bool(ROBOT_PATTERN_DRAW_TRIANGLE.match(command))
    if result:
        return True

    # draw circle
    result = bool(ROBOT_PATTERN_DRAW_CIRCLE.match(command))
    if result:
        return True

    # draw cross
    result = bool(ROBOT_PATTERN_DRAW_CROSS.match(command))
    if result:
        return True

    # draw spiral
    result = bool(ROBOT_PATTERN_DRAW_SPIRAL.match(command))
    if result:
        return True

    return False

# End of Function: isRobotCommand( command )

# Function: parseCommand( command )
def parseCommand( command ):
    """
        Parses the command the user has written.
        Parameter: the command to parse.
        It will get the main keyword and then the arguments if needed.
        Once the real command has been found, the dedicated feature in the API will be triggered
        Returns: True if the command has been weel parsed, False if a problem occred
    """

    # get-angles
    result = bool(ROBOT_PATTERN_GET_ANGLES.match(command))
    if result:
        robot_getAngles()
        return True

    # set-angles a b c
    result = bool(ROBOT_PATTERN_SET_ANGLES.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_setAngles(theta1=splits[1], theta2=splits[2], theta3=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # get-position
    result = bool(ROBOT_PATTERN_GET_POSITION.match(command))
    if result:
        robot_getPosition()
        return True

    # set-position x y z
    result = bool(ROBOT_PATTERN_SET_POSITION.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_setPosition(x=splits[1], y=splits[2], z=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # tap x y
    result = bool(ROBOT_PATTERN_TAP.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 3:
            robot_tap(x=splits[1], y=splits[2])
            return True
        else:
            print "Bad parameters"
            return False

    # long tap x y
    result = bool(ROBOT_PATTERN_LONG_TAP.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_longTap(x=splits[1], y=splits[2], duration=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # double tap x y
    result = bool(ROBOT_PATTERN_DOUBLE_TAP.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_doubleTap(x=splits[1], y=splits[2], duration=splits[3])
            return True
        else:
            print "Bad parameters"
            return False            

    # reset
    result = bool(ROBOT_PATTERN_RESET.match(command))
    if result:
        robot_reset()
        return True

    # get-calibration
    result = bool(ROBOT_PATTERN_GET_CALIBRATION.match(command))
    if result:
        robot_getCalibration()
        return True

    # set-calibration JSON
    result = bool(ROBOT_PATTERN_SET_CALIBRATION.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 2:
            robot_setCalibration(jsonData=splits[1])
            return True
        else:
            print "Bad parameters"
            return False

    # status
    result = bool(ROBOT_PATTERN_STATUS.match(command))
    if result:
        robot_status()
        return True

    # dance
    result = bool(ROBOT_PATTERN_DANCE.match(command))
    if result:
        robot_dance()
        return True

    # stop dancing
    result = bool(ROBOT_PATTERN_STOP_DANCE.match(command))
    if result:
        robot_stopDance()
        return True

    # swipe
    result = bool(ROBOT_PATTERN_SWIPE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 5:
            robot_swipe(startX=splits[1], startY=splits[2], endX=splits[3], endY=splits[4])
            return True
        else:
            print "Bad parameters"
            return False

    # n-tap
    result = bool(ROBOT_PATTERN_N_TAP.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_ntap(n=splits[1], x=splits[2], y=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # posForScreen
    result = bool(ROBOT_PATTERN_POSITION_FOR_SCREEN_COORD.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 3:
            robot_posForScreen(x=splits[1], y=splits[2])
            return True
        else:
            print "Bad parameters"
            return False

    # angForPos
    result = bool(ROBOT_PATTERN_ANGLES_FOR_POSITION.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_angForPos(x=splits[1], y=splits[2], z=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # check robot's server connection
    result = bool(ROBOT_PATTERN_CHECK.match(command))
    if result:
        checkRobotConnection()
        return True

    # stress swipe
    result = bool(ROBOT_PATTERN_STRESS_SWIPE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 6:
            robot_stressSwipe(n=splits[1], startX=splits[2], startY=splits[3], endX=splits[4], endY=splits[5])
            return True
        else:
            print "Bad parameters"
            return False

    # stress tap
    result = bool(ROBOT_PATTERN_STRESS_TAP.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_stressTap(n=splits[1], x=splits[2], y=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # contact-z
    result = bool(ROBOT_PATTERN_CONTACT_Z.match(command))
    if result:
        robot_getContactZ()
        return True

    # n-swipe
    result = bool(ROBOT_PATTERN_N_SWIPE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 6:
            robot_nswipe(n=splits[1], startX=splits[2], startY=splits[3], endX=splits[4], endY=splits[5])
            return True
        else:
            print "Bad parameters"
            return False

    # draw square
    result = bool(ROBOT_PATTERN_DRAW_SQUARE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 3:
            robot_drawSquare(n=splits[1], length=splits[2])
            return True
        else:
            print "Bad parameters"
            return False

    # draw star
    result = bool(ROBOT_PATTERN_DRAW_STAR.match(command))
    if result:
        robot_drawStar()
        return True

    # draw triangle
    result = bool(ROBOT_PATTERN_DRAW_TRIANGLE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 7:
            robot_drawTriangle(x1=splits[1], y1=splits[2], x2=splits[3], y2=splits[4], x3=splits[5], y3=splits[6])
            return True
        else:
            print "Bad parameters"
            return False

    # draw circle
    result = bool(ROBOT_PATTERN_DRAW_CIRCLE.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 4:
            robot_drawCircle(x=splits[1], y=splits[2], r=splits[3])
            return True
        else:
            print "Bad parameters"
            return False

    # draw cross
    result = bool(ROBOT_PATTERN_DRAW_CROSS.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 9:
            robot_drawCross(x1=splits[1], y1=splits[2], x2=splits[3], y2=splits[4], x3=splits[5], y3=splits[6], x4=splits[7], y4=splits[8])
            return True
        else:
            print "Bad parameters"
            return False

    # draw circle
    result = bool(ROBOT_PATTERN_DRAW_SPIRAL.match(command))
    if result:
        splits = command.split( )
        if len(splits) == 5:
            robot_drawSpiral(x=splits[1], y=splits[2], n=splits[3], r=splits[4])
            return True
        else:
            print "Bad parameters"
            return False

# End of Function: parseCommand( command )
