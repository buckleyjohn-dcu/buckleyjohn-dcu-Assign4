package com.example.john.sdaprojectjohnbuckley;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class GuestlistActivity extends ListActivity
{
    SQLiteDatabase db;
    private static final String TAG = "GuestlistActivity";
    private SQLHelper mDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.i(TAG, "The GuestlistActivity about to be created.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestlist);
        mDbHelper = new SQLHelper(this);


        insertTestGuest();

    }
    public void addGuest(View v)
        {
            Log.i(TAG, "The Add guest button was clicked");
            Intent addGuestintent = new Intent(this, AddGuestsActivity.class);
            startActivity(addGuestintent);
        }

    protected void onStart()
    {
        super.onStart();

        displayGuestDatabaseInfo();
    }

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

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.guestlist_adapterview, cursor, projection, toViews, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);
        if (adapter.isEmpty()) {
            Log.e(TAG, "The Cursor adapter is empty");
        } else {
            Log.i(TAG, "The Cursor adapter has been populated");
        }


    }

    private void insertTestGuest()
        {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
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

}



