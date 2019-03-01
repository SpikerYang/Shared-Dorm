package com.spike.bluetoothtest;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnBlueTooth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BTsearch.class));
            }
        });
        //httpGet
        findViewById(R.id.btnHttpGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncTask<String, Void, Void>() {
                    @Override
                    protected Void doInBackground(String... strings) {
                        try {
                            URL url = new URL(strings[0]);
                            try {
                                URLConnection connection = url.openConnection();
                                InputStream is = connection.getInputStream();
                                InputStreamReader isr = new InputStreamReader(is,"utf-8");
                                BufferedReader br = new BufferedReader(isr);
                                String line;
                                while ((line = br.readLine()) != null) {
                                    System.out.println(line);
                                }
                                br.close();
                                isr.close();
                                is.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute("http://openapi.youdao.com/api");
            }
        });
        //httpClientGet


    }

}
