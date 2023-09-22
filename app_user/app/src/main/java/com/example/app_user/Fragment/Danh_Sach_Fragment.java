package com.example.app_user.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app_user.Adapter.ViewPagerAdapter;
import com.example.app_user.DAO.TheLoaiDAO;
import com.example.app_user.Interface.OnclickListen;
import com.example.app_user.Model.TheLoai;
import com.example.app_user.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;


public class Danh_Sach_Fragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    ArrayList<TheLoai> getList;


    public Danh_Sach_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_danh__sach_, container, false);
        setHasOptionsMenu(true);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ScrollTab(view);
    }

    private void ScrollTab(View view) {
        ViewPagerAdapter adapter=new ViewPagerAdapter(getActivity());
        tabLayout=view.findViewById(R.id.tabdanhmuc);
        viewPager=view.findViewById(R.id.viewpagedanhmuc);
        viewPager.setAdapter(adapter);
        TheLoaiDAO dao=new TheLoaiDAO();
        getList=dao.getAll();
        new TabLayoutMediator(tabLayout,viewPager,((tab, position) ->tab.setText(getList.get(position).getTenTheLoai()) )).attach();
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Create_Fragment.ID_TL=tab.getPosition()+1;
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }


}