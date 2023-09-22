package com.example.app_user.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app_user.DAO.KhachHangDAO;
import com.example.app_user.DangNhapActivity;
import com.example.app_user.MainActivity;
import com.example.app_user.Model.KhachHang;
import com.example.app_user.R;



public class Thong_Tin_Fragment extends Fragment {

    TextView txtten,txtdiachi,txtsodt;
    LinearLayout dangxuat;




    public Thong_Tin_Fragment() {
        // Required empty public constructor




    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_thong__tin_, container, false);
        txtten=view.findViewById(R.id.user_ten);
        txtdiachi=view.findViewById(R.id.user_diachi);
        txtsodt=view.findViewById(R.id.user_sdt);
        dangxuat=view.findViewById(R.id.idDangXuat);
        laodData();
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangNhapActivity.sharedPreferences=getContext().getSharedPreferences("THONGTIN", Context.MODE_PRIVATE);
                DangNhapActivity.sharedPreferences.edit().clear().commit();

                Intent intent=new Intent(getActivity(), DangNhapActivity.class);
                startActivity(intent);

            }
        });


        return view;
    }
    private void laodData(){
        KhachHangDAO dao =new KhachHangDAO(getContext());
        KhachHang kh=new KhachHang();
        kh=dao.getThongTin(MainActivity.idKhachHang);
        txtten.setText(kh.getHoTen());
        txtsodt.setText(kh.getSdt());
        txtdiachi.setText(kh.getDiaChi());
    }
}