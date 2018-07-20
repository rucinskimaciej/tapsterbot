/*
    MIT License
    Copyright (c) 2018  Pierre-Yves Lapersonne (Twitter: @pylapp, Mail: pylapp(dot)pylapp(at)gmail(dot)com)
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
 */
// ✿✿✿✿ ʕ •ᴥ•ʔ/ ︻デ═一

package pylapp.tapster.client.android.assistants.snips

import ai.snips.hermes.IntentMessage
import ai.snips.hermes.SessionEndedMessage
import ai.snips.hermes.SessionQueuedMessage
import ai.snips.hermes.SessionStartedMessage
import ai.snips.megazord.Megazord
import ai.snips.queries.ontology.Slot
import ai.snips.queries.ontology.SlotValue
import android.app.Activity

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import android.preference.PreferenceManager
import android.text.Html
import android.util.Log
import android.widget.Toast

import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.assistants.AssistantMessage
import pylapp.tapster.client.android.assistants.AssistantStub
import pylapp.tapster.client.android.assistants.UiNotifierStub
import pylapp.tapster.client.android.networks.HttpClientStub
import pylapp.tapster.client.android.tools.Config
import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub

import java.io.File

/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with assistants.
 * Will use the API of Snips.ai.
 *
 * Source code picked from https://github.com/snipsco/snips-platform-android-demo
 *
 * @author pylapp
 * @since 02/02/2018
 *
 * @version 1.0.0
 */
class SnipsAssistantSkeleton : AssistantStub {


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     * Companion defined constants for audio records and assistants based on Snips.ai
     */
    companion object {

        /**
         * The sample rate in Hz
         */
        private const val FREQUENCY = 16000

        /**
         * The channel for the audio record
         */
        private const val CHANNEL = AudioFormat.CHANNEL_IN_MONO

        /**
         * The encoding to apply for the audio record
         */
        private const val ENCODING = AudioFormat.ENCODING_PCM_16BIT

        /**
         * The Snips platform codename for Android port is Megazord
         */
        private var snips: Megazord? = null

        /*
         * The log tag
         */
        private val LOG_TAG = SnipsAssistantSkeleton::class.qualifiedName
                ?: "SnipsAssistantSkeleton"

    }


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The audio mRecorder to use so as to catch the voice of the user
     */
    private var mRecorder: AudioRecord? = null

    /**
     * The object which can make notifications through the GUI
     */
    private var mNotifier: UiNotifierStub? = null


    /* ************************ *
     * METHODS OF AssistantStub *
     * ************************ */

