# Powerful off-the-grid privacy-safe assistant with ASR and NLU: Snips.ai

Because we have a robot which make tests and tap on a device, why not using an assistant to talk to it?

That is the reason why an assistant has been defined, and today integrated to the [Android app](https://github.com/pylapp/tapsterbot/wiki/08-%5C--Drive-the-robot:-Android-client). The chosen solution is [Snips](https://snips.ai).
Snips is a solution which respects the private life, is not intrusive, does not send data to foreign clouds, and has all its logic local. Thus, instead of an _on-the-grid_ solution like _Google Assistant_ and _Dialog Flow_, Snips is _off-the-grid_ and keep its resources locally.
The bad side of this kind of tools is the final APK is quite heavy, but it is not a fatality :)
In fact, if you worry about your life, why not keeping it near you? 

## The assets

In this [folder](https://github.com/pylapp/tapsterbot/tree/master/clients/chatbot-snips), you can find some files. These are the assistant's assets you must use. In the Android app's case, you have to download in your device these files, and refer them to the assistant. Without them, no logic, no features and... a dumb assistant which does not understand what you say to him. These files are downloaded from the web interface you can have with a Snips account. The logic about Automatic Speech Recognition and Natural Language Understanding engine are downloaded from Snips's repositories in the APK. They allow the assistant to catch, parse and understand what you say, but without the logic of your app.

## Awake the assistant

To awake assistant, i.e. make it be aware of what you will say, you should say the hotword "_Hey Snips!_".
Today, the assistant for Tapster robot understands only english and french.

## Some commands

If you look in the [data](https://github.com/pylapp/tapsterbot/tree/master/clients/chatbot-snips/data) folder, you will see files containing slots and training samples. The slots define the variables in the sentences (like coordinates, values, etc), and the training samples train the assistant. If you talk to the assistant using one of them, it will in the end catch your intention.

In french, you can say for examples:
* Quelles sont les coordonnées du doigt ?
* Pointe sur 100 150 -150
* Glisse de 100 100 à 550 550
* Frappe en 42 666

In english, you can say for examples:
* Give me the current position of the robot's finger  
* Move your pointer to 100 150 -150
* Swipe from 100 100 to 550 550
* Click on 42 666
ay (2018-03-02) Snips is young, specially the Android part :)

### Get assistants from the store
You can find the _Tapster2 commands (french)_ on the [Snips store](https://console.snips.ai/store/fr/bundle_PPn5qxGGbBQ "Get french Snips assistant").

You may find also the _Tapster2 commands (english)_ on the [Snips store](https://console.snips.ai/store/en/bundle_rkqD2NNVvb8 "Get english Snips assistant").