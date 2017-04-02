package com.example.john.sdaprojectjohnbuckley;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.support.v4.app.Fragment;

public class MainActivity extends AppCompatActivity
{
    static final int REQUEST_IMAGE_CAPTURE = 1;
    String mCurrentPhotoPath;
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
        Button cameraButton = (Button) findViewById(R.id.button3);
        cameraButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                dispatchTakePictureIntent();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK)
        {
            galleryAddPic();
        }
    }
    private File createImageFile() throws IOException
    {
        File storageDir = Environment.getExternalStorageDirectory();
        File image = File.createTempFile("example",  /* prefix */".jpg",         /* suffix */storageDir      /* directory */);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }
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
    private void galleryAddPic()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}