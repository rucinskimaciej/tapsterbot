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

package pylapp.tapster.client.android.ui.permissions

import android.app.Activity


/**
 * Stub based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with an object or a library which deals with permissions, grant dialogs, etc.
 * This pattern is used so as to avoid an activity to have too many dependencies and code about the way
 * permissions are asked, granted and managed.
 * A library is used, so let's thing a step further in case of the death of this library.
 *
 * @author pylapp
 * @since 21/02/2018
 *
 * @version 1.0.0
 */
interface PermissionsManagerStub {


    /* ******************* *
     * METHODS TO OVERRIDE *
     * ******************* */

    /**
     * Requests the grant to use a permission
     *
     * @param activity - The activity where the dialogs are come from
     * @param callback - A callback to trigger if needed
     * @param permission - The permission to grant
     * @param title - The title of the message which can be displayed
     * @param message - More details about the use of the permission
     */
    fun askForPermission(activity: Activity, callback: (() -> Unit?)?, permission: String,
                         title: String, message: String)

    /**
     * Requests the grants of all these permissions
     *
     * @param activity - The activity where the dialogs are come from
     * @param callback - A callback to trigger if needed
     * @param permissions - The permission to grant
     */
    fun askForPermissions(activity: Activity, callback: (() -> Unit?)?, vararg permissions: String)

    /**
     * Checks if the permission is granted or not
     *
     * @param activity - The activity where the dialogs are come from
     * @param permission - The permission to check
     * @return [Boolean] - True if granted, false otherwise
     */
    fun isPermissionGranted(activity: Activity, permission: String): Boolean

}