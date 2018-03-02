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

package pylapp.tapster.client.android.ui.settings

import android.app.Activity

/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with an object which can display the licenses of third party tools in use.
 * This pattern is used so as to avoid an activity to have too many dependencies and code about the way licenses are displayed.
 * A library may be used, so let's thing a step further in case of the death of this library.
 *
 * @author pylapp
 * @since 08/02/2018
 *
 * @version 1.0.0
 */
interface LicensesDisplayerStub {


    /* ******************* *
     * METHODS TO OVERRIDE *
     * ******************* */

    /**
     * Displays the license sin use
     *
     * @param context - The context to use, the Activity where the display starts
     */
    fun displayLicenses(context: Activity)

}