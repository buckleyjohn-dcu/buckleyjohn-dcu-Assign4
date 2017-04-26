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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Users populate information for deletion in the guest list database
 * Citation:
 * Class contains code adapted from
 * URL:https://developer.android.com/reference/java/util/ArrayList.html
 * Retrieved on 22th of April 2017
 * Class contains code adapted from
 * URL:http://stackoverflow.com/questions/15523966/add-item-in-array-list-of-android
 * Retrieved on 24th of April 2017
 */


public class DeleteGuestActivity extends AppCompatActivity {
    EditText guestNameDeleted;

    public static ArrayList<String> deletedList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_guest);
        guestNameDeleted = (EditText) findViewById(R.id.userInputDialog);

    }

    /**
     * Array of names to be passed to the Guestlist activity as the where clause in deletion
     * Code adapted form https://developer.android.com/reference/java/util/ArrayList.html
     * Code adapted http://stackoverflow.com/questions/15523966/add-item-in-array-list-of-android
     * @param v The view that was clicked.
     */
    public void deleteguestinfo(View v) {
        String deleteName = guestNameDeleted.getText().toString();
        if (guestNameDeleted.length() != 0) {
            deletedList.add(deleteName);
            guestNameDeleted.setText("");
        }


    }


}

