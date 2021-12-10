package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ChangePassword extends AppCompatActivity {

    EditText currentPass, newPass, confirmPass;
    Button confirmChanges;
    ImageButton backPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPass = findViewById(R.id.currentPassEdit);
        newPass = findViewById(R.id.newPassEdit);
        confirmPass = findViewById(R.id.confirmPassEdit);
        confirmChanges = findViewById(R.id.confirmPassChangeButton);
        backPass = findViewById(R.id.backPasswordEditButton);
    }
}