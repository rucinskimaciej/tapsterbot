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

package pylapp.tapster.client.android.ui.mainscreen

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View

import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub
import pylapp.tapster.client.android.ui.appintro.AppIntroActivity
import pylapp.tapster.client.android.ui.settings.SettingsActivity
import pylapp.tapster.client.android.ui.taptargets.TapTargetViewBuilder


/**
 * Main activity of the application.
 * This version possesses only widgets related to Tapster, and not Snips.
 *
 * @constructor Creates a FragmentActivity
 * @author pylapp
 * @since 01/02/2018
 *
 * @version 2.0.0
 */
class MainActivity : AppCompatActivity() {


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     * Companion defining only a constant for the audio permission
     */
    companion object {

        /**
         * The request code of the [AppIntroActivity] started from this activity
         */
        private const val APP_INTRO_REQUEST = 1

    } // End of companion object


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The [TabLayout] which will contain the tabs
     */
    private var mTabLayout: TabLayout? = null

    /**
     * The [ViewPager] in use
     */
    private var mViewPager: ViewPager? = null

    /**
     * The [Fragment] for the commands panel with widgets for moves
     */
    private var mMovesCommandsFragment: Fragment? = null

    /**
     * The [Fragment] for the commands panel with widgets for configuration
     */
    private var mConfigurationCommandsFragment: Fragment? = null

    /**
     * The [Fragment] for the commands panel containing widgets to draw things through the robot
     */
    private var mDrawsCommandsFragment: Fragment? = null


    /* ******* *
     * METHODS *
     * ******* */

    /**
     * Triggered when the activity is being created
     *
     * @param savedInstanceState - The bundle with saved data
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        // Defines the "good" theme instead of the splash screen's theme
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)

        // Deal with permissions
        askForPermissionsIfNeeded()

        // Check in properties if the app intro feature is enabled
        // Check also in shared preferences (in case the screens have been displayed previously)
        val propertiesReader = FeaturesFactory().buildPropertiesReader()
        propertiesReader.loadProperties(MainActivity@ this)
        val preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity@ this)

        if (propertiesReader
                        .readProperty(PropertiesReaderStub.ENABLE_GUI_DISPLAY_INTROSCREENS)!!.toBoolean()
                && !preferences
                        .getBoolean(AppIntroActivity.PREFERENCES_KEY_HAS_BEEN_DISPLAYED, false)) {
            startAppIntro()
        } else {
            prepareActivityContent()
        }

    }

    /**
     * Triggered when options menu is being created
     * @param menu -
     * @return [Boolean] -
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    /**
     * Triggered when an option in the menu has been selected
     * @param item -
     * @return [Boolean] -
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.display_screen_settings -> startSettingsScreen()
        }
        return true
    }

    /**
     * Triggered when a certain activity started from here and with a waited result has finished
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (requestCode == APP_INTRO_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                prepareActivityContent()
                manageTapTargets()
            }
        }
    }


    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Asks for permissions if needed
     */
    private fun askForPermissionsIfNeeded() {

        val permissionsGranter = FeaturesFactory().buildPermissionsManager()

        val listPermissions = ArrayList<String>()

        if (!permissionsGranter.isPermissionGranted(this, Manifest.permission.INTERNET)) {
            listPermissions.add(Manifest.permission.INTERNET)
        }

        if (listPermissions.size > 0) {
            val varargsPermissions = (listPermissions as List<String>).toTypedArray()
            permissionsGranter.askForPermissions(this, null, *varargsPermissions)
        }

    }

    /**
     * Displays the app introduction screens
     */
    private fun startAppIntro() {
        val appIntroIntent = Intent(MainActivity@ this, AppIntroActivity::class.java)
        startActivityForResult(appIntroIntent, APP_INTRO_REQUEST)
    }

