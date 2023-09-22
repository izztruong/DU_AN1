package com.example.app_user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_user.Adapter.ChiTietGioHangAdapter;
import com.example.app_user.DAO.HoaDonDAO;
import com.example.app_user.DAO.KhachHangDAO;
import com.example.app_user.DAO.tbGioHangDAO;
import com.example.app_user.Model.KhachHang;
import com.example.app_user.Model.tbGioHang;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class HoaDonChiTietActivity extends AppCompatActivity {
    TextView txtten,txtsdt,txtdiachi,txttongtienhang,txttongthanhtoan,txtdathang;
    RecyclerView rcvhdct;
    ArrayList<tbGioHang> list=new ArrayList<>();
    Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        list=new ArrayList<>();
        txtten=findViewById(R.id.txttencthd);
        txtsdt=findViewById(R.id.txtsdtcthd);
        txtdiachi=findViewById(R.id.txtdiachicthd);
        txttongtienhang=findViewById(R.id.txttongtienhang);
        txttongthanhtoan=findViewById(R.id.txttongthanhtoan);
        txtdathang=findViewById(R.id.tvdathang);
        rcvhdct=findViewById(R.id.rcvcthd);
        toolbar=findViewById(R.id.toolbarcthd);
        txtdathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickTVDatHang();
            }
        });

        setThongtin();
        setrcv();
        settoolbar();

    }
    private void setThongtin(){
        KhachHangDAO dao=new KhachHangDAO(this);
        KhachHang kh=new KhachHang();
        kh=dao.getThongTin(MainActivity.idKhachHang);
        txtten.setText(kh.getHoTen());
        txtsdt.setText(kh.getSdt());
        txtdiachi.setText(kh.getDiaChi());
    }
    private void setrcv(){
        tbGioHangDAO dao=new tbGioHangDAO(this);
        list.clear();
        list=dao.getAll();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        rcvhdct.setLayoutManager(linearLayoutManager);
        ChiTietGioHangAdapter adapter=new ChiTietGioHangAdapter(list,this);
        rcvhdct.setAdapter(adapter);
        int tongtien=0;
        //if(listgh!=null) {
        for (int i = 0; i < list.size(); i++) {
            tongtien += list.get(i).getGia() * list.get(i).getSoLuong();

        }
        txttongtienhang.setText(tongtien+"đ");
        txttongthanhtoan.setText((tongtien+50000)+"đ");


    }
    private void settoolbar(){
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
    private void clickTVDatHang(){
        tbGioHangDAO gioHangDAO=new tbGioHangDAO(this);
        list.clear();
        list=gioHangDAO.getAll();
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String datetime = dateformat.format(calendar.getTime());
        HoaDonDAO dao=new HoaDonDAO(this);
        long idhd=dao.themHoaDon(String.valueOf(MainActivity.idKhachHang),datetime);
        for(int i=0;i<list.size();i++){
            dao.themChiTietHoaDon(String.valueOf(list.get(i).getIdSach()),String.valueOf(idhd),String.valueOf(list.get(i).getSoLuong()),String.valueOf(list.get(i).getSoLuong()*list.get(i).getGia()));

        }
        gioHangDAO.delete();
        Toast.makeText(this, "Đặt hàng thành công", Toast.LENGTH_SHORT).show();
        finish();
        Intent intent=new Intent(HoaDonChiTietActivity.this,DatHangThanhCongActivity.class);
        startActivity(intent);

    }

}