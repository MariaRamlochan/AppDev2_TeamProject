package com.example.teamproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper synchronizedInstance;
    private static final String DATABASE_NAME = "test3.db";

    private static final String TABLE_USER = "User";
    private static final String TABLE_POST = "Post";

    private static final String COL_ID = "user_id";
    private static final String COL_USER_TYPE = "user_type";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_BUSINESS_NAME = "business_name";
    private static final String COL_PHONE_NUM = "phone_num";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIP_CODE = "zip_code";
    private static final String COL_CITY = "city";
    private static final String COL_PROVINCE = "province";
    private static final String COL_COUNTRY = "country";
    private static final String COL_USER_PIC = "user_pic";
    Context context;

    // Use the application context, which will ensure that we don't leak an Activity's context.
    public static synchronized DatabaseHelper getInstance(Context context) {

        if (synchronizedInstance == null) {
            synchronizedInstance = new DatabaseHelper(context.getApplicationContext());
        }
        return synchronizedInstance;
    }

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
        sqLiteDatabase.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String user_table = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, %s TEXT, " +
                        "%s TEXT, %s TEXT, %s TEXT);", TABLE_USER, COL_ID, COL_USER_TYPE,
                COL_EMAIL, COL_PASSWORD, COL_BUSINESS_NAME, COL_PHONE_NUM, COL_ADDRESS, COL_ZIP_CODE,
                COL_CITY, COL_PROVINCE, COL_COUNTRY, COL_USER_PIC);
        sqLiteDatabase.execSQL(user_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertDataUser(String userType, String email, String password, String businessName,
                                  String phoneNumber, String address, String zipCode, String city,
                                  String province, String country, String userPic) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_TYPE, userType);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_BUSINESS_NAME, businessName);
        contentValues.put(COL_PHONE_NUM, phoneNumber);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_ZIP_CODE, zipCode);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_PROVINCE, province);
        contentValues.put(COL_COUNTRY, country);
        contentValues.put(COL_USER_PIC, userPic);
        long result = sqLiteDatabase.insert(TABLE_USER, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public Cursor getAllDataUser() {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from " + TABLE_USER, null);
        return res;
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE email = ? ", new String[] {email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE email = ? AND password = ?",
                new String[] {email, password});
        return cursor.getCount() > 0;
    }


    public Integer deleteData(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_USER, COL_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }
}