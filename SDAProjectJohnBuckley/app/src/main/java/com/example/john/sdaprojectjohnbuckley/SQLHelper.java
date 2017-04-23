package com.example.john.sdaprojectjohnbuckley;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 27/03/2017.
 */
public class SQLHelper extends SQLiteOpenHelper {


    private static final String SQL_GUEST_CREATE_ENTRIES =
            "CREATE TABLE " + GuestContract.GuestFeedEntry.TABLE_NAME + " (" +
                    GuestContract.GuestFeedEntry._ID + " INTEGER PRIMARY KEY," +
                    GuestContract.GuestFeedEntry.GUEST_NAME + " TEXT," +
                    GuestContract.GuestFeedEntry.GUEST_EMAIL + " TEXT," +
                    GuestContract.GuestFeedEntry.GUEST_PHONENUMBER + " TEXT)";

    private static final String SQL_GUEST_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + GuestContract.GuestFeedEntry.TABLE_NAME;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "GuestsList.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SQL_GUEST_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        database.execSQL(SQL_GUEST_DELETE_ENTRIES);
        onCreate(database);
    }
}

