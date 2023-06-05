package com.example.app_user.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.app_user.DAO.TheLoaiDAO;
import com.example.app_user.Fragment.Create_Fragment;
import com.example.app_user.Model.TheLoai;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    TheLoaiDAO theLoaiDAO=new TheLoaiDAO();
    ArrayList<TheLoai> getList=new ArrayList<>();

    public ArrayList<TheLoai> getGetList(){
        getList=theLoaiDAO.getAll();
        return getList;
    }
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new Create_Fragment();
    }

    @Override
    public int getItemCount() {
        return getGetList().size();
    }
}
