package com.example.app_user.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public  static final String DB_NAME="GioHang";
    public DBhelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableGioHang="create table GioHang( id integer primary key,idSach integer,tenSach text,gia integer,linkAnh text,soLuong integer)";
              db.execSQL(createTableGioHang);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists GioHang");
        onCreate(db);

    }
}
