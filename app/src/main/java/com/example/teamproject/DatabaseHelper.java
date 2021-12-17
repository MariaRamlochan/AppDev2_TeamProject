package com.example.teamproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "test9.db";

    private static final String TABLE_USER = "User";
    private static final String TABLE_POST = "Post";

    private static final String COL_ID = "user_id";
    private static final String COL_USER_TYPE = "user_type";
    private static final String COL_EMAIL = "email";
    private static final String COL_PASSWORD = "password";
    private static final String COL_BUSINESS_NAME = "business_name";
    private static final String COL_PHONE_NUM = "phone_num";
    private static final String COL_ADDRESS = "address";
    private static final String COL_CITY = "city";
    private static final String COL_COUNTRY = "country";
    private static final String COL_USER_PIC = "user_pic";

    private static final String COL_POST_ID = "post_id";
    private static final String COL_POST_DESC = "post_desc";
    private static final String COL_POST_PIC = "post_pic";
    private static final String COL_POST_DATE = "post_date";
    private static final String COL_POST_STATUS = "post_status";
    private static final String COL_USER_ID = "user_id";
    Context context;

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
                        "%s TEXT);", TABLE_USER, COL_ID, COL_USER_TYPE, COL_EMAIL, COL_PASSWORD,
                COL_BUSINESS_NAME, COL_PHONE_NUM, COL_ADDRESS, COL_CITY, COL_COUNTRY, COL_USER_PIC);

        String post_table = "CREATE TABLE "
                + TABLE_POST + " ("
                + COL_POST_ID + " integer primary key autoincrement, "
                + COL_POST_DESC + " text not null, "
                + COL_POST_PIC + " text not null, "
                + COL_POST_DATE + " datetime default current_timestamp, "
                + COL_POST_STATUS + " text not null, "
                + COL_USER_ID + " integer,"
                + " FOREIGN KEY ("+COL_USER_ID+") REFERENCES "+TABLE_USER+"("+COL_ID+"));";

        sqLiteDatabase.execSQL(user_table);
        sqLiteDatabase.execSQL(post_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_POST);
        onCreate(sqLiteDatabase);
    }

    public Boolean insertDataUser(String userType, String email, String password, String businessName,
                                  String phoneNumber, String address, String city, String country,
                                  String userPic) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_TYPE, userType);
        contentValues.put(COL_EMAIL, email);
        contentValues.put(COL_PASSWORD, password);
        contentValues.put(COL_BUSINESS_NAME, businessName);
        contentValues.put(COL_PHONE_NUM, phoneNumber);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_CITY, city);
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
        @SuppressLint("Recycle") Cursor cursor =
                sqLiteDatabase.rawQuery("SELECT * FROM User WHERE email = ? ", new String[] {email});
        return cursor.getCount() > 0;
    }

    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM User WHERE email = ? AND password = ?",
                new String[] {email, password});
        return cursor.getCount() > 0;
    }

    public Cursor getUserId(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select user_id from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserType(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select user_type from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserPic(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select user_pic from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getSpecificUser(String user_id) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from User Where user_id = ? ",
                new String[] {user_id});
        return res;
    }

    public Cursor getUserBusinessName(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select business_name from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserPhone(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select phone_num from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserAddress(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select address from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserCity(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select city from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getUserCountry(String email) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select country from User Where email = ? ", new String[] {email});
        return res;
    }

    public Cursor getListDonors() {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from User " +
                "Where user_type = 'Donor' ", null);
        return res;
    }

    public Cursor getListBanks() {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from User " +
                "Where user_type = 'Food Bank' ", null);
        return res;
    }

    public boolean updateDataUser (String id, String businessName, String phone, String address,
                                   String city, String country, String pic) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_USER_ID, id);
        contentValues.put(COL_BUSINESS_NAME, businessName);
        contentValues.put(COL_PHONE_NUM, phone);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(COL_CITY, city);
        contentValues.put(COL_COUNTRY, country);
        contentValues.put(COL_USER_PIC, pic);
        db.update(TABLE_USER, contentValues, "user_id = ? ", new String[] {id});
        return  true;
    }

    public Integer deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_USER, COL_ID + " = ? ", new String[] {id});
    }

    public Boolean insertDataPost(String postDesc, String postPic, String user_id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        contentValues.put(COL_POST_DESC, postDesc);
        contentValues.put(COL_POST_DATE, strDate);
        contentValues.put(COL_POST_PIC, postPic);
        contentValues.put(COL_POST_STATUS, "available");
        contentValues.put(COL_USER_ID, user_id);
        long result = sqLiteDatabase.insert(TABLE_POST, null, contentValues);
        if (result == -1) {
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            Toast.makeText(context, "Added Successfully", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    public Cursor getAllDataPost() {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select Post.post_pic, Post.post_desc, " +
                "Post.post_date, User.email, User.phone_num " +
                "from User inner join Post on User.user_id = Post.user_id " +
                "where Post.post_status = 'available' ", null);
        return res;
    }


    public Cursor getAllDataUserPost(String user_id) {
        SQLiteDatabase  sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("Select * from  Post  Where user_id  = ? " +
                "and post_status = 'available' ",  new String[]
                {user_id});
        return res;
    }

    public boolean updateDataPost (String id, String desc, String pic, String userId, String status) {
        SQLiteDatabase db = this.getWritableDatabase();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_POST_ID, id);
        contentValues.put(COL_POST_DESC, desc);
        contentValues.put(COL_POST_PIC, pic);
        contentValues.put(COL_POST_DATE, strDate);
        contentValues.put(COL_POST_STATUS, status);
        contentValues.put(COL_USER_ID, userId);
        db.update(TABLE_POST, contentValues, "post_id = ? ", new String[] {id});
        return  true;
    }

    public Integer deleteDataPost(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_POST, COL_POST_ID + " = ? ", new String[] {id});
    }

}
