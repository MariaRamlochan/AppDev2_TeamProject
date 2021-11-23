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
    private static final String COL_ID = "user_id";
    private static final String COL_TYPE = "user_type";
    private static final String COL_EMAIL = "user_email";
    private static final String COL_PASSWORD = "password_hash";
    private SQLiteDatabase sqLiteDatabase;

    public User(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "%s ENUM ('Donor', 'Food Bank'), %s VARCHAR, %s VARCHAR);",
                TABLE_NAME, COL_ID, COL_TYPE, COL_EMAIL, COL_PASSWORD);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addData(String type, String email, String password_hash) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_TYPE,type);
        cv.put(COL_EMAIL,email);
        cv.put(COL_PASSWORD,password_hash);

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
