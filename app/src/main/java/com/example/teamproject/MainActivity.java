package com.example.teamproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginBtn, signUpBtn, viewBtn;
    EditText email, password;
    User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = findViewById(R.id.loginButton);
        signUpBtn = findViewById(R.id.signUpButton);
        viewBtn = findViewById(R.id.viewButton);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        myUser = new User(this);

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myUser.getAllData();
                if(res.getCount() == 0) {
                    showMessage ("error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append( "Type : " + res.getString(1) + "\n");
                    buffer.append("Email : " + res.getString(2) + "\n");
                    buffer.append( "Password : " + res.getString(3) + "\n");
                }
                showMessage ("Data ", buffer.toString());
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Sign in was clicked", Toast.LENGTH_SHORT).show();
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(MainActivity.this, "All Fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean checkUserPass = myUser.checkEmailPassword(user, pass);
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

    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}