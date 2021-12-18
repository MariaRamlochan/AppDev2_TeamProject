package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    EditText currentPass, newPass, confirmPass;
    Button confirmChanges;
    ImageButton backPass;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        currentPass = findViewById(R.id.currentPassEdit);
        newPass = findViewById(R.id.newPassEdit);
        confirmPass = findViewById(R.id.confirmPassEdit);
        confirmChanges = findViewById(R.id.confirmPassChangeButton);
        backPass = findViewById(R.id.backPasswordEditButton);
        databaseHelper = new DatabaseHelper(this);

        backPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChangePassword.this, SettingsFragment.class);
                startActivity(intent);
            }
        });

        confirmChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                //Bundle bundle = getIntent().getExtras();
                String currentPassword = currentPass.getText().toString().trim();
                String newPassword = newPass.getText().toString().trim();
                String confirmPassword = confirmPass.getText().toString().trim();

                if (currentPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
                    Toast.makeText(ChangePassword.this, "All Fields must be filled",
                            Toast.LENGTH_SHORT).show();
                } else {
                    if (newPassword.equals(confirmPassword)) {
                        Boolean isUpdated = databaseHelper.updatePassword(currentPassword, newPassword);
                        if (isUpdated) {
                            Intent intent = new Intent(ChangePassword.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ChangePassword.this, "Not updated", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePassword.this, "New Password and Confirm" +
                                " Password Does not Match", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}