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
# File.......: tapster_keywords.robot
# Brief......: File contaning Robot Framework keywords to use to as to deal with the Tapster2 bot
# Author.....: pylapp
# Version....: 2.0.0
# Since......: 17/01/2018


*** Settings ***

Documentation
...    Here are keywords you can use in your Robot Framework tests cases.
...    If you are in a TDD/BDD/ATDD method, you may need to test your mobile applications with a Tapster 2 robot.
...    You can find here kewyrods to integrate so as to deal with the robot's API.
...    The things you can do with another client can be done with these keywords.
...    Be aware that keywords send HTTP requests to robot's server, so cascaded operations might be a mess, feel free to add wait operation between them.

Library    RequestsLibrary
Library    json
Library    Collections

Resource    config.robot


*** Keywords ***

# Some glue

Create robot session
    [Documentation]    Creates a network session (using RequestsLirbary) with the robot with a dedicated alias and matbe a robot's server URL. You must create a session before using the robot. Returns the created session.
    [Arguments]    ${session_alias}    ${roboturl}=${DEFAULT_ROBOT_URL}
    ${session} =    Create Session    ${session_alias}    ${roboturl}
    [Return]    ${session}

Delete robot session
    [Documentation]    Deletes an existing session which have thealais defined in parameter.
    [Arguments]    ${session_alias}    ${roboturl}=${DEFAULT_ROBOT_URL}
    ${results} =    Delete Request    ${session_alias}    ${roboturl}
    [Return]    ${results}

Wait
    [Documentation]    Waits during a time so as to let the robot's server process its last received request. Indeed this server cannot temporise/cache request itself yet, here is a trick to temporise in the client side.
    [Arguments]    ${duration}=${WAIT_BETWEEN_CASCADED_OPERATION}
    Sleep    ${duration}

# Robot's API

Check connection of robot
    [Documentation]    Checks if the connection of the robot with the given session has been established. Returns the status code sent by the robot's server.
    [Arguments]    ${session}
    ${response} =    Head Request    ${session}    ${ROBOT_URL_STATUS}
    Should Be Equal As Strings    ${response.status_code}    200
    Wait
    [Return]    ${response.status_code}

Get angles of arms
    [Documentation]    Returns the angles the arms of the robot have using the given session.
    [Arguments]    ${session}
    ${response} =    Get Request    ${session}    ${ROBOT_URL_GET_ANGLES}
    Wait
    [Return]    ${response.text}

Set angles of arms
    [Documentation]    Defines the (theta1, theta2, theta3) angles of the arms the robot should have with the given session. Returns the response of the robot's server.
    [Arguments]    ${session}    ${theta1}    ${theta2}    ${theta3}
    &{angles_raw} =    Create Dictionary    theta1=${theta1}    theta2=${theta2}    theta3=${theta3}
    ${angles} =    json.dumps    ${angles_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_SET_ANGLES}    data=${angles}
    Wait
    [Return]    ${response.text}

Get 3D position
    [Documentation]    Returns the position in 3D landmark of the robot with the given session
    [Arguments]    ${session}
    ${response} =    Get Request    ${session}    ${ROBOT_URL_GET_POSITION}
    Wait
    [Return]    ${response.text}

Set 3D position
    [Documentation]    Defines the (x, y, z) position the robot must have with the given session. Returns the response of the robot's server.
    [Arguments]    ${session}    ${x}    ${y}    ${z}
    &{position_raw} =    Create Dictionary    x=${x}    y=${y}    z=${z}
    ${position} =    json.dumps    ${position_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_SET_POSITION}    data=${position}
    Wait
    [Return]    ${response.text}

Tap to point
    [Documentation]    Makes the robot with the given session to tap to the point at (x, y)
    [Arguments]    ${session}    ${x}    ${y}
    &{tap_raw} =    Create Dictionary    x=${x}    y=${y}
    ${tap} =    json.dumps    ${tap_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_TAP}    data=${tap}
    Wait
    [Return]    ${response.text}

Long tap to point
    [Documentation]    Makes the robot with the given session tap to the point at (x, y) during duration in ms
    [Arguments]    ${session}    ${x}    ${y}    ${duration}=${DEFAULT_DURATION_LONG_TAP}
    &{long_tap_raw} =    Create Dictionary    x=${x}    y=${y}    duration=${duration}
    ${long_tap} =    json.dumps    ${long_tap_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_LONG_TAP}    data=${long_tap}
    ${duration_in_seconds} =    Evaluate    ${duration}/1000
    Wait    ${duration_in_seconds}
    [Return]    ${response.text}

Double tap to point
    [Documentation]    Makes the robot with the given session tap twice to the point at (x, y) during duration in ms
    [Arguments]    ${session}    ${x}    ${y}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}
    &{double_tap_raw} =    Create Dictionary    x=${x}    y=${y}    duration=${duration}
    ${double_tap} =    json.dumps    ${double_tap_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_DOUBLE_TAP}    data=${double_tap}
    ${duration_in_seconds} =    Evaluate    ${duration}/1000
    Wait
    [Return]    ${response.text}

Reset
    [Documentation]    Resets the position / arms of the robot to their default values with the given session.  Returns the response of the robot's server.
    [Arguments]    ${session}
    &{empty_payload_raw} =    Create Dictionary
    ${empty_payload} =    json.dumps    ${empty_payload_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_RESET}    data=${empty_payload}
    Wait
    [Return]    ${response.text}

Get calibration data
    [Documentation]    Returns the calibration data of the robot with the given session
    [Arguments]    ${session}
    ${response} =    Get Request    ${session}    ${ROBOT_URL_GET_CALIBRATION}
    Wait
    [Return]    ${response.text}

