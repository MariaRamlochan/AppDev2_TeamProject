package com.example.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BankProfile extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "teamProject.db";
    private static final String TABLE_NAME = "BankProfile";
    private static final String COL_ID = "bank_id";
    private static final String COL_BUSINESS_NAME = "business_name";
    private static final String COL_PHONE_NUM = "phone_num";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ZIP_CODE = "zip_code";
    private static final String COL_CITY = "city";
    private static final String COL_PROVINCE = "province";
    private static final String COL_COUNTRY = "county";
    private SQLiteDatabase sqLiteDatabase;

    public BankProfile(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "%s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR, %s VARCHAR," +
                        "%s VARCHAR);",
                TABLE_NAME, COL_ID, COL_BUSINESS_NAME, COL_PHONE_NUM, COL_ADDRESS,
                COL_ZIP_CODE, COL_CITY, COL_PROVINCE, COL_COUNTRY);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addData(String business_name, String phone_num, String address, String zip_code,
                 String city, String province, String country) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_BUSINESS_NAME, business_name);
        cv.put(COL_PHONE_NUM, phone_num);
        cv.put(COL_ADDRESS, address);
        cv.put(COL_ZIP_CODE, zip_code);
        cv.put(COL_CITY, city);
        cv.put(COL_PROVINCE, province);
        cv.put(COL_COUNTRY, country);

        long result = db.insert(TABLE_NAME, null,cv);
        if (result == -1) {
            Toast.makeText(context,"Failed",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context,"Added Successfully",Toast.LENGTH_SHORT
            ).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    public Integer deleteData(int id){
        sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME, COL_ID + " = ? ", new String[]
                {String.valueOf(id)});
    }
}
