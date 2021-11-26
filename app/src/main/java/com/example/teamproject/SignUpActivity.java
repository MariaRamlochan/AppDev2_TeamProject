package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, confirmPassword;
    Button continueBtn;
    Spinner dropdown;
    User myUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dropdown = findViewById(R.id.spinner);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        continueBtn = findViewById(R.id.continueButton);
        myUser = new User(this);

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
                }
                else {
                    if (pass.equals(confPass)) {
                        Boolean checkUser = myUser.checkEmail(user);
                        if (!checkUser) {
                            Boolean insert = myUser.insertData(type, user, pass);
                            if (insert) {
                                Toast.makeText(SignUpActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent;
                                if (type.equals("Donor"))  {
                                    intent = new Intent(getApplicationContext(), SetUpActivityDonor.class);
                                } else {
                                    intent = new Intent(getApplicationContext(), SetUpActivityBank.class);
                                }
                                startActivity(intent);

                            } else {
                                Toast.makeText(SignUpActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
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


//                if(type.equals("Donor")){
//                    if(password.getText().toString().equals(confirmPassword.getText().toString())) {
//                        myUser.addData(type, email.getText().toString().trim(), password.getText().toString().trim());
//                        intent = new Intent(SignUpActivity.this, SetUpActivityDonor.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(SignUpActivity.this,
//                                "Password and Confirm Password does not match",
//                                Toast.LENGTH_LONG).show();
//                    }
//
//                }
//                else if(type.equals("Food Bank")){
//                    if(password.getText().toString().equals(confirmPassword.getText().toString())) {
//                        myUser.addData(type, email.getText().toString().trim(), password.getText().toString().trim());
//                        intent = new Intent(SignUpActivity.this, SetUpActivityBank.class);
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(SignUpActivity.this,
//                                "Password and Confirm Password does not match",
//                                Toast.LENGTH_LONG).show();
//                    }
//
//                }
//                else{
//                    Toast.makeText(SignUpActivity.this, "Please choose a type", Toast.LENGTH_LONG).show();
//                }