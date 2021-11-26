package com.example.teamproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class User extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "teamProject.db";
    private static final String TABLE_NAME = "User";
    private static final String COL_ID = "user_id";
    private static final String COL_TYPE = "user_type";
    private static final String COL_EMAIL = "user_email";
    private static final String COL_PASSWORD = "password_hash";

    public User(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s TEXT, %s TEXT, %s TEXT);",
                TABLE_NAME, COL_ID, COL_TYPE, COL_EMAIL, COL_PASSWORD);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String type, String email, String password) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
            return false;
        }
        else {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase  db = this.getWritableDatabase();
        Cursor res = db.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE user_email = ? ", new String[] {email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE user_email = ? AND password_hash = ?",
                new String[] {email, password});
        return cursor.getCount() > 0;
    }
}

//    public User(@Nullable Context context) {
//        super(context, DATABASE_NAME, null, 1);
//        this.context = context;
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                "%s VARCHAR, %s VARCHAR, %s VARCHAR);",
//                TABLE_NAME, COL_ID, COL_TYPE, COL_EMAIL, COL_PASSWORD);
//        sqLiteDatabase.execSQL(query);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
//        onCreate(sqLiteDatabase);
//    }
//
//    void addData(String type, String email, String password_hash) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put(COL_TYPE,type);
//        cv.put(COL_EMAIL,email);
//        cv.put(COL_PASSWORD,password_hash);
//
//        long result = db.insert(TABLE_NAME, null,cv);
//        if (result == -1) {
//            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT
//            ).show();
//        }
//    }
//
//    Cursor readAllData() {
//        String query = "SELECT * FROM " + TABLE_NAME;
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = null;
//        if (db != null) {
//            cursor = db.rawQuery(query,null);
//        }
//        return cursor;
//    }
//
//    public Integer deleteData(int id){
//        sqLiteDatabase = this.getWritableDatabase();
//        return sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = ? ", new String[]
//                {String.valueOf(id)});
//    }
//}
