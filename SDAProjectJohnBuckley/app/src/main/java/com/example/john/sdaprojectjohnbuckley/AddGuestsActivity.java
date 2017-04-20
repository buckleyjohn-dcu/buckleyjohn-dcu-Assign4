package com.example.john.sdaprojectjohnbuckley;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddGuestsActivity extends AppCompatActivity {

    EditText guestnameEntered;
    EditText guestemailEntered;
    EditText guestphonenumberEntered;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guests);

        guestnameEntered = (EditText) findViewById(R.id.guestname);
        guestemailEntered = (EditText) findViewById(R.id.guestemail);
        guestphonenumberEntered = (EditText) findViewById(R.id.guestphone);

    }

    public void guestSave(View v)
    {
        SQLHelper mDbHelper = new SQLHelper(this);

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SQLHelper.GUEST_NAME, guestnameEntered.getText().toString());
        values.put(SQLHelper.GUEST_EMAIL, guestemailEntered.getText().toString());
        values.put(SQLHelper.GUEST_PHONE, guestphonenumberEntered.getText().toString());


        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = database.insert(SQLHelper.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1)
         {
             // If the row ID is -1, then there was an error with insertion.
             Toast.makeText(this, "Error with saving Guest", Toast.LENGTH_SHORT).show();
         } else
         {
             // Otherwise, the insertion was successful and we can display a toast with the row ID.
             Toast.makeText(this, "Guest saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
         }
     }

}
