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
import android.content.Context
import android.os.*
import android.preference.PreferenceManager
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import pylapp.tapster.client.android.R
import pylapp.tapster.client.android.assistants.AssistantStub
import pylapp.tapster.client.android.tools.Config
import pylapp.tapster.client.android.tools.FeaturesFactory
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub
import pylapp.tapster.client.android.ui.permissions.PermissionsManagerStub

/**
 * Fragment for assistant screen, will be included in the main screen.
 *
 * @author pylapp
 * @since 05/02/2018
 * @version 1.0.0
 */
class AssistantFragment : Fragment() {


    /* **************** *
     * COMPANION OBJECT *
     * **************** */

    /**
     * Companion containing the stub if the Assistant
     */
    companion object {

        /**
         * The assistant, chatbot-like, to use
         */
        private var assistant: AssistantStub? = null

    } // End of companion object


    /* ********** *
     * ATTRIBUTES *
     * ********** */

    /**
     * The adapter for the list of messages
     */
    private var mListViewMessageAdapter: ListOfAssistantDialogueMessagesAdapter? = null

    /**
     * The object which managed the permissions
     */
    private var mPermissionsGranter: PermissionsManagerStub? = null


    /* ********************* *
     * METHODS FROM Fragment *
     * ********************* */

    /**
     *
     * @param savedInstanceState -
     */
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)

        // Ask for permissions
        mPermissionsGranter = FeaturesFactory().buildPermissionsManager()
        askForPermissionsIfNeeded()

        // Able to record audio (better if we wanna talk to the assistant) ?
        if (!mPermissionsGranter!!.isPermissionGranted(activity as Activity,
                        Manifest.permission.RECORD_AUDIO)) {
            Toast.makeText(activity, R.string.error_permission_not_granted_recordaudio,
                    Toast.LENGTH_LONG).show()
            return
        }

        // Able the read storage (better if we wanna load assistant's assets)
        if (!mPermissionsGranter!!.isPermissionGranted(activity as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(activity, R.string.error_permission_not_granted_storage,
                    Toast.LENGTH_LONG).show()
            return
        }

    }

    /**
     *
     * @param inflater -
     * @param container -
     * @param savedInstanceState -
     * @return View -
     */
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_assistant, container, false)
    }

    /**
     * Will check if the permissions have been granted.
     * If need, builds the assistant.
     */
    override fun onResume() {

        super.onResume()

        // Ask for permissions
        mPermissionsGranter = FeaturesFactory().buildPermissionsManager()
        askForPermissionsIfNeeded()

        // Before initializing the assistant, check if the user has granted the uses we need
        if (mPermissionsGranter!!.isPermissionGranted(activity as Activity,
                        Manifest.permission.RECORD_AUDIO)
                && mPermissionsGranter!!.isPermissionGranted(activity as Activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
            assistant = FeaturesFactory().buildAssistant()
            startAssistant()
        }

    }


    /* ************* *
     * OTHER METHODS *
     * ************* */

    /**
     * Asks for permissions if needed
     */
    private fun askForPermissionsIfNeeded() {

        val listPermissions = ArrayList<String>()
        if (!mPermissionsGranter!!.isPermissionGranted(activity as Activity, Manifest.permission.RECORD_AUDIO)) {
            listPermissions.add(Manifest.permission.RECORD_AUDIO)
        }
        if (!mPermissionsGranter!!.isPermissionGranted(activity as Activity, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            listPermissions.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (listPermissions.size > 0) {
            val varargsPermissions = (listPermissions as List<String>).toTypedArray()
            mPermissionsGranter!!.askForPermissions(activity as Activity, null, *varargsPermissions)
        }

    }

    /**
     * Starts the assistant and update the GUI
     */
    private fun startAssistant() {

        // Set up the layouts
        val loadingLayout = activity!!.findViewById<View>(R.id.ll_assistant_loading)
        loadingLayout.visibility = View.VISIBLE
        val dialogLayout = activity!!.findViewById<View>(R.id.ll_assistant_dialogue)
        dialogLayout.visibility = View.GONE

//        // Note: With Snips 0.57.3, if assistant loaded outside UI Thread, callbacks won't be triggered!
//        object : Thread() {
//            override fun run() {
//
//                // Start the assistant
//
//                Looper.prepare()

        assistant!!.startAssistant(activity as Activity, {
            activity!!.runOnUiThread {
                Toast.makeText(activity,
                        getString(R.string.snips_service_started),
                        Toast.LENGTH_LONG).show()
            }
        })

        // Update the GUI and then start a new dialogue session
        activity!!.runOnUiThread {

            // Is the vibrations disabled?
            val properties = FeaturesFactory().buildPropertiesReader()
            properties.loadProperties(activity!!)
            if (properties.readProperty(PropertiesReaderStub.ENABLE_VIBRATIONS)!!.toBoolean()) {

                // Vibrations enabled in settings?
                val preferences = PreferenceManager.getDefaultSharedPreferences(context)
                if (preferences.getBoolean(Config.PREFERENCES_ASSISTANT_VIBRATIONS, true)) {

                    // Notify to the user the assistant has been loaded
                    val vibrator = activity!!.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
                    val durationInMs = 300L
                    val amplitude = 10
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        vibrator.vibrate(VibrationEffect.createOneShot(durationInMs, amplitude))
                    } else {
                        vibrator.vibrate(durationInMs)
                    }

                }

            }

            loadingLayout.visibility = View.GONE
            dialogLayout.visibility = View.VISIBLE

            mListViewMessageAdapter = ListOfAssistantDialogueMessagesAdapter()
            val listOfMessages: ListView = activity!!.findViewById<View>(R.id.lv_assistant_dialogue_messages) as ListView
            listOfMessages.adapter = mListViewMessageAdapter

        }

//            }
//        }.start()

    } // End of private fun startAssistant()

}
