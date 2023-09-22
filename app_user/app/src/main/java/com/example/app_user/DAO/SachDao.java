package com.example.app_user.DAO;

import android.util.Log;

import com.example.app_user.Database.DbSqlServer;
import com.example.app_user.Model.Sach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class SachDao {
    Connection connection;

    public SachDao() {
        DbSqlServer dbSqlServer=new DbSqlServer();
        connection= dbSqlServer.openConnect();

    }
    public List<Sach> getAll(){
List<Sach> list=new ArrayList<Sach>();
try {
    if (this.connection != null) {

        String sqlQuery = "SELECT * FROM Sach ";
        Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn
        ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

        while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

            Sach objCat = new Sach();
            objCat.setIdSach(resultSet.getInt("idSach")); // truyền tên cột dữ liệu
            objCat.setLinkAnh(resultSet.getString("linkAnh"));
            objCat.setGia(resultSet.getInt("giaTien"));
            objCat.setMoTa(resultSet.getString("moTa"));
            objCat.setIdNxb(resultSet.getInt("idNXB"));
            objCat.setTenSach(resultSet.getString("tenSach")); // tên cột dữ liệu là name
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
    public ArrayList<Sach> getAlltheoTL(int idTL){
        ArrayList<Sach> list=new ArrayList<Sach>();
        try {
            if (this.connection != null) {

                String sqlQuery = "SELECT * FROM Sach where idTheLoai="+String.valueOf(idTL);
                Statement statement = this.connection.createStatement(); // khởi tạo cấu trúc truy vấn
                ResultSet resultSet = statement.executeQuery(sqlQuery); // thực thi câu lệnh truy vấn

                while (resultSet.next()) { // đọc dữ liệu gán vào đối tượng và đưa vào list

                    Sach objCat = new Sach();
                    objCat.setIdSach(resultSet.getInt("idSach")); // truyền tên cột dữ liệu
                    objCat.setLinkAnh(resultSet.getString("linkAnh"));
                    objCat.setGia(resultSet.getInt("giaTien"));
                    objCat.setMoTa(resultSet.getString("moTa"));
                    objCat.setIdNxb(resultSet.getInt("idNXB"));
                    objCat.setTenSach(resultSet.getString("tenSach")); // tên cột dữ liệu là name
                    list.add(objCat);
                    //Log.e("aa","ok");
                }
            } // nếu kết nối khác null thì mới select và thêm dữ liệu vào, nếu không thì trả về ds rỗng
        } catch (Exception e) {
            Log.e("zzzzzzzzzz", "getAll: Có lỗi truy vấn dữ liệu getallsachtheoTL " );
            e.printStackTrace();
        }
        return list;

    }


}
