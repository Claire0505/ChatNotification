package com.claire.chatnotification;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "onStartCommand");
        Log.d("MyService", "下載檔案中.....");

        //android.os.Handler的postDelayed方法，可以延後一定的時間後執行特定的執行緒作工作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("MyService", "下載完成");
            }
        },3000);

        Log.d("MyService", "onStartCommand將結束");
        return START_NOT_STICKY; //表示Service結束時並不會再重新啟動它

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
