package com.example.myapp;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

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

public class atyRegister extends AppCompatActivity {
    private EditText etResUsername;
    private EditText etResEmail;
    private EditText etResId;
    private EditText etResPhone;
    private EditText etResPassword;
    private EditText etRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aty_register);
        etResUsername = findViewById(R.id.etResUsername); // Initiate
        etResPassword = findViewById(R.id.etResPassword);
        etRePassword = findViewById(R.id.etRePassword);
        etResEmail = findViewById(R.id.etResEmail);
        etResId = findViewById(R.id.etResId);
        etResPhone = findViewById(R.id.etResPhone);

        findViewById(R.id.btnResRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etResUsername.getText().toString();
                String password = etResPassword.getText().toString();
                String repassword = etRePassword.getText().toString();
                String email = etResEmail.getText().toString();
                String id = etResId.getText().toString();
                String phone = etResPhone.getText().toString();

                if (!password.equals(repassword)) {
                    Toast.makeText(atyRegister.this, "Passwords you typed in twice are different!", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println(username + ": "+ password + ": " + repassword);
                    // OKhttp register
                    final appUser app = (appUser) getApplication();
                    OkHttpClient client = new OkHttpClient();

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("stdId",id);
                        jsonObject.put("username",username);
                        jsonObject.put("password",password);
                        jsonObject.put("email",email);
                        jsonObject.put("phone",phone);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    System.out.println(jsonObject.toString());

                    RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8")
                            , jsonObject.toString());
                    Request request = new Request.Builder()
                            .url(app.Server + getString(R.string.register))
                            .post(requestBody)
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("Register Connection failed");
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String strRes = response.body().string();
                            System.out.println(strRes);
                            try {
                                JSONObject state = new JSONObject(strRes);
                                if (state.get("result").equals("success")) {
                                    Toast.makeText(atyRegister.this,"Registration success!",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(atyRegister.this,"Registration failed!",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });

                }
            }
        });
    }
}
