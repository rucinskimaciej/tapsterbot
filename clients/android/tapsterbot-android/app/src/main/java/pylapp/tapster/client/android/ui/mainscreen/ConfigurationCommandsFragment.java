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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;

import pylapp.tapster.client.android.R;
import pylapp.tapster.client.android.networks.HttpClientStub;
import pylapp.tapster.client.android.tools.Config;

/**
 * Fragment containing commands related to robot configuration and status
 *
 * @author pylapp
 * @version 1.0.0
 * @since 21/07/2018
 */
public class ConfigurationCommandsFragment extends AbstractCommandsFragment {


    /* *********** *
     * CONSTRUCTOR *
     * *********** */

    /**
     * Default constructor
     */
    public ConfigurationCommandsFragment(){
        super();
    }


    /* ******* *
     * METHODS *
     * ******* */

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
        return inflater.inflate(R.layout.fragment_commands_configuration, container, false);
    }

    /**
     * Initializes the listeners for the widgets.
     * Should be called in the super class {@link AbstractCommandsFragment} in its onResume() method
     * so as to be sure elements have been inflated.
     */
    @Override
    protected void initListeners() {

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

    }

    /**
     * Initializes the listeners for widgets in the folding cell dedicated to get position command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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
     * Initializes the listeners for widgets in the folding cell dedicated to set position command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initSetPositionListener() {

        // Get the folding cell
        //noinspection ConstantConditions
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
                if (!content.matches(Config.REGEX_COMMAND_SET_POSITION)) {
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
     * Initializes the listeners for widgets in the folding cell dedicated to get angles command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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
     * Initializes the listeners for widgets in the folding cell dedicated to set angles command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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
                if (!content.matches(Config.REGEX_COMMAND_SET_ANGLES)) {
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
     * Initializes the listeners for widgets in the folding cell dedicated to get status command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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
     * Initializes the listeners for widgets in the folding cell dedicated to get contact command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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
     * Initializes the listeners for widgets in the folding cell dedicated to reset command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
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

}
