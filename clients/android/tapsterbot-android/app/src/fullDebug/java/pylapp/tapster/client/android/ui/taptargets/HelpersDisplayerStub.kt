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

package pylapp.tapster.client.android.ui.taptargets

import android.app.Activity
import android.view.View

/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with an object which can display commandTap targets / pointers / elements as showcases/demo
 * to introduce features or components of the GUI.
 * This pattern is used so as to avoid an activity to have too many dependencies and code about the way
 * tap targets are displayed.
 * A library is used, so let's thing a step further in case of the death of this library.
 *
 * @author Pierre-Yves Lapersonne
 * @since 08/02/2018
 *
 * @version 1.0.0
 */
interface HelpersDisplayerStub {


    /* ******************* *
     * METHODS TO OVERRIDE *
     * ******************* */

    /**
     * Builds a commandTap target / pointer to a view, with title and descriptions
     *
     * @param view - The target on which the pointer must be displayed
     * @param title - The title or main text to display
     * @param description - A secondary text if needed
     * @return T - The just-freshly-built pointer
     */
    fun <T : Any> buildPointer(view: View, title: String, description: String?): T

    /**
     * Builds a sequence of commandTap targets and start the display of this sequence
     *
     * @param context - The activity in use
     * @param targets - The element which have been built and will be displayed
     */
    fun <T : Any> startNewSequence(context: Activity, targets: List<T>)

}