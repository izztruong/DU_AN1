package com.example.app_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class DatHangThanhCongActivity extends AppCompatActivity {
    Toolbar toolbar;
    LinearLayout donmua,trangchu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dat_hang_thanh_cong);
        toolbar=findViewById(R.id.toolbar);
        donmua=findViewById(R.id.idDonMua);
        trangchu=findViewById(R.id.idTrangChu);
        trangchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DatHangThanhCongActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Thanh Toán");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}