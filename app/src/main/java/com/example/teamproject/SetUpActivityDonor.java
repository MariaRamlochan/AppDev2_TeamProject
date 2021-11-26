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

public class SetUpActivityDonor extends AppCompatActivity {

    EditText businessD, phoneD, addressD, zipD, cityD, provinceD, countryD;
    Button confirmD;
    Spinner dropdown;
    DonorProfile myDonor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_donor);

        dropdown = findViewById(R.id.spinner2);
        businessD = findViewById(R.id.editTextBusinessD);
        phoneD = findViewById(R.id.editTextPhoneD);
        addressD = findViewById(R.id.editTextAddressD);
        zipD = findViewById(R.id.editTextZipD);
        cityD = findViewById(R.id.editTextCityD);
        provinceD = findViewById(R.id.editTextProvinceD);
        countryD = findViewById(R.id.editTextCountryD);
        confirmD = findViewById(R.id.buttonConfirmD);
        myDonor = new DonorProfile(SetUpActivityDonor.this);

        String[] items = new String[]{"Select your business type", "Restaurant", "Bakery"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        confirmD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = dropdown.getSelectedItem().toString().trim();
                String business = businessD.getText().toString().trim();
                String phone = phoneD.getText().toString().trim();
                String address = addressD.getText().toString().trim();
                String zip = zipD.getText().toString().trim();
                String city = cityD.getText().toString().trim();
                String province = provinceD.getText().toString().trim();
                String country = countryD.getText().toString().trim();

                if (business.equals("") || phone.equals("") || address.equals("") || zip.equals("")
                        || city.equals("") || province.equals("") || country.equals("")
                        || type.equals("Select your business type")) {
                    Toast.makeText(SetUpActivityDonor.this, "All fields must be filled", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean insert = myDonor.insertDataDonor(type, business, phone, address, zip, city, province, country);
                    if (!insert) {
                        Toast.makeText(SetUpActivityDonor.this, "Registration completed", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SetUpActivityDonor.this, ProfileSetupActivity.class);
                        startActivity(intent);
                    }

                }

            }
        });
    }
}