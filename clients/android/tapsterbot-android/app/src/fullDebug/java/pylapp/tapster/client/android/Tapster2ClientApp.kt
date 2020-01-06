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

package pylapp.tapster.client.android

import android.app.Application
import android.content.Context


import org.acra.ACRA
import org.acra.annotation.AcraCore
import org.acra.annotation.AcraMailSender
import org.acra.annotation.AcraNotification
import org.acra.data.StringFormat

import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub


/**
 * Main class of the application.
 * Defined here so as to deal with bug reports and metrics using ACRA library.
 * Defined also to load the Snips.ai client.
 *
 * See: https://github.com/ACRA/acra
 * See: https://snips.gitbook.io/documentation/installing-snips/on-android/using-the-platform-on-android
 *
 * @author Pierre-Yves Lapersonne
 * @since 13/02/2018
 *
 * @version 3.0.0
 */

@AcraCore(
        buildConfigClass = BuildConfig::class,
        reportFormat = StringFormat.JSON
)

@AcraMailSender(
        mailTo = "pylapp.pylapp+tapsterbot@gmail.com" // TODO Fill this value
)

@AcraNotification(
        resText = R.string.acra_crash_description,
        resTitle = R.string.acra_crash_title,
        resChannelName = R.string.acra_notification_channel
)

class Tapster2ClientApp : Application() {


    /* ********************************** *
     * METHODS INHERITED FROM Application *
     * ********************************** */

    /**
     *
     */
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // The following line triggers the initialization of the feature managing crashes of the app
        if (isCrashReportManagementEnabled()) {
            ACRA.init(this)
        }
    }


    /* ************* *
     * INNER METHODS *
     * ************* */

    /**
     * @returns [Boolean] - True if crash report management feature is enabled, false otherwise
     */
    private fun isCrashReportManagementEnabled(): Boolean {
        val properties = FeaturesFactory().buildPropertiesReader()
        properties.loadProperties(this)
        return properties.readProperty(PropertiesReaderStub.ENABLE_CRASH_REPORT_MANAGEMENT)!!.toBoolean()
    }

}