    /**
     * Starts the assistant
     * @param context - The context to use to make notifications, build assistant, etc.
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun startAssistant(context: Activity, callback: (() -> Unit?)?) {

        if (snips != null) return

        val featureFactory = FeaturesFactory()
        mNotifier = featureFactory.buildUiNotifier(context)
        val properties = featureFactory.buildPropertiesReader()
        properties.loadProperties(context)
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        // A directory where the assistant models was unziped.
        // It should contain the folders ASR dialogue hotword and NLU
        snips = buildInstance(context)
        if (snips == null) {
            Log.e(LOG_TAG, "Snips is null, a problem occurred with assistant's assets!")
            mNotifier?.toastMessage(context, AssistantMessage.Type.ALERT,
                    context.getString(R.string.assistant_error_no_assets_found) ?: "(x_x) *dead*")
            return
        }

        snips!!.onHotwordDetectedListener = fun() {
            Log.d(LOG_TAG, "An hotword was detected !")
            mNotifier?.displayMessage(context, AssistantMessage.Type.INFORMATION,
                    context.getString(R.string.assistant_hotword_detected) ?: ":-)")
            return
        }

        snips!!.onIntentDetectedListener = fun(intentMessage: IntentMessage) {
            Log.d(LOG_TAG, "received an intent: " + intentMessage)
            handleDetectedIntent(context, intentMessage)
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, intentMessage.toString())
            snips!!.endSession(intentMessage.sessionId, null)
            return
        }

        snips!!.onListeningStateChangedListener = fun(isListening: Boolean?) {
            Log.d(LOG_TAG, "Asr listening state: " + isListening!!)
            val message: String = when (isListening) {
                true -> context.getString(R.string.assistant_listening) ?: "(^_^)"
                false -> context.getString(R.string.assistant_hotword_detected) ?: "Sleeping"
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.INFORMATION, message)
            return
        }

        snips!!.onSessionStartedListener = fun(sessionStartedMessage: SessionStartedMessage) {
            Log.d(LOG_TAG, "Dialogue session started: " + sessionStartedMessage)
            if (properties.readProperty(PropertiesReaderStub.ENABLE_VIBRATIONS)!!.toBoolean()
                    && preferences.getBoolean(Config.PREFERENCES_ASSISTANT_VIBRATIONS, true)) {
                mNotifier?.vibrate(context, Config.DURATION_ASSISTANT_SESSION_START)
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, context.resources.getString(R.string.snips_session_started))
            return
        }

        snips!!.onSessionQueuedListener = fun(sessionQueuedMessage: SessionQueuedMessage) {
            Log.d(LOG_TAG, "Dialogue session queued: " + sessionQueuedMessage)
            return
        }

        snips!!.onSessionEndedListener = fun(sessionEndedMessage: SessionEndedMessage) {
            Log.d(LOG_TAG, "Dialogue session ended: " + sessionEndedMessage)
            if (properties.readProperty(PropertiesReaderStub.ENABLE_VIBRATIONS)!!.toBoolean()
                    && preferences.getBoolean(Config.PREFERENCES_ASSISTANT_VIBRATIONS, true)) {
                mNotifier?.vibrate(context, Config.DURATION_ASSISTANT_SESSION_END)
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.WARNING, context.resources.getString(R.string.assistant_session_ended))
            return
        }

        // This api is really for debugging purposes and you should not have features depending on its output
        // If you need us to expose more APIs please do ask !
        snips!!.onSnipsWatchListener = fun(s: String) {
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, s)
            parseAndDisplayMessageIfSuitable(context, s)
            return
        }

        // We enabled steaming in the builder, so we need to provide the platform an audio stream. If you don't want
        // to manage the audio stream do no enable the option, and the snips platform will grab the mic by itself
        object : Thread() {
            override fun run() {
                runStreaming()
            }
        }.start()

        snips!!.start() // no way to stop it yet, coming soon

        callback?.invoke()

    }

    /**
     * Stops the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun stopAssistant(callback: (() -> Unit?)?) {
        // TODO
        mNotifier?.cleanup()
        snips?.close()
        callback?.invoke()
    }

    /**
     * Starts a new session with the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun startSession(callback: (() -> Unit?)?) {
        if (snips == null) throw NullSnipsException("The Snips object has not been defined")
        snips!!.startSession(null, null, false, null)
        callback?.invoke()
    }


    /* ************* *
     * OTHER METHODS *
     * ************* */


    /**
     * Builds an instance of the Snips service using the assets
     * in the external storage of the device. If the assets are not found, returns null.
     *
     * @param context - The context to use to get preferences
     * @return [Megazord]? - The Snips service, or null
     */
    private fun buildInstance(context: Activity): Megazord? {

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val pathToAssetsOfAssistant = preferences.getString(Config.PREFERENCES_ASSISTANT_ASSETS,
                context.resources?.getString(R.string.default_value_assistant_assets))
        val assistantDir = File(Environment.getExternalStorageDirectory().toString(), pathToAssetsOfAssistant)

        return when (assistantDir.exists() && assistantDir.isDirectory) {
            true -> Megazord.builder(assistantDir)
                    .enableDialogue(true) // defaults to true
                    .enableHotword(true) // defaults to true
                    .enableSnipsWatchHtml(true) // defaults to false
                    .enableLogs(true) // defaults to false
                    .withHotwordSensitivity(0.5f) // defaults to 0.5
                    .enableStreaming(true) // defaults to false
                    .build()
            else -> null
        }

    }

    /**
     * Runs the user's audio record streaming
     */
    private fun runStreaming() {

        val minBufferSizeInBytes = AudioRecord.getMinBufferSize(FREQUENCY, CHANNEL, ENCODING)
        Log.d(LOG_TAG, "minBufferSizeInBytes: " + minBufferSizeInBytes)

        mRecorder = AudioRecord(MediaRecorder.AudioSource.MIC, FREQUENCY, CHANNEL,
                ENCODING, minBufferSizeInBytes)
        mRecorder!!.startRecording()

        // In a non demo app, you want to have a way to stop this :)
        while (true) {
            val buffer = ShortArray(minBufferSizeInBytes / 2)
            mRecorder!!.read(buffer, 0, buffer.size)
            if (snips != null) {
                snips!!.sendAudioBuffer(buffer)
            }
        }

    }

