package com.example.user.dogslife;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    //Button mBtHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mBtHome = (Button) findViewById(R.id.bt_home);

        Handler handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                startActivity(new Intent(MainActivity.this, MenuTab.class));
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,2500);

    }
}
