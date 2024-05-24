package com.rifat.sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME="my_database2";
    public  static final int DB_VERSION=1;


    public DataBaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table my_table (id INTEGER primary key autoincrement, name TEXT,gmail TEXT, password TEXT,mobile TEXT, semester TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists my_table");
    }


    public void SubmitData(String name,String gmail,String password,String mobile,String semester){
        SQLiteDatabase db=this.getWritableDatabase();

        ContentValues conval=new ContentValues();
        conval.put("name",name);
        conval.put("gmail",gmail);
        conval.put("password",password);
        conval.put("mobile",mobile);
        conval.put("semester",semester);

        // db.insert("user_table",null,conval);
        db.insert("my_table",null,conval);
    }

    //=============================================================
    //Amra jokon sokol data show korte cai

    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from my_table",null);
        return  cursor;
    }
    //=============================================================

    //=============================================================
    public Cursor SearchDataByName(String key){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from my_table where name like '%"+key+"%' ",null);
        return  cursor;
    }

    //=============================================================


    //=============================================================

    public void deleteData(String id){
        SQLiteDatabase db=this.getReadableDatabase();
        db.execSQL("delete from my_table where id like "+id);
    }
    //=============================================================


}
