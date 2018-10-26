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

package pylapp.tapster.client.android.ui.mainscreen;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import pylapp.tapster.client.android.R;
import pylapp.tapster.client.android.networks.HttpClientStub;
import pylapp.tapster.client.android.tools.Config;
import pylapp.tapster.client.android.tools.FeaturesFactory;
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub;
import pylapp.tapster.client.android.ui.permissions.PermissionsManagerStub;

/**
 * Abstract fragment for commands to send to the robot
 *
 * @author pylapp
 * @version 2.0.0
 * @since 05/02/2018
 */
public abstract class AbstractCommandsFragment extends Fragment {


    /**
     * The object to use to send HTTP requests
     */
    HttpClientStub mHttpClient;

    /**
     * The object which checks the permissions granted to the app
     * Permissions may managed, specially for HTTP requests.
     */
    PermissionsManagerStub mPermissionsManager;

    /**
     * Default constructor
     */
    public AbstractCommandsFragment() {
        super();
    }

    /**
     * @param savedInstanceState -
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    /*
    /**
     * @param inflater           -
     * @param container          -
     * @param savedInstanceState -
     * @return View -
     * /
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initHttpClient();
        return inflater.inflate(R.layout.fragment_commands_moves, container, false);
    }*/

    /**
     *
     */
    @Override
    public void onResume() {
        super.onResume();
        initListeners();
        mPermissionsManager = new FeaturesFactory().buildPermissionsManager();
        askForPermissionsIfNeeded();
    }

    /**
     * Asks for permissions if needed
     */
    private void askForPermissionsIfNeeded() {
        Activity activity = getActivity();
        if (activity != null &&
                !mPermissionsManager.isPermissionGranted(activity, Manifest.permission.INTERNET)) {
            mPermissionsManager.askForPermissions(activity, null,
                    Manifest.permission.INTERNET);
        }
    }

    /**
     * Initializes the HTTP client which will be used to send HTTP requests
     */
    void initHttpClient() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String serverProtocol = preferences.getString(Config.PREFERENCES_ROBOT_PROTOCOL,
                getContext().getString(R.string.default_value_server_protocol));
        String serverIp = preferences.getString(Config.PREFERENCES_ROBOT_IP,
                getContext().getString(R.string.default_value_server_ip_address));
        String serverPort = preferences.getString(Config.PREFERENCES_ROBOT_PORT,
                getContext().getString(R.string.default_value_server_port));

        mHttpClient = new FeaturesFactory().buildHttpClient();
        mHttpClient.setServerIpAddress(serverIp);
        mHttpClient.setServerProtocol(serverProtocol);
        mHttpClient.setServerPort(serverPort);

    }

    /**
     * Updates the HTTP client which will be used to send HTTP requests,
     * with values defined in preferences. Checks also the permissions.
     */
    void updateHttpClient() {

        askForPermissionsIfNeeded();

        // Undefined?
        if (mHttpClient == null) {
            initHttpClient();
            return;
        }

        // Just need to update it
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String serverProtocol = preferences.getString(Config.PREFERENCES_ROBOT_PROTOCOL,
                getContext().getString(R.string.default_value_server_protocol));
        String serverIp = preferences.getString(Config.PREFERENCES_ROBOT_IP,
                getContext().getString(R.string.default_value_server_ip_address));
        String serverPort = preferences.getString(Config.PREFERENCES_ROBOT_PORT,
                getContext().getString(R.string.default_value_server_port));

        mHttpClient.setServerIpAddress(serverIp);
        mHttpClient.setServerProtocol(serverProtocol);
        mHttpClient.setServerPort(serverPort);

    }


    /**
     * Initializes the listeners of the folding cells, to define for each implementation
     * of this fragment
     */
    protected abstract void initListeners();

    /**
     * @return boolean - True if the commandTap targets feature is enabled, false otherwise
     */
    boolean isTapTargetsEnabled() {
        PropertiesReaderStub propertiesReader = new FeaturesFactory().buildPropertiesReader();
        propertiesReader.loadProperties(getActivity());
        return "true".equals(propertiesReader.readProperty(PropertiesReaderStub.ENABLE_GUI_DISPLAY_TAPTARGETS));
    }


}
