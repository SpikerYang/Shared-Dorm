package com.example.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver1 extends BroadcastReceiver {
    public static final String ACTION = "com.example.myapp.intent.action.MyReceiver";
    public MyReceiver1() {

    }
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver1 received msg!");
    }
}
