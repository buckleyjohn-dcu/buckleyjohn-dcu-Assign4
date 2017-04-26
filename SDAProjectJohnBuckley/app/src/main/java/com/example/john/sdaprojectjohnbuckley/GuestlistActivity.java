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

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


/**
 * The Activity to view the guestlist database
 * Buttons to add, edit and delete database data
 * Citation:
 * Class contains code adapted from
 * URL:https://github.com/udacity/ud845-Pets
 * Retrieved on 11th of April 2017
 * URL:https://developer.android.com/training/basics/data-storage/databases.html
 * Retrieved on 11th of April 2017
 * URL:https://github.com/aporter/coursera-android/tree/master/Examples/DataManagementSQL
 * Retrieved on 8th of October 2016
 * URL:https://developer.android.com/guide/topics/ui/declaring-layout.html
 * Retrieved on 24th of April 2017
 */

public class GuestlistActivity extends ListActivity
{
    SQLiteDatabase db;
    private static final String TAG = "GuestlistActivity";
    private SQLHelper mDbHelper;
    private Button mButton;
    final Context c = this;
    String selectionArgs [];
    static final int REQUEST_GUEST_DELETE = 1;
    String delname = DeleteGuestActivity. deletedList.get(0);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "The GuestlistActivity about to be created.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestlist);
        /**
         * To access our database, we instantiate our subclass of SQLiteOpenHelper
         * and pass the context, which is the current activity.
         */
        mDbHelper = new SQLHelper(this);
        insertTestGuest();

    }

    /**
     * Button to intialise AddGuestActivity
     * Code adapted from Floating Action Button described at at https://github.com/udacity/ud845-Pets
     * @param v The button that was clicked.
     */
    public void addGuest(View v)
        {
            Log.i(TAG, "The Add guest button was clicked");
            Intent addGuestintent = new Intent(this, AddGuestsActivity.class);
            startActivity(addGuestintent);
        }

    /**
     * On Start method
     * Code adapted from method Onstart() described at at https://github.com/udacity/ud845-Pets
     *
     */
    protected void onStart()
    {
        super.onStart();

        displayGuestDatabaseInfo();
    }

    /**
     * use the query() method, passing it your selection criteria and desired columns
     * The method combines elements of insert() and update(),
     * except the column list defines the data you want to fetch, rather than the data to insert.
     * The results of the query are returned to you in a Cursor object.
     * Code adapted from method displayGuestDatabaseInfo() described at at https://github.com/udacity/ud845-Pets
     * Code adapted from https://developer.android.com/training/basics/data-storage/databases.html
     */
    private void displayGuestDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        String[] projection =
                {
                        GuestContract.GuestFeedEntry._ID,
                        GuestContract.GuestFeedEntry.GUEST_NAME,
                        GuestContract.GuestFeedEntry.GUEST_EMAIL,
                        GuestContract.GuestFeedEntry.GUEST_PHONENUMBER
                };
        if (projection.length < 1) {
            Log.i(TAG, "The Projection has been populated.");
        } else {
            Log.e(TAG, "The Projection failed to populate");
        }
        int[] toViews = {R.id.weddingGuestName, R.id.weddingGuestEmail, R.id.weddingGuestPhone};
        if (toViews.length > 0) {
            Log.i(TAG, "The entries had been entered.");
        } else {
            Log.e(TAG, "The entries failed to enter");
        }
        String sortOrder = GuestContract.GuestFeedEntry.GUEST_NAME;
        Cursor cursor = database.query(
                GuestContract.GuestFeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        if (cursor.getCount() > 0) {
            Log.i(TAG, "The Cursor had been populated.");
        } else {
            Log.e(TAG, "The Cursor failed to populate");
        }
        /**
         * Code adapted from https://developer.android.com/guide/topics/ui/declaring-layout.html
         * Code adapted from https://developer.android.com/reference/android/widget/SimpleCursorAdapter.html
         */
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.guestlist_adapterview, cursor, projection, toViews, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        if (adapter.isEmpty()) {
            Log.e(TAG, "The Cursor adapter is empty");
        } else {
            Log.i(TAG, "The Cursor adapter has been populated");
        }
    }
    /**
     * Helper method to insert hardcoded guest data into the database. For test purposes only.
     * Code adapted from method displayGuestDatabaseInfo() described at at https://github.com/udacity/ud845-Pets
     */
    private void insertTestGuest()
        {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and a test guest attributes are the values.
        ContentValues values = new ContentValues();
        values.put(GuestContract.GuestFeedEntry.GUEST_NAME, "Jack Sparrow");
        values.put(GuestContract.GuestFeedEntry.GUEST_EMAIL, "jacksparrow@pirates.com");
        values.put(GuestContract.GuestFeedEntry.GUEST_PHONENUMBER, 1800999999);
        // Insert a new row for Jack Sparrow in the database, returning the ID of that new row.
        // The first argument for db.insert() is the guest table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(GuestContract.GuestFeedEntry.TABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
         if (newRowId == -1) {
         // If the row ID is -1, then there was an error with insertion.
        Toast.makeText(this, "Error with saving the Test Guest", Toast.LENGTH_SHORT).show();
        } else {
        // Otherwise, the insertion was successful and we can display a toast with the row ID.
        Toast.makeText(this, "Test Guest saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Button to intialise DeleteGuestActivity
     * @param view The delete button pressed
     */
    public void delete(View view)
    {
        Log.i(TAG, "The delete guest button was clicked");
        Intent deleteGuestintent = new Intent(this, AddGuestsActivity.class);
        startActivityForResult(deleteGuestintent,REQUEST_GUEST_DELETE);
    }
    /**
     *
     * @param requestCode int
     * @param resultCode int
     * @param data The Intent to be called
     *  Returns whether the request code was handled (in which case subsequent listeners will not be called.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_GUEST_DELETE && resultCode == RESULT_OK)
        {
            // Define 'where' part of query.
            String selection = GuestContract.GuestFeedEntry.GUEST_NAME + " LIKE ?";
         // Specify arguments in placeholder order.
            String[] selectionArgs = {delname};
            // Issue SQL statement.
            db.delete(GuestContract.GuestFeedEntry.TABLE_NAME, selection, selectionArgs);
        }
    }


}



