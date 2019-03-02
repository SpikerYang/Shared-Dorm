package com.example.myapp;

import android.app.Application;

public class appUser extends Application {
    static final String Server = "http://47.100.99.193:80";
    private User user;
    private boolean isLogin = false;

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
