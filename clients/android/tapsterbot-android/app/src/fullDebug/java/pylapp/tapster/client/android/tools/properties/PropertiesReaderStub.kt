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


package pylapp.tapster.client.android.tools.properties

import android.content.Context

/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with properties.
 * With this pattern, it should be easier to deal with several sources of properties and configurations.
isTextToSpeechEnabled
 *
 * @author pylapp
 * @since 07/02/2018
 *
 * @version 3.0.0
 */
interface PropertiesReaderStub {


    /* **************** *
     * COMPANION OBJECT *
     * *************** */

    /**
     * A companion object which contain as constants the name of the properties
     */
    companion object {

        /**
         * The name of the properties file
         */
        const val PROPERTIES_FILE = "features.properties"

        /**
         * Display or not commandTap targets
         */
        const val ENABLE_GUI_DISPLAY_TAPTARGETS = "enable_gui_display_taptarget"

        /**
         * Display or not intro screens
         */
        const val ENABLE_GUI_DISPLAY_INTROSCREENS = "enable_gui_display_introscreens"

        /**
         * Display the licenses or not
         */
        const val ENABLE_GUI_DISPLAY_LICENSES = "enable_gui_display_licenses"

        /**
         * Enable or disable the commands panel
         */
        const val ENABLE_GUI_COMMANDS = "enable_commands"

        /**
         * Enable or disable the assistant
         */
        const val ENABLE_ASSISTANT = "enable_assistant"

        /**
         * Display debug messages of the assistant
         */
        const val ENABLE_ASSISTANT_GUI_DISPLAY_DEBUG_MESSAGES = "enable_assistant_gui_display_debug_messages"

        /**
         * Display verbose messages of the assistant
         */
        const val ENABLE_ASSISTANT_GUI_DISPLAY_VERBOSE_MESSAGES = "enable_assistant_gui_display_verbose_messages"

        /**
         * Display information messages of the assistant
         */
        const val ENABLE_ASSISTANT_GUI_DISPLAY_INFORMATION_MESSAGES = "enable_assistant_gui_display_information_messages"

        /**
         * Display warning messages of the assistant
         */
        const val ENABLE_ASSISTANT_GUI_DISPLAY_WARNING_MESSAGES = "enable_assistant_gui_display_warning_messages"

        /**
         * Display alert messages of the assistant
         */
        const val ENABLE_ASSISTANT_GUI_DISPLAY_ALERT_MESSAGES = "enable_assistant_gui_display_alert_messages"

        /**
         * Enable or disable Text To Speech
         */
        const val ENABLE_ASSISTANT_TTS = "enable_assistant_tts"

        /**
         * Enable of not the crash-report-management feature
         */
        const val ENABLE_CRASH_REPORT_MANAGEMENT = "enable_monitoring_crash"

        /**
         * Enable or not the vibrations made to notify something to the user
         */
        const val ENABLE_VIBRATIONS = "enable_vibrations"

    }

    /* ******************* *
     * METHODS TO OVERRIDE *
    * ******************* */

    /**
     * Load the properties in the reader
     * @param context - The context to use if needed
     */
    fun loadProperties(context: Context?)

    /**
     * Reads in the loaded properties the property with this name
     * @param name - The name of the property
     * @return [String] - The value of the property
     */
    fun readProperty(name: String): String?

}