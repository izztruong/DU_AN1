package com.example.app_user.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app_user.Model.Sach;
import com.example.app_user.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Adapter extends BaseAdapter {
    private List<Sach> list;
    private Context context;

    public Home_Adapter(List<Sach> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public static class viewHolder{
        TextView txttensach,txtgiasach;
        ImageView ivsach;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        viewHolder holder;
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        if(view==null){
            view=inflater.inflate(R.layout.item_sanpham_home,null);
            holder=new viewHolder();
            holder.txtgiasach= view.findViewById(R.id.txtgiasach);
            holder.txttensach= view.findViewById(R.id.txttensaxh);
            holder.ivsach=view.findViewById(R.id.ivsach);
            view.setTag(holder);
        }else{
            holder=(viewHolder) view.getTag();
        }

        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivsach);

        holder.txttensach.setText(list.get(position).getTenSach());
        holder.txtgiasach.setText(list.get(position).getGia()+"đ");
        return view;
    }

}
