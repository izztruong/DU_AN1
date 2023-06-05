package com.example.app_user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_user.Database.DbSqlServer;
import com.google.android.material.textfield.TextInputEditText;

import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.Statement;

public class DangKiActivity extends AppCompatActivity {
    Button btn_signup, btn_signup_cancel;
    EditText edt_signup_username, edt_signup_email;
    TextInputEditText etPassword_signup, etPassword_re_signup;

    Statement stmt;
    DbSqlServer db = new DbSqlServer();
    Connection connection = db.openConnect();
    TextView txtlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        btn_signup = findViewById(R.id.btn_signup);
        btn_signup_cancel = findViewById(R.id.btn_signup_cancel);
        edt_signup_username = findViewById(R.id.edt_signup_username);
        edt_signup_email = findViewById(R.id.edt_signup_email);
        etPassword_signup = findViewById(R.id.etPassword_signup);
        etPassword_re_signup = findViewById(R.id.etPassword_re_signup);
        txtlogin=findViewById(R.id.tv_signup_login);
        btn_signup_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_signup_username.setText("");
                edt_signup_email.setText("");
                etPassword_signup.setText("");
                etPassword_re_signup.setText("");
            }
        });
        txtlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DangKiActivity.this,DangNhapActivity.class);
                startActivity(intent);
            }
        });


        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!etPassword_signup.getText().toString().equals(etPassword_re_signup.getText().toString())){
                    Toast.makeText(DangKiActivity.this, "Bạn phải nhập đúng mật khẩu như trên", Toast.LENGTH_SHORT).show();
                }
                else if (etPassword_signup.getText().toString().isEmpty() || etPassword_re_signup.getText().toString().isEmpty()||
                        edt_signup_email.getText().toString().isEmpty() || edt_signup_username.getText().toString().isEmpty()){

                    Toast.makeText(DangKiActivity.this, "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    new  registeruser().execute("");
                }

            }
        });

    }

    public class registeruser extends AsyncTask<String, String, String> {


        String z = "";
        Boolean isSucces = false;

        @Override
        protected void onPreExecute() {
            Toast.makeText(DangKiActivity.this, "lưu vào database", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(DangKiActivity.this, "đăng ký thành công", Toast.LENGTH_SHORT).show();
            edt_signup_username.setText("");
            edt_signup_email.setText("");
            etPassword_signup.setText("");
            etPassword_re_signup.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                String sql = "insert into KhachHang (username,diaChi,pass) values ('" + edt_signup_username.getText() + "','" + edt_signup_email.getText() + "','" + etPassword_signup.getText() + "')";
                stmt = connection.createStatement();
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                isSucces = false;
                z = e.getMessage();
            }

            return z;
        }
    }

}