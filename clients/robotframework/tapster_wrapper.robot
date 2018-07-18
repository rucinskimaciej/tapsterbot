# Copyright © 2018 Orange
#
# Redistribution and use in source and binary forms, with or without modification,
# are permitted provided that the following conditions are met:
#
# 1. Redistributions of source code must retain the above copyright notice,
# this list of conditions and the following disclaimer.
# 2. Redistributions in binary form must reproduce the above copyright notice,
# this list of conditions and the following disclaimer in the documentation and/or
# other materials provided with the distribution.
#
# THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
# ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
# OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
# IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
# INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
# BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
# DATA, OR PROFITS; #OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
# THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
# NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
# EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
#
# [This is a 2-Clause BSD License. You can find it at https://opensource.org/licenses/BSD-2-Clause]

# File: tapster_wrapper.robot
# Brief: Robot Framework file providing feature to use so as to click on items with Tapster bot
# Author: Pierre-Yves Lapersonne
# Version: 1.1.0
# Since: 29/05/2018

*** Settings ***

Documentation
...    Here are keywords so use to as to get widgets (through Appium) and click on them (with Tapster bot)

Library     AppiumLibrary

Resource    ./tapster_keywords.robot

*** Keywords ***

# ###############
# Useful keywords
# ###############

