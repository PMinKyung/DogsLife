package com.example.user.dogslife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuTab extends AppCompatActivity {

    Button mBtCamera;
    Button mBtInformation;
    Button mBtDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_tab);

        mBtCamera = (Button) findViewById(R.id.bt_camera);
        mBtDiary = (Button) findViewById(R.id.bt_diary);
        mBtInformation = (Button) findViewById(R.id.bt_infor);

        mBtCamera.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Watch your Animal", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        Camera.class);
                startActivity(intent);
            }
        });

        mBtInformation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Get Information", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        Information.class);
                startActivity(intent);
            }
        });

        mBtDiary.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Write Diary", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        Diary.class);
                startActivity(intent);

            }
        });

    }
}
