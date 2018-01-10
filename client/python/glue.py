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
File.......: glue.py
Brief......: File including all the Python glue to use for the client, except the API of the bot
Author.....: pylapp
Version....: 1.0.0
Since......: 10/01/2018
"""

# *******
# IMPORTS
# *******

# For regular expressions and patterns
from config import *

# Robot API
from robot_api import *

# *********
# FUNCTIONS
# *********

# Function: welcome()
def welcome():
        """
            Displays the welcome message.
        """
        print "*************************************"
        print "* Welcome to Tapster2 Python client *"
        print "*************************************"
        return
# End of Function: welcome()

# Function: help()
def help():
        """
            Displays the help, i.e. the commands the client can deal with.
        """
        print ""
        print "Here are the commands you can use for the Tapster2 bot:"
        print "\tget-angles................: Get the angles of the arms of the bot"
        print "\tset-angles a b c..........: Define the 3 angles (a = theta 1, b = theta 2, c = theta3) for the arms of the bot"
        print "\tget-position..............: Get the posiition of the arms in its 3D landmark"
        print "\tset-position x y z........: Sets the position of the bot in its 3D landmark at (x,y,z) coordinates"
        print "\ttap x y...................: Tap on (x,y) using the 2D landmark of the device"
        print "\tswipe x1 y1 x2 y2.........: Swipe from (x1, y1) to (x2, y2) using the 2D landmark of the device"
        print "\treset.....................: Resets the position of the bot"
        print "\tget-calibration...........: Gets the calibration data in use for the bot"
        print "\tset-calibration JSON......: Defines the calibraiton data to use for the bot, defined in JSON format"
        print "\tconfig....................: Displays the global configuration in use"
        print "\thelp......................: Displays this help"
        print "\tstatus....................: What is the status of the bot?"
        print "\tdance.....................: Let's dance!"
        print "\tstop-dance................: Stop dancing"
        print "\tbye.......................: Good bye!"
        print ""
        return
# End of Function: welcome()

# Function: config()
def config():
    """
        Displays the configuration in use.
    """
    print ""
    print "\tVersion of Python client.......: " + CLIENT_VERSION
    print "\tIP address of the robot........: " + ROBOT_IP_ADDRESS
    print "\tPort fo the robot..............: " + ROBOT_PORT
    print "\tProtocol of the robot..........: " + ROBOT_PROTOCOL
    print "\tURl of the robot...............: " + ROBOT_URL
    print ""
# End of Function: config()

# Function: askForCommand()
def askForCommand():
    """
        Asks for a command to the user.
        Returns the written command.
    """
    return raw_input("> Enter a command: ")
# End of Function: askForCommand()

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

    return False

# End of Function: isRobotCommand( command )

# Function isStopCommand( command )
def isStopCommand( command ):
        """
            Checks if the command is a stop command.
            Returns a boolean value, true if the command is good, false otherwise.
        """
        return bool(ROBOT_PATTERN_BYE.match(command))
# End of Function isStopCommand( command )

# Function isHelpCommand( command )
def isHelpCommand( command ):
        """
            Checks if the command is an help command.
            Returns a boolean value, true if the command is good, false otherwise.
        """
        return bool(ROBOT_PATTERN_HELP.match(command))
# End of Function isHelpCommand( command )

# Function isConfigCommand( command )
def isConfigCommand( command ):
        """
            Checks if the command is a config command.
            Returns a boolean value, true if the command is good, false otherwise.
        """
        return bool(ROBOT_PATTERN_CONFIG.match(command))
# End of Function isConfigCommand( command )

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

# End of Function: parseCommand( command )
