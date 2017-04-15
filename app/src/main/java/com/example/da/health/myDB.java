package com.example.da.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

/**
 * Created by DELL on 2017/4/15.
 */

public class myDB extends SQLiteOpenHelper{
    private static final String DB_NAME = "DB.db";
    private static final String TABLE_NAME = "HeartRate";
    private static final String TABLE_NAME1 = "PersonalInformation";
    private static final int DB_VERSION = 1;

    //构造函数建立数据库
    public myDB(Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
        String CREATE_TABLE1 = "CREATE TABLE if not exists "+TABLE_NAME1+"(date text primary key, gender text, height text, weight text);";
        sqLiteDatabase.execSQL(CREATE_TABLE1);
       // String CREATE_TABLE = "CREATE TABLE if not exists "+TABLE_NAME+"(date text primary key, rate text)";
      //  sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int il){
    }

    /*
     心率表
     */
    public void insert2DB(String date,String rate){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date",date);
        cv.put("rate",rate);
        db.insert(TABLE_NAME,null,cv);
        db.close();
    }

    public void update2DB(String date,String rate){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("rate",rate);
        String whereClause = "date = ?";
        String[] whereArgs = {date};
        db.update(TABLE_NAME,cv,whereClause,whereArgs);
        db.close();
    }

    public Cursor query2DB(){
        SQLiteDatabase db;
        db = getReadableDatabase();
        return db.rawQuery("select * from " + "'"+TABLE_NAME+"'", null);
    }
    public boolean isDuplicated(String dateIn){
        Cursor cursor = query2DB();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int dateColumn = cursor.getColumnIndex("date");
            String date1 = cursor.getString(dateColumn);
            if( TextUtils.equals(date1,dateIn)) return true;
        }
        return false;
    }


    /*
    个人信息表
     */
    public void insert2PerInfoTable(String date,String gender,String height,String weight){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("date",date);
        cv.put("gender",gender);
        cv.put("height",height);
        cv.put("weight",weight);
        db.insert(TABLE_NAME1,null,cv);
        db.close();
    }

    public void update2PerInfoTable(String date,String gender,String height,String weight){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("gender",gender);
        cv.put("height",height);
        cv.put("weight",weight);
        String whereClause = "date = ?";
        String[] whereArgs = {date};
        db.update(TABLE_NAME1,cv,whereClause,whereArgs);
        db.close();
    }

    public Cursor queryPerInfoTable(){
        SQLiteDatabase db;
        db = getReadableDatabase();
        return db.rawQuery("select * from " + "'"+TABLE_NAME1+"'"+" order by date desc", null);
    }
    public boolean isDuplicated_PerInfoTable(String dateIn){
        Cursor cursor = queryPerInfoTable();
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            int dateColumn = cursor.getColumnIndex("date");
            String date1 = cursor.getString(dateColumn);
            if( TextUtils.equals(date1,dateIn)) return true;
        }
        return false;
    }

}
