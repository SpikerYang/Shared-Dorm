package com.example.myapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class atyLogin extends AppCompatActivity implements View.OnClickListener {
    private EditText etUsername;
    private EditText etPassword;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_aty_login);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        findViewById(R.id.btnSignIn).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                // OKhttp sign in
                signIn();
                break;
            case R.id.btnRegister:
                // register
                startActivity(new Intent(atyLogin.this, atyRegister.class));
                break;
        }
    }

    public void signIn() {
        final appUser app = (appUser) getApplication();
        OkHttpClient client = new OkHttpClient();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("stdId",etUsername.getText().toString());
            jsonObject.put("password",etPassword.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonObject.toString());

        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                , jsonObject.toString());
        Request request = new Request.Builder()
                .url(app.Server + getString(R.string.login))
                .post(requestBody)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("Login Connection failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strRes = response.body().string();
                System.out.println(strRes);

                try {
                    JSONObject state = new JSONObject(strRes);
                    JSONObject user = state.getJSONObject("user");
                    if (state.get("result").equals("success")) {
                        app.setLogin(true);
                        User userLogin = new User(user.getString("name"),etPassword.getText().toString(),
                                user.getString("stdId"),user.getString("email"),user.getString("phone"),state.getString("jwt"));
                        app.setUser(userLogin);
                        System.out.println(app.getUser().getEmail());
                    } else {
                        Toast.makeText(atyLogin.this,"Login failed!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}









