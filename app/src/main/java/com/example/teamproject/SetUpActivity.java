package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SetUpActivity extends AppCompatActivity {

    EditText business, phone, address, zip, city, province, country;
    Button confirm;
    Spinner dropdown;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up);

        dropdown = findViewById(R.id.spinner);
        business = findViewById(R.id.editTextBusiness);
        phone = findViewById(R.id.editTextPhone);
        address = findViewById(R.id.editTextAddress);
        zip = findViewById(R.id.editTextZipCode);
        city = findViewById(R.id.editTextCity);
        province = findViewById(R.id.editTextProvince);
        country = findViewById(R.id.editTextCountry);
        confirm = findViewById(R.id.buttonConfirm);

        String[] items = new String[]{"1", "2", "3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}