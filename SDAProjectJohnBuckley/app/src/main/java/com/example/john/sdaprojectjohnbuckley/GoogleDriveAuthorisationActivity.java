/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.john.sdaprojectjohnbuckley;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;

/**
 * Users populate information for insertation into the guestlist database
 * Citation:
 * Class contains code adapted from
 * URL:https://https://github.com/googledrive/android-quickstart
 * Retrieved on 10th of April 2017
 * URL:https://github.com/googledrive/android-demos
 * Retrieved on 15th of April 2017
 * Created by John on 15/04/2017.
 */
public class GoogleDriveAuthorisationActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener
{
    private static final String TAG = "GoogleDriveAuthorisationActivity";
    private GoogleApiClient mGoogleApiClient;
    protected static final int REQUEST_CODE_RESOLUTION = 1;
    protected static final int NEXT_AVAILABLE_REQUEST_CODE = 2;

    /**
     * Code adapted from method onResume() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onResume() described at at https://github.com/googledrive/android-demos
     */
    protected void onResume()
    {
        super.onResume();
        if (mGoogleApiClient == null)
        {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
            .addApi(Drive.API)
           .addScope(Drive.SCOPE_FILE)
           .addScope(Drive.SCOPE_APPFOLDER) // required for App Folder sample
           .addConnectionCallbacks(this)
           .addOnConnectionFailedListener(this)
           .build();
        }
        mGoogleApiClient.connect();
    }

    /**
     * Handles resolution callbacks.
     * Code adapted from method onActivityResult() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onActivityResult() described at at https://github.com/googledrive/android-demos
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_RESOLUTION && resultCode == RESULT_OK) {
            mGoogleApiClient.connect();
        }
    }

    /**
     * Called when activity gets invisible. Connection to Drive service needs to
     * Code adapted from method onPause() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onPause() described at at https://github.com/googledrive/android-demos
     * be disconnected as soon as an activity is invisible.
     */
    @Override
    protected void onPause() {
        if (mGoogleApiClient != null) {
            mGoogleApiClient.disconnect();
        }
        super.onPause();
    }

    /**
     * Code adapted from method onConnected() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onConnected() described at at https://github.com/googledrive/android-demos
     * Called when {@code mGoogleApiClient} is connected.
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.i(TAG, "GoogleApiClient connected");
    }

    /**
     * Code adapted from method onConnectionSuspended() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onConnectionSuspended() described at at https://github.com/googledrive/android-demos
     * Called when {@code mGoogleApiClient} is disconnected.
     */
    @Override
    public void onConnectionSuspended(int cause) {
        Log.i(TAG, "GoogleApiClient connection suspended");
    }

    /**
     * Called when {@code mGoogleApiClient} is trying to connect but failed.
     * Handle {@code result.getResolution()} if there is a resolution is
     * available.
     * Code adapted from method onConnectionFailed() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method onConnectionFailed() described at at https://github.com/googledrive/android-demos
     */
    @Override
    public void onConnectionFailed(ConnectionResult result) {
        Log.i(TAG, "GoogleApiClient connection failed: " + result.toString());
        if (!result.hasResolution()) {
            // show the localized error dialog.
            GoogleApiAvailability.getInstance().getErrorDialog(this, result.getErrorCode(), 0).show();
            return;
        }
        try {
            result.startResolutionForResult(this, REQUEST_CODE_RESOLUTION);
        } catch (IntentSender.SendIntentException e) {
            Log.e(TAG, "Exception while starting resolution activity", e);
        }
    }

    /**
     * Shows a toast message.
     * Code adapted from method showMessage() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method showMessage() described at at https://github.com/googledrive/android-demos

     * @param message
     */
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Getter for the {@code GoogleApiClient}.
     * Code adapted from method getGoogleApiClient() described at at https://https://github.com/googledrive/android-quickstart
     * Code adapted from method getGoogleApiClient() described at at https://github.com/googledrive/android-demos
     * @return the result of the connection
     */
    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }
}

