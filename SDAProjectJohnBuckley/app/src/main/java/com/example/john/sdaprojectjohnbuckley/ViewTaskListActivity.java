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
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;
import android.widget.Toast;

/**
 * The Activity to view the Tasklist database
 * Buttons to add database data
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

public class ViewTaskListActivity extends ListActivity {

    private TaskSQLHelper mDbHelper;
    private static final String TAG = "ViewTaskListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_list);

        /**
         * Floating Action Button to intialise AddGuestActivity
         * Code adapted from Floating Action Button described at at https://github.com/udacity/ud845-Pets
         * @param v The button that was clicked.
         */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "The Add task floating button was clicked");
                Intent intent = new Intent(ViewTaskListActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        });
        /**
         * To access our database, we instantiate our subclass of SQLiteOpenHelper
         * and pass the context, which is the current activity.
         */
        mDbHelper = new TaskSQLHelper(this);
        insertTestTask();
    }

    @Override
    /**
     * On Start method
     * Code adapted from method Onstart() described at at https://github.com/udacity/ud845-Pets
     */
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * use the query() method, passing it your selection criteria and desired columns
     * The method combines elements of insert() and update(),
     * except the column list defines the data you want to fetch, rather than the data to insert.
     * The results of the query are returned to you in a Cursor object.
     * Code adapted from method displayGuestDatabaseInfo() described at at https://github.com/udacity/ud845-Pets
     * Code adapted from https://developer.android.com/training/basics/data-storage/databases.html
     */
    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TaskContract.TaskFeedEntry._ID,
                TaskContract.TaskFeedEntry.TASK_NAME,
                TaskContract.TaskFeedEntry.TASKENTRY_NAME,
                TaskContract.TaskFeedEntry.TASK_DATE
        };
        if (projection.length < 1) {
            Log.i(TAG, "The Projection has been populated.");
        } else {
            Log.e(TAG, "The Projection failed to populate");
        }
        int[] taskColumns = {R.id.taskdesc, R.id.taskcompletedby, R.id.taskduedate};
        if (taskColumns.length > 0) {
            Log.i(TAG, "The entries had been entered.");
        } else {
            Log.e(TAG, "The entries failed to enter");
        }
        Cursor taskCursor = db.query(
                TaskContract.TaskFeedEntry.TASKTABLE_NAME,                     // The table to query
                projection,                                      // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        if (taskCursor.getCount() > 0) {
            Log.i(TAG, "The Cursor had been populated.");
        } else {
            Log.e(TAG, "The Cursor failed to populate");
        }
        /**
         * Code adapted from https://developer.android.com/guide/topics/ui/declaring-layout.html
         * Code adapted from https://developer.android.com/reference/android/widget/SimpleCursorAdapter.html
         */
        SimpleCursorAdapter taskAdapter = new SimpleCursorAdapter(this, R.layout.tasklist_adapterview, taskCursor, projection, taskColumns, 0);
        ListView listView = getListView();
        listView.setAdapter(taskAdapter);
        if (taskAdapter.isEmpty()) {
            Log.e(TAG, "The Cursor adapter is empty");
        } else {
            Log.i(TAG, "The Cursor adapter has been populated");
        }

        }

    /**
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_catalog, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);


    }
    /**
     * Helper method to insert hardcoded guest data into the database. For test purposes only.
     * Code adapted from method displayGuestDatabaseInfo() described at at https://github.com/udacity/ud845-Pets
     */
    private void insertTestTask()
    {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskFeedEntry.TASK_NAME, "Take over the World");
        values.put(TaskContract.TaskFeedEntry.TASKENTRY_NAME, "Pinky and the Brain");
        values.put(TaskContract.TaskFeedEntry.TASK_DATE, "31/1/16");


        // Insert a new row for Take over the World in the database, returning the ID of that new row.
        // The first argument for db.insert() is the guest table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(TaskContract.TaskFeedEntry.TASKTABLE_NAME, null, values);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving the Test Task", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Test Task saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }
}

