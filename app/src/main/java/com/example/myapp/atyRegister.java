package com.example.myapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class atyRegister extends AppCompatActivity {
    private EditText etResUserrname;
    private EditText etResPassword;
    private EditText etRePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_register);
        etResUserrname = findViewById(R.id.etResUsername); // Initiate
        etResPassword = findViewById(R.id.etResPassword);
        etRePassword = findViewById(R.id.etRePassword);

        findViewById(R.id.btnResRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etResUserrname.getText().toString();
                String password = etResPassword.getText().toString();
                String repassword = etRePassword.getText().toString();
                if (!password.equals(repassword)) {
                    Toast.makeText(atyRegister.this, "Passwords you typed in twice are different!", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println(username + ": "+ password + ": " + repassword);
                    // OKhttp register
                    appUser user = (appUser) getApplication();
                    OkHttpClient client = new OkHttpClient();

                    FormBody formBody = new FormBody.Builder()
                            .add("Username", username)
                            .add("Password", password)
                            .build();
                    final Request request = new Request.Builder()
                            .url(user.Server)
                            .post(formBody)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("Connection failed");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            System.out.println(response.body().string());
                        }
                    });

                }
            }
        });
    }
}
