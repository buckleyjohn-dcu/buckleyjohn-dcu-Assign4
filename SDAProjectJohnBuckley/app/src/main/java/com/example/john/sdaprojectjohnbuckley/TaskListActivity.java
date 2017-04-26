
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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
/**
 * Users populate information for insertation into the guestlist database
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
public class TaskListActivity extends AppCompatActivity {
    EditText taskdescript;
    EditText taskCompleteBy;
    EditText taskduedate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskdescript= (EditText) findViewById(R.id.taskdesc);
        taskCompleteBy = (EditText) findViewById(R.id.taskcompletedby);
        taskduedate = (EditText) findViewById(R.id.taskduedate);

    }
    /**
     * Interface definition for a callback to be invoked when a view is clicked
     * Code adapted from method insertPet() described at at https://github.com/udacity/ud845-Pets
     * @param v  The button that was clicked.
     */
    public void save(View v)
    {
        SQLHelper mTaskDbHelper = new SQLHelper(TaskListActivity.this);
        SQLiteDatabase taskDB = mTaskDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskFeedEntry.TASK_NAME, taskdescript.getText().toString());
        values.put(TaskContract.TaskFeedEntry.TASKENTRY_NAME, taskCompleteBy.getText().toString());
        values.put(TaskContract.TaskFeedEntry.TASK_DATE, taskduedate.getText().toString());

        // Insert a new row for guest in the database, returning the ID of that new row.
        long newRowId = taskDB.insert(TaskContract.TaskFeedEntry.TASKTABLE_NAME, null, values);
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving task", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Task saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }


}
