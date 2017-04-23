package com.example.john.sdaprojectjohnbuckley;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
    public void save(View v)
    {
        SQLHelper mTaskDbHelper = new SQLHelper(TaskListActivity.this);
        SQLiteDatabase taskDB = mTaskDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskContract.TaskFeedEntry.TASK_NAME, taskdescript.getText().toString());
        values.put(TaskContract.TaskFeedEntry.TASKENTRY_NAME, taskCompleteBy.getText().toString());
        values.put(TaskContract.TaskFeedEntry.TASK_DATE, taskduedate.getText().toString());

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
