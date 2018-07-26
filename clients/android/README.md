# android

Here is an Android client to use so as to drive the robot by sending to it HTTP requests.  
Indeed the Android device and the robot's server should be in the same network: **the server must be reachable by the Android device of course**.

The app may embed also a [Snips.ai-based assistant](https://snips.ai/ "Snips.ai") so as to drive the robot in a more elegant way.  
We can talk to the assistant (which uses ASR and NLU) so as to make the robot move.  
With the Android Studio project, you can build this full version of the app using the _ful_ Fragle flavor. If you are looking for onyl the app without assistant (far more lighter), you should buld the app using the _light_ Gradle flavor.  

If you want to use this assistant, you must a _Tapster2client_ folde in the external storage of your device. You can also modyfy this apth inside the app's settings. In this folder, you should place another folder **with the assets of the assistant**. These assets are available [here](https://github.com/pylapp/tapsterbot/tree/master/clients/chatbot-snips "Assets of the assistant").

## Install the APK

You can install the Android APK in the device with the following command.

```shell
  adb install bin/VERSION/APK.apk
```
_VERSION_ here is the version of the app, and _APK_ is the name of the file you want to use.

There is onlu one APK without the assistant. If you need to the full app, you have to build it using Androdi Studio.
Indeed the weight of the full app is incredibly high (200MB) and cannot be uploaded through GitHub.

## Build the app from sources

You can build the application from the sources available in _tapsterbot_android_.
You need Android Studio and Gradle to build them to an APK.

The versions in use for the project are :
  - _Android Studio_ 3.0.1
  - _Gradle_ 4.1
  - _Build tools_ 26.0.2
  - _Compile SDK version_ API27
  - _JRE_ 1.8.0
  - _JVM_ OpenJDK 64bits
  - _Snips for Android_ 0.52.7

## Customize the app

You can customize the application for your needs.
The app embeds the [ACRA](https://github.com/ACRA/acra "GitHub project of ACRA") library which provides mail-sending feature when the app crashes.
You can define the email to send the crash reports in the _Tapster2ClientApp.kt_ file, in _@AcraMailSender_ tag.
Some features can also be disabled or enabled. Feel free to change the true/false values in the _features.properties_ in _assets_ folder.

## The look of the app

<table>
<tr>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v2.0.0_en_intro1.png" alt="The introduction screen" title="Hello world ;-D" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v2.0.0_en_helper1.png" alt="Some helpers to introduce the app" title="Helpers are here :)" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v2.0.0_en_commands_inuse.png" alt="The commands board" title="The commands board" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v2.0.0_en_comands_assistant.png" alt="The assistant board" title="The assistant board" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v2.0.0_en_settings.png" alt="The settings screen" title="The settings screen" width="200">
</td>
<td>
<img src="https://github.com/pylapp/tapsterbot/blob/case/clients/android/doc/screen_captures/v1.0.0_en_licenses.png" alt="Some licenses" title="The licenses screen" width="200">
</td>
</tr>
</table>