Long Tap To Element With Id
    [Documentation]    Make a long tap to an element, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    # The duration is by default ${DEFAULT_DURATION_LONG_TAP} (in ms)
    [Arguments]    ${id}    ${duration}=${DEFAULT_DURATION_LONG_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Long tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Long Tap To Element With Text
    [Documentation]    Make a long tap to an element, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this text, will keep the 1st.
    # The duration is by default ${DEFAULT_DURATION_LONG_TAP} (in ms)
    [Arguments]    ${text}    ${duration}=${DEFAULT_DURATION_LONG_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Long tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Long Tap To Element With Xpath
    [Documentation]    Make a long tap to an element, in portrait mode, using this XPath locator.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_LONG_TAP} (in ms)
    [Arguments]    ${xpath_locator}    ${duration}=${DEFAULT_DURATION_LONG_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Xpath    ${xpath_locator}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Long tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Double Tap To Element With Id
    [Documentation]    Make a double tap to an element, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${id}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Double tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Double Tap To Element With Text
    [Documentation]    Make a double tap to an element, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${text}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Double tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Double Tap To Element With Xpath
    [Documentation]    Make a double tap to an element, in portrait mode, using this XPath locator.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${xpath_locator}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Xpath    ${xpath_locator}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Double tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Triple Tap To Element With Id
    [Documentation]    Make a triple tap to an element, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${id}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Triple tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Triple Tap To Element With Text
    [Documentation]    Make a triple tap to an element, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${text}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Triple tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Triple Tap To Element With Xpath
    [Documentation]    Make a triple tap to an element, in portrait mode, using this XPath locator.
    # The contact point will be computed according to location and dimension of the widget.
    # The duration is by default ${DEFAULT_DURATION_MULTI_TAP} (in ms)
    [Arguments]    ${xpath_locator}    ${duration}=${DEFAULT_DURATION_MULTI_TAP}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Xpath    ${xpath_locator}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Triple tap to point    my_session    ${x}    ${y}    ${duration}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Element With Id
    [Documentation]    Tap to an element, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${id}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Element With Text
    [Documentation]    Tap to an element, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this text, will keep the 1st.
    [Arguments]    ${text}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Reset
    [Documentation]    Resets the position of the robot's finger
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Reset    my_session
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Swipe
    [Documentation]    Swipe one time from (a,b) to (c,d) points
    [Arguments]    ${a}    ${b}    ${c}    ${d}    ${offset_x}=0    ${offset_y}=0
    ${new_a} =    Evaluate    ${a}+${offset_x}
    ${new_b} =    Evaluate    ${b}+${offset_y}
    ${new_c} =    Evaluate    ${c}+${offset_x}
    ${new_d} =    Evaluate    ${d}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Swipe    my_session    ${new_a}    ${new_b}    ${new_c}    ${new_d}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Element N Times With Id
    [Documentation]    Tap to an element, N times, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${N}    ${id}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap n times    my_session    ${N}    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Element N Times With Text
    [Documentation]    Tap to an element, N times, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${N}    ${text}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap n times    my_session    ${N}    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Stresstap To Element With Id
    [Documentation]    Tap to an element, N times, very quickly, in portrait mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${N}    ${id}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Stress taps    my_session    ${N}    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Stresstap To Element With Text
    [Documentation]    Tap to an element, N times, very quickly, in portrait mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${N}    ${text}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Stress taps    my_session    ${N}    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Swipe N Times
    [Documentation]    Swipe N times from (a,b) to (c,d) points
    [Arguments]    ${N}    ${a}    ${b}    ${c}    ${d}    ${offset_x}=0    ${offset_y}=0
    ${new_a} =    Evaluate    ${a}+${offset_x}
    ${new_b} =    Evaluate    ${b}+${offset_y}
    ${new_c} =    Evaluate    ${c}+${offset_x}
    ${new_d} =    Evaluate    ${d}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Swipe n times    my_session    ${N}    ${new_a}    ${new_b}    ${new_c}    ${new_d}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Swipe using elements id
    [Documentation]    Swipe from an element to another using their id
    [Arguments]    ${source_id}    ${destination_id}    ${offset_x}=0    ${offset_y}=0
    ${source_x}    ${source_y} =    Get Suitable Contact Point For Widget With Id    ${source_id}
    ${source_x} =    Evaluate    ${source_x}+${offset_x}
    ${source_y} =    Evaluate    ${source_y}+${offset_y}
    ${destination_x}    ${destination_y} =    Get Suitable Contact Point For Widget With Id    ${destination_id}
    ${destination_x} =    Evaluate    ${destination_x}+${offset_x}
    ${destination_y} =    Evaluate    ${destination_y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Swipe    my_session    ${source_x}    ${source_y}    ${destination_x}    ${destination_y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Swipe using elements text
    [Documentation]    Swipe from an element to another using their texts
    [Arguments]    ${source_text}    ${destination_text}    ${offset_x}=0    ${offset_y}=0
    ${source_x}    ${source_y} =    Get Suitable Contact Point For Widget With Text    ${source_text}
    ${source_x} =    Evaluate    ${source_x}+${offset_x}
    ${source_y} =    Evaluate    ${source_y}+${offset_y}
    ${destination_x}    ${destination_y} =    Get Suitable Contact Point For Widget With Text    ${destination_text}
    ${destination_x} =    Evaluate    ${destination_x}+${offset_x}
    ${destination_y} =    Evaluate    ${destination_y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Swipe    my_session    ${source_x}    ${source_y}    ${destination_x}    ${destination_y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Swipe using elements xpath
    [Documentation]    Swipe from an element to another using XPath locators
    [Arguments]    ${source_xpath}    ${destination_xpath}    ${offset_x}=0    ${offset_y}=0
    ${source_x}    ${source_y} =    Get Suitable Contact Point For Widget With Xpath    ${source_xpath}
    ${source_x} =    Evaluate    ${source_x}+${offset_x}
    ${source_y} =    Evaluate    ${source_y}+${offset_y}
    ${destination_x}    ${destination_y} =    Get Suitable Contact Point For Widget With Xpath    ${destination_xpath}
    ${destination_x} =    Evaluate    ${destination_x}+${offset_x}
    ${destination_y} =    Evaluate    ${destination_y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Swipe    my_session    ${source_x}    ${source_y}    ${destination_x}    ${destination_y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Stress swipes
    [Documentation]    Swipe N times from (a,b) to (c,d) points very quickly
    [Arguments]    ${N}    ${a}    ${b}    ${c}    ${d}    ${offset_x}=0    ${offset_y}=0
    ${new_a} =    Evaluate    ${a}+${offset_x}
    ${new_b} =    Evaluate    ${b}+${offset_y}
    ${new_c} =    Evaluate    ${c}+${offset_x}
    ${new_d} =    Evaluate    ${d}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Stress swipes    my_session    ${N}    ${new_a}    ${new_b}    ${new_c}    ${new_d}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap Somewhere To Element With Id
    [Documentation]    Tap somewhere in a widget which has this id. If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${id}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Random Contact Point For Widget With Id    ${id}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap Somewhere To Element With Text
    [Documentation]    Tap somewhere in a widget which has this text. If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${text}    ${offset_x}=0    ${offset_y}=0
    ${x}    ${y} =    Get Random Contact Point For Widget With Text    ${text}
    ${x} =    Evaluate    ${x}+${offset_x}
    ${y} =    Evaluate    ${y}+${offset_y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${x}    ${y}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Landscape Element With Text
    [Documentation]    Unstable! Keyword "Get Window Width" missing in AppiumLibrary
    #  Tap to an element, in landscape mode, which has this text.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this text, will keep the 1st.
    [Arguments]    ${text}    ${offset_x}=0    ${offset_y}=0
    # Coordinates in landscape mode!
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    # Robot does not care of orientation, need to convert to fake portrait mode
    ${screen_width} =    Get Window width # Does not work O_o, unknown keyword in AppiumLibrary!
    ${y} =    Evaluate    ${x}
    ${x} =    Evaluate    ${screen_width}-${y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${y}    ${x}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Tap To Landscape Element With Id
    [Documentation]    Unstable! Keyword "Get Window Width" missing in AppiumLibrary
    # Tap to an element, in landscape mode, which has this id.
    # The contact point will be computed according to location and dimension of the widget.
    # If there are several widgets with this id, will keep the 1st.
    [Arguments]    ${id}    ${offset_x}=0    ${offset_y}=0
    # Coordinates in landscape mode!
    ${x}    ${y} =    Get Suitable Contact Point For Widget With Id    ${id}
    # Robot does not care of orientation, need to convert to fake portrait mode
    ${screen_width} =    Get Window width # Does not work O_o, unknown keyword in AppiumLibrary!
    ${y} =    Evaluate    ${x}
    ${x} =    Evaluate    ${screen_width}-${y}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Tap to point    my_session    ${y}    ${x}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}

Draw Random Pattern
    [Documentation]    Draws a random pattern on the screen, suing strokes
    # Will choose in the defined area n points wich wil be used to draw the lines.
    [Arguments]    ${n}    ${startX}    ${startY}    ${endX}    ${endY}
    tapster_keywords.Create robot session    my_session
    ${response} =    tapster_keywords.Draw random pattern    my_session    ${n}    ${startX}    ${startY}    ${endX}    ${endY}
    tapster_keywords.Delete robot session    my_session
    [Return]    ${response}


# #########
# Some glue
# #########

Get Suitable Contact Point For Widget With Text
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${text}
    ${location} =    Get Element Location     xpath=//*[@text=${text}]
    ${size} =    Get Element Size     xpath=//*[@text=${text}]
    ${contact_x} =    Evaluate    ${location['x']}+${size['width']}/2
    ${contact_y} =    Evaluate    ${location['y']}+${size['height']}/2
    [Return]    ${contact_x}    ${contact_y}

Get Suitable Contact Point For Widget With Id
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${id}
    ${location} =    Get Element Location     xpath=//*[@resource-id=${id}]
    ${size} =    Get Element Size     xpath=//*[@resource-id=${id}]
    ${contact_x} =    Evaluate    ${location['x']}+${size['width']}/2
    ${contact_y} =    Evaluate    ${location['y']}+${size['height']}/2
    [Return]    ${contact_x}    ${contact_y}

Get Suitable Contact Point For Widget With Xpath
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${xpath_locator}
    ${location} =    Get Element Location     xpath=${xpath_locator}
    ${size} =    Get Element Size     xpath=${xpath_locator}
    ${contact_x} =    Evaluate    ${location['x']}+${size['width']}/2
    ${contact_y} =    Evaluate    ${location['y']}+${size['height']}/2
    [Return]    ${contact_x}    ${contact_y}

Get Random Contact Point For Widget With Id
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${id}
    ${location} =    Get Element Location     xpath=//*[@resource-id=${id}]
    ${size} =    Get Element Size     xpath=//*[@resource-id=${id}]
    ${min_x} =    Convert To Integer    ${location['x']}
    ${max_x} =    Evaluate    ${location['x']}+${size['width']}
    ${max_x} =    Convert To Integer    ${max_x}
    ${min_y} =    Convert To Integer    ${location['y']}
    ${max_y} =    Evaluate    ${location['y']}+${size['height']}
    ${max_y} =    Convert To Integer    ${max_y}
    ${contact_x} =    Evaluate   random.sample(range(${min_x},${max_x}), 1)    random
    ${contact_y} =    Evaluate   random.sample(range(${min_y},${max_y}), 1)    random
    [Return]    ${contact_x}[0]    ${contact_y}[0]

Get Random Contact Point For Widget With Text
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${text}
    ${location} =    Get Element Location     xpath=//*[@text=${text}]
    ${size} =    Get Element Size     xpath=//*[@text=${text}]
    ${min_x} =    Convert To Integer    ${location['x']}
    ${max_x} =    Evaluate    ${location['x']}+${size['width']}
    ${max_x} =    Convert To Integer    ${max_x}
    ${min_y} =    Convert To Integer    ${location['y']}
    ${max_y} =    Evaluate    ${location['y']}+${size['height']}
    ${max_y} =    Convert To Integer    ${max_y}
    ${contact_x} =    Evaluate   random.sample(range(${min_x},${max_x}), 1)    random
    ${contact_y} =    Evaluate   random.sample(range(${min_y},${max_y}), 1)    random
    [Return]    ${contact_x}[0]    ${contact_y}[0]

Get Random Contact Point For Widget With Xpath
    [Documentation]    According to the location and the dimension of an element, returns a point to click on
    [Arguments]    ${xpath_locator}
    ${location} =    Get Element Location     xpath=${xpath_locator}
    ${size} =    Get Element Size     xpath=${xpath_locator}
    ${min_x} =    Convert To Integer    ${location['x']}
    ${max_x} =    Evaluate    ${location['x']}+${size['width']}
    ${max_x} =    Convert To Integer    ${max_x}
    ${min_y} =    Convert To Integer    ${location['y']}
    ${max_y} =    Evaluate    ${location['y']}+${size['height']}
    ${max_y} =    Convert To Integer    ${max_y}
    ${contact_x} =    Evaluate   random.sample(range(${min_x},${max_x}), 1)    random
    ${contact_y} =    Evaluate   random.sample(range(${min_y},${max_y}), 1)    random
    [Return]    ${contact_x}[0]    ${contact_y}[0]
