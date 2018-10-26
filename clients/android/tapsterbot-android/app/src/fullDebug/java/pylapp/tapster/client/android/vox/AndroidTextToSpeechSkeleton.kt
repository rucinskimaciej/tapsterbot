/*
    MIT License
    Copyright (c) 2018 Pierre-Yves Lapersonne (Mail: dev@pylapersonne.info)
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

package pylapp.tapster.client.android.vox

import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import android.speech.tts.TextToSpeech
import android.util.Log
import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.tools.Config
import java.util.*

/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with TTS features.
 * This pattern is used so as to avoid an activity or something else to have too many dependencies
 * and code about the way TTS is made.
 * A library may be used, so let's thing a step further in case of the death of this library.
 *
 * This class is also based on design pattern Proxy so as to simplify the use of the native TTS system
 *
 * @author pylapp
 * @since 08/02/2018
 *
 * @version 1.0.0
 */
class AndroidTextToSpeechSkeleton : TextToSpeechStub, TextToSpeech.OnInitListener {


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The Text To Speech native object
     */
    private var mTextToSpeech: TextToSpeech? = null

    /**
     * The locale in use
     */
    private var mLocale: Locale? = null


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     *
     */
    companion object {

        /*
         * The log tag
         */
        private val LOG_TAG = AndroidTextToSpeechSkeleton::class.qualifiedName

    }


    /* ***************************** *
     * METHODS FROM TextToSpeechStub *
     * ***************************** */

    /**
     * Initializes the TTS system
     *
     * @param context - The context to use to initialize the system
     */
    override fun init(context: Context) {

        // Clean up if needed
        if (mTextToSpeech != null) {
            mTextToSpeech?.stop()
            mTextToSpeech?.shutdown()
        }

        // Get locale for preferences
        val preferences = PreferenceManager.getDefaultSharedPreferences(context)
        val localeAsString = preferences.getString(Config.PREFERENCES_ASSISTANT_VOCALIZATION_LOCALE,
                context.resources.getString(R.string.default_value_assistant_language))
        mLocale = when (localeAsString) {
            "French" -> Locale.FRENCH
            "English" -> Locale.US
            else -> Locale.US
        }

        mTextToSpeech = TextToSpeech(context, NativeTextToSpeechSkeleton@ this)
    }

    /**
     * Vocalizes the text
     *
     * @param text - The vocalized texts
     */
    override fun speak(text: String) {
        if (mTextToSpeech == null) throw UninitializedSystemException("TTS system has not been initialized")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mTextToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        } else {
            mTextToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    /**
     * Stops the vocalization
     */
    override fun stop() {
        mTextToSpeech?.stop()
    }

    /**
     * Frees the resources, stops listeners and clean all the things.
     * The TTS system will be then destroyed.
     */
    override fun shutdown() {
        mTextToSpeech?.shutdown()
    }


    /* ********************************** *
     * METHOD TextToSpeech.OnInitListener *
     * ********************************** */

    /**
     * Initializes the TTS system with the locale
     */
    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = mTextToSpeech?.setLanguage(mLocale)
            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(LOG_TAG, "This language is not supported")
            }
            //speak("Hello")
        } else {
            Log.e(LOG_TAG, "Initialization failed!")
        }
    }


    /* ************* *
     * INNER CLASSES *
     * ************* */

    /**
     * Exception to throw is something wrong occurred about the TTS system because it may be undefined
     */
    class UninitializedSystemException(message: String) : IllegalStateException(message)

}