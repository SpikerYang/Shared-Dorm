package com.example.myapp;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         setContentView(R.layout.activity_main);
        setContentView(R.layout.my_layout);
        result = findViewById(R.id.result);
        findViewById(R.id.btnStartAnotherAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,AnotherAty.class);
//                i.putExtra("data","hello 12345");
//                Bundle b = new Bundle();
//                b.putString("name","yqr")0;
//                b.putInt("age",2);
//                i.putExtras(b);

                i.putExtra("user", new User("222",4));

//                startActivity(i);

                startActivityForResult(i,0);
            }
        });
    };
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        result.setText("send back:"+data.getStringExtra("data"));
    };


}









