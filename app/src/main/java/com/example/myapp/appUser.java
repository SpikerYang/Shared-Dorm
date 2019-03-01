package com.example.myapp;

import android.app.Application;

public class appUser extends Application {
    static final String Server = "www.baidu.com";
    private boolean isLogin = false;
    private String Username;
    private String Password;

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setLogin(boolean islogin) {
        isLogin = islogin;
    }
}
