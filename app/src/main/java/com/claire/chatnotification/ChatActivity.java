package com.claire.chatnotification;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ChatActivity extends AppCompatActivity implements ServiceConnection {
    ChatService mService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = new Intent(this, ChatService.class);
        bindService(intent, this,BIND_AUTO_CREATE);
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        ChatService.ChatBinder binder = (ChatService.ChatBinder) service;
        mService = binder.getService();
        Log.d("CHAT", "onServiceConnected: ");
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
        mService = null;
        Log.d("CHAT", "onServiceDisconnected: ");

    }

    //脫離已綁定服務
    @Override
    protected void onStop() {
        super.onStop();
        unbindService(this);
    }
}
