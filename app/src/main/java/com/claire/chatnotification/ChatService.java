package com.claire.chatnotification;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

//綁定型Service類別必須實作onBind方法，並回傳一個IBinder物件
//如Line、WhatsApp等即時訊息
public class ChatService extends Service {
    //設計一個內部類別ChatBinder並繼承Binder類別
    ChatBinder mBinder = new ChatBinder();
    public class ChatBinder extends Binder{
        public ChatService getService(){
            return ChatService.this;
        }

    }
    public ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return  mBinder;
    }

    public void sendMessage(String message){
        Log.d("ChatService", "sendMessage:" + message);
    }

    public void deleteMessage(String message){
        Log.d("ChatService", "deleteMessage:" + message);
    }
}
