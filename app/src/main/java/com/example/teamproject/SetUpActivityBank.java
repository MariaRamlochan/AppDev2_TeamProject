package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SetUpActivityBank extends AppCompatActivity {

    EditText businessB, phoneB, addressB, zipB, cityB, provinceB, countryB;
    Button confirmB;
    BankProfile myBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_bank);

        businessB = findViewById(R.id.editTextBusinessB);
        phoneB = findViewById(R.id.editTextPhoneB);
        addressB = findViewById(R.id.editTextAddressB);
        zipB = findViewById(R.id.editTextZipB);
        cityB = findViewById(R.id.editTextCityB);
        provinceB = findViewById(R.id.editTextProvinceB);
        countryB = findViewById(R.id.editTextCountryB);
        confirmB = findViewById(R.id.buttonConfirmB);
        myBank = new BankProfile(this);

        confirmB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String business = businessB.getText().toString().trim();
                String phone = phoneB.getText().toString().trim();
                String address = addressB.getText().toString().trim();
                String zip = zipB.getText().toString().trim();
                String city = cityB.getText().toString().trim();
                String province = provinceB.getText().toString().trim();
                String country = countryB.getText().toString().trim();

                if (business.equals("") || phone.equals("") || address.equals("") || zip.equals("")
                        || city.equals("") || province.equals("") || country.equals("")) {
                    Toast.makeText(SetUpActivityBank.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = myBank.insertDataBank(business, phone, address, zip, city, province, country);
                    if (!insert) {
                        Toast.makeText(SetUpActivityBank.this, "Registration completed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SetUpActivityBank.this, ProfileSetupActivity.class);
                        startActivity(intent);
                    }

                }
            }
        });
    }
}