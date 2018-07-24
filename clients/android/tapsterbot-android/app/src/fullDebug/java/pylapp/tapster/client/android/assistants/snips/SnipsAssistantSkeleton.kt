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
import ai.snips.nlu.ontology.Slot
import ai.snips.nlu.ontology.SlotValue
import ai.snips.platform.SnipsPlatformClient

import android.app.Activity
import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Environment
import android.os.Process
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
 * More details at https://snips.ai/
 *
 * Warning: Wa have to refactor this class to optimize it, Snips initialization is heavy, and we may face to memory leaks problems!
 *
 * @author pylapp
 * @since 02/02/2018
 *
 * @version 3.0.0
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

        /*
         * The log tag
         */
        private val LOG_TAG = SnipsAssistantSkeleton::class.qualifiedName
                ?: "SnipsAssistantSkeleton"

        /**
         * The Snips client, might be placed in another class in the near future because of memory leaks issues
         */
        var snipsClient: SnipsPlatformClient? = null //FIXME !

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

    /**
     *
     */
    private var mContinueStreaming: Boolean = true


    /* ************************ *
     * METHODS OF AssistantStub *
     * ************************ */

    /**
     * Starts the assistant
     * @param context - The context to use to make notifications, build assistant, etc.
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun startAssistant(context: Activity, callback: (() -> Unit?)?) {

        mContinueStreaming = true
        val featureFactory = FeaturesFactory()
        mNotifier = featureFactory.buildUiNotifier(context)

        if (snipsClient != null) return

        val properties = featureFactory.buildPropertiesReader()
        properties.loadProperties(context)
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)

        snipsClient = buildAssistant(context)
        if (snipsClient == null) {
            Log.e(LOG_TAG, "Snips is null, a problem occurred with assistant's assets!")
            mNotifier?.toastMessage(context, AssistantMessage.Type.ALERT,
                    context.getString(R.string.assistant_error_no_assets_found) ?: "(x_x) *dead*")
            return
        }

        snipsClient!!.onHotwordDetectedListener = fun() {
            Log.i(LOG_TAG, "An hotword was detected !")
            mNotifier?.displayMessage(context, AssistantMessage.Type.INFORMATION,
                    context.getString(R.string.assistant_hotword_detected) ?: ":-)")
            return
        }

        snipsClient!!.onIntentDetectedListener = fun(intentMessage: IntentMessage) {
            Log.i(LOG_TAG, "Received an intent: $intentMessage" )
            handleDetectedIntent(context, intentMessage)
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, intentMessage.toString())
            snipsClient!!.endSession(intentMessage.sessionId, null)
            return
        }

        snipsClient!!.onListeningStateChangedListener = fun(isListening: Boolean?) {
            Log.d(LOG_TAG, "ASR listening state: " + isListening!!)
            val message: String = when (isListening) {
                true -> context.getString(R.string.assistant_listening) ?: "(^_^)"
                false -> context.getString(R.string.assistant_hotword_detected) ?: "Sleeping"
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.INFORMATION, message)
            return
        }

        snipsClient!!.onSessionStartedListener = fun(sessionStartedMessage: SessionStartedMessage) {
            Log.d(LOG_TAG, "Dialogue session started: $sessionStartedMessage")
            if (properties.readProperty(PropertiesReaderStub.ENABLE_VIBRATIONS)!!.toBoolean()
                    && preferences.getBoolean(Config.PREFERENCES_ASSISTANT_VIBRATIONS, true)) {
                mNotifier?.vibrate(context, Config.DURATION_ASSISTANT_SESSION_START)
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, context.resources.getString(R.string.snips_session_started))
            return
        }

        snipsClient!!.onSessionQueuedListener = fun(sessionQueuedMessage: SessionQueuedMessage) {
            Log.d(LOG_TAG, "Dialogue session queued: $sessionQueuedMessage")
            return
        }

        snipsClient!!.onSessionEndedListener = fun(sessionEndedMessage: SessionEndedMessage) {
            Log.d(LOG_TAG, "Dialogue session ended: $sessionEndedMessage")
            if (properties.readProperty(PropertiesReaderStub.ENABLE_VIBRATIONS)!!.toBoolean()
                    && preferences.getBoolean(Config.PREFERENCES_ASSISTANT_VIBRATIONS, true)) {
                mNotifier?.vibrate(context, Config.DURATION_ASSISTANT_SESSION_END)
            }
            mNotifier?.displayMessage(context, AssistantMessage.Type.WARNING, context.resources.getString(R.string.assistant_session_ended))
            return
        }

        snipsClient!!.onPlatformReady = fun(){
            Log.i(LOG_TAG, "The Snips platform is now ready")
//            startSession()
        }

        snipsClient!!.onPlatformError = fun(error: SnipsPlatformClient.SnipsPlatformError){
            Log.e(LOG_TAG, "Error within the Snips platform: ${error.message}")
        }

        // This api is really for debugging purposes and you should not have features depending on its output
        snipsClient!!.onSnipsWatchListener = fun(s: String) {
            mNotifier?.displayMessage(context, AssistantMessage.Type.DEBUG, s)
            parseAndDisplayMessageIfSuitable(context, s)
            return
        }

        startStreaming()

        snipsClient!!.connect(context.applicationContext)

        callback?.invoke()

    }

    /**
     * Stops the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun stopAssistant(callback: (() -> Unit?)?) {
        mNotifier?.cleanup()
        mContinueStreaming = false
        snipsClient?.disconnect()
        callback?.invoke()
    }

    /**
     * Starts a new session with the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    override fun startSession(callback: (() -> Unit?)?) {
        if (snipsClient == null) throw NullSnipsException("The Snips object has not been defined")
        snipsClient!!.startSession(null, ArrayList(), false, null)
        callback?.invoke()
    }

    /**
     * Make the assistant in frozen state, a pause
     */
    override fun pauseAssistant(callback: (() -> Unit?)?) {
        mContinueStreaming = false
        snipsClient?.pause()
    }

    /**
     * Resume the assistant, end of the pause
     */
    override fun resumeAssistant(callback: (() -> Unit?)?) {
        startStreaming()
        snipsClient?.resume()
    }


    /* ************* *
     * OTHER METHODS *
     * ************* */


    /**
     * Builds an instance of the Snips service using the assets
     * in the external storage of the device. If the assets are not found, returns null.
     *
     * @param context - The context to use to get preferences
     * @return [SnipsPlatformClient]? - The Snips service, or null
     */
    private fun buildAssistant(context: Activity): SnipsPlatformClient? {

        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val pathToAssetsOfAssistant = preferences.getString(Config.PREFERENCES_ASSISTANT_ASSETS,
                context.resources?.getString(R.string.default_value_assistant_assets))
        val assistantDir = File(Environment.getExternalStorageDirectory().toString(), pathToAssetsOfAssistant)

        return when (assistantDir.exists() && assistantDir.isDirectory) {
            true -> SnipsPlatformClient.Builder(assistantDir)
                    // Dialogue + ASR + NLU features
                    .enableDialogue(true)
                    // Hotword feature
                    .enableHotword(true)
                    // Add HTML coloration to Snips watch output
                    .enableSnipsWatchHtml(true)
                    // Enable platforms debug logs
                    .enableLogs(true)
                    // Hotword sensibility
                    .withHotwordSensitivity(0.5f)
                    // True: disable OpenSL integration and send our own sound to the platform
                    .enableStreaming(true)
                    .build()
            else -> null
        }

    }


    /**
     * Starts the streaming capture
     */
    private fun startStreaming(){
        mContinueStreaming = true
        object : Thread() {
            override fun run() {
                android.os.Process.setThreadPriority(Process.THREAD_PRIORITY_URGENT_AUDIO)
                runStreaming()
            }
        }.start()
    }

    /**
     * Runs the user's audio record streaming
     */
    private fun runStreaming() {

        Log.i(LOG_TAG, "Starting audio streaming...")

        val minBufferSizeInBytes = AudioRecord.getMinBufferSize(FREQUENCY, CHANNEL, ENCODING)

        mRecorder = AudioRecord(MediaRecorder.AudioSource.MIC, FREQUENCY, CHANNEL,
                ENCODING, minBufferSizeInBytes)
        mRecorder!!.startRecording()

        // In a non demo app, you want to have a way to stop this :)
        while (mContinueStreaming) {
            val buffer = ShortArray(minBufferSizeInBytes / 2)
            mRecorder!!.read(buffer, 0, buffer.size)
            if (snipsClient != null) {
                snipsClient!!.sendAudioBuffer(buffer)
            }
        }

        mRecorder!!.stop()
        Log.i(LOG_TAG, "Audio streaming stopped.")

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
            // TODO
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
            // TODO
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