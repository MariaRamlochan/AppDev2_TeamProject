package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SetUpActivityBank extends AppCompatActivity {

    EditText businessName, phone, address, zip, city, province, country;
    Button confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_bank);

        businessName = findViewById(R.id.editTextBusinessB);
        phone = findViewById(R.id.editTextPhoneB);
        address = findViewById(R.id.editTextAddressB);
        zip = findViewById(R.id.editTextZipB);
        city = findViewById(R.id.editTextCityB);
        province = findViewById(R.id.editTextProvinceB);
        country = findViewById(R.id.editTextCountryB);
        confirm = findViewById(R.id.buttonConfirmB);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}