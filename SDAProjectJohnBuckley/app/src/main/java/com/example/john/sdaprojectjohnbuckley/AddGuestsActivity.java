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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Users populate information for insertation into the guestlist database
 * Citation:
 * Class contains code adapted from
 * URL:https://github.com/udacity/ud845-Pets
 * Retrieved on 11th of April 2017
 */

public class AddGuestsActivity extends AppCompatActivity
{

    EditText guestnameEntered;//initialise variable
    EditText guestemailEntered;//initialise variable
    EditText guestphonenumberEntered;//initialise variable
    private static final String TAG = "AddGuestActivity";

    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "Oncreate Method ran");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guests);
        guestnameEntered = (EditText) findViewById(R.id.guestname);
        guestemailEntered = (EditText) findViewById(R.id.guestemail);
        guestphonenumberEntered = (EditText) findViewById(R.id.guestphone);
    }

    /**
     * Interface definition for a callback to be invoked when a view is clicked
     * Code adapted from method insertPet() described at at https://github.com/udacity/ud845-Pets
     * @param v  The button that was clicked.
     */
    public void saveGuestInfo(View v)
    {
        Log.i(TAG, "Save button clicked");
        SQLHelper mDbHelper = new SQLHelper(AddGuestsActivity.this);
        SQLiteDatabase database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GuestContract.GuestFeedEntry.GUEST_NAME, guestnameEntered.getText().toString());
        values.put(GuestContract.GuestFeedEntry.GUEST_EMAIL, guestemailEntered.getText().toString());
        values.put(GuestContract.GuestFeedEntry.GUEST_PHONENUMBER, guestphonenumberEntered.getText().toString());

         // Insert a new row for guest in the database, returning the ID of that new row.
         long newRowId = database.insert(GuestContract.GuestFeedEntry.TABLE_NAME, null, values);

         // Show a toast message depending on whether or not the insertion was successful
         if (newRowId == -1) {
         // If the row ID is -1, then there was an error with insertion.
         Toast.makeText(AddGuestsActivity.this, "Error with saving Guest", Toast.LENGTH_SHORT).show();
         } else {
         // Otherwise, the insertion was successful and we can display a toast with the row ID.
         Toast.makeText(AddGuestsActivity.this, "Guest saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
         }
     }
}
