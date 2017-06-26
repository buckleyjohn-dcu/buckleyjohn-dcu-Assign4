package com.example.john.assignfour2017johnbukley;

import android.app.NotificationManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Events;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * Created by John on 07/06/2017.
 */
public class NotificationItem extends AppCompatActivity
{
    // Notification ID to allow for future updates
    private static final int MY_NOTIFICATION_ID = 1;
    String notificationTitle = "Restautant Notification!";
    String notificationText = "Notification: Save a date in your diary";
    private Uri soundURI = Uri
            .parse("android.resource://edu.dcu.cs.notifydemo/"
                    + R.raw.alarm_rooster);
    private long[] mVibratePattern = { 0, 200, 200, 300 };
    String eventTitle = "Christmas Party";
    String eventLocation="DCU Restaurant";



    public void notification()
    {

        Intent calendarIntent = new Intent(Intent.ACTION_INSERT);
        calendarIntent.setData(Events.CONTENT_URI);
        calendarIntent.putExtra(Events.TITLE, eventTitle);
        calendarIntent.putExtra(Events.EVENT_LOCATION, eventLocation);

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
