package com.example.teamproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class User extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "teamProject.db";
    private static final String TABLE_NAME = "User";
    private static final String COL_1 = "user_id";
    private static final String COL_2 = "user_type";
    private static final String COL_3 = "user_email";
    private static final String COL_4 = "password_hash";
    private SQLiteDatabase sqLiteDatabase;

    public User(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME +  " ("
                + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " + ""
                + COL_2 + " ENUM, "
                + COL_3 + " INTEGER, "
                + COL_4 + " INTEGER);";

        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addData(String name, int age, int phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_2,name);
        cv.put(COL_3,age);
        cv.put(COL_4,phone);

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
        return sqLiteDatabase.delete(TABLE_NAME, COL_1 + " = ? ", new String[]
                {String.valueOf(id)});
    }
}
