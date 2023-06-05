package com.example.app_user.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_user.Model.tbGioHang;
import com.example.app_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChiTietGioHangAdapter extends RecyclerView.Adapter<ChiTietGioHangAdapter.ViewHolder> {
    private ArrayList<tbGioHang> list;
    private Context context;

    public ChiTietGioHangAdapter(ArrayList<tbGioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_chitietgiohang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivanh);
        holder.txtgia.setText(String.valueOf(list.get(position).getGia())+"đ");
        holder.txtsoluong.setText(list.get(position).getSoLuong()+"");
        holder.txttensach.setText(list.get(position).getTenSach());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txttensach,txtgia,txtsoluong;
        ImageView ivanh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttensach=itemView.findViewById(R.id.txttensachctdh);
            txtgia=itemView.findViewById(R.id.txtgiactdh);
            txtsoluong=itemView.findViewById(R.id.txtsoluongchtdh);
            ivanh=itemView.findViewById(R.id.ivitemctdh);
        }
    }
}
