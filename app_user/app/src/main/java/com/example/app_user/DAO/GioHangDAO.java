package com.example.app_user.DAO;

import android.util.Log;

import com.example.app_user.Database.DbSqlServer;
import com.example.app_user.Model.GioHang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GioHangDAO {
    Connection objConn;

    public GioHangDAO(){
        // hàm khởi tạo để mở kết nối
        DbSqlServer db = new DbSqlServer();
        objConn = db.openConnect(); // tạo mới DAO thì mở kết nối CSDL

    }

    public ArrayList<GioHang> getAllSachGioHang(String idKH){
        ArrayList<GioHang> list=new ArrayList<>();
        try {
            if (this.objConn != null) {

                String sqlQuery = "select s.idSach,s.tenSach,s.linkAnh,s.giaTien from GioHang as gh join Sach as s on gh.idSach=s.idSach join KhachHang as kh on kh.idKH=gh.idKH where gh.idKH="+idKH;
                Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    GioHang objCat = new GioHang();
                    objCat.setIdSach(resultSet.getInt("idSach")); // truyền tên cột dữ liệu
                    objCat.setLinkAnh(resultSet.getString("linkAnh"));
                    objCat.setTenSach(resultSet.getString("tenSach"));
                    objCat.setGia(resultSet.getInt("giaTien"));
                    // tên cột dữ liệu là name
                    list.add(objCat);
                    //Log.e("aa","ok");
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu " );
            e.printStackTrace();
        }
        return list;
    }

    public void ThemVaoGioHang(String idsach,String idkh)  {
        try {
            if(this.objConn!=null){
                String sql="insert into GioHang(idSach,idKH,soluong) values("+idsach+","+idkh+",1)";
                String generatedColums[]={"ID"};
                PreparedStatement statement=this.objConn.prepareStatement(sql,generatedColums);
                statement.execute();
            }
        }catch (Exception e){
            Log.e("izz","them vao gio hang co loi ");
        }

    }
    //check xem sp đã dc thêm vào gio hang hay chua
    public boolean checkthemgiohang (int idsach, int idkh) throws SQLException {
        String sqlQuery = "SELECT * FROM GioHang where idSach="+String.valueOf(idsach)+"and idKH="+String.valueOf(idkh);

        Statement statement = this.objConn.createStatement(); // khởi tạo cấu trúc truy vấn

        ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn
        if (resultSet!=null){
            return true;
        } else {
            return false;
        }
    }
    public void deleteGioHang(int idkh, int idsach){
        try {
            if (this.objConn != null) {
                String sqlDelete = "DELETE FROM GioHang WHERE idKH = "+idkh+" AND idSach = "+idsach+"";
                PreparedStatement stmt = this.objConn.prepareStatement(sqlDelete);
                stmt.execute(); // thực thi câu lệnh SQL
            }
        } catch (Exception e) {
            Log.i("TAG", "deleteGioHang: lỗi");
        }
    }
}
