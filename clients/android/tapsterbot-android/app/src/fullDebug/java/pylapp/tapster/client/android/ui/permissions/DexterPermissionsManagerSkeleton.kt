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

package pylapp.tapster.client.android.ui.permissions

import android.app.Activity
import android.app.AlertDialog
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import pylapp.tapster.client.android.R


/**
 * Skeleton based on design pattern Bridge which provides the declarations of methods to call
 * so as to deal with with an object or a library which deals with permissions, grant dialogs, etc.
 * This pattern is used so as to avoid an activity to have too many dependencies and code about the way
 * permissions are asked, granted and managed.
 * A library is used, so let's thing a step further in case of the death of this library.
 *
 * @author Pierre-Yves Lapersonne
 * @since 21/02/2018
 *
 * @version 1.0.1
 */
class DexterPermissionsManagerSkeleton : PermissionsManagerStub {

    /**
     * Requests the grant to use a permission
     *
     * @param activity - The activity where the dialogs are come from
     * @param callback - A callback to trigger if needed
     * @param permission - The permission to grant
     * @param title - The title of the message which can be displayed
     * @param message - More details about the use of the permission
     */
    override fun askForPermission(activity: Activity, callback: (() -> Unit?)?,
                                  permission: String, title: String, message: String) {

        // The listener for rationale dialog, i.e to explain to the user why this permission
        // is needed
        val dialogPermissionListener = DialogOnDeniedPermissionListener.Builder
                .withContext(activity)
                .withTitle(title)
                .withMessage(message)
                .withButtonText(android.R.string.ok)
                .build()

        // Display the dialog
        Dexter.withActivity(activity)
                .withPermission(permission)
                .withListener(dialogPermissionListener)
                .check()

    }

    /**
     * Requests the grants of all these permissions
     *
     * @param activity - The activity where the dialogs are come from
     * @param callback - A callback to trigger if needed
     * @param permissions - The permission to grant
     */
    override fun askForPermissions(activity: Activity, callback: (() -> Unit?)?,
                                   vararg permissions: String) {

        Dexter.withActivity(activity)
                .withPermissions(*permissions)
                .withListener(object : MultiplePermissionsListener {

                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        /* ... */
                    }

                    override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>
                                                                    , token: PermissionToken) {
                        //token.continuePermissionRequest()
                        for (permission in permissions) {
                            showPermissionRationale(activity, permission, token)
                        }
                    }

                })
                .onSameThread()
                .check()

    }

    /**
     * Checks if the permission is granted or not
     *
     * @param activity - The activity where the dialogs are come from
     * @param permission - The permission to check
     * @return [Boolean] - True if granted, false otherwise
     */
    override fun isPermissionGranted(activity: Activity, permission: String): Boolean {
        return ContextCompat
                .checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED
    }

    /**
     * Show an alter with more details about the permission
     *
     * @param activity - The activity where the dialogs are come from
     * @param permission - The permission request concerned by the rationale
     * @param token - the bundle containing interesting info about the managed permission
     */
    private fun showPermissionRationale(activity: Activity, permission: PermissionRequest,
                                        token: PermissionToken) {

        val rationaleTitle = when (permission.name) {
            android.Manifest.permission.RECORD_AUDIO -> activity.getString(R.string.permission_recordaudio_title)
            android.Manifest.permission.INTERNET -> activity.getString(R.string.permission_internet_title)
            android.Manifest.permission.READ_EXTERNAL_STORAGE -> activity.getString(R.string.permission_readexternalstorage_title)
            else -> activity.getString(R.string.permission_title)
        }

        val rationaleMessage = when (permission.name) {
            android.Manifest.permission.RECORD_AUDIO -> activity.getString(R.string.permission_recordaudio_message)
            android.Manifest.permission.INTERNET -> activity.getString(R.string.permission_internet_message)
            android.Manifest.permission.READ_EXTERNAL_STORAGE -> activity.getString(R.string.permission_readexternalstorage_message)
            else -> activity.getString(R.string.permission_message)
        }

        AlertDialog.Builder(activity)
                .setTitle(rationaleTitle)
                .setMessage(rationaleMessage)
                .setNegativeButton(android.R.string.cancel) { dialog, _
                    ->
                    dialog.dismiss()
                    token.cancelPermissionRequest()
                }
                .setPositiveButton(android.R.string.ok) { dialog, _
                    ->
                    dialog.dismiss()
                    token.continuePermissionRequest()
                }
                .setOnDismissListener { _ -> token.cancelPermissionRequest() }
                .show()

    }

}