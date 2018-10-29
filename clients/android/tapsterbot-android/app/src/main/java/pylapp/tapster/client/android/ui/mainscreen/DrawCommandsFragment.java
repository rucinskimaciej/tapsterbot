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
 * Fragment containing commands related to robot draws
 *
 * @author Pierre-Yves Lapersonne
 * @version 1.0.0
 * @since 21/07/2018
 */
public class DrawCommandsFragment extends AbstractCommandsFragment {


    /* *********** *
     * CONSTRUCTOR *
     * *********** */

    /**
     * Default constructor
     */
    public DrawCommandsFragment(){
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
        return inflater.inflate(R.layout.fragment_commands_draws, container, false);
    }

    /**
     * Initializes the listeners for the widgets.
     * Should be called in the super class {@link AbstractCommandsFragment} in its onResume() method
     * so as to be sure elements have been inflated.
     */
    @Override
    protected void initListeners() {
        initDrawStarListener();
        initDrawCircleListener();
        initDrawSpiralListener();
        initDrawSquareListener();
        initDrawCrossListener();
        initDrawTriangleListener();
        initDrawRandomPatternListener();
    }

    /**
     * Initializes the listeners on widgets related to draw star command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawStarListener(){

        // The cell for the draw star feature
        final FoldingCell fcCommandDrawStar = getActivity().findViewById(R.id.fc_command_drawstar);
        fcCommandDrawStar.setOnClickListener(v -> {

            fcCommandDrawStar.toggle(false);

            // The action button
            Button processButton = fcCommandDrawStar.findViewById(R.id.bt_command_action_drawstar);
            processButton.setOnClickListener(v2 -> {

                try {
                    // Update the HTTP client, and send the request if permission is granted
                    updateHttpClient();
                    if (!mPermissionsManager.isPermissionGranted(getActivity(),
                            Manifest.permission.INTERNET)) {
                        Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                    } else {
                        mHttpClient.commandDrawStar(new HttpClientStub.HttpClientCallback() {
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
     * Initializes the listeners on widgets related to draw circle command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawCircleListener(){

        // The cell for the draw circle feature
        final FoldingCell fcCommandDrawCircle = getActivity().findViewById(R.id.fc_command_drawcircle);
        fcCommandDrawCircle.setOnClickListener(v -> {

            fcCommandDrawCircle.toggle(false);

            // The action button
            Button processButton = fcCommandDrawCircle.findViewById(R.id.bt_command_action_drawcircle);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawCircle.findViewById(R.id.et_params_drawcircle);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_CIRCLE)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawCircle(
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of  fcCommandDrawCircle.setOnClickListener

    }

    /**
     * Initializes the widgets for the draw spiral folding cell.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawSpiralListener(){

        // The cell for the draw spiral feature
        final FoldingCell fcCommandDrawSpiral = getActivity().findViewById(R.id.fc_command_drawspiral);
        fcCommandDrawSpiral.setOnClickListener(v -> {

            fcCommandDrawSpiral.toggle(false);

            // The action button
            Button processButton = fcCommandDrawSpiral.findViewById(R.id.bt_command_action_drawspiral);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawSpiral.findViewById(R.id.et_params_drawspiral);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_SPIRAL)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                    // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawSpiral(
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of fcCommandDrawSpiral.setOnClickListener

    }

    /**
     * Initializes the listeners on widgets related to draw square command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawSquareListener(){

        // The cell for the draw square feature
        final FoldingCell fcCommandDrawSquare = getActivity().findViewById(R.id.fc_command_drawsquare);
        fcCommandDrawSquare.setOnClickListener(v -> {

            fcCommandDrawSquare.toggle(false);

            // The action button
            Button processButton = fcCommandDrawSquare.findViewById(R.id.bt_command_action_drawsquare);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawSquare.findViewById(R.id.et_params_drawsquare);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_SQUARE)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                    // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawSquare(
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of fcCommandDrawSquare.setOnClickListener

    }

    /**
     * Initializes the listeners on widgets related to draw cross command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawCrossListener(){

        // The cell for the draw square feature
        final FoldingCell fcCommandDrawCross = getActivity().findViewById(R.id.fc_command_drawcross);
        fcCommandDrawCross.setOnClickListener(v -> {

            fcCommandDrawCross.toggle(false);

            // The action button
            Button processButton = fcCommandDrawCross.findViewById(R.id.bt_command_action_drawcross);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawCross.findViewById(R.id.et_params_drawcross);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_CROSS)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                    // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawCross(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    Integer.parseInt(params[3]),
                                    Integer.parseInt(params[4]),
                                    Integer.parseInt(params[5]),
                                    Integer.parseInt(params[6]),
                                    Integer.parseInt(params[7]),
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of fcCommandDrawSquare.setOnClickListener

    }

    /**
     * Initializes the listeners on widgets related to draw triangle command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawTriangleListener(){

        // The cell for the draw square feature
        final FoldingCell fcCommandDrawTriangle = getActivity().findViewById(R.id.fc_command_drawtriangle);
        fcCommandDrawTriangle.setOnClickListener(v -> {

            fcCommandDrawTriangle.toggle(false);

            // The action button
            Button processButton = fcCommandDrawTriangle.findViewById(R.id.bt_command_action_drawtriangle);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawTriangle.findViewById(R.id.et_params_drawtriangle);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_TRIANGLE)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                    // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawTriangle(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    Integer.parseInt(params[3]),
                                    Integer.parseInt(params[4]),
                                    Integer.parseInt(params[5]),
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of fcCommandDrawSquare.setOnClickListener

    }

    /**
     * Initializes the listeners on widgets related to draw random pattern command.
     * Should be called within initListeners() method, itself called in the onResume() method to be sure
     * widgets and layouts have been inflated.
     */
    private void initDrawRandomPatternListener(){

        // The cell for the draw square feature
        final FoldingCell fcCommandDrawRandomPattern = getActivity().findViewById(R.id.fc_command_drawrandom);
        fcCommandDrawRandomPattern.setOnClickListener(v -> {

            fcCommandDrawRandomPattern.toggle(false);

            // The action button
            Button processButton = fcCommandDrawRandomPattern.findViewById(R.id.bt_command_action_drawrandom);
            processButton.setOnClickListener(v2 -> {

                // Get parameters
                EditText textField = fcCommandDrawRandomPattern.findViewById(R.id.et_params_drawrandom);

                // Parse and check parameters
                String content = textField.getText().toString();
                if (!content.matches(Config.REGEX_COMMAND_DRAW_RANDOM)) {
                    Toast.makeText(getContext(), getString(R.string.command_bad_parameters),
                            Toast.LENGTH_SHORT).show();

                // Send request
                } else {

                    String[] params = content.split(Config.REGEX_PARAMETERS_SEPARATOR);

                    try {
                        // Update the HTTP client, and send the request if permission is granted
                        updateHttpClient();
                        if (!mPermissionsManager.isPermissionGranted(getActivity(),
                                Manifest.permission.INTERNET)) {
                            Toast.makeText(getActivity(), R.string.error_permission_not_granted_internet, Toast.LENGTH_LONG).show();
                        } else {
                            mHttpClient.commandDrawRandomPattern(
                                    Integer.parseInt(params[0]),
                                    Integer.parseInt(params[1]),
                                    Integer.parseInt(params[2]),
                                    Integer.parseInt(params[3]),
                                    Integer.parseInt(params[4]),
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
                                    }
                            );
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                } // End of else

            }); // End of  processButton.setOnClickListener

        }); // End of fcCommandDrawSquare.setOnClickListener

    }

}

