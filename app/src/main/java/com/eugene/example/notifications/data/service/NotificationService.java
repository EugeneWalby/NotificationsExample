package com.eugene.example.notifications.data.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;

import com.eugene.example.notifications.R;

/**
 * Created by eugene on 10/30/2017.
 */
public class NotificationService extends IntentService {
    public NotificationService() {
        this(NotificationService.class.getName());
    }

    public NotificationService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(3000);
                showToast("Msg : " + i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected void showToast(final String msg) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Notification notification = new Notification.Builder(NotificationService.this)
                        .setContentTitle(msg)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setSound(alarmSound)
                        .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                        .setLights(Color.RED, 3000, 3000)
                        .build();
                ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
            }
        });
    }
}
