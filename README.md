# Tapster - Mobile Automation Robot

![A Taspter2 bot](poster.png)

## Why using such bot?
- Bring fun in your office
- Bring automation for your tests
- Extend your software-based instrumented tests (using UI Automator, Espresso, Kakao, Appium or whatever) with hardware-based tests
- Improve quality of your products
- Deal with secured elements on which no software can click
- Be as close as possible of the ideal use case (users use their fingers to click :3)
- Get a new tool for software development and mobile app tests: robots!

## Some references and things to see
- http://www.tapster.io (the creator of the Tapster bots)
- https://twitter.com/tapsterbot
- https://www.tindie.com/products/hugs/tapster/
- https://github.com/hugs/tapsterbot
- https://github.com/tapsterbot/tapsterbot
- https://github.com/appium/appium
- https://github.com/appium/robots
- https://github.com/penguinho/tapsterbot
- https://github.com/jackskalitzky/tapsterbot
- https://speakerdeck.com/pylapp/why-not-tapster

## Documentation
- [Getting started](https://github.com/pylapp/tapsterbot/wiki/01-%5C--Getting-started-with-Tapster-2)
- [Calibration of the robot](https://github.com/pylapp/tapsterbot/wiki/02-%5C--The-calibration-workflow)
- [Robot HTTP API](https://github.com/pylapp/tapsterbot/wiki/04-%5C--The-Tapster-2-API)
- [Python client](https://github.com/pylapp/tapsterbot/wiki/06-%5C--Drive-the-robot:-Python-API)
- [Robot Framework keywords](https://github.com/pylapp/tapsterbot/wiki/11--%5C-Automation-of-tests)
- [Robot Framework wrapper](https://github.com/pylapp/tapsterbot/wiki/11--%5C-Automation-of-tests)
- [Technical details](https://github.com/pylapp/tapsterbot/wiki/12--%5C-More-technical-details)
- [FAQ](https://github.com/pylapp/tapsterbot/wiki/13--%5C-FAQ)
- [Wiki](https://github.com/pylapp/tapsterbot/wiki)

## Versions
1. astro - (tag: vAstro) - The base version of the project, with calibrations workflows and assets.
2. bb8 - (tag: vBb8) - Verson with documentation and CURL commands.
3. c3po - (tag: vC3po) - Version with a client (written in Python) to use to drive the bot.
4. chappie - (tag: vChappie) - Version with Robot Framework keywords to use to drive the bot. Improved Python client. Cleaner files tree.
5. case - (tag: vCase) - Version with an Android app which embeds an assistant based on [Snips](https://snips.ai/ "Snips.ai").
6. deckard - (tag: vDeckard) - Version with a Progressive Web App and a version of the server accepting CORS.
7. dalek - (tag: vDalek) - Version with a wrapper for Robot Framework which provides keywords so as to use for Tapster bot in an easy way (with Appium or other automation tools)
8. eve - (tag: vEve) - More features in both sides and clients (stress inputs, multi tap, multi swipes, draw objects...)
9. finalfive - (tag: vFinalfive) - Bug fixes and new Robot Framework keywords, and bring cylons.

## Wanna help?
 - Make an iOS app to drive the robot
 - Add controls in server-side and clients-side to prevent the use of bad parameters (i.e. tap on with x=-42 and y=0.666)
 - Plug a camera to the robot to stream the tests
 - Draw a SVG on your screen (or pick it from your files) and send its XML content to the server so as to make the robot paint the thing :)
 - Integrate Snips assistants within the Progressive Web Apps
 - Define actions in Snips assistants
 - Integrate the use of Tapster inside Appium
 - Deal with coordinates based on landscape mode of devices
 - ... feel free to make pull requests and forks!

## Notice
This fork has been made so as to build a community around Tapster and bring new usages for this incredible tool.
If you read AUTHORS file, you may notice my name appeares twice. Indeed contributions to this project are professional and personal.
If you want to contact me, please use the appropriate email in the AUTHORS file.
If you want to blame me or send me cookies for the made work, please refer to the Copyright owner, make the difference between professional and personal contributions (not hard, read the commit message), and contact me ;-)


This Tapster (Tapster model 2) is free, open source et open hardware, so feel free to contribute!
