package com.example.app_user.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_user.Interface.OnclickListen;
import com.example.app_user.Model.Sach;
import com.example.app_user.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Home_Adapter extends RecyclerView.Adapter<Home_Adapter.ViewHolder> {
    private List<Sach> list;
    private Context context;
    OnclickListen onclickListen;

    public Home_Adapter(List<Sach> list, Context context, OnclickListen onclickListen) {
        this.list = list;
        this.context = context;
        this.onclickListen=onclickListen;
    }


//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return list.get(position);
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity) context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_sanpham_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivsach);
        holder.txttensach.setText(list.get(position).getTenSach());
        holder.txtgiasach.setText("Giá: "+list.get(position).getGia()+" đ");

    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

//    public static class viewHolder{
//        TextView txttensach,txtgiasach;
//        ImageView ivsach;
//    }

//    @Override
//    public View getView(int position, View view, ViewGroup parent) {
//        viewHolder holder;
//        LayoutInflater inflater=((Activity)context).getLayoutInflater();
//        if(view==null){
//            view=inflater.inflate(R.layout.item_sanpham_home,null);
//            holder=new viewHolder();
//            holder.txtgiasach= view.findViewById(R.id.txtgiasach);
//            holder.txttensach= view.findViewById(R.id.txttensaxh);
//            holder.ivsach=view.findViewById(R.id.ivsach);
//            view.setTag(holder);
//        }else{
//            holder=(viewHolder) view.getTag();
//        }
//
//        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivsach);
//
//        holder.txttensach.setText(list.get(position).getTenSach());
//        holder.txtgiasach.setText(list.get(position).getGia()+"đ");
//        return view;
//    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txttensach,txtgiasach;
        ImageView ivsach;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtgiasach= itemView.findViewById(R.id.txtgiasach);
            txttensach= itemView.findViewById(R.id.txttensaxh);
            ivsach=itemView.findViewById(R.id.ivsach);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onclickListen!=null){
                        int po=getAdapterPosition();
                        if(po!=RecyclerView.NO_POSITION){
                            onclickListen.OnClickSach(po);
                        }
                    }
                }
            });

        }
    }

}
