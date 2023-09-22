package com.example.app_user.DAO;

import android.util.Log;

import com.example.app_user.Database.DbSqlServer;
import com.example.app_user.Model.TheLoai;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TheLoaiDAO {
    Connection connection;

    public TheLoaiDAO() {
        DbSqlServer db = new DbSqlServer();
        connection = db.openConnect();

    }
    public ArrayList<TheLoai> getAll(){
        ArrayList<TheLoai> list=new ArrayList<>();
        try {
            if(this.connection!=null){
                String sqlQuery="select * from TheLoai";
                Statement statement=this.connection.createStatement();
                ResultSet resultSet=statement.executeQuery(sqlQuery);
                while (resultSet.next()){
                    TheLoai tl=new TheLoai();
                    tl.setIdTheLoai(resultSet.getInt("idTheLoai"));
                    tl.setTenTheLoai(resultSet.getString("tenTheLoai"));
                    list.add(tl);
                }
            }
        }catch (Exception e){
            Log.i("tag","get all lỗi");
        }


        return list;
    }
}
