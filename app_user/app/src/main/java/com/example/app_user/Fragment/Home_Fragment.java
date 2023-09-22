package com.example.app_user.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.app_user.Adapter.Home_Adapter;
import com.example.app_user.Adapter.Photo_Adapter;
import com.example.app_user.ChiTietSachActivity;
import com.example.app_user.DAO.SachDao;
import com.example.app_user.Interface.OnclickListen;
import com.example.app_user.Model.Photo;
import com.example.app_user.Model.Sach;
import com.example.app_user.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;


public class Home_Fragment extends Fragment implements OnclickListen {

    RecyclerView rcv;
    private List<Sach> list=new ArrayList<>();
    private ViewPager id_viewpager;
    private CircleIndicator circle_indiacator;
    private Photo_Adapter photo_adapter;
    private List<Photo> list_photo;
    private Timer timer;


    public Home_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home_, container, false);
        rcv=view.findViewById(R.id.rcvtrangchu);


        id_viewpager = view.findViewById(R.id.id_viewpager);
        circle_indiacator = view.findViewById(R.id.circle_indiacator);

        SachDao sachDao=new SachDao();
        list = sachDao.getAll();

        Home_Adapter adapter=new Home_Adapter(list,getContext(),this);
        GridLayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        rcv.setLayoutManager(layoutManager);
        rcv.setAdapter(adapter);

//        gridViewtrangchu.setAdapter(adapter);
//        registerForContextMenu(gridViewtrangchu);
//        gridViewtrangchu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Sach sach=list.get(position);
//                Intent intent=new Intent(getContext(), ChiTietSachActivity.class);
//                intent.putExtra("chitietsanpham",sach);
//                startActivity(intent);
//            }
//        });

        list_photo = getListPhoto();
        photo_adapter = new Photo_Adapter(getActivity(),list_photo);
        id_viewpager.setAdapter(photo_adapter);

        circle_indiacator.setViewPager(id_viewpager);
        photo_adapter.registerDataSetObserver(circle_indiacator.getDataSetObserver());

        autoSLideImages();

        return view;
    }
    private List<Photo> getListPhoto() {
        List<Photo> photo_list = new ArrayList<>();

        photo_list.add(new Photo(R.drawable.ngon_tinh_1));
        photo_list.add(new Photo(R.drawable.ngon_tinh_2));
        photo_list.add(new Photo(R.drawable.doraemon_1));
        photo_list.add(new Photo(R.drawable.bachuloncon));

        return photo_list;
    }

    private void Themsp(){

    }
    private void autoSLideImages(){

        if(list_photo == null || list_photo.isEmpty() || id_viewpager == null){
            return;
        }

        //khoi tao timer
        if(timer == null){
            timer = new Timer();
        }

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        int currentItem = id_viewpager.getCurrentItem();
                        int totalItem = list_photo.size() - 1;
                        if(currentItem < totalItem){
                            currentItem ++;
                            id_viewpager.setCurrentItem(currentItem);
                        }
                        else{
                            id_viewpager.setCurrentItem(0);
                        }
                    }
                });
            }
        }, 500, 2222); // prriod : thời gian chuyển ảnh
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
            timer = null;
        }

    }

    @Override
    public void OnClickSach(int po) {
        Sach sach=list.get(po);
                Intent intent=new Intent(getContext(), ChiTietSachActivity.class);
                intent.putExtra("chitietsanpham",sach);
                startActivity(intent);
    }
}