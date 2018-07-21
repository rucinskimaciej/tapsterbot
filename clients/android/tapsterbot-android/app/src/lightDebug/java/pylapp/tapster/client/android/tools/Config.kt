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


package pylapp.tapster.client.android.tools

/**
 * Just a configuration file to prevent the use of raw or hard-coded values in the code
 *
 * @author pylapp
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
         * The path for commandTap command
         */
        const val ROBOT_URL_PATH_TAP = "/tap"

        /**
         * The path for commandSwipe command
         */
        const val ROBOT_URL_PATH_SWIPE = "/swipe"

        /**
         * The path for contact point command
         */
        const val ROBOT_URL_PATH_CONTACT = "/contactZ"

        /* **** *
         * Misc *
         * **** */

        /**
         * The regular expression to use to check if parameters are well formed
         */
        const val REGEX_COMMAND_PARAMETERS_2 = "\\d{1,9} \\d{1,9}"

        /**
         * The regular expression to use to check if parameters are well formed
         */
        const val REGEX_COMMAND_PARAMETERS_3 = "\\d{1,9} \\d{1,9} -{0,1}\\d{1,9}"

        /**
         * The regular expression to use to check if parameters are well formed
         */
        const val REGEX_COMMAND_PARAMETERS_4 = "\\d{1,9} \\d{1,9} \\d{1,9} \\d{1,9}"

        /**
         * The separator to use to separate parameters
         */
        const val REGEX_PARAMETERS_SEPARATOR = " "

    } // End of companion object

}