package com.example.myapp;

import android.app.Application;

public class appUser extends Application {
    static final String Server = "http://47.100.99.193:80";
    static private User user;
    static private boolean isLogin;

    @Override
    public void onCreate() {
        super.onCreate();
        setLogin(false);
    }

    public boolean isLogin() {
        return isLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLogin(boolean state) {
        isLogin = state;
    }
}