Set calibration data
    [Documentation]    Defines the calibation data in JSON format to apply to the robot with the given session. Returns the response of the robot's server.
    [Arguments]    ${session}    ${calibration_data}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_SET_CALIBRATION}    data=${calibration_data}
    Wait
    [Return]    ${response.text}

Get status
    [Documentation]    Returns the status of the robot's server with the given session
    [Arguments]    ${session}
    ${response} =    Get Request    ${session}    ${ROBOT_URL_STATUS}
    Wait
    [Return]    ${response.text}

Dance
    [Documentation]    Make the robot dance with the given session!.  Returns the response of the robot's server.
    [Arguments]    ${session}
    &{empty_payload_raw} =    Create Dictionary
    ${empty_payload} =    json.dumps    ${empty_payload_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_DANCE}    data=${empty_payload}
    Wait
    [Return]    ${response.text}

Stop dance
    [Documentation]    Make the robot stop dancing with the given session :-(  Returns the response of the robot's server.
    [Arguments]    ${session}
    &{empty_payload_raw} =    Create Dictionary
    ${empty_payload} =    json.dumps    ${empty_payload_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_STOP_DANCE}    data=${empty_payload}
    Wait
    [Return]    ${response.text}

Swipe
    [Documentation]    Makes the robot with the given session swipe from (a,b) to (c,d)
    [Arguments]    ${session}    ${a}    ${b}    ${c}    ${d}
    &{swipe_raw} =    Create Dictionary    startX=${a}    startY=${b}    endX=${c}    endY=${d}
    ${swipe} =    json.dumps    ${swipe_raw}
    ${response} =    Post Request    ${session}    ${ROBOT_URL_SWIPE}    data=${swipe}
    Wait
    [Return]    ${response.text}

Tap n times
    [Documentation]    Makes the robot with the given session tap n times on (x,y). Returns the last received response.
    [Arguments]    ${session}    ${n}    ${x}    ${y}
    &{tap_raw} =    Create Dictionary    x=${x}    y=${y}
    ${tap} =    json.dumps    ${tap_raw}
    : FOR    ${INDEX}    IN RANGE    0    ${n}
    \        ${response} =    Post Request    ${session}    ${ROBOT_URL_TAP}    data=${tap}
    \        Wait    ${WAIT_TIME_BETWEEN_TAP}
    [Return]    ${response.text}

Swipe n times
    [Documentation]    Makes the robot with the given session swipes n times from (a,b) to (c,d). Returns the last received response.
    [Arguments]    ${session}    ${n}    ${a}    ${b}    ${c}    ${d}
    &{swipe_raw} =    Create Dictionary    startX=${a}    startY=${b}    endX=${c}    endY=${d}
    ${swipe} =    json.dumps    ${swipe_raw}
    : FOR    ${INDEX}    IN RANGE    0    ${n}
    \        ${response} =    Post Request    ${session}    ${ROBOT_URL_SWIPE}    data=${swipe}
    \        Wait    ${WAIT_TIME_BETWEEN_SWIPE}
    [Return]    ${response.text}

Position for screen
    [Documentation]    Gets the position of the robot (using the given session) according to an (x,y) 2D landmark point
    [Arguments]    ${session}    ${x}    ${y}
    ${url} =    Catenate    SEPARATOR=  ${ROBOT_URL_POSITION_FOR_SCREEN_COORD}    /x/    ${x}    /y/    ${y}
    ${response} =    Get Request    ${session}    ${url}
    Wait
    [Return]    ${response.text}

Angles for position
    [Documentation]    Gets the angles of the arms for the (x, y, z) 3D point
    [Arguments]    ${session}    ${x}    ${y}    ${z}
    ${url} =    Catenate    SEPARATOR=  ${ROBOT_URL_ANGLES_FOR_POSITION}    /x/    ${x}    /y/    ${y}    /z/    ${z}
    ${response} =    Get Request    ${session}    ${url}
    Wait
    [Return]    ${response.text}

Get contact Z
    [Documentation]    Gets the Z-axis value of the contact point the robot has (using the given session)
    [Arguments]    ${session}
    ${response} =    Get Request    ${session}    ${ROBOT_URL_CONTACT_Z}
    Wait
    [Return]    ${response.text}

Stress taps
    [Documentation]    Stresses the app under the robot with n quick tap on (x,y) using a session
    [Arguments]    ${session}    ${n}    ${x}    ${y}
    &{tap_raw} =    Create Dictionary    x=${x}    y=${y}
    ${tap} =    json.dumps    ${tap_raw}
    : FOR    ${INDEX}    IN RANGE    0    ${n}
    \        ${response} =    Post Request    ${session}    ${ROBOT_URL_TAP}    data=${tap}
    \        Wait    ${WAIT_TIME_STRESS_TAP}
    [Return]    ${response.text}

Stress swipes
    [Documentation]    Stresses the app under the robot with n quick swipes from (a,b) to (c,d)v using a session
    [Arguments]    ${session}    ${n}    ${a}    ${b}    ${c}    ${d}
    &{swipe_raw} =    Create Dictionary    startX=${a}    startY=${b}    endX=${c}    endY=${d}
    ${swipe} =    json.dumps    ${swipe_raw}
    : FOR    ${INDEX}    IN RANGE    0    ${n}
    \        ${response} =    Post Request    ${session}    ${ROBOT_URL_SWIPE}    data=${swipe}
    \        Wait    ${WAIT_TIME_STRESS_SWIPE}
    [Return]    ${response.text}
