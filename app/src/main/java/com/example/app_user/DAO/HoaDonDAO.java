package com.example.app_user.DAO;

import android.content.Context;
import android.util.Log;

import com.example.app_user.Database.DBhelper;
import com.example.app_user.Database.DbSqlServer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HoaDonDAO {
    Connection connection;
    public HoaDonDAO(Context context){
        DbSqlServer db=new DbSqlServer();
        connection=db.openConnect();
    }
    public long themHoaDon(String idKh,String tg){
        long idhd=0;
        try {
            if(this.connection!=null){
                String sql="insert into HoaDon(idKH,idNV,ThoiGian,TrangThai) values("+idKh+",1,'"+tg+"',N'Chưa xác nhận')";
                String generatedColums[]={"ID"};
                PreparedStatement statement=this.connection.prepareStatement(sql,generatedColums);
                statement.execute();
                ResultSet rs = statement.getGeneratedKeys();
                if (rs.next()) {
                    idhd = rs.getLong(1);

                }
            }
        }catch (Exception e){
            Log.e("izz","them hoadon co loi ");
        }
        return idhd;
    }
    public void themChiTietHoaDon(String idSach,String idHoaDon,String soLuong,String donGia){
        try {
            if(this.connection!=null){
                String sql="insert into ChiTietHoaDon(idSach,idHoaDon,SoLuong,DonGia) values("+idSach+","+idHoaDon+","+soLuong+","+donGia+")";
                String generatedColums[]={"ID"};
                PreparedStatement statement=this.connection.prepareStatement(sql,generatedColums);
                statement.execute();
            }
        }catch (Exception e){
            Log.e("izz","them chitiethoadon co loi ");
        }
    }


}
