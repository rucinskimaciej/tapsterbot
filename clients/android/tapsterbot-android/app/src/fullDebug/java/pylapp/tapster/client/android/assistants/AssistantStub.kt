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

package pylapp.tapster.client.android.assistants

import android.app.Activity

/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with assistants
 *
 * @author pylapp
 * @since 02/02/2018
 *
 * @version 3.0.0
 */
interface AssistantStub {


    /* ******************* *
     * METHODS TO OVERRIDE *
     * ******************* */

    /**
     * Starts the assistant
     * @param context - The context to use to get preferences, make notifications, etc
     * @param callback - Something to trigger when action is done, nullable
     */
    fun startAssistant(context: Activity, callback: (() -> Unit?)?)

    /**
     * Starts a new session with the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    fun startSession(callback: (() -> Unit?)? = null)

    /**
     * Stops the assistant
     * @param callback - Something to trigger when action is done, nullable
     */
    fun stopAssistant(callback: (() -> Unit?)? = null)

    /**
     * Make the assistant in frozen state, a pause
     */
    fun pauseAssistant(callback: (() -> Unit?)? = null)

    /**
     * Resume the assistant, end of the pause
     */
    fun resumeAssistant(callback: (() -> Unit?)? = null)


    /* ****************** *
     * INNER ENUMERATIONS *
     * ****************** */

    /**
     * The enumeration of intents the assistant can deal.
     * Their usage names are the names which can help the user to discriminate an intent from others.
     * Indeed, with several bundles / packages / other folders, we may have several declinations for
     * a single intent. For example, the intent "Throw_my_banana" can be declined to "fr_Throw_my_banana"
     * and "en_Throw_my_banana".
     */
    enum class Intents(val usageName: String) {
        /**
         * Get the 3D position of the robot
         */
        GET_POSITION("Get_position"),
        /**
         * Set the 3D position of the robot
         */
        SET_POSITION("Set_position"),
        /**
         * Get the angles of the arms
         */
        GET_ARMS_ANGLES("Get_arms_angles"),
        /**
         * Set the angles of the arms
         */
        SET_ARMS_ANGLES("Set_arms_angles"),
        /**
         * Make the robot tap to a point
         */
        TAP("Tap"),
        /**
         * Swipe from a point to another
         */
        SWIPE("Swipe"),
        /**
         * Make the robot dance
         */
        DANCE("Dance"),
        /**
         * Make the robot stop dancing
         */
        STOP_DANCE("Stop_dancing"),
        /**
         * Get the contact point
         */
        CONTACT_Z("Contact_z"),
        /**
         * Get the status of the server
         */
        GET_STATUS("Get_status"),
        /**
         * Reset the robot to its default state
         */
        RESET("Reset"),
        /**
         * Make the robot tap n times to a point
         */
        TAP_MANY("Tap_n_times"),
        /**
         * Draw a star
         */
        DRAW_STAR("Draw_star"),
        /**
         * Draw a triangle
         */
        DRAW_TRIANGLE("Draw_triangle"),
        /**
         * Draw a square
         */
        DRAW_SQUARE("Draw_square"),
        /**
         * Draw a circle
         */
        DRAW_CIRCLE("Draw_circle"),
        /**
         * Draw a spiral
         */
        DRAW_SPIRAL("Draw_spiral"),
        /**
         * Draw a random pattern
         */
        DRAW_RANDOM("Draw_random"),
        /**
         * Unknown / not dealt intent
         */
        UNKNOWN("")
    } // End of enum class Intents


} // End of interface AssistantStub