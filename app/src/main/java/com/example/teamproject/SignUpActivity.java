package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    EditText email, password, confirmPassword;
    Button continueBtn;
    Spinner dropdown;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dropdown = findViewById(R.id.spinner);
        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        confirmPassword = findViewById(R.id.confirmPasswordEditText);
        continueBtn = findViewById(R.id.continueButton);

        String[] items = new String[]{"Choose a type", "Donor", "Food Bank"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                User myUser = new User(SignUpActivity.this);
                String type = dropdown.getSelectedItem().toString().trim();
//                myUser.addData(type, email.getText().toString().trim(), password.getText().toString().trim());
//                Toast.makeText(SignUpActivity.this, "values saved", Toast.LENGTH_LONG).show();


                if(type.equals("Donor")){
                    intent = new Intent(SignUpActivity.this, SetUpActivity.class);
                    startActivity(intent);
                }
                else if(type.equals("Food Bank")){
                    Toast.makeText(SignUpActivity.this, "I am food Bank", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Please choose a type", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}