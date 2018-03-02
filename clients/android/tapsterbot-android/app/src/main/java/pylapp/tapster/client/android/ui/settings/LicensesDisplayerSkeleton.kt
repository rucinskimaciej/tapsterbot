package pylapp.tapster.client.android.ui.settings

import android.app.Activity
import de.psdev.licensesdialog.LicensesDialog
import de.psdev.licensesdialog.licenses.ApacheSoftwareLicense20
import de.psdev.licensesdialog.licenses.License
import de.psdev.licensesdialog.licenses.MITLicense
import de.psdev.licensesdialog.model.Notice
import de.psdev.licensesdialog.model.Notices
import pylapp.tapster.client.android.R

/**
 * Skeleton based on design pattern Bridge which provides the definitions of methods to call
 * so as to deal with an object which can make notifications to the user interface.
 * This pattern is used so as to avoid the assistant to have too many dependencies and code about the UI.
 *
 * @author pylapp
 * @since 08/02/2018
 *
 * @version 1.0.0
 */
class LicensesDisplayerSkeleton : LicensesDisplayerStub {

    /**
     * Displays the license sin use
     *
     * @param context - The context to use, the Activity where the display starts
     */
    override fun displayLicenses(context: Activity) {

        // The data to display

        val names = context.resources.getStringArray(R.array.credits_names)
        val copyrights = context.resources.getStringArray(R.array.credits_copyrights)
        val licenses = context.resources.getStringArray(R.array.credits_licenses)
        val urls = context.resources.getStringArray(R.array.credits_url)

        // The dialog

        val notices = Notices()

        // Assuming the developer of this feature is not a jackass and has written the same
        // amount of entries in each array
        for (i in 0..6) {
            val licence: License = when (licenses[i]) {
                "Apache 2.0" -> ApacheSoftwareLicense20()
                "MIT" -> MITLicense()
                else -> ApacheSoftwareLicense20() // FIXME Duh
            }
            notices.addNotice(Notice(names[i], urls[i], copyrights[i], licence))
        }

        // Display them

        LicensesDialog.Builder(context)
                .setNotices(notices)
                .setIncludeOwnLicense(false)
                .build()
                .show()

    }

}