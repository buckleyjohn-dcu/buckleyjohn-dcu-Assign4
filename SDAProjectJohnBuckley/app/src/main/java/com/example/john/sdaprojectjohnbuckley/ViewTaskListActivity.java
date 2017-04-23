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

import java.util.ArrayList;
import java.util.List;


public class ViewTaskListActivity extends ListActivity {

    private TaskSQLHelper mDbHelper;
    private static final String TAG = "ViewTaskListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task_list);

        // Setup FAB to open EditorActivity
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "The Add task floating button was clicked");
                Intent intent = new Intent(ViewTaskListActivity.this, TaskListActivity.class);
                startActivity(intent);
            }
        });
        mDbHelper = new TaskSQLHelper(this);
        insertTestTask();
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayDatabaseInfo();
    }

    /**
     * Temporary helper method to display information in the onscreen TextView about the state of
     * the pets database.
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

        SimpleCursorAdapter taskAdapter = new SimpleCursorAdapter(this, R.layout.guestlist_adapterview, taskCursor, projection, taskColumns, 0);
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


        // Insert a new row for Jack Sparrow in the database, returning the ID of that new row.
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

