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

package pylapp.tapster.client.android.ui.mainscreen;

import android.Manifest;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pylapp.tapster.client.android.R;
import pylapp.tapster.client.android.networks.HttpClientStub;
import pylapp.tapster.client.android.tools.Config;
import pylapp.tapster.client.android.tools.FeaturesFactory;
import pylapp.tapster.client.android.tools.properties.PropertiesReaderStub;
import pylapp.tapster.client.android.ui.permissions.PermissionsManagerStub;
import pylapp.tapster.client.android.ui.taptargets.TapTargetViewBuilder;

/**
 * Fragment for the commands panel.
 *
 * @author pylapp
 * @version 1.0.0
 * @since 05/02/2018
 */
// FIXME Too long / big / heavy Fragment, split it to several shorter fragments
public class CommandsFragment extends Fragment {


    /**
     * The object to use to send HTTP requests
     */
    private HttpClientStub mHttpClient;

    /**
     * The object which checks the permissions granted to the app
     * Permissions may managed, specially for HTTP requests.
     */
    private PermissionsManagerStub mPermissionsManager;

    /**
     * Default constructor
     */
    public CommandsFragment() {
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

    /**
     * @param inflater           -
     * @param container          -
     * @param savedInstanceState -
     * @return View -
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initHttpClient();
        return inflater.inflate(R.layout.fragment_commands, container, false);
    }

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
        if (!mPermissionsManager.isPermissionGranted(getActivity(), Manifest.permission.INTERNET)) {
            mPermissionsManager.askForPermissions(getActivity(), null,
                    Manifest.permission.INTERNET);
        }
    }

    /**
     * Initializes the HTTP client which will be used to send HTTP requests
     */
    private void initHttpClient() {

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
    private void updateHttpClient() {

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
     * Initializes the listeners of the folding cells
     */
    private void initListeners() {

        // The cell for the get-position feature
        initGetPositionListener();

        // The cell for the set-position feature
        initSetPositionListener();

        // The cell for the get-angles feature
        initGetAnglesListener();

        // The cell for the set-angles feature
        initSetAnglesListener();

        // The cell for the get-status feature
        initGetStatusListener();

        // The cell for the get-contactz feature
        initGetContactZlistener();

        // The cell for the reset feature
        initResetListener();

        // The cell for the dance feature
        initDanceListener();

        // The cell for the stop-dance feature
        initStopDanceListener();

        // The cell for the swipe feature
        initSwipeListener();

        // The cell for the tap feature
        initTapListener();

    }

    /**
     * @return boolean - True if the commandTap targets feature is enabled, false otherwise
     */
    private boolean isTapTargetsEnabled() {
        PropertiesReaderStub propertiesReader = new FeaturesFactory().buildPropertiesReader();
        propertiesReader.loadProperties(getActivity());
        return "true".equals(propertiesReader.readProperty(PropertiesReaderStub.ENABLE_GUI_DISPLAY_TAPTARGETS));
    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to get position command
     */
    private void initGetPositionListener() {

        // Get the folding cell
        final FoldingCell fcCommandGetPosition = getActivity().findViewById(R.id.fc_command_getposition);

        // The listener to open / close it
        fcCommandGetPosition.setOnClickListener(v -> {

            fcCommandGetPosition.toggle(false);

            // The action button
            Button processButton = fcCommandGetPosition.findViewById(R.id.bt_command_action_getposition);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandGetPosition(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }); // End of processButton.setOnClickListener

        }); // End of fcCommandGetPosition.setOnClickListener

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to set position command
     */
    private void initSetPositionListener() {

        // Get the folding cell
        final FoldingCell fcCommandSetPosition = getActivity().findViewById(R.id.fc_command_setposition);

        // The listener to open / close it
        fcCommandSetPosition.setOnClickListener(v -> {

            fcCommandSetPosition.toggle(false);

            // The action button
            Button processButton = fcCommandSetPosition.findViewById(R.id.bt_command_action_setposition);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandSetPosition.findViewById(R.id.et_params_setposition);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_PARAMETERS_3)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();
                } else {
                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);
                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandSetPosition(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    new HttpClientStub.HttpClientCallback() {
                                        @Override
                                        public void onSuccess(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }

                                        @Override
                                        public void onFailure(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }
                                    });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }); // End of processButton.setOnClickListener

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to get angles command
     */
    private void initGetAnglesListener() {

        // Get the folding cell
        final FoldingCell fcCommandGetAngles = getActivity().findViewById(R.id.fc_command_getangles);

        // The listener to open / close it
        fcCommandGetAngles.setOnClickListener(v -> {

            fcCommandGetAngles.toggle(false);

            // The action button
            Button processButton = fcCommandGetAngles.findViewById(R.id.bt_command_action_getangles);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandGetAngles(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to set angles command
     */
    private void initSetAnglesListener() {

        // Get the folding cell
        final FoldingCell fcCommandSetAngles = getActivity().findViewById(R.id.fc_command_setangles);

        // The listener to open / close it
        fcCommandSetAngles.setOnClickListener(v -> {

            fcCommandSetAngles.toggle(false);

            // The action button
            Button processButton = fcCommandSetAngles.findViewById(R.id.bt_command_action_setangles);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandSetAngles.findViewById(R.id.et_params_setangles);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_PARAMETERS_3)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();
                } else {
                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);
                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandSetAngles(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    new HttpClientStub.HttpClientCallback() {
                                        @Override
                                        public void onSuccess(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }

                                        @Override
                                        public void onFailure(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }
                                    });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }); // End of processButton.setOnClickListener

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to get status command
     */
    private void initGetStatusListener() {

        // Get the folding cell
        final FoldingCell fcCommandGetStatus = getActivity().findViewById(R.id.fc_command_getstatus);

        // The listener to open / close it
        fcCommandGetStatus.setOnClickListener(v -> {

            fcCommandGetStatus.toggle(false);

            // The action button
            Button processButton = fcCommandGetStatus.findViewById(R.id.bt_command_action_getstatus);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandGetStatus(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to get contact command
     */
    private void initGetContactZlistener() {

        // Get the folding cell
        final FoldingCell fcCommandGetContactZ = getActivity().findViewById(R.id.fc_command_getcontactz);

        // The listener to open / close it
        fcCommandGetContactZ.setOnClickListener(v -> {

            fcCommandGetContactZ.toggle(false);

            // The action button
            Button processButton = fcCommandGetContactZ.findViewById(R.id.bt_command_action_getcontactz);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandGetContactPoint(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to reset command
     */
    private void initResetListener() {

        // Get the folding cell
        final FoldingCell fcCommandReset = getActivity().findViewById(R.id.fc_command_reset);

        // The listener to open / close it
        fcCommandReset.setOnClickListener(v -> {

            fcCommandReset.toggle(false);

            // The action button
            Button processButton = fcCommandReset.findViewById(R.id.bt_command_action_reset);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandReset(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to dance command
     */
    private void initDanceListener() {

        // The cell for the dance feature
        final FoldingCell fcCommandDance = getActivity().findViewById(R.id.fc_command_dance);

        fcCommandDance.setOnClickListener(v -> {

            fcCommandDance.toggle(false);

            // The action button
            Button processButton = fcCommandDance.findViewById(R.id.bt_command_action_dance);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandDance(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to stop dance command
     */
    private void initStopDanceListener() {

        // The cell for the stop dance feature
        final FoldingCell fcCommandStopDance = getActivity().findViewById(R.id.fc_command_stopdance);

        fcCommandStopDance.setOnClickListener(v -> {

            fcCommandStopDance.toggle(false);

            // The action button
            Button processButton = fcCommandStopDance.findViewById(R.id.bt_command_action_stopdance);
            processButton.setOnClickListener(v2 -> {
                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandStopDance(new HttpClientStub.HttpClientCallback() {
                            @Override
                            public void onSuccess(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }

                            @Override
                            public void onFailure(@Nullable String message) {
                                getActivity().runOnUiThread(
                                        () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                );
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            });

        });

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to swipe command
     */
    private void initSwipeListener() {

        // Get the folding cell
        final FoldingCell fcCommandSwipe = getActivity().findViewById(R.id.fc_command_swipe);

        // Listener to open / close the cell
        fcCommandSwipe.setOnClickListener(v -> {

            fcCommandSwipe.toggle(false);

            // Tap targets to display ?
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

            if (isTapTargetsEnabled()
                    && !preferences.getBoolean(TapTargetViewBuilder.PREFERENCES_KEY_SWIPE_PARAMETERS_POINTED, false)
                    && !preferences.getBoolean(TapTargetViewBuilder.PREFERENCES_KEY_SWIPE_PROCESS_BUTTON_POINTED, false)) {
                TapTargetViewBuilder builder = new TapTargetViewBuilder();
                List<TapTargetViewBuilder.Targets> pointers = new ArrayList<>();
                pointers.add(TapTargetViewBuilder.Targets.TAP_CELL);
                pointers.add(TapTargetViewBuilder.Targets.SWIPE_CELL_PARAMETER_FIELD);
                pointers.add(TapTargetViewBuilder.Targets.SWIPE_CELL_PROCESS_BUTTON);
                builder.buildSequenceOfTargetsAndStart(getActivity(), pointers);
                preferences.edit().putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_SWIPE_PARAMETERS_POINTED, true).apply();
                preferences.edit().putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_SWIPE_PROCESS_BUTTON_POINTED, true).apply();
                preferences.edit().putBoolean(TapTargetViewBuilder.PREFERENCES_KEY_TAP_CELL_POINTED, true).apply();
            }

            // The action button
            Button processButton = fcCommandSwipe.findViewById(R.id.bt_command_action_swipe);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandSwipe.findViewById(R.id.et_params_swipe);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_PARAMETERS_4)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();
                } else {
                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);
                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandSwipe(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    Integer.parseInt(params[3]),
                                    new HttpClientStub.HttpClientCallback() {
                                        @Override
                                        public void onSuccess(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }

                                        @Override
                                        public void onFailure(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }
                                    });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }); // End of processButton.setOnClickListener

        }); // End of  fcCommandSwipe.setOnClickListener

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to tap command
     */
    private void initTapListener() {

        // Get the folding cell
        final FoldingCell fcCommandTap = getActivity().findViewById(R.id.fc_command_tap);

        // Listener to open / close the cell
        fcCommandTap.setOnClickListener(v -> {

            fcCommandTap.toggle(false);

            // The action button
            Button processButton = fcCommandTap.findViewById(R.id.bt_command_action_tap);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandTap.findViewById(R.id.et_params_tap);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_PARAMETERS_2)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();
                } else {
                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);
                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandTap(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    new HttpClientStub.HttpClientCallback() {
                                        @Override
                                        public void onSuccess(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }

                                        @Override
                                        public void onFailure(@Nullable String message) {
                                            getActivity().runOnUiThread(
                                                    () -> Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show()
                                            );
                                        }
                                    });
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }); // End of processButton.setOnClickListener

        });

    }

}
