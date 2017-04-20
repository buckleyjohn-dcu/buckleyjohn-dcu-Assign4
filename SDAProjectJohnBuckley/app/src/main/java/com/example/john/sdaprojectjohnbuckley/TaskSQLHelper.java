package com.example.john.sdaprojectjohnbuckley;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by John on 06/04/2017.
 */
public class TaskSQLHelper extends SQLiteOpenHelper
{
    final static String TASKTABLE_NAME = "tasklistTable";
    final static String TASK_NAME = "guestName";
    final static String TASKENTRY_NAME = "email";
    final static String TASK_DATE = "phone";
    final static String _ID = "_id";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TASKTABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    TASK_NAME + " TEXT," +
                    TASKENTRY_NAME + " TEXT)" +
                    TASK_DATE + " TEXT)";
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TASKTABLE_NAME;

    public static final int DB_VERSION = 1;
    public static final String TASKDB_NAME = "taskList.db";

    public TaskSQLHelper(Context context) {
        super(context, TASKDB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

}
