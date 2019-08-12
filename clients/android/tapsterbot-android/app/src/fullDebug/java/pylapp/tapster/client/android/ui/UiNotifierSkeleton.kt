/*
    MIT License
    Copyright (c) 2018 - 2019 Pierre-Yves Lapersonne (Mail: dev@pylapersonne.info)
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

package pylapp.tapster.client.android.ui

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.preference.PreferenceManager
import android.util.Log
import android.widget.ListView
import android.widget.Toast

import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.assistants.AssistantMessage
import pylapp.tapster.client.android.assistants.UiNotifierStub
import pylapp.tapster.client.android.tools.Config
import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub
import pylapp.tapster.client.android.ui.mainscreen.ListOfAssistantDialogueMessagesAdapter
import pylapp.tapster.client.android.vox.TextToSpeechStub

/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with an object which can make notifications to the user interface.
 * This pattern is used so as to avoid the assistant to have too many dependencies and code about the UI.
 *
 * @author Pierre-Yves Lapersonne
 * @since 06/02/2018
 *
 * @version 1.0.1
 */
class UiNotifierSkeleton(context: Context) : UiNotifierStub {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The Text To Speech vocalizer
     */
    private var mVocalizer: TextToSpeechStub? = null

    init {
        mVocalizer = initVocalizer(context)
    }


    /* ******* *
     * METHODS *
     * ******* */

    /**
     * Displays in the GUI the messages
     *
     * @param context - The context to use
     * @param type - The type of the message
     * @param message - The string to display
     */
    override fun displayMessage(context: Activity, type: AssistantMessage.Type, message: String) {

        Log.d("SnipsAssistantSkeleton", "New message: $message" )

        // Read in the properties file if we can display the message according to its type
        if (!isMessageTypeDisplayEnabled(type, context)) return

        // If we are here, the message can be displayed
        context.runOnUiThread {
            var listOfMessages = context.findViewById<ListView>(R.id.lv_assistant_dialogue_messages)
            if (listOfMessages != null) {
                listOfMessages = listOfMessages
                if (listOfMessages.adapter == null) return@runOnUiThread
                val listOfMessagesAdapter = listOfMessages.adapter as ListOfAssistantDialogueMessagesAdapter
                listOfMessagesAdapter.messages.add(AssistantMessage(type, message))
                listOfMessagesAdapter.notifyDataSetChanged()
                listOfMessages.setSelection(listOfMessagesAdapter.count - 1)
            }
        }


        // Check if we can vocalize (in preferences and firstly in .properties)
        if (isTextToSpeechEnabled(context)) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val isVocalizationEnabled = preferences.getBoolean(
                    Config.PREFERENCES_ASSISTANT_VOCALIZATION, false)
            if (isVocalizationEnabled && type != AssistantMessage.Type.DEBUG) {
                mVocalizer?.speak(message)
            }
        }

    }

    /**
     * Toasts a message
     *
     * @param context - The context to use
     * @param type - The type of the message
     * @param message - The string to display
     */
    override fun toastMessage(context: Activity, type: AssistantMessage.Type, message: String) {
        context.runOnUiThread {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    /**
     * Allows the caller of this function to clean the notifier
     */
    override fun cleanup() {
        destroyVocalizer()
    }

    /**
     * Vibrates to notify a new state to the user
     *
     * @param context - To get the vibrator service
     * @param duration - The duration of the vibration (in ms)
     */
    override fun vibrate(context: Context, duration: Long) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val amplitude = 10
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(duration, amplitude))
        } else {
            vibrator.vibrate(duration)
        }
    }

    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Returns if the feature to display this type of message is enabled or not
     *
     * @param type - The type of message to display, which is be enabled or disabled
     * @param context - The context to use to get the property
     */
    private fun isMessageTypeDisplayEnabled(type: AssistantMessage.Type, context: Context): Boolean {

        val propertiesReader: PropertiesReaderStub = FeaturesFactory().buildPropertiesReader()
        propertiesReader.loadProperties(context)

        return when (type) {
            AssistantMessage.Type.DEBUG -> {
                propertiesReader.readProperty(PropertiesReaderStub
                        .ENABLE_ASSISTANT_GUI_DISPLAY_DEBUG_MESSAGES)!!.toBoolean()
            }
            AssistantMessage.Type.VERBOSE -> {
                propertiesReader.readProperty(PropertiesReaderStub
                        .ENABLE_ASSISTANT_GUI_DISPLAY_VERBOSE_MESSAGES)!!.toBoolean()
            }
            AssistantMessage.Type.INFORMATION -> {
                propertiesReader.readProperty(PropertiesReaderStub
                        .ENABLE_ASSISTANT_GUI_DISPLAY_INFORMATION_MESSAGES)!!.toBoolean()
            }
            AssistantMessage.Type.WARNING -> {
                propertiesReader.readProperty(PropertiesReaderStub
                        .ENABLE_ASSISTANT_GUI_DISPLAY_WARNING_MESSAGES)!!.toBoolean()
            }
            AssistantMessage.Type.ALERT -> {
                propertiesReader.readProperty(PropertiesReaderStub
                        .ENABLE_ASSISTANT_GUI_DISPLAY_ALERT_MESSAGES)!!.toBoolean()
            }
        }

    }

    /**
     * Returns true or false depending on the activation / deactivation in configuration file
     * for Text To Speech
     *
     * @param context - The context to use
     * @return [Boolean] - True is feature enabled by configuration, false otherwise
     */
    private fun isTextToSpeechEnabled(context: Context): Boolean {
        val properties = FeaturesFactory().buildPropertiesReader()
        properties.loadProperties(context)
        return properties.readProperty(PropertiesReaderStub.ENABLE_ASSISTANT_TTS)!!.toBoolean()
    }

    /**
     * Initializes the vocalizer for the assistant
     *
     * @param context -
     * @return [TextToSpeechStub] -
     */
    private fun initVocalizer(context: Context): TextToSpeechStub? {
        val tts = FeaturesFactory().buildTextToSpeechVocalizer()
        tts.init(context)
        return tts
    }

    /**
     * Destroys the vocalizer
     */
    private fun destroyVocalizer() {
        mVocalizer?.stop()
        mVocalizer?.shutdown()
        mVocalizer = null
    }

}