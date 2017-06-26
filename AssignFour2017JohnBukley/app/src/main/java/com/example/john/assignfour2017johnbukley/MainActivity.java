package com.example.john.assignfour2017johnbukley;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.design.widget.TabLayout;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {
    private static final int MY_NOTIFICATION_ID = 1;
    String notificationTitle = "Restautant Notification!";
    String notificationText = "Notification: Save a date in your diary";
    private Uri soundURI = Uri
            .parse("android.resource://edu.dcu.cs.notifydemo/"
                    + R.raw.alarm_rooster);
    private long[] mVibratePattern = { 0, 200, 200, 300 };
    String eventTitle = "Christmas Party";
    String eventLocation="DCU Restaurant";

    private static final String TAG = "MainActivity";
    ViewPager mViewPager;
    FragmentAdapter myFragmentAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    MenuItem shareOption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        TabLayout myTabLayout = (TabLayout) findViewById(R.id.my_tablayout);
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        myTabLayout.addTab(myTabLayout.newTab());
        if (myTabLayout.getTabCount() > 0) {
            Log.i(TAG, "The tab has been populated");
        } else {
            Log.e(TAG, "Error Tab not populated");
        }
        myFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), myTabLayout.getTabCount());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myFragmentAdapter);
        myTabLayout.setupWithViewPager(mViewPager);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbarmenu, menu);
        shareOption = menu.findItem(R.id.action_share);
        shareOption.setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Log.i(TAG, "User chose the Settings item, show the app settings UI");
                return true;
            case R.id.action_send:
                Log.i(TAG, "User chose the Send action from Action Bar");
                notification();
                return true;
            case R.id.action_share:
                Log.i(TAG, "User chose the Share action from Action Bar");
                invalidateOptionsMenu();
                return true;
            case R.id.action_search:
                Log.i(TAG, "User chose the Search action, show a search bar UI");
                return true;
            case R.id.action_add:
                Log.i(TAG, "User chose the Add action from Action Bar");
                return true;

            case R.id.action_feedback:
                Log.i(TAG, "User chose the Feedback action from Action Bar");
                return true;
            case R.id.action_about:
                Log.i(TAG, "User chose the About action from Action Bar");
                return true;
            case R.id.action_quit:
                Log.i(TAG, "User chose the Quit action from Action Bar");
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.john.assignfour2017johnbukley/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.john.assignfour2017johnbukley/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
    public void notification()
    {

        Intent calendarIntent = new Intent(Intent.ACTION_INSERT);
        calendarIntent.setData(CalendarContract.Events.CONTENT_URI);
        calendarIntent.putExtra(CalendarContract.Events.TITLE, eventTitle);
        calendarIntent.putExtra(CalendarContract.Events.EVENT_LOCATION, eventLocation);

        PendingIntent resultPendingIntent =PendingIntent.getActivity(getApplicationContext(), 0,
                calendarIntent, FLAG_ACTIVITY_NEW_TASK);

        NotificationCompat.Builder mBuilder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_event_note_black_24dp)
                        .setContentTitle(notificationTitle)
                        .setContentText(notificationText)
                        .setVibrate(mVibratePattern)
                        .setContentIntent(resultPendingIntent)
                        .setAutoCancel(true)
                        .setSound(soundURI);


        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(MY_NOTIFICATION_ID, mBuilder.build());

    }
}