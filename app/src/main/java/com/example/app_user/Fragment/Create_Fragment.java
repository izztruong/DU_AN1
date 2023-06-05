package com.example.app_user.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.app_user.Adapter.GridView_TL_Adapter;
import com.example.app_user.ChiTietSachActivity;
import com.example.app_user.DAO.SachDao;
import com.example.app_user.Model.Sach;
import com.example.app_user.R;

import java.util.ArrayList;


public class Create_Fragment extends Fragment {
    public  GridView gridView;
    public  GridView_TL_Adapter adapter;
    public  ArrayList<Sach> list;
    public static int id;
    private View view;
    public static Context context;
    public TextView soluongSP;
    public static int ID_TL=1;


    public Create_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_create_, container, false);
        gridView=view.findViewById(R.id.idGridView);

        soluongSP=view.findViewById(R.id.soLuongSP);
        //context=view.getContext();
        list=new ArrayList<>();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SachDao sachDao=new SachDao();
        list.clear();
        list=sachDao.getAlltheoTL(ID_TL);
        adapter=new GridView_TL_Adapter(getContext(),list);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sach sach=list.get(position);
                Intent intent=new Intent(getContext(), ChiTietSachActivity.class);
                intent.putExtra("chitietsanpham",sach);
                startActivity(intent);
            }
        });
        gridView.setAdapter(adapter);
        soluongSP.setText(list.size()+"sản phẩm");
    }
}