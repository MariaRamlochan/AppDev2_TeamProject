package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SetUpActivityDonor extends AppCompatActivity {

    EditText business, phone, address, zip, city, province, country;
    Button confirm;
    Spinner dropdown;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_donor);

        dropdown = findViewById(R.id.spinner2);
        business = findViewById(R.id.editTextBusinessD);
        phone = findViewById(R.id.editTextPhoneD);
        address = findViewById(R.id.editTextAddressD);
        zip = findViewById(R.id.editTextZipD);
        city = findViewById(R.id.editTextCityD);
        province = findViewById(R.id.editTextProvinceD);
        country = findViewById(R.id.editTextCountryD);
        confirm = findViewById(R.id.buttonConfirmD);

        String[] items = new String[]{"Select your business type", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}