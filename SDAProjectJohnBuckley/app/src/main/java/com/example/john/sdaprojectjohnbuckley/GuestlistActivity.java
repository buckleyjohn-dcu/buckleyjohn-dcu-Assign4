package com.example.john.sdaprojectjohnbuckley;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GuestlistActivity extends ListActivity
{
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guestlist);

        String[] projection =
        {
            SQLHelper._ID,
            SQLHelper.GUEST_NAME,
            SQLHelper.GUEST_EMAIL,
            SQLHelper.GUEST_PHONE
        };
        int[] toViews = {R.id.weddingGuestName, R.id.weddingGuestEmail,R.id.weddingGuestPhone};
        String sortOrder = SQLHelper.GUEST_NAME;
        Cursor cursor = db.query(
                SQLHelper.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                                // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.guestlist_adapterview, cursor, projection, toViews, 0);
        ListView listView = getListView();
        listView.setAdapter(adapter);


        cursor.close();

    }


}
