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
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;

import java.io.IOException;

import pylapp.tapster.client.android.R;
import pylapp.tapster.client.android.networks.HttpClientStub;

/**
 * Fragment containing commands related to robot draws
 *
 * @author pylapp
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
     * Initializes the listeners for the widgets
     */
    @Override
    protected void initListeners() {

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

        // The cell for the draw circle feature
        final FoldingCell fcCommandDrawCircle = getActivity().findViewById(R.id.fc_command_drawcircle);
        fcCommandDrawCircle.setOnClickListener(v -> {

            fcCommandDrawCircle.toggle(false);

            // The action button
            Button processButton = fcCommandDrawCircle.findViewById(R.id.bt_command_action_drawcircle);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });

        // The cell for the draw spiral feature
        final FoldingCell fcCommandDrawSpiral = getActivity().findViewById(R.id.fc_command_drawspiral);
        fcCommandDrawSpiral.setOnClickListener(v -> {

            fcCommandDrawSpiral.toggle(false);

            // The action button
            Button processButton = fcCommandDrawSpiral.findViewById(R.id.bt_command_action_drawspiral);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });

        // The cell for the draw square feature
        final FoldingCell fcCommandDrawSquare = getActivity().findViewById(R.id.fc_command_drawsquare);
        fcCommandDrawSquare.setOnClickListener(v -> {

            fcCommandDrawSquare.toggle(false);

            // The action button
            Button processButton = fcCommandDrawSquare.findViewById(R.id.bt_command_action_drawsquare);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });

        // The cell for the draw cross feature
        final FoldingCell fcCommandDrawCross = getActivity().findViewById(R.id.fc_command_drawcross);
        fcCommandDrawCross.setOnClickListener(v -> {

            fcCommandDrawCross.toggle(false);

            // The action button
            Button processButton = fcCommandDrawCross.findViewById(R.id.bt_command_action_drawcross);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });

        // The cell for the draw triangle feature
        final FoldingCell fcCommandDrawTriangle = getActivity().findViewById(R.id.fc_command_drawtriangle);
        fcCommandDrawTriangle.setOnClickListener(v -> {

            fcCommandDrawTriangle.toggle(false);

            // The action button
            Button processButton = fcCommandDrawTriangle.findViewById(R.id.bt_command_action_drawtriangle);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });


        // The cell for the draw random feature
        final FoldingCell fcCommandDrawRandom = getActivity().findViewById(R.id.fc_command_drawrandom);
        fcCommandDrawRandom.setOnClickListener(v -> {

            fcCommandDrawRandom.toggle(false);

            // The action button
            Button processButton = fcCommandDrawRandom.findViewById(R.id.bt_command_action_drawrandom);
            processButton.setOnClickListener(v2 -> {
                // TODO
            });

        });


    }


}
