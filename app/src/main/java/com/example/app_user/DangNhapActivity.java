package com.example.app_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_user.DAO.KhachHangDAO;
import com.example.app_user.Model.KhachHang;

public class DangNhapActivity extends AppCompatActivity {
    public static  SharedPreferences sharedPreferences;

    EditText edtusername, edtpass;
    Button btndangnhap;
    Toolbar toolbar;
    CheckBox cboghinho;
    TextView txtsignup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edtusername=findViewById(R.id.id_edt_login);
        edtpass=findViewById(R.id.edtPassword);
        btndangnhap=findViewById(R.id.btndangnhap);
        toolbar=findViewById(R.id.toolbardangnhap);
        cboghinho=findViewById(R.id.checkghinho);
        txtsignup=findViewById(R.id.tv_login_signup);
        KhachHangDAO dao=new KhachHangDAO(this);
        ActionToolBar();
        sharedPreferences=this.getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);


        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtusername.getText().toString().trim();
                String pass=edtpass.getText().toString().trim();
                int check=dao.checkdangnhap(username,pass);
                if(check !=0){
                    Intent intent =new Intent(DangNhapActivity.this,MainActivity.class);
                    Toast.makeText(DangNhapActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    MainActivity.idKhachHang=check;
//                    if(cboghinho.isChecked()) {

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        editor.putString("pass", pass);
                        editor.putInt("idkh", check);

                        editor.commit();
                    //}

                    startActivity(intent);
                }else {
                    Toast.makeText(DangNhapActivity.this, "tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangNhapActivity.this,DangKiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}