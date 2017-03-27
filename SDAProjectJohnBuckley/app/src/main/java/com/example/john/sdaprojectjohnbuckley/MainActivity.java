package com.example.john.sdaprojectjohnbuckley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //intent to load up the inviting activity.
        Button inviteButton = (Button) findViewById(R.id.button1);
        inviteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(MainActivity.this, InviteActivity.class);
                startActivity(invitesIntent);
            }
        });

        Button guestlistButton = (Button) findViewById(R.id.button2);
        guestlistButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(MainActivity.this, GuestlistActivity.class);
                startActivity(invitesIntent);
            }
        });
    }

}