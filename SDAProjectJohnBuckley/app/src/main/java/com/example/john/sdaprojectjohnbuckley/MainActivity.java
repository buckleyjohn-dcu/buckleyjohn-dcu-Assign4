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

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v4.app.Fragment;
import android.widget.RelativeLayout;

/**
 * Main screen when application is launched
 * Citation:
 * URL:https://developer.android.com/training/basics/intents/sending.html
 * Retrieved on 21st of March 2017
 * URL: https://github.com/OpenEducationDCU/SDA/tree/master/PhotoIntentActivity
 * Retrieved on 13th of October 2017
 * URL: https://developer.android.com/training/camera/photobasics.html
 * Retrieved on 21st of March 2017
 */
public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
    RelativeLayout screen;
    private static final int REQUEST_CODE_OPENER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * intent to load up the inviting activity.
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */
        Button inviteButton = (Button) findViewById(R.id.button1);
        inviteButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(MainActivity.this, InviteActivity.class);
                startActivity(invitesIntent);
            }
        });
        /**
         * intent to load up the GuestList activity.
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */
        Button guestlistButton = (Button) findViewById(R.id.button2);
        guestlistButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent invitesIntent = new Intent(MainActivity.this, GuestlistActivity.class);
                startActivity(invitesIntent);
            }
        });
        /**
         * intent to load up the Camera activity.
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */
        Button cameraButton = (Button) findViewById(R.id.button3);
        cameraButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                dispatchTakePictureIntent();
            }
        });
       /**
        * intent to load up the ViewGoogleDrive activity.
        * Code adapted from https://developer.android.com/training/basics/intents/sending.html
        */
        Button driveButton = (Button) findViewById(R.id.button4);
        driveButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent driveIntent = new Intent(MainActivity.this, ViewGoogleDriveActivity.class);
                startActivity(driveIntent);

            }
        });
        /**
         * Intent to load up the RSSFeedactivity.
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */
        Button rssButton = (Button) findViewById(R.id.button5);
        rssButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent tasksIntent = new Intent(MainActivity.this, RSSFeedActivity.class);
                startActivity(tasksIntent);
            }
        });
        /**
         * Intent to load up the ViewTasklist.
         * Code adapted from https://developer.android.com/training/basics/intents/sending.html
         */
        Button tasksButton = (Button) findViewById(R.id.button6);
        tasksButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent tasksIntent = new Intent(MainActivity.this, ViewTaskListActivity.class);
                startActivity(tasksIntent);
            }
        });



    }
    @Override
    /**
     * @param requestCode int The request code you passed to startActivityForResult().
     * @param resultCode int Specified by the second activity.
     * @param data The Intent to be called
     * Returns whether the request code was handled (in which case subsequent listeners will not be called.
     * Code adapted from onActivityResult() method https://developer.android.com/training/basics/intents/sending.html
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            galleryAddPic();
        }
    }

    /**
     * Create a collision-resistant file name
     * @return A unique file name
     * @throws IOException
     * Code adapted from createImageFile() method https://developer.android.com/training/camera/photobasics.html
     * Code adapted from createImageFile() method https://github.com/OpenEducationDCU/SDA/tree/master/PhotoIntentActivity
     */
    private File createImageFile() throws IOException
    {
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile("example",  /* prefix */".jpg",         /* suffix */storageDir      /* directory */);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    /**
     * create and invoke the Intent
     * Code adapted from createImageFile() method https://developer.android.com/training/camera/photobasics.html
     * Code adapted from createImageFile() method https://github.com/OpenEducationDCU/SDA/tree/master/PhotoIntentActivity
     */
    private void dispatchTakePictureIntent()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null)
        {
            File photoFile = null;
            try
            {
                photoFile = createImageFile();
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    /**
     * Invoke the system's media scanner to add your photo to the Media Provider's database
     * making it available in the Android Gallery application and to other apps
     * Code adapted from createImageFile() method https://developer.android.com/training/camera/photobasics.html
     * Code adapted from createImageFile() method https://github.com/OpenEducationDCU/SDA/tree/master/PhotoIntentActivity
     */
    private void galleryAddPic()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

}