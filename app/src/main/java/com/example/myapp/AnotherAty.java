package com.example.myapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AnotherAty extends AppCompatActivity{

    private TextView tv;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_aty);
        Intent i = getIntent();
//        Bundle data = i.getExtras();
        tv =  findViewById(R.id.tv);
        editText = findViewById(R.id.editText);
//        tv.setText(String.format("name=%s, age=%d",data.getString("name"), data.getInt("age")));
        User user = (User) i.getSerializableExtra("user");
        tv.setText(String.format("User info(name=%s, age=%d)",user.getName(),user.getAge()));
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("data",editText.getText().toString());
                setResult(1,i);
                finish();
            }
        });

    }
}
