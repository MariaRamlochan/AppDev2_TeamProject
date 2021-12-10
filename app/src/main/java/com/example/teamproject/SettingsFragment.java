package com.example.teamproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    EditText businessName, phoneNumber, address, city, country;
    Button saveChange, changePass, terminate;
    ImageButton addGallery, addCamera;
    ImageView settingProfile;
    String userBusinessName, userPhone, userAddress, userCity, userCountry, userPic, userEmail;
    DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        businessName = view.findViewById(R.id.settingsBName);
        phoneNumber = view.findViewById(R.id.settingPhoneNum);
        address = view.findViewById(R.id.settingAddress);
        city = view.findViewById(R.id.settingCity);
        country = view.findViewById(R.id.settingCountry);
        saveChange = view.findViewById(R.id.settingSave);
        changePass = view.findViewById(R.id.settingChangePass);
        terminate = view.findViewById(R.id.settingTerminate);
        addGallery = view.findViewById(R.id.settingGallery);
        addCamera = view.findViewById(R.id.settingCamera);
        settingProfile = view.findViewById(R.id.settingsPic);
        databaseHelper = new DatabaseHelper(getContext());

        if (getArguments() != null) {
            userBusinessName = getArguments().getString("userBusinessName");
            userPhone = getArguments().getString("userPhone");
            userAddress = getArguments().getString("userAddress");
            userCity = getArguments().getString("userCity");
            userCountry = getArguments().getString("userCountry");
            userPic = getArguments().getString("userPic");
            userEmail = getArguments().getString("email");
        }

        businessName.setText(userBusinessName);
        phoneNumber.setText(userPhone);
        address.setText(userAddress);
        city.setText(userCity);
        country.setText(userCountry);
        settingProfile.setImageURI(Uri.parse(userPic));

        changePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ChangePassword.class);
                startActivity(intent);
            }
        });

        saveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = "";
                Cursor cursor = databaseHelper.getUserId(userEmail);
                if (cursor.moveToNext()) {
                    int userTypeColumn = cursor.getColumnIndex("user_id");
                    userId = cursor.getString(userTypeColumn);
                }

                if (businessName.getText().equals("") || phoneNumber.getText().equals("")
                        || address.getText().equals("") || city.getText().equals("")
                        || country.getText().equals("")) {
                    Toast.makeText(getContext(), " All Fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    boolean isUpdated = databaseHelper.updateDataUser(userId, businessName.getText().toString(),
                            phoneNumber.getText().toString(), address.getText().toString(),
                            city.getText().toString(), country.getText().toString(), userPic);
                    if(isUpdated) {
                        Toast.makeText(getContext(), "Update successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }
}
