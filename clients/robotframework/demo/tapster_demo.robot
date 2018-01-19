# MIT License
# Copyright (c) 2016-2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
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
# File.......: tapster_demo.robot
# Brief......: File contaning some tests cases and demos of usages
# Author.....: pylapp
# Version....: 1.0.0
# Since......: 17/01/2018


*** Settings ***

Documentation
...    Here are demos and tests cases to run to as to try and test Robot Framework's Tapster keywords.work guidelines and best practices

#Library    AppiumLibrary

Resource    ../tapster_keywords.robot

Suite Setup    Hello

Suite Teardown    Goodbye

*** Test Cases ***

Hey
    [Documentation]    Quite useless except if you want ot be sure your test cases are playable
    Log To Console    Hey!

Session
    [Documentation]    Creates a session with the robot
    ${result} =    Create robot session    foo
    Log To Console    ${result}
    Set angles of arms    foo    10    20    16
    ${result} =    Delete robot session    foo
    Log To Console    ${result}
    ${result} =    Create robot session    bar    http://127.0.0.1:4242
    Reset    bar
    ${result} =    Delete robot session    bar
    Log To Console    ${result}

Test the connection of the robot
    [Documentation]    Tests the connection with the dedicated keyword
    Create robot session    mySession
    ${status_code} =    Check connection of robot    mySession
    Log To Console    ${status_code}
    Delete robot session    mySession

Get angles of arms
    [Documentation]    Returns the angles of the arms of the robot
    Create robot session    mySession
    ${angles} =    Get angles of arms    mySession
    Log To Console    ${angles}
    Delete robot session    mySession

Set angles of arms
    [Documentation]    Defines angles for the arms to (10, 20, 16) and (33, 10, 6)
    Create robot session    mySession
    ${response} =    Set angles of arms    mySession    10    20    16
    Log To Console    ${response}
    ${response} =    Set angles of arms    mySession    33    10    6
    Log To Console    ${response}
    ${response} =    Set angles of arms    mySession    0    45    5
    Log To Console    ${response}
    Delete robot session    mySession

Get position in 3D
    [Documentation]    Returns the 3D position of the robot
    Create robot session    mySession
    ${position} =    Get 3D position    mySession
    Log To Console    ${position}
    Delete robot session    mySession

Set position in 3D
    [Documentation]    Defines 3D position of the robot to (10, 20, 16) and (0, -50, -145)
    Create robot session    mySession
    ${response} =    Set 3D position    mySession    10    20    16
    Log To Console    ${response}
    ${response} =    Set 3D position    mySession    0    50    -145
    Log To Console    ${response}
    Delete robot session    mySession

Reset robot
    [Documentation]    Reset the robot, make it moves to a point, and then reset again.
    Create robot session    mySession
    ${response} =    Reset    mySession
    Log To Console    ${response}
    ${response} =    Set 3D position    mySession    10    5    33
    Log To Console    ${response}
    ${response} =    Reset    mySession
    Log To Console    ${response}
    Delete robot session    mySession

Tap to point
    [Documentation]    Tap to points
    Create robot session    mySession
    ${response} =    Reset    mySession
    Log To Console    ${response}
    ${response} =    Tap to point    mySession    100    100
    Log To Console    ${response}
    ${response} =    Tap to point    mySession    300    450
    Log To Console    ${response}
    Delete robot session    mySession

Get calibration
    [Documentation]    Returns the calibration data in use
    Create robot session    mySession
    ${calibration} =    Get calibration data    mySession
    Log To Console    ${calibration}
    Delete robot session    mySession

Get status
    [Documentation]    Returns the status of the robot's server
    Create robot session    mySession
    ${status} =    Get status    mySession
    Log To Console    ${status}
    Delete robot session    mySession

Dance
   [Documentation]    Let's dance baby!
    Create robot session    mySession
    ${status} =    Dance    mySession
    Log To Console    ${status}
    Wait    30s
    ${status} =    Stop dance    mySession
    Log To Console    ${status}
    Delete robot session    mySession

Swipes
    [Documentation]    Makes two swipes
    Create robot session    mySession
    Reset    mySession
    ${response} =    Swipe    mySession    50    50    450    450
    Log To Console    ${response}
    ${response} =    Swipe    mySession    50    450    450    50
    Log To Console    ${response}
    Delete robot session    mySession

N taps
    [Documentation]    Taps n times on a point
    Create robot session    mySession
    Reset    mySession
    ${response} =    Tap n times    mySession    20    100    100
    Log To Console    ${response}
    Delete robot session    mySession

Pos for screen
    [Documentation]    Gets the position for a dedicated 2D point
    Create robot session    mySession
    Reset    mySession
    ${response} =    Position for screen    mySession    100    100
    Log To Console    ${response}
    Delete robot session    mySession

Ang for pos
    [Documentation]    Gets the angles of the arms for a dedicated 3D point
    Create robot session    mySession
    Reset    mySession
    ${response} =    Angles for position    mySession    100    100    100
    Log To Console    ${response}
    Delete robot session    mySession

Z contact
    [Documentation]    Gets the Z-axis value of the contact point the robot has
    Create robot session    mySession
    Reset    mySession
    ${response} =    Get contact Z   mySession
    Log To Console    ${response}
    Delete robot session    mySession

Stress taps
        [Documentation]    Make many taps to stress the app under the robot
        Create robot session    mySession
        Reset    mySession
        ${response} =    Stress taps    mySession    20    100    100
        Log To Console    ${response}
        Delete robot session    mySession

Stress swipes
        [Documentation]    Make many swipes to stress the app under the robot
        Create robot session    mySession
        Reset    mySession
        ${response} =    Stress swipes    mySession    20    100    100    500    500
        Log To Console    ${response}
        Delete robot session    mySession

N swipes
        [Documentation]    Make n swipes
        Create robot session    mySession
        Reset    mySession
        ${response} =    Swipe n times    mySession    20    100    100    500    500
        Log To Console    ${response}
        Delete robot session    mySession

*** Keywords ***

Hello
    Log To Console    "Hello, Tapster!"

Goodbye
    Log To Console    "Bye buddy!"
