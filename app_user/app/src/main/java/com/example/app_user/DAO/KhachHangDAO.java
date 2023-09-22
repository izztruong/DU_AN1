package com.example.app_user.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.app_user.Database.DbSqlServer;
import com.example.app_user.Model.KhachHang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class KhachHangDAO {

    Connection objConn;

    public KhachHangDAO(Context context){
        // hàm khởi tạo để mở kết nối
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect();
        // tạo mới DAO thì mở kết nối CSDL

    }
    int idkh=0;
    public int checkdangnhap (String username, String pass) {
        int check = 0;
        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM KhachHang where username like '"+username+"'and pass like '"+pass+"'";

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list
                    idkh=resultSet.getInt("idKH");
                    check = resultSet.getInt("idKH");

                }

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }
        return check;
    }
    public KhachHang getThongTin( int idkh){
        KhachHang user=new KhachHang();
        try {
            if (this.objConn != null) {

                String sqlQuery = "SELECT * FROM KhachHang where idKH="+String.valueOf(idkh);

                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    user.setIdUser(resultSet.getInt("idKH"));
                    user.setHoTen(resultSet.getString("hoTen"));
                    user.setDiaChi(resultSet.getString("diaChi"));
                    user.setSdt(resultSet.getString("sdt"));
                    user.setTenDangNhap(resultSet.getString("username"));
                    user.setPass(resultSet.getString("pass"));

                }

            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu ");
            e.printStackTrace();
        }
        return user;
    }
}
