#!/usr/bin/env python3
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
File.......: client.py
Brief......: The Python client to use soa s to drive the Tapster2 bot
Author.....: pylapp
Version....: 1.0.0
Since......: 10/01/2018
"""

import argparse
import sys

from glue import *
from config import *
from commands_parser import *

if __name__ == "__main__":

    # Check args if this execution is a "light" execution, i.e. without verbatims
    parser = argparse.ArgumentParser(description='Python client for Tapster2 robot.')
    parser.add_argument('--light', dest='command', help='Run in light mode, i.e. without verbatims nor prompts. Insert commands after --light', required=False)
    parser.add_argument('--version', action='version', help='Displays the version of this program.', version='%(prog)s '+CLIENT_VERSION)
    args = parser.parse_args()

# In this case we are in light mode

    if args.command:
        if isRobotCommand(args.command):
            parseCommand(args.command)
        elif isHelpCommand(args.command):
            help()
        elif isConfigCommand(args.command):
            config()
        else:
            print "ERROR: Bad command."
        sys.exit()

# In that case we are in verbose / interactive mode

    # Welcome guys!
    print "Version " + CLIENT_VERSION
    welcome()

    # Some help?
    help()

    # Deal with commands
    stop = False
    lastCommand = None
    while not stop:
        command = askForCommand()
        if isRepeatCommand(command):
            command = lastCommand
        if isRobotCommand(command):
            parseCommand( command )
        elif isHelpCommand(command):
            help()
        elif isConfigCommand(command):
            config()
        elif isStopCommand(command):
            stop = True
        else:
            print "Nope. Bad command."
        lastCommand = command
    # Bye!
    print "Ok, bye!"
