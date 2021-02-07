package com.one.shivam.Service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.one.shivam.Database.DatabaseHelper;
import com.one.shivam.MainActivity;
import com.one.shivam.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ForegroundService extends Service {

    public static final String CHANNEL_ID = "ForegroundServiceChannel";
    DatabaseHelper databaseHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh.mm.ss.S");
        String formattedDate = dateFormat.format(new Date()).toString();
        Log.e("TEST","get Time :"+formattedDate);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        databaseHelper = new DatabaseHelper(this);
        Log.d("serviewChek", "onStartCommand: call");
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("Foreground Service")
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        final Handler handler = new Handler();
        final int delay = 1000; // 1000 milliseconds == 1 second

        /*handler.postDelayed(new Runnable() {
            public void run() {

                handler.postDelayed(this, delay);
            }
        }, delay);*/
        //stopSelf();
        return START_NOT_STICKY;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Shivam Service Running",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }
}