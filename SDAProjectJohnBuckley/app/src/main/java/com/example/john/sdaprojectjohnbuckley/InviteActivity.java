package com.example.john.sdaprojectjohnbuckley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class InviteActivity extends MainActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);

        Button importGuestsButton = (Button) findViewById(R.id.button7);
        importGuestsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(InviteActivity.this, ImportGuestContactsActivity.class);
                startActivity(invitesIntent);
            }
        });
        Button enterGuestsButton = (Button) findViewById(R.id.button8);
        enterGuestsButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(InviteActivity.this, AddGuestsActivity.class);
                startActivity(invitesIntent);
        }
        });
    }
}
