package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, signUpBtn, viewBtn;
    EditText email, password;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.signUpButton);
        viewBtn = findViewById(R.id.viewButton);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        databaseHelper = new DatabaseHelper(this);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DatabaseViewActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String userType = "";
                String userPic = "";
                String userBusinessName = "";
                String userPhone = "";
                String userAddress = "";
                String userCity = "";
                String userCountry = "";

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "All Fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = databaseHelper.checkEmailPassword(user, pass);
                    if (checkUserPass) {
                        Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Cursor cursor = databaseHelper.getUserType(user);
                        Cursor cursor2 = databaseHelper.getUserPic(user);
                        Cursor cursor3 = databaseHelper.getUserBusinessName(user);
                        Cursor cursor4 = databaseHelper.getUserPhone(user);
                        Cursor cursor5 = databaseHelper.getUserAddress(user);
                        Cursor cursor6 = databaseHelper.getUserCity(user);
                        Cursor cursor7 = databaseHelper.getUserCountry(user);
                        if (cursor.moveToNext()) {
                            int userTypeColumn = cursor.getColumnIndex("user_type");
                            userType = cursor.getString(userTypeColumn);
                        }
                        if (cursor2.moveToNext()) {
                            int userTypeColumn = cursor2.getColumnIndex("user_pic");
                            userPic = cursor2.getString(userTypeColumn);
                        }
                        if (cursor3.moveToNext()) {
                            int userTypeColumn = cursor3.getColumnIndex("business_name");
                            userBusinessName = cursor3.getString(userTypeColumn);
                        }
                        if (cursor4.moveToNext()) {
                            int userTypeColumn = cursor4.getColumnIndex("phone_num");
                            userPhone = cursor4.getString(userTypeColumn);
                        }
                        if (cursor5.moveToNext()) {
                            int userTypeColumn = cursor5.getColumnIndex("address");
                            userAddress = cursor5.getString(userTypeColumn);
                        }
                        if (cursor6.moveToNext()) {
                            int userTypeColumn = cursor.getColumnIndex("city");
                            userCity = cursor6.getString(userTypeColumn);
                        }
                        if (cursor7.moveToNext()) {
                            int userTypeColumn = cursor7.getColumnIndex("country");
                            userCountry = cursor7.getString(userTypeColumn);
                        }
                        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                        intent.putExtra("email", user);
                        intent.putExtra("userType", userType);
                        intent.putExtra("userPic", userPic);
                        intent.putExtra("userBusinessName", userBusinessName);
                        intent.putExtra("userPhone", userPhone);
                        intent.putExtra("userAddress", userAddress);
                        intent.putExtra("userCity", userCity);
                        intent.putExtra("userCountry", userCountry);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

}