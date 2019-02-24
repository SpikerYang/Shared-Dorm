package com.example.myapp;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Binder;
import android.os.IBinder;

public class testService extends Service {
    private boolean service_running = false;
    private String data;
    public class Binder extends  android.os.Binder {
        public void setData(String data) {
            testService.this.data = data;
        }
    }
    public testService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("service start!");
        data = intent.getStringExtra("data");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("Service created!");
        service_running = true;
        new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (service_running) {
                    i++;
                    System.out.println(i+":"+data);
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("Service destroied!");
        service_running = false;
    }
}
