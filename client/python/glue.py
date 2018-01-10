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
        print "\t get-angles................: Get the angles of the arms of the bot"
        print "\t set-angles a b c..........: Defines the 3 angles (a = theta 1, b = theta 2, c = theta3) for the arms of the bot"
        print "\t get-position..............: Get the posiition of the arms in its 3D landmark"
        print "\t set-position x y z........: Sets the position of the bot in its 3D landmark at (x,y,z) coordinates"
        print "\t tap x y...................: Tap on (x,y) using the 2D landmark of the device"
        print "\t reset.....................: Resets the position of the bot"
        print "\t get-calibration...........: Gets the calibration data in use for the bot"
        print "\t set-calibration JSON......: Defines the calibraiton data to use for the bot, defined in JSON format"
        print "\t config....................: Displays the global configuration in use"
        print "\t help......................: Displays this help"
        print "\t bye.......................: Good bye!"
        print ""
        return
# End of Function: welcome()

# Function: askForCommand()
def askForCommand():
    """
        Asks for a command to the user.
        Returns the written command.
    """
    return raw_input("Enter a command: ")
# End of function: askForCommand()

# Function: isSuitableCommand( command )
def isSuitableCommand( command ):
    """
        Checks if the command is suitable, i.e. if it matches one of the managed commands.
        Returns a boolean value, true if the command is good, false otherwise.
    """

    # get-angles
    result = bool(PATTERN_GET_ANGLES.match(command))
    if result:
        return True

    # set-angles a b c
    result = bool(PATTERN_SET_ANGLES.match(command))
    if result:
        return True

    # get-position
    result = bool(PATTERN_GET_POSITION.match(command))
    if result:
        return True

    # set-position x y z
    result = bool(PATTERN_SET_POSITION.match(command))
    if result:
        return True

    # tap x y
    result = bool(PATTERN_TAP.match(command))
    if result:
        return True

    # reset
    result = bool(PATTERN_RESET.match(command))
    if result:
        return True

    # get-calibration
    result = bool(PATTERN_GET_CALIBRATION.match(command))
    if result:
        return True

    # set-calibration JSON
    result = bool(PATTERN_SET_CALIBRATION.match(command))
    if result:
        return True

    # config
    result = bool(PATTERN_CONFIG.match(command))
    if result:
        return True

    # help
    result = bool(PATTERN_HELP.match(command))
    if result:
        return True

    return False

# End of function: isSuitableCommand( command )

# Function isStopCommand( command )
def isStopCommand( command ):
        """
            Checks if the command is a stop command.
            Returns a boolean value, true if the command is good, false otherwise.
        """
        return bool(PATTERN_BYE.match(command))
# End of function isStopCommand( command )

# Function: parseCommand( command )
def parseCommand( command ):
    """
        Parses the command the user has written.
        It will get the main keyword and then the arguments if needed.
        Once the real command has been found, the dedicated frazture in the API will be triggered
    """
    print "Ok I'll parse it!"