    /**
     * Prepares the content of this activity, i.e. defines the content view, the action bar
     * and the tabs
     */
    private fun prepareActivityContent() {

        // Content view, action bar, tabs
        setContentView(R.layout.activity_main_with_tabs)
        supportActionBar?.elevation = 0f

        // View pager with fragments
        setupViewPager()
        mTabLayout = findViewById(R.id.tl_client_screens)
        mTabLayout!!.setupWithViewPager(mViewPager)

        // Tabs icons
        mTabLayout!!.getTabAt(0)?.setIcon(R.mipmap.ic_commands_move)
        mTabLayout!!.getTabAt(1)?.setIcon(R.mipmap.ic_commands_drawings)
        mTabLayout!!.getTabAt(2)?.setIcon(R.mipmap.ic_commands_configuration)

    }

    /**
     * Displays the settings screen
     */
    private fun startSettingsScreen() {
        val settingsIntent = Intent(MainActivity@ this, SettingsActivity::class.java)
        startActivity(settingsIntent)
    }

    /**
     * Deals with tap targets
     */
    private fun manageTapTargets() {

        val propertiesReader = FeaturesFactory().buildPropertiesReader()
        propertiesReader.loadProperties(MainActivity@ this)
        val preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity@ this)

        // FIXME Fu***ng dirty, must use instead one flag per sequence
        // The commandTap targets are in a feature which can be disabled
        if (propertiesReader.readProperty(PropertiesReaderStub.ENABLE_GUI_DISPLAY_TAPTARGETS)!!.toBoolean()
                && !preferences.getBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_MOVES_TAB_POINTED, false)
                && !preferences.getBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_DRAWINGS_TAB_POINTED, false)
                && !preferences.getBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_SETTINGS_TAB_POINTED, false)) {

            val builder = TapTargetViewBuilder()
            val pointers = ArrayList<TapTargetViewBuilder.Targets>()

            // The command panel is in a feature which can be disabled
            if (propertiesReader.readProperty(PropertiesReaderStub.ENABLE_GUI_COMMANDS)!!.toBoolean()) {
                pointers.add(TapTargetViewBuilder.Targets.MOVES_TAB)
                pointers.add(TapTargetViewBuilder.Targets.DRAWINGS_TAB)
                pointers.add(TapTargetViewBuilder.Targets.SETTINGS_TAB)
                preferences.edit()
                        .putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_MOVES_TAB_POINTED, true)
                        .putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_DRAWINGS_TAB_POINTED, true)
                        .putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_COMMANDS_SETTINGS_TAB_POINTED, true)
                        .apply()
            }

            if (pointers.size > 0) {
                builder.buildSequenceOfTargetsAndStart(MainActivity@ this, pointers)
            }

        }

    }

    /**
     * Defines and configures the view pager
     */
    private fun setupViewPager() {

        mViewPager = findViewById<View>(R.id.vp_assistant_main_screen) as ViewPager
        mViewPager!!.offscreenPageLimit = 2

        val adapter = ViewPagerAdapter(supportFragmentManager)

        val propertiesReader: PropertiesReaderStub = FeaturesFactory().buildPropertiesReader()
        propertiesReader.loadProperties(MainActivity@ this)

        if (propertiesReader.readProperty(PropertiesReaderStub.ENABLE_GUI_COMMANDS)!!.toBoolean()) {
            mMovesCommandsFragment = MovesCommandsFragment()
            adapter.addFragment(mMovesCommandsFragment as Fragment, resources.getString(R.string.tab_title_commands_moves))
            mDrawsCommandsFragment = DrawCommandsFragment()
            adapter.addFragment(mDrawsCommandsFragment as Fragment, resources.getString(R.string.tab_title_commands_drawings))
            mConfigurationCommandsFragment = ConfigurationCommandsFragment()
            adapter.addFragment(mConfigurationCommandsFragment as Fragment, resources.getString(R.string.tab_title_commands_configuration))
        }

        mViewPager!!.adapter = adapter

    }

}
