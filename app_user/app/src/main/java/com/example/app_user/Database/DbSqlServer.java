package com.example.app_user.Database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbSqlServer {
    Connection connection;
    final String TAG = "zzzzzz";

    public Connection openConnect(){
        try {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ip= "192.168.4.113";
            String url="jdbc:jtds:sqlserver://"+ip+";instance=SQLEXPRESS;user=sa;password=konenbiet1;databasename=BookShop;";
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            connection=DriverManager.getConnection(url);
            Log.e("ASK","Connnection Call");
        } catch (Exception e) {
            Log.e(TAG, "getCollection: Loi ket noi CSDL" );
            e.printStackTrace();
        }
        return connection;
    }
}
