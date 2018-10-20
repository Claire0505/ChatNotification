package com.claire.chatnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // makeNotification(); //開發版本為Android8.0以前
                makOreoNotification(); //開發版本為Android8.0之後
            }
        });
    }

    //開發版本為Android8.0之後
    private void makOreoNotification() {
        String channelId = "love";
        String channelName = "我的最愛";
        final int NOTIFICATION_ID = 8;
        NotificationManager manager = getNotificationManager(channelId, channelName);

        //產生通知
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_menu_today)
                        .setContentTitle("開發版本為Android8.0之後")
                        .setContentText("Testing...")
                        .setSubText("This is info")
                        .setWhen(System.currentTimeMillis()) //設定通知的時間，目前先設現在
                        .setChannelId(channelId);
        //送出通知
        manager.notify(1, builder.build());

    }

    @NonNull
    private NotificationManager getNotificationManager(String channelId, String channelName) {
        //取得通知管理器
        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        //產生通知頻道
        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    channelId,
                    channelName, NotificationManager.IMPORTANCE_HIGH);

            //產生頻道
            manager.createNotificationChannel(channel);
        }

        return manager;
    }

    //開發版本為android8.0以前
    private void makeNotification() {
        //取得通知管理器
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //產生通知
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "channel")
                .setSmallIcon(android.R.drawable.ic_menu_today)
                .setContentTitle("This is Title")
                .setContentText("Testing...")
                .setSubText("This is info")
                .setWhen(System.currentTimeMillis()); //設定通知的時間，目前先設現在
        //送出通知
        manager.notify(1, builder.build());


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
