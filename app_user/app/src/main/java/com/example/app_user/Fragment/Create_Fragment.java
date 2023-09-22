package com.example.app_user.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.app_user.Adapter.CategoryBook_Adapter;
import com.example.app_user.ChiTietSachActivity;
import com.example.app_user.DAO.SachDao;
import com.example.app_user.Interface.OnclickListen;
import com.example.app_user.Model.Sach;
import com.example.app_user.R;

import java.util.ArrayList;


public class Create_Fragment extends Fragment implements OnclickListen {
    public RecyclerView rcv;
    public CategoryBook_Adapter adapter;
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
        rcv=view.findViewById(R.id.rcvtheloai);

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
        adapter=new CategoryBook_Adapter(getContext(),list,this);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);
        soluongSP.setText(list.size()+"sản phẩm");
    }

    @Override
    public void OnClickSach(int po) {
        Sach sach=list.get(po);
                Intent intent=new Intent(getContext(), ChiTietSachActivity.class);
                intent.putExtra("chitietsanpham",sach);
                startActivity(intent);
    }
}