# PWA!

A Progressive Web App or a Single Page Application has been implemented in order to drive the robot.
This web app allows to send HTTP requests to the robot's server, just like the Android app, the Python client or the Robot Framework keywords.

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
<table>
<tr>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/web/doc/scren_captures/v2.0.0_en_smartphone_SamsungGalaxyA3.png" alt="The web app is responsive for smartphones" title="Responsive app for smartphones" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/web/doc/screen_captures/v2.0.0_en_computer_chromium.png" alt="The web app is responsive for computers screens" title="Responsive app for computers" width="200">
</td>
</tr>
</table>

## ʕ •ᴥ•ʔ
Keep in mind this web app, like the other softwares in this project, are not perfect! Feel free to improve them and submit pull requests ;-)

