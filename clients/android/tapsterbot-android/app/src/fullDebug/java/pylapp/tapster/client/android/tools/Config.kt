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


package pylapp.tapster.client.android.tools

/**
 * Just a configuration file to prevent the use of rax or hard-coded values in the code
 *
 * @author Pierre-Yves Lapersonne
 * @since 08/02/2018
 *
 * @version 2.0.0
 */
class Config {

    /**
     * The companion object
     */
    companion object {

        /* ********************** *
         * Related to preferences *
         * ********************** */

        /**
         * The key in the preferences screen
         */
        const val PREFERENCES_SCREEN = "pref_app"

        /**
         * The key in the preferences screen for robot's server's protocol
         */
        const val PREFERENCES_ROBOT_PROTOCOL = "pref_key_settings_robot_protocol"

        /**
         * The key in the preferences screen for robot's server's IP address
         */
        const val PREFERENCES_ROBOT_IP = "pref_key_settings_robot_ip"

        /**
         * The key in the preferences screen for robot's server's port
         */
        const val PREFERENCES_ROBOT_PORT = "pref_key_settings_robot_port"

        /**
         * The key in the preferences screen for the "assistant" category
         */
        const val PREFERENCES_CATEGORY_ASSISTANT = "pref_key_settings_assistant"

        /**
         * The key in the preferences screen for the assistant's assets
         */
        const val PREFERENCES_ASSISTANT_ASSETS = "pref_key_settings_assistant_assets"

        /**
         * The key in the preferences screen for activation of the vocalization
         */
        const val PREFERENCES_ASSISTANT_VOCALIZATION = "pref_key_settings_assistant_enable"

        /**
         * The key in the preferences screen for locale of the vocalization
         */
        const val PREFERENCES_ASSISTANT_VOCALIZATION_LOCALE = "pref_key_settings_assistant_language_locale"

        /**
         * The key in the preferences screen for the vibrations
         */
        const val PREFERENCES_ASSISTANT_VIBRATIONS= "pref_key_settings_assistant_vibrations"

        /**
         * The key in the preferences screen for the "about" category
         */
        const val PREFERENCES_CATEGORY_ABOUT = "pref_key_about"

        /**
         * The key in the preferences screen for the app's version
         */
        const val PREFERENCES_APP_VERSION = "pref_key_about_app"

        /**
         * The key in the preferences screen for the licenses
         */
        const val PREFERENCES_APP_LICENSES = "pref_key_about_licenses"


        /* ********************** *
         * Related to robot's URL *
         * ********************** */

        /**
         * The path for status command
         */
        const val ROBOT_URL_PATH_STATUS = "/status"

        /**
         * The path for commandReset command
         */
        const val ROBOT_URL_PATH_RESET = "/reset"

        /**
         * The path for commandDance command
         */
        const val ROBOT_URL_PATH_DANCE = "/dance"

        /**
         * The path for stop dancing command
         */
        const val ROBOT_URL_PATH_STOP_DANCE = "/stopDancing"

        /**
         * The path for set angles command
         */
        const val ROBOT_URL_PATH_SET_ANGLES = "/setAngles"

        /**
         * The path for set position command
         */
        const val ROBOT_URL_PATH_SET_POSITION = "/setPosition"

        /**
         * The path for get angles command
         */
        const val ROBOT_URL_PATH_GET_ANGLES = "/angles"

        /**
         * The path for get position command
         */
        const val ROBOT_URL_PATH_GET_POSITION = "/position"

        /**
         * The path for tap command
         */
        const val ROBOT_URL_PATH_TAP = "/tap"

        /**
         * The path for n-tap command
         */
        const val ROBOT_URL_PATH_TAP_MANY = "/nTap"

        /**
         * The path for swipe command
         */
        const val ROBOT_URL_PATH_SWIPE = "/swipe"

        /**
         * The path for n-swipe command
         */
        const val ROBOT_URL_PATH_SWIPE_MANY = "/nSwipe"

        /**
         * The path for contact point command
         */
        const val ROBOT_URL_PATH_CONTACT = "/contactZ"

        /**
         * The path for draw star command
         */
        const val ROBOT_URL_PATH_DRAW_STAR = "/drawStar"

        /**
         * The path for draw circle command
         */
        const val ROBOT_URL_PATH_DRAW_CIRCLE = "/drawCircle"

        /**
         * The path for draw spiral command
         */
        const val ROBOT_URL_PATH_DRAW_SPIRAL = "/drawSpiral"

        /**
         * The path for draw square command
         */
        const val ROBOT_URL_PATH_DRAW_SQUARE = "/drawSquare"

        /**
         * The path for draw cross command
         */
        const val ROBOT_URL_PATH_DRAW_CROSS = "/drawCross"

        /**
         * The path for draw triangle command
         */
        const val ROBOT_URL_PATH_DRAW_TRIANGLE = "/drawTriangle"

        /**
         * The path for draw random pattern command
         */
        const val ROBOT_URL_PATH_DRAW_RANDOM_PATTERN = "/drawRandomPattern"


        /* **** *
         * Misc *
         * **** */

        /**
         * The regular expression to use to check parameters of the tap command
         */
        const val REGEX_COMMAND_TAP = "\\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the n-tap command
         */
        const val REGEX_COMMAND_TAP_MANY = "\\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the swipe command
         */
        const val REGEX_COMMAND_SWIPE = "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the n-swipe command
         */
        const val REGEX_COMMAND_SWIPE_MANY = "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw circle command
         */
        const val REGEX_COMMAND_DRAW_CIRCLE = "\\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw spiral command
         */
        const val REGEX_COMMAND_DRAW_SPIRAL= "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw square command
         */
        const val REGEX_COMMAND_DRAW_SQUARE = "\\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw cross command
         */
        const val REGEX_COMMAND_DRAW_CROSS = "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw triangle command
         */
        const val REGEX_COMMAND_DRAW_TRIANGLE = "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the draw random pattern command
         */
        const val REGEX_COMMAND_DRAW_RANDOM = "\\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The regular expression to use to check parameters of the set position command
         */
        const val REGEX_COMMAND_SET_POSITION = "-{0,1}\\d{0,9} -{0,1}\\d{0,9} -{0,1}\\d{0,9}"

        /**
         * The regular expression to use to check parameters of the set angles command
         */
        const val REGEX_COMMAND_SET_ANGLES = "\\d{0,9} \\d{0,9} \\d{0,9}"

        /**
         * The separator to use to separate parameters
         */
        const val REGEX_PARAMETERS_SEPARATOR = " "

        /**
         * The duration of the vibration to notify the session wht the assistant starts
         */
        const val DURATION_ASSISTANT_SESSION_START = 50L

        /**
         * The duration of the vibration to notify the session wht the assistant ends
         */
        const val DURATION_ASSISTANT_SESSION_END = 100L

    } // End of companion object

}