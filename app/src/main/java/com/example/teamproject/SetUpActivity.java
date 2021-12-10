package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetUpActivity extends AppCompatActivity {

    EditText businessD, phoneD, addressD, cityD, countryD;
    Button confirmD;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        businessD = findViewById(R.id.editTextBusinessD);
        phoneD = findViewById(R.id.editTextPhoneD);
        addressD = findViewById(R.id.editTextAddressD);
        cityD = findViewById(R.id.editTextCityD);
        countryD = findViewById(R.id.editTextCountryD);
        confirmD = findViewById(R.id.buttonConfirmD);
        databaseHelper = new DatabaseHelper(this);


        confirmD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String businessName = businessD.getText().toString().trim();
                String phone = phoneD.getText().toString().trim();
                String address = addressD.getText().toString();
                String city = cityD.getText().toString().trim();
                String country = countryD.getText().toString().trim();

                Bundle bundle = getIntent().getExtras();
                String type = bundle.getString("userType");
                String email = bundle.getString("email");
                String password = bundle.getString("password");


                if (businessName.equals("") || phone.equals("") || address.equals("")
                        || city.equals("") || country.equals("")) {
                    Toast.makeText(SetUpActivity.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = new Intent(SetUpActivity.this, SetUpPictureActivity.class);
                    intent.putExtra("userType", type);
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    intent.putExtra("businessName", businessName);
                    intent.putExtra("phone", phone);
                    intent.putExtra("address", address);
                    intent.putExtra("city", city);
                    intent.putExtra("country", country);
                    Toast.makeText(SetUpActivity.this, "Continue...", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }

            }
        });
    }
}