    /**
     * Handles the freshly detected intent
     *
     * @param context -
     * @param intentMessage - The message do deal with
     */
    private fun handleDetectedIntent(context: Activity, intentMessage: IntentMessage) {

        // Get the intent
        val rawIntentName = intentMessage.intent.intentName
        val intentName: AssistantStub.Intents = when {
            rawIntentName.endsWith(AssistantStub.Intents.TAP.usageName, true) -> AssistantStub.Intents.TAP
            rawIntentName.endsWith(AssistantStub.Intents.SWIPE.usageName, true) -> AssistantStub.Intents.SWIPE
            rawIntentName.endsWith(AssistantStub.Intents.SET_POSITION.usageName, true) -> AssistantStub.Intents.SET_POSITION
            rawIntentName.endsWith(AssistantStub.Intents.RESET.usageName, true) -> AssistantStub.Intents.RESET
            rawIntentName.endsWith(AssistantStub.Intents.GET_POSITION.usageName, true) -> AssistantStub.Intents.GET_POSITION
            rawIntentName.endsWith(AssistantStub.Intents.GET_STATUS.usageName, true) -> AssistantStub.Intents.GET_STATUS
            rawIntentName.endsWith(AssistantStub.Intents.SET_ARMS_ANGLES.usageName, true) -> AssistantStub.Intents.SET_ARMS_ANGLES
            rawIntentName.endsWith(AssistantStub.Intents.GET_ARMS_ANGLES.usageName, true) -> AssistantStub.Intents.GET_ARMS_ANGLES
            rawIntentName.endsWith(AssistantStub.Intents.CONTACT_Z.usageName, true) -> AssistantStub.Intents.CONTACT_Z
            rawIntentName.endsWith(AssistantStub.Intents.DANCE.usageName, true) -> AssistantStub.Intents.DANCE
            rawIntentName.endsWith(AssistantStub.Intents.STOP_DANCE.usageName, true) -> AssistantStub.Intents.STOP_DANCE
            else -> AssistantStub.Intents.UNKNOWN
        }

        // Get the parameters
        val listOfSlots: List<Slot> = intentMessage.slots
        val mapOfSlots: HashMap<String, Int> = HashMap()
        for (slot in listOfSlots) {
            val slotValue = slot.value as SlotValue.NumberValue // WARNING Assume all slots of all intents are numbers!
            mapOfSlots[slot.slotName] = slotValue.component1().toInt()
        }

        // Prepare the HTTP client
        val httpClient = initHttpClient(context)

        // The callback to use
        val callback = object : HttpClientStub.HttpClientCallback {
            override fun onSuccess(message: String?) {
                context.runOnUiThread({
                    if (message != null && message.isNotEmpty()) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, context.getString(R.string.state_command_success), Toast.LENGTH_LONG).show()
                    }
                })
            }

            override fun onFailure(message: String?) {
                context.runOnUiThread({
                    if (message != null && message.isNotEmpty()) {
                        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(context, context.getString(R.string.state_command_failure), Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        // Send the command according to the intent
        when (intentName) {
            AssistantStub.Intents.TAP -> {
                httpClient.commandTap(mapOfSlots["x"]!!.toInt(), mapOfSlots["y"]!!.toInt(), callback)
            }
            AssistantStub.Intents.SWIPE -> {
                httpClient.commandSwipe(mapOfSlots["startX"]!!.toInt(),
                        mapOfSlots["startY"]!!.toInt(),
                        mapOfSlots["endX"]!!.toInt(),
                        mapOfSlots["endY"]!!.toInt(),
                        callback)
            }
            AssistantStub.Intents.SET_POSITION -> {
                httpClient.commandSetPosition(mapOfSlots["x"]!!.toInt(),
                        mapOfSlots["y"]!!.toInt(),
                        mapOfSlots["z"]!!.toInt(),
                        callback)
            }
            AssistantStub.Intents.RESET -> {
                httpClient.commandReset(callback)
            }
            AssistantStub.Intents.GET_POSITION -> {
                httpClient.commandGetPosition(callback)
            }
            AssistantStub.Intents.GET_STATUS -> {
                httpClient.commandGetStatus(callback)
            }
            AssistantStub.Intents.SET_ARMS_ANGLES -> {
                httpClient.commandSetAngles(mapOfSlots["theta1"]!!.toInt(),
                        mapOfSlots["theta2"]!!.toInt(),
                        mapOfSlots["theta3"]!!.toInt(),
                        callback)
            }
            AssistantStub.Intents.GET_ARMS_ANGLES -> {
                httpClient.commandGetAngles(callback)
            }
            AssistantStub.Intents.CONTACT_Z -> {
                httpClient.commandGetContactPoint(callback)
            }
            AssistantStub.Intents.DANCE -> {
                httpClient.commandDance(callback)
            }
            AssistantStub.Intents.STOP_DANCE -> {
                httpClient.commandStopDance(callback)
            }
            AssistantStub.Intents.UNKNOWN -> {
                context.runOnUiThread({
                    mNotifier?.displayMessage(context, AssistantMessage.Type.VERBOSE,
                            context.getString(R.string.assistant_unknown_intent))
                })
            }
        } // End of when (intentName)

        if (intentName != AssistantStub.Intents.UNKNOWN) {
            context.runOnUiThread({
                mNotifier?.displayMessage(context, AssistantMessage.Type.VERBOSE,
                        context.getString(R.string.assistant_command_sent))
            })
        }

    }

    /**
     * Returns an HTTP client which will be used to send HTTP requests
     *
     * @return [HttpClientStub] - The ready to use client
     */
    private fun initHttpClient(context: Activity): HttpClientStub {

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val serverProtocol = preferences.getString(Config.PREFERENCES_ROBOT_PROTOCOL,
                context.getString(R.string.default_value_server_protocol))
        val serverIp = preferences.getString(Config.PREFERENCES_ROBOT_IP,
                context.getString(R.string.default_value_server_ip_address))
        val serverPort = preferences.getString(Config.PREFERENCES_ROBOT_PORT,
                context.getString(R.string.default_value_server_port))

        val httpClient = FeaturesFactory().buildHttpClient()
        httpClient.setServerIpAddress(serverIp!!)
        httpClient.setServerProtocol(serverProtocol!!)
        httpClient.setServerPort(serverPort!!)

        return httpClient

    }

    /**
     * Because with this current version on Snips.ai Android API with lost a lot of interesting
     * messages send through a "debug only" listener, we may check some messages and display
     * them if needed. We can see this method a s a glue stick to catch interesting things
     * from debug canal.
     *
     * @param context - Activity
     * @param message - The debug message we really enjoooooyyy to display because it may be cool
     */
    private fun parseAndDisplayMessageIfSuitable(context: Activity, message: String) {

        // Raw strings because we like them rough & raw
        val helloMessage = "Say the hotword, ask your query and let the magic happen"
        val tagMissingSlot = "[Tts] was asked to say"
        val tagParsedSlot = "[Nlu] parsed slot : "
        //val cleanMessage = message.replace("\\<[^>]*>","")
        val cleanMessage = Html.fromHtml(message).toString()

        // Case #1: Hello message
        if (message.contains(helloMessage, true)) {
            mNotifier?.displayMessage(context, AssistantMessage.Type.VERBOSE, cleanMessage)
            return
        }

        // Case #2: Missing slot
        if (message.contains(tagMissingSlot, true)) {
            val splits = cleanMessage.split('"', ignoreCase = true, limit = 0)
            mNotifier?.displayMessage(context, AssistantMessage.Type.VERBOSE, splits[splits.count() - 2])
            return
        }

        // Case #3: Parsed slot
        if (message.contains(tagParsedSlot, true)) {
            val splits = cleanMessage.split(":", ignoreCase = true, limit = 0)
            mNotifier?.displayMessage(context, AssistantMessage.Type.VERBOSE, splits[splits.count() - 1])
        }

    }


    /* ************* *
     * INNER CLASSES *
     * ************* */

    /**
     * An exception to throw if the [Megazord] has not been defined
     */
    class NullSnipsException(override val message: String) : IllegalStateException(message)

}