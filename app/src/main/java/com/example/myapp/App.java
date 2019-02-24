package com.example.myapp;

import android.app.Application;
/*
Store data for the whole project
 */
public class App extends Application {
    private String textdData = "yqr666";

    public void setTextdData(String textdData) {
        this.textdData = textdData;
    }

    public String getTextdData() {
        return textdData;
    }
}
