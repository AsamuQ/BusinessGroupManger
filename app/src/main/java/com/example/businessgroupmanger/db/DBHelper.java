package com.example.businessgroupmanger.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private String dbname;

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name+".db", factory, version);
        dbname=name;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+dbname+" (name varchar(20) primary key, password varchar)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+dbname); //删除数据表，谨慎使用
        onCreate(db); //重新建表
    }
}
