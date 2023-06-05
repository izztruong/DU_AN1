package com.example.app_user.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.app_user.Model.Photo;
import com.example.app_user.R;

import java.util.List;


public class Photo_Adapter extends PagerAdapter {

    private Context context;
    private List<Photo> list_photo_adapter;

    public Photo_Adapter(Context context, List<Photo> list_photo) {
        this.context = context;
        this.list_photo_adapter = list_photo;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.layout_album, container, false);

        ImageView imgphto = view.findViewById(R.id.img_album);

        Photo photo = list_photo_adapter.get(position);
        if(photo != null){
            Glide.with(context).load(photo.getResourceID()).into(imgphto);
        }

        //add view to view group
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(list_photo_adapter != null){
            return list_photo_adapter.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //Remove view
        container.removeView((View) object);
    }
}
