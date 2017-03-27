package com.example.john.sdaprojectjohnbuckley;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by John on 27/03/2017.
 */
public class SQLHelper extends SQLiteOpenHelper {
    final static String TABLE_NAME = "guestListTable";
    final static String GUEST_NAME = "guestName";
    final static String GUEST_EMAIL = "email";
    final static String GUEST_PHONE = "phone";
    final static String _ID = "_id";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    GUEST_NAME + " TEXT," +
                    GUEST_EMAIL + " TEXT)" +
                    GUEST_PHONE + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "GuestsList.db";

    public SQLHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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

