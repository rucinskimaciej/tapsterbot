# android

Here are an Android client to use so as to drive the robot by sending to it HTTP requests.
Indeed the Android device and the robot's server should be in the same network: **the server must be reachable by the Android device**.

The app embeds also a Snips.ai-based assistant so as to drive the robot in a more elegant way. We can talk to the assistant (which uses ASR and NLU) so as to make the robot move.
If you want to use this assistant, you must **create at the root of the external storage** area of the device, a _Tapster2client_ folder. In this folder, you should place another one **with the assets of the assistant**. These assets are available in this repository. You can define the path to the assets to use in the settings of the app.

## Install the APK

You can install the Android APK in the device with the following command.

```shell
  adb install bin/VERSION/APK.apk
```
_VERSION_ here is the version of the app, and _APK_ is the name of the file you want to use.

There are two APK, one with the assistant enabled, the other with the assistant disabled.

## Build the app from sources

You can also build the application from the sources available in _tapsterbot_android_.
You need Android Studio and Gradle to build them to an APK.

The versions in use for the project are :
  - _Android Studio_ 3.0.1
  - _Gradle_ 4.1
  - _Build tools_ 26.0.2
  - _Compile SDK version_ API27
  - _JRE_ 1.8.0
  - _JVM_ OpenJDK 64bits

## Customize the app

You can customize the application for your needs.
The app embeds the ACRA library which provides mail-sending feature when the app crashes.
You can define the email to send the crash reports in the _Tapster2CLientApp.kt_ file, in _@AcraMailSender_ tag.
Some features can also be disabled or enbled. Feel free to change the true/false value in the _features.properties_ in _assets_ folder.

## The look of the app

<table>
<tr>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/scren_captures/v1.0.0_en_commands_inuse.png" alt="The commands board" title="The commands board" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/scren_captures/v1.0.0_en_assistant.png" alt="The assistant board" title="The assistant board" width="200">
</td>
</tr>
</table>


