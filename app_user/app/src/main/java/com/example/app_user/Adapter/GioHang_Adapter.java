package com.example.app_user.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_user.DAO.tbGioHangDAO;
import com.example.app_user.Fragment.Gio_Hang_Fragment;
import com.example.app_user.Model.GioHang;
import com.example.app_user.Model.tbGioHang;
import com.example.app_user.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class GioHang_Adapter extends RecyclerView.Adapter<GioHang_Adapter.ViewHolder>  {
    private ArrayList<GioHang> list;
    private Context context;

    public GioHang_Adapter(ArrayList<GioHang> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_gio_hang,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (Gio_Hang_Fragment.check){
            holder.cboitemgiohang.setChecked(true);
        }else {
            holder.cboitemgiohang.setChecked(false);
        }
        holder.txtten.setText(list.get(position).getTenSach());
        holder.txtgia.setText(String.valueOf("Giá:"+ list.get(position).getGia())+"đ");
        Picasso.get().load(list.get(position).getLinkAnh()).into(holder.ivanh);
        holder.txtsoluong.setText("1");
        holder.ivcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem=Integer.parseInt(String.valueOf(holder.txtsoluong.getText()));
                dem++;
                holder.txtsoluong.setText(String.valueOf(dem));
                GioHang giohang=new GioHang();
                if(holder.cboitemgiohang.isChecked()){
                    tbGioHangDAO tbGioHangDAO=new tbGioHangDAO(context);
                    if(tbGioHangDAO.updateSoLuong(position+1,dem)){

                    }else {

                    }
                }

                if(onItemClickedListener!=null){
                    onItemClickedListener.onItemClick(giohang,position);
                }
            }
        });
        holder.ivchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int dem=Integer.parseInt(String.valueOf(holder.txtsoluong.getText()));
                dem--;
                if(dem<1){
                    dem=1;
                }
                holder.txtsoluong.setText(String.valueOf(dem));
                GioHang giohang=new GioHang();
                if(holder.cboitemgiohang.isChecked()){
                    tbGioHangDAO tbGioHangDAO=new tbGioHangDAO(context);
                    if(tbGioHangDAO.updateSoLuong(position+1,dem)){

                    }else {

                    }
                }
                if(onItemClickedListener!=null){
                    onItemClickedListener.onItemClick(giohang,position);
                }
            }
        });
        holder.cboitemgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang gioHang=new GioHang();
                gioHang.setCbo(1);
                tbGioHang tbgiohang=new tbGioHang();
                tbgiohang.setId(position+1);
                tbgiohang.setIdSach(list.get(position).getIdSach());
                tbgiohang.setGia(list.get(position).getGia());
                tbgiohang.setLinkAnh(list.get(position).getLinkAnh());
                tbgiohang.setTenSach(list.get(position).getTenSach());
                tbgiohang.setSoLuong(Integer.parseInt(holder.txtsoluong.getText().toString()));


                if(holder.cboitemgiohang.isChecked()){
                    tbGioHangDAO tbGioHangDAO=new tbGioHangDAO(context);
                    if (tbGioHangDAO.themvaoHoaDon(tbgiohang)){

                    }else {

                    }

                }else {
                    tbGioHangDAO tbGioHangDAO=new tbGioHangDAO(context);
                    if(tbGioHangDAO.deleteHoaDon(position+1)){

                    }else {

                    }
                }
                if(onItemClickedListener!=null){
                    onItemClickedListener.onItemClick(gioHang,position);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten,txtgia,txtsoluong;
        ImageView ivcong,ivchu,ivanh;
        CheckBox cboitemgiohang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtten=itemView.findViewById(R.id.txttensachgiohang);
            txtgia=itemView.findViewById(R.id.txtgiagiohang);
            txtsoluong=itemView.findViewById(R.id.txtsoluonggiohang);
            ivanh=itemView.findViewById(R.id.ivitemgiohang);
            ivchu=itemView.findViewById(R.id.ivtrugiohang);
            ivcong=itemView.findViewById(R.id.ivcongsgiohang);
            cboitemgiohang=itemView.findViewById(R.id.cboitem);

        }
    }
    public  interface onItemClickedListener{
        void onItemClick(GioHang giohang,int position);
    }
    private onItemClickedListener onItemClickedListener;
    public void  setOnItemClickedListener(onItemClickedListener onItemClickedListener){
        this.onItemClickedListener=onItemClickedListener;
    }


}
