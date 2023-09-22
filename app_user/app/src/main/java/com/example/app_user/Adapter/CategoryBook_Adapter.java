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

import com.example.app_user.Interface.OnclickListen;
import com.example.app_user.Model.Sach;
import com.example.app_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CategoryBook_Adapter extends RecyclerView.Adapter<CategoryBook_Adapter.ViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    OnclickListen onclickListen;


    public CategoryBook_Adapter(Context context, ArrayList<Sach> list,OnclickListen onclickListen) {
        this.context = context;
        this.list = list;
        this.onclickListen=onclickListen;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=((Activity) context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.item_sanpham_home,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivsach);
        holder.txttensach.setText(list.get(position).getTenSach());
        holder.txtgiasach.setText("Giá: "+list.get(position).getGia()+"đ");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

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
