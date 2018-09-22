package com.example.businessgroupmanger.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.businessgroupmanger.tables.Account;

public class AccountDAO {
    private DBHelper helper;
    private SQLiteDatabase db;
    private String dbname="account";

    public AccountDAO(Context context){
        helper=new DBHelper(context,dbname,null,1);
    }

    public void add(Account account){
        db=helper.getWritableDatabase();
        db.execSQL("INSERT INTO "+dbname+" VALUES(?,?)",new Object[]{account.getName(),account.getPassword()});
        db.close();
    }

    public Account find(String account_name){
        db=helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name, password from "+dbname+" where name = ?", new String[] {account_name});
        if (cursor.moveToNext()){
            return new Account(cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("password")));
        }
        db.close();
        return null;
    }
}
