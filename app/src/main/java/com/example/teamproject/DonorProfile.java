package com.example.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DonorProfile extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "teamProject.db";
    private static final String TABLE_NAME = "DonorProfile";
    private static final String COL_ID = "profile_id";
    private static final String COL_TYPE = "profile_type";
    private static final String COL_BUSINESS_NAME = "business_name";
    private static final String COL_PHONE_NUM = "phone_num";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIP_CODE = "zip_code";
    private static final String COL_CITY = "city";
    private static final String COL_PROVINCE = "province";
    private static final String COL_COUNTRY = "country";

    public DonorProfile(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s TEXT, %s TEXT, %s TEXT,  %s TEXT,  %s TEXT,  %s TEXT,  %s TEXT, %s TEXT);",
                TABLE_NAME, COL_ID, COL_TYPE, COL_BUSINESS_NAME, COL_PHONE_NUM, COL_ADDRESS,
                COL_ZIP_CODE, COL_CITY, COL_PROVINCE, COL_COUNTRY);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertData(String type, String business, String phone, String address, String zip, String city,
                              String province, String country) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_TYPE, type);
        contentValues.put(COL_BUSINESS_NAME, business);
        contentValues.put(COL_PHONE_NUM, phone);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_ZIP_CODE, zip);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_PROVINCE, province);
        contentValues.put(COL_COUNTRY, country);
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
}
