package com.example.myapp;

import android.os.Parcelable;

public class User {
    private String Username;
    private String Password;
    private String Id;
    private String Email;
    private String Phone;
    private String Jwt;

    public User(String username, String password, String id, String email, String phone, String jwt) {
        Username = username;
        Password = password;
        Id = id;
        Email = email;
        Phone = phone;
        Jwt = jwt;
    }

    public User(String username, String password, String id, String email, String phone) {
        Username = username;
        Password = password;
        Id = id;
        Email = email;
        Phone = phone;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(String id) {
        this.Id = id;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }

    public String getJwt() {
        return Jwt;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getId() {
        return Id;
    }

    public String getPhone() {
        return Phone;
    }

    public void setJwt(String jwt) {
        this.Jwt = jwt;
    }
}
