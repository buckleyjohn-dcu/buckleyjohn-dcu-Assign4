package com.example.john.sdaprojectjohnbuckley;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class AddGuestsActivity extends AppCompatActivity
{

    EditText guestnameEntered;
    EditText guestemailEntered;
    EditText guestphonenumberEntered;
    SQLiteDatabase guest_db = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guests);

        guestnameEntered = (EditText) findViewById(R.id.guestname);
        guestemailEntered = (EditText) findViewById(R.id.guestemail);
        guestphonenumberEntered = (EditText) findViewById(R.id.guestphone);


    }
}
