/**
 * Copyright 2013 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.john.sdaprojectjohnbuckley;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Activity to intialise the entry into the guestlist database
 *  Citation:
 * Class contains code adapted from
 * URL:http://stackoverflow.com/questions/5662473/how-to-import-contacts-from-phonebook-to-our-application
 * Retrieved on 25th of April 2017
 * URL:https://developer.android.com/training/basics/intents/sending.html
 * Retrieved on 21st of March 2017
 */
public class InviteActivity extends MainActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite);
        /**
         * Button to import contacts
         * Code adapted from importGuestsButton at
         * http://stackoverflow.com/questions/5662473/how-to-import-contacts-from-phonebook-to-our-application
         * https://developer.android.com/training/basics/intents/sending.html
         */
        Button importGuestsButton = (Button) findViewById(R.id.button7);
        importGuestsButton.setOnClickListener(new View.OnClickListener()
        {

            /**
             * Interface definition for a callback to be invoked when a view is clicked
             * Start Access Contacts
             * @param v The view that was clicked, which is the button
             */
            public void onClick(View v)
            {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
                startActivityForResult(intent, 1);
            }
        });
        /**
         * Button to add contacts to the database manually
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */

        Button enterGuestsButton = (Button) findViewById(R.id.button8);
        enterGuestsButton.setOnClickListener(new View.OnClickListener()
        {
            /**
             * Interface definition for a callback to be invoked when a view is clicked
             * Start the AddGuestActivity
             * @param v The view that was clicked, which is the button
             */
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(InviteActivity.this, AddGuestsActivity.class);
                startActivity(invitesIntent);
        }
        });
    }
}
