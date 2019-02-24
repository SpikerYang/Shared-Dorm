package com.example.myapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver2 extends BroadcastReceiver {
    public static final String ACTION = "com.example.myapp.intent.action.MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("MyReceiver2 receive msg!");
    }
}
