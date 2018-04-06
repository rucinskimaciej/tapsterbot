A Progressive Web App or a Single Page Application has been implemented in order to drive the robot.
This web app allows to send HTTP requests to the robot's server, just like the Android app, the python client or the Robot Framework keywords.

## Technical details

* JavaScript (ES 6) is used with Promises
* A Service Worker is used as a cache proxy to make the web app more offline
* CSS 3 media queries are used to try to make the web app responsive
* The web browser's local storage is used to store the URl of the robot's server
* A manifest file has been written to make possible the download of the web app in the home screen of the device
* If you host this web app in your web server, keep in mind you should have HTTPS connections, otherwise the web app will be available only through _localhost_

## The look of the app

<table>
<tr>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/master/clients/web/doc/screen_captures/v1.0.0_en_computer_chromium.png" alt="Picture of the web app from computer using Chromium on Ubuntu" board" title="Web app from computer using Chromium on Ubuntu" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/master/clients/web/doc/screen_captures/v1.0.0_en_smartphone_OnePlusOne_installed.png" alt="Picture of the web app installed in a OnePlus One smartphone" title="Picture of the web app installed in a OnePlus One smartphone" width="200">
</td>
</tr>
</table>

## ʕ •ᴥ•ʔ
Keep in mind this web app, like the other softwares in this project, are not perfect! Feel free to improve them and submit pull requests ;-)

