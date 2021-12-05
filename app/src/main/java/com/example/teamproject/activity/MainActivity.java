package com.example.teamproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teamproject.R;
import com.example.teamproject.database.DatabaseHelper;

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
                Intent intent = new Intent(MainActivity.this, ChildView.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "All Fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = databaseHelper.checkEmailPassword(user, pass);
                    if (checkUserPass) {
                        Toast.makeText(MainActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, TestingPage.class);
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