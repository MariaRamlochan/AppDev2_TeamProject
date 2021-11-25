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
                DonorProfile myDonor = new DonorProfile(SetUpActivityDonor.this);
                String type = dropdown.getSelectedItem().toString().trim();


                if(type.equals("2")){
                    myDonor.addData(type, business.getText().toString().trim(),
                            phone.getText().toString().trim(), address.getText().toString().trim(),
                            zip.getText().toString().trim(), city.getText().toString().trim(),
                            province.getText().toString().trim(), country.getText().toString().trim());
                    intent = new Intent(SetUpActivityDonor.this, SetUpActivityDonor.class);
                    startActivity(intent);

                }
                else if(type.equals("3")){
                    myDonor.addData(type, business.getText().toString().trim(),
                            phone.getText().toString().trim(), address.getText().toString().trim(),
                            zip.getText().toString().trim(), city.getText().toString().trim(),
                            province.getText().toString().trim(), country.getText().toString().trim());
                    intent = new Intent(SetUpActivityDonor.this, SetUpActivityDonor.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SetUpActivityDonor.this, "Please choose a type", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}