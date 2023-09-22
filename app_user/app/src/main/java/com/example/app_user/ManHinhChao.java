package com.example.app_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class ManHinhChao extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chao);
        Handler handler=new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(ManHinhChao.this,MainActivity.class);
                startActivity(intent);
            }
        },2000);
    }
}