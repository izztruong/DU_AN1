package com.example.app_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.app_user.DAO.tbGioHangDAO;
import com.example.app_user.Fragment.Danh_Sach_Fragment;
import com.example.app_user.Fragment.Gio_Hang_Fragment;
import com.example.app_user.Fragment.Home_Fragment;
import com.example.app_user.Fragment.Thong_Tin_Fragment;
import com.example.app_user.Interface.OnclickListen;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    public static int idKhachHang = 0;
    public static boolean checkDangNhap = false;
    BottomNavigationView bottom_nav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        int idkh = sharedPreferences.getInt("idkh", 0);
        idKhachHang = idkh;
        bottom_nav = findViewById(R.id.nav_bottom);
        clicknavbottom();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frame_Layout, new Home_Fragment()).commit();

    }


    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("THONGTIN", MODE_PRIVATE);
        int idkh = sharedPreferences.getInt("idkh", 0);
        idKhachHang = idkh;
        clicknavbottom();


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private void clicknavbottom() {

        bottom_nav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new Home_Fragment();
                        break;
                    case R.id.danhmuc:
                        fragment = new Danh_Sach_Fragment();
                        break;
                    case R.id.giohang:
                        if (idKhachHang != 0) {
                            fragment = new Gio_Hang_Fragment();
                            tbGioHangDAO tbGioHangDAO = new tbGioHangDAO(getApplicationContext());
                            if (tbGioHangDAO.delete()) {

                            } else {

                            }

                        } else {
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }
                        break;
                    case R.id.acc:
                        if (idKhachHang != 0) {
                            fragment = new Thong_Tin_Fragment();
                        } else {
                            Intent intent = new Intent(MainActivity.this, DangNhapActivity.class);
                            startActivity(intent);
                        }

                        break;
                    default:
                        fragment = new Home_Fragment();


                }
                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.frame_Layout, fragment).commit();
                }
                return true;
            }
        });
    }


}