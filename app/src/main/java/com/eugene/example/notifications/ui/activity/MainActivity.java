package com.eugene.example.notifications.ui.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.eugene.example.notifications.R;
import com.eugene.example.notifications.data.service.NotificationService;

public class MainActivity extends AppCompatActivity {
    public static final String NOTIFICATION_TITLE = "Test notification";
    public static final String WEB_PAGE_URL = "http://www.google.com";
    public static final String NOTIFICATION_MSG = "Go to " + WEB_PAGE_URL;
    public static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runNotificationService();
    }

    public void onSendNotification(View view) {
        PendingIntent moveToWebPageIntent = createMoveToWebPageIntent();
        Notification notification = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(NOTIFICATION_TITLE)
                        .setContentText(NOTIFICATION_MSG)
                        .setContentIntent(moveToWebPageIntent)
                        .build();
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private void runNotificationService() {
        Intent intentService = new Intent(this, NotificationService.class);
        startService(intentService);
    }

    private PendingIntent createMoveToWebPageIntent() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_PAGE_URL));
        return PendingIntent.getActivity(this, 0, intent, 0);
    }
}
