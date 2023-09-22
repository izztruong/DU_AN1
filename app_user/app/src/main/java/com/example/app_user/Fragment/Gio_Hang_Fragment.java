package com.example.app_user.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.app_user.Adapter.GioHang_Adapter;
import com.example.app_user.DAO.GioHangDAO;
import com.example.app_user.DAO.tbGioHangDAO;
import com.example.app_user.HoaDonChiTietActivity;
import com.example.app_user.MainActivity;
import com.example.app_user.Model.GioHang;
import com.example.app_user.Model.tbGioHang;
import com.example.app_user.R;

import java.util.ArrayList;


public class Gio_Hang_Fragment extends Fragment {
    GioHangDAO gioHangDAO;
    RecyclerView recyclerGioHang;
    ArrayList<GioHang> list;
    Button btnmuahang;

    CheckBox cbogiohang;
    TextView giaall;
    ArrayList<tbGioHang> listgh=new ArrayList<>();
    public static boolean check;
    tbGioHangDAO dao;




    public Gio_Hang_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_gio__hang_, container, false);
        recyclerGioHang=view.findViewById(R.id.rvgiohang);
        giaall=view.findViewById(R.id.txttongtien);
        cbogiohang=view.findViewById(R.id.cboall);
        btnmuahang=view.findViewById(R.id.btnmuahang);

        btnmuahang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao=new tbGioHangDAO(getContext());

                 listgh=dao.getAll();
                if(list.size()!=0){
                    Intent intent=new Intent(getActivity(), HoaDonChiTietActivity.class);

                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "vui lòng chọn sản phẩm", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cbogiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbogiohang.isChecked()){
                    checkall();

                    check=true;
                    loadData();
                }else {
                    tbGioHangDAO dao=new tbGioHangDAO(getContext());
                    dao.delete();
                    check=false;
                    loadData();
                    giaall.setText("0đ");
                }
                loadData();

            }
        });

        loadData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        },500);

        return view;
    }

    private void loadData() {
        int idKh= MainActivity.idKhachHang;
        gioHangDAO= new GioHangDAO();
        list=gioHangDAO.getAllSachGioHang(String.valueOf(idKh));
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerGioHang.setLayoutManager(linearLayoutManager);
        GioHang_Adapter adapter=new GioHang_Adapter(list,getContext());


        adapter.setOnItemClickedListener(new GioHang_Adapter.onItemClickedListener() {
            @Override
            public void onItemClick(GioHang giohang,int position) {
                if (giohang.getCbo()==1){
                    cbogiohang.setChecked(false);
                }
                tbGioHangDAO tbGioHangDAO=new tbGioHangDAO(getContext());
                listgh.clear();
                listgh=tbGioHangDAO.getAll();
                int tongtien=0;
                //if(listgh!=null) {
                for (int i = 0; i < listgh.size(); i++) {
                    tongtien += listgh.get(i).getGia() * listgh.get(i).getSoLuong();

                }

                giaall.setText(tongtien+"đ");
            }
        });

        recyclerGioHang.setAdapter(adapter);

    }
    private void checkall(){
        int idKh= MainActivity.idKhachHang;
        gioHangDAO= new GioHangDAO();
        list=gioHangDAO.getAllSachGioHang(String.valueOf(idKh));
        tbGioHangDAO dao=new tbGioHangDAO(getContext());
        dao.delete();
        for(int i=0;i<list.size();i++){
            tbGioHang tbGioHang=new tbGioHang();
            tbGioHang.setId(i+1);
            tbGioHang.setTenSach(list.get(i).getTenSach());
            tbGioHang.setGia(list.get(i).getGia());
            tbGioHang.setLinkAnh(list.get(i).getLinkAnh());
            tbGioHang.setIdSach(list.get(i).getIdSach());
            tbGioHang.setSoLuong(1);
            dao.themvaoHoaDon(tbGioHang);
        }
        listgh.clear();
        listgh=dao.getAll();
        int tongtien=0;
        //if(listgh!=null) {
        for (int i = 0; i < listgh.size(); i++) {
            tongtien += listgh.get(i).getGia() * listgh.get(i).getSoLuong();

        }

        giaall.setText(tongtien+"đ");
    }

}