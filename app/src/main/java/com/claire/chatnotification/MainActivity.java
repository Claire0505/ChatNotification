package com.claire.chatnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
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

        //附加動作在通知中。點擊後會到指定的畫面去
        Intent intent = new Intent(this, ChatActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(ChatActivity.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //使用此方法，當按返回時不會回到上一個主畫面
//        PendingIntent pendingIntent =
//                PendingIntent.getActivities(this,  //參數傳入Context物件
//                        0,  //參數為指令一個辦識碼，目前只有一個因此直接使用0為其辦識碼
//                        new Intent[]{intent}, //第三個參數為intent物件
//                        PendingIntent.FLAG_UPDATE_CURRENT); //代表沿用舊的，並更新附帶資料

        //產生通知
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(android.R.drawable.ic_menu_today)
                        .setContentTitle("開發版本為Android8.0之後")
                        .setContentText("Testing...")
                        .setSubText("This is info")
                        .setWhen(System.currentTimeMillis()) //設定通知的時間，目前先設現在
                        .setChannelId(channelId)
                        .setContentIntent(pendingIntent); //設定PendingIntent物件到通知中
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
