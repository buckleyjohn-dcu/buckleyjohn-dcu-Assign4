package com.example.john.sdaprojectjohnbuckley;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by John on 06/04/2017.
 */
public class TaskSQLHelper extends SQLiteOpenHelper
{

    private static final String SQL_TASK_CREATE_ENTRIES =
            "CREATE TABLE " + TaskContract.TaskFeedEntry.TASKTABLE_NAME + " (" +
                    TaskContract.TaskFeedEntry._ID + " INTEGER PRIMARY KEY," +
                    TaskContract.TaskFeedEntry.TASK_NAME + " TEXT," +
                    TaskContract.TaskFeedEntry.TASKENTRY_NAME + " TEXT," +
                    TaskContract.TaskFeedEntry.TASK_DATE + " TEXT)";
    private static final String SQL_TASK_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TaskContract.TaskFeedEntry.TASKTABLE_NAME;

    public static final int DB_VERSION = 2;
    public static final String TASKDB_NAME = "taskList.db";

    public TaskSQLHelper(Context context) {
        super(context, TASKDB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase taskDB)
    {
        taskDB.execSQL(SQL_TASK_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase taskDB, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        taskDB.execSQL(SQL_TASK_DELETE_ENTRIES);
        onCreate(taskDB);
    }

}
