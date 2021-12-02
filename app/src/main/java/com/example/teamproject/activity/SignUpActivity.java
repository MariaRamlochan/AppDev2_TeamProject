package com.example.teamproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teamproject.database.DatabaseHelper;
import com.example.teamproject.R;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, confirmPassword;
    Button continueBtn;
    Spinner dropdown;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dropdown = findViewById(R.id.spinner);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        continueBtn = findViewById(R.id.continueButton);
        databaseHelper = new DatabaseHelper(this);

        String[] items = new String[]{"Choose a type", "Donor", "Food Bank"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = dropdown.getSelectedItem().toString().trim();
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confPass = confirmPassword.getText().toString().trim();

                if (user.equals("") || pass.equals("") || confPass.equals("") || type.equals("Choose a type")) {
                    Toast.makeText(SignUpActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(confPass)) {
                        Boolean checkUser = databaseHelper.checkEmail(user);
                        if (!checkUser) {
                            Toast.makeText(SignUpActivity.this, "Continue Setup", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignUpActivity.this, SetUpProfile.class);
                            intent.putExtra("userType", type);
                            intent.putExtra("email", user);
                            intent.putExtra("password", pass);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Email Already Exist", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(SignUpActivity.this, "Password and Confirm Password Does Not Match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}