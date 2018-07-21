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
import pylapp.tapster.client.android.ui.taptargets.TapTargetViewBuilder;

/**
 * Fragment containing commands related to robot moves
 *
 * @author pylapp
 * @version 1.0.0
 * @since 21/07/2018
 */
public class MovesCommandsFragment extends AbstractCommandsFragment {


    /* *********** *
     * CONSTRUCTOR *
     * *********** */

    /**
     * Default constructor
     */
    public MovesCommandsFragment(){
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
        return inflater.inflate(R.layout.fragment_commands_moves, container, false);
    }

    /**
     * Initializes the listeners for the widgets
     */
    @Override
    protected void initListeners() {

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