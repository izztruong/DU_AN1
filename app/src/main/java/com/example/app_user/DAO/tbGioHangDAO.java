package com.example.app_user.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app_user.Database.DBhelper;
import com.example.app_user.Model.GioHang;
import com.example.app_user.Model.tbGioHang;

import java.util.ArrayList;

public class tbGioHangDAO {
    DBhelper dBhelper;
    public tbGioHangDAO(Context context){
        dBhelper=new DBhelper(context);
    }
    public ArrayList<tbGioHang> getAll(){
        ArrayList<tbGioHang> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from GioHang",null);
        if(cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
               list.add(new tbGioHang(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean updateSoLuong(int id,int soLuong){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("soLuong",soLuong);
        long check=sqLiteDatabase.update("GioHang",values,"id=?",new String[]{String.valueOf(id)});
        if (check==-1){
            return false;
        }else {
            return true;
        }
    }
    public boolean themvaoHoaDon(tbGioHang gioHang){
        SQLiteDatabase db=dBhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("id",gioHang.getId());
        values.put("idSach",gioHang.getIdSach());
        values.put("tenSach",gioHang.getTenSach());
        values.put("gia",gioHang.getGia());
        values.put("linkAnh",gioHang.getLinkAnh());
        values.put("soLuong",gioHang.getSoLuong());
        long check=db.insert("GioHang",null,values);
        return (check>0);

    }
    public boolean deleteHoaDon(int id){
        SQLiteDatabase db=dBhelper.getWritableDatabase();
        long check=db.delete("GioHang","id=?",new String[]{String.valueOf(id)});
        if (check==-1)
            return false;
        return true;
    }
    public boolean delete(){
        SQLiteDatabase db=dBhelper.getWritableDatabase();
        long check=db.delete("GioHang",null,null);
        if (check==-1)
            return false;
            return true;
    }
}
