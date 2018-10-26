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

package pylapp.tapster.client.android.ui.appintro

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v4.content.ContextCompat
import com.codemybrainsout.onboarder.AhoyOnboarderActivity
import com.codemybrainsout.onboarder.AhoyOnboarderCard
import pylapp.tapster.client.android.R


/**
 * Activity used to introduce the application.
 *
 * FIXME : break the strong relation between this class and AhoyOnboarderActivity library
 *
 * @author pylapp
 * @since 07/02/2018
 *
 * @version 1.0.0
 */
class AppIntroActivity : AhoyOnboarderActivity() {


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     *
     */
    companion object {
        /**
         * The key to use to check if the app intro screens have been displayed or not
         */
        const val PREFERENCES_KEY_HAS_BEEN_DISPLAYED = "AppintroActivity.preferences.PREFERENCES_KEY_HAS_BEEN_DISPLAYED"
    }


    /* ********************************** *
     * METHODS FROM AhoyOnboarderActivity *
     * ********************************** */

    /**
     * @param savedInstanceState -
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        // Define the screens
        val screen1 = AhoyOnboarderCard(resources.getString(R.string.appintro_screen1_title),
                resources.getString(R.string.appintro_screen1_description),
                R.drawable.appintro_image_intro)

        val screen2 = AhoyOnboarderCard(resources.getString(R.string.appintro_screen2_title),
                resources.getString(R.string.appintro_screen2_description),
                R.drawable.appintro_image)

        val screen3 = AhoyOnboarderCard(resources.getString(R.string.appintro_screen3_title),
                resources.getString(R.string.appintro_screen3_description),
                R.drawable.appintro_image)

        val screen4 = AhoyOnboarderCard(resources.getString(R.string.appintro_screen4_title),
                resources.getString(R.string.appintro_screen4_description),
                R.drawable.appintro_image)

        // No more colors in the bully world
        screen1.setBackgroundColor(R.color.black_transparent)
        screen2.setBackgroundColor(R.color.black_transparent)
        screen3.setBackgroundColor(R.color.black_transparent)
        screen4.setBackgroundColor(R.color.black_transparent)

        // Define the pages
        val pages = ArrayList<AhoyOnboarderCard>()
        pages.add(screen1)
        pages.add(screen2)
        pages.add(screen3)
        pages.add(screen4)

        for (page in pages) {
            page.setTitleColor(R.color.appIntroScreenTitle)
            page.setDescriptionColor(R.color.appIntroScreenDescription)
        }

        setFinishButtonTitle(resources.getString(R.string.appintro_finish_button))
        showNavigationControls(true)
        setColorBackground(R.color.appIntroScreenBackground)
        setFinishButtonDrawableStyle(ContextCompat.getDrawable(this, R.drawable.appintro_finish_button))

        setOnboardPages(pages)

    }

    /**
     * Triggered when the finish button has been clicked.
     * Terminates this activity
     */
    override fun onFinishButtonPressed() {

        // Never display this screens again
        val preferences = PreferenceManager.getDefaultSharedPreferences(AppIntroActivity@ this)
        preferences.edit().putBoolean(PREFERENCES_KEY_HAS_BEEN_DISPLAYED, true).apply()

        // Finish and return to the main activity
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()

    }

}