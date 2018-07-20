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


package pylapp.tapster.client.android.ui.taptargets

import android.app.Activity
import android.support.design.widget.TabLayout
import android.view.View
import android.view.ViewGroup

import com.getkeepsafe.taptargetview.TapTarget

import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub


/**
 * Class which builds commandTap target views so as to introduce features of the app.
 * See https://github.com/KeepSafe/TapTargetView
 *
 * FIXME : break the strong relation between this class and TapTargetView library
 *
 * @author pylapp
 * @since 07/02/2018
 *
 * @version 1.0.0
 */
class TapTargetViewBuilder {


    /* ******* *
     * METHODS *
     * ******* */

    /**
     * Builds a commandTap target to use to point to an element
     *
     * @param context - The context to use
     * @param element - The element on which the help must be placed
     * @return [TapTarget]  - The pointer
     */
    private fun buildTapTarget(context: Activity, element: Targets): TapTarget {

        var targetView: View? = null
        var targetTitleText: String? = null
        var targetDescriptionText: String? = null

        // Get the configuration (view and texts)

        when (element) {
        // The first tab, with the commands
            Targets.COMMANDS_TAB -> {
                val tabLayout = context.findViewById<TabLayout>(R.id.tl_client_screens)
                targetView = (tabLayout.getChildAt(0) as ViewGroup).getChildAt(0)
                targetTitleText = context.resources.getString(R.string.taptarget_commandtab_title)
                targetDescriptionText = context.resources.getString(R.string.taptarget_commandtab_description)
            }
        // The second tabs, with the assistant
            Targets.ASSISTANT_TAB -> {
                val tabLayout = context.findViewById<TabLayout>(R.id.tl_client_screens)
                // The commands tab, in 1st position, at index 0 can be disabled, so keep an eye on it
                val propertiesReader = FeaturesFactory().buildPropertiesReader()
                propertiesReader.loadProperties(context)
                targetView = when (propertiesReader
                        .readProperty(PropertiesReaderStub.ENABLE_GUI_COMMANDS)!!.toBoolean()) {
                    true -> (tabLayout.getChildAt(0) as ViewGroup).getChildAt(1)
                    false -> (tabLayout.getChildAt(0) as ViewGroup).getChildAt(0)
                }
                targetTitleText = context.resources.getString(R.string.taptarget_assistanttab_title)
                targetDescriptionText = context.resources.getString(R.string.taptarget_assistanttab_description)
            }
        // A command
            Targets.TAP_CELL -> {
                targetView = context.findViewById(R.id.tv_folded_swipe)
                targetTitleText = context.resources.getString(R.string.taptarget_tap_title)
                targetDescriptionText = context.resources.getString(R.string.taptarget_tap_description)
            }
        // A command button
            Targets.SWIPE_CELL_PROCESS_BUTTON -> {
                targetView = context.findViewById(R.id.bt_command_action_swipe)
                targetTitleText = context.resources.getString(R.string.taptarget_swipe_button_title)
                targetDescriptionText = context.resources.getString(R.string.taptarget_swipe_button_description)
            }
        // A command parameters field
            Targets.SWIPE_CELL_PARAMETER_FIELD -> {
                targetView = context.findViewById(R.id.et_params_swipe)
                targetTitleText = context.resources.getString(R.string.taptarget_swipe_params_title)
                targetDescriptionText = context.resources.getString(R.string.taptarget_swipe_params_description)
            }
        }

        // Build the pointer
        return FeaturesFactory().buildTapTargetsDisplayer()
                .buildPointer(targetView, targetTitleText, targetDescriptionText)

    }

    /**
     * Builds a sequence of commandTap targets to process.
     *
     * @param context - The activity which have the targets
     * @param targets - The element to point
     */
    fun buildSequenceOfTargetsAndStart(context: Activity, targets: List<Targets>) {

        // Build the list of TapTargets
        val tapTargets = ArrayList<TapTarget>()

        /*
        // Kotlin like Java
        for (target in targets) {
            val tapTarget = buildTapTarget(context, target)
            tapTargets.add(tapTarget)
        }
        */
        // Kotlin like a chief
        targets.asSequence().mapTo(tapTargets) { buildTapTarget(context, it) }

        // Build the sequence
        FeaturesFactory().buildTapTargetsDisplayer()
                .startNewSequence(context, tapTargets)

    }


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     *
     */
    companion object {
        /**
         * The key to use to check if the help has been displayed for the 1st tab
         */
        const val PREFERENCES_KEY_COMMANDS_TAB_POINTED = "TapTargetViewBuilder.preferences.PREFERENCES_KEY_COMMANDS_TAB_POINTED"
        /**
         * The key to use to check if the help has been displayed for a command
         */
        const val PREFERENCES_KEY_TAP_CELL_POINTED = "TapTargetViewBuilder.preferences.PREFERENCES_KEY_TAP_CELL_POINTED"
        /**
         * The key to use to check if the help has been displayed for a command's button
         */
        const val PREFERENCES_KEY_SWIPE_PROCESS_BUTTON_POINTED = "TapTargetViewBuilder.preferences.PREFERENCES_KEY_SWIPE_PROCESS_BUTTON_POINTED"
        /**
         * The key to use to check if the help has been displayed for a command's parameter field
         */
        const val PREFERENCES_KEY_SWIPE_PARAMETERS_POINTED = "TapTargetViewBuilder.preferences.PREFERENCES_KEY_SWIPE_PARAMETERS_POINTED"
        /**
         * The key to use to check if the help has been displayed for the 2nd tab
         */
        const val PREFERENCES_KEY_ASSISTANT_TAB_POINTED = "TapTargetViewBuilder.preferences.PREFERENCES_KEY_ASSISTANT_TAB_POINTED"
    }


    /* ********** *
     * INNER ENUM *
     * ********** */

    /**
     * The targets where help must me displayed
     */
    enum class Targets {
        /**
         * The first tab with the widgets
         */
        COMMANDS_TAB,
        /**
         * A command
         */
        TAP_CELL,
        /**
         * The process button of the command
         */
        SWIPE_CELL_PROCESS_BUTTON,
        /**
         * The parameter field of the command
         */
        SWIPE_CELL_PARAMETER_FIELD,
        /**
         * The second tab with the assistant
         */
        ASSISTANT_TAB
    } // End of enum Targets

}