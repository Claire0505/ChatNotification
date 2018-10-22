package com.claire.chatnotification;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;

//android.app.IntentService 大多數設計都在處理批次型的工作，傳送訊息等工作
//使用Intent物件並呼叫starService，它會自動以內建的佇列(Queue)依序處理這些Intent物件
public class HelloIntentService extends IntentService {
    public static final String PARAM_MSG = "message";

    public HelloIntentService() {
        super("HelloIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String msg = intent.getStringExtra(PARAM_MSG);
        SystemClock.sleep(3000);
        String debug = DateFormat.format("hh:mm:ss",System.currentTimeMillis())
                + "\t" + msg;
        Log.d("HelloIntentService", debug);

    }
}
