package com.otemainc.miniatendancemanager.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Db extends SQLiteOpenHelper {

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "att.db";
    private static final String SQL_CREATE_USERS_TABLE =  "CREATE TABLE tbl_users (" +
            "id int(11) PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "name varchar(100) NOT NULL," +
            "dept varchar(100) NOT NULL," +
            "email varchar(60) NOT NULL,"+
            "password varchar(255) NOT NULL);";
    private static final String SQL_CREATE_DEPT_TABLE =  "CREATE TABLE tbl_dept (" +
            "id int(11) PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "name varchar(100) NOT NULL," +
            "descr varchar(255) NOT NULL);";
    private static final String SQL_CREATE_ATTENDANCE_TABLE =  "CREATE TABLE tbl_attendance (" +
            "id int(11) PRIMARY KEY AUTOINCREMENT NOT NULL," +
            "staff varchar(100) NOT NULL," +
            "dept varchar(60) NOT NULL," +
            "timeIn varchar(100),"+
            "timeOut varchar(100));";

    private static final String SQL_DELETE_USERS_TABLE = "DROP TABLE IF EXISTS tbl_users";
    private static final String SQL_DELETE_DEPT_TABLE = "DROP TABLE IF EXISTS tbl_dept";
    private static final String SQL_DELETE_ATTENDANCE_TABLE = "DROP TABLE IF EXISTS tbl_attendance";

    public Db(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS_TABLE);
        db.execSQL(SQL_CREATE_DEPT_TABLE);
        db.execSQL(SQL_CREATE_ATTENDANCE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_USERS_TABLE);
        db.execSQL(SQL_DELETE_DEPT_TABLE);
        db.execSQL(SQL_DELETE_ATTENDANCE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    //Create
    public boolean addUser(String name,String dept, String email,String pass){
        boolean added;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("dept",dept);
        contentValue.put("email",email);
        contentValue.put("password", pass);
        long result = db.insert("tbl_users",null,contentValue);
        if(result==-1){
            added=false;
        }else{
            added=true;
        }
        return added;
    }
    public boolean addDept(String name, String descr){
        boolean addedDept;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("name",name);
        contentValue.put("descr",descr);
        long result = db.insert("tbl_dept",null,contentValue);
        if(result==-1){
            addedDept=false;
        }else{
            addedDept=true;
        }
        return addedDept;
    }
    public boolean addTimmeIn(String staff, String dept, String timeIn){
        boolean addedTimeIn;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("staff",staff);
        contentValue.put("dept",dept);
        contentValue.put("timeIn",timeIn);
        long result = db.insert("tbl_attendance",null,contentValue);
        if(result==-1){
            addedTimeIn=false;
        }else{
            addedTimeIn=true;
        }
        return addedTimeIn;
    }
    //Edit
    public boolean updateUser(String id, String name, String dept, String email, String pass){
                SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("id",id);
        contentValue.put("name",name);
        contentValue.put("dept",dept);
        contentValue.put("email",email);
        contentValue.put("password",pass);
        db.update("tbl_users",contentValue,"id=?",new String[]{id});
                return true;
    }
    public boolean updateDept(String id, String name, String descr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("id",id);
        contentValue.put("name",name);
        contentValue.put("descr",descr);
        db.update("tbl_dept",contentValue,"id=?",new String[]{id});
        return true;
    }
    public boolean AddTimeOut(String id, String staff, String dept, String timeIn, String timeOut){
        boolean addedTimeIn;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put("id",id);
        contentValue.put("staff",staff);
        contentValue.put("dept",dept);
        contentValue.put("timeIn",timeIn);
        contentValue.put("timeOut", timeOut);
        db.update("tbl_attendance", contentValue,"id=?",new String[]{id});
                return true;
    }
    //Retrieve
    public Cursor getAllUsers(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from tbl_users",null);
        return  res;

    }
    public Cursor getAllDepts(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from tbl_dept",null);
        return  res;

    }
    public Cursor getAllAttend(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from tbl_attendance",null);
        return  res;

    }
    //Delete
    public int deleteUser(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_users","id=?",new String[]{id});
    }
    public int deleteDept(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_dept","id=?",new String[]{id});
    }
    public int deleteAttend(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_attendance","id=?",new String[]{id});
    }
}
