package com.example.teamproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChildView extends AppCompatActivity {

    Button userDbBtn, donorDbBtn, bankDbBtn;
    User myUser;
    DonorProfile myDonor;
    BankProfile myBank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        userDbBtn = findViewById(R.id.userDbButton);
        donorDbBtn = findViewById(R.id.donorDbButton);
        bankDbBtn = findViewById(R.id.bankDbButton);
        myUser = new User(this);
        myDonor = new DonorProfile(this);
        myBank = new BankProfile(this);

        userDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myUser.getAllData();
                if(res.getCount() == 0) {
                    showMessage ("error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append( "Type : " + res.getString(1) + "\n");
                    buffer.append("Email : " + res.getString(2) + "\n");
                    buffer.append( "Password : " + res.getString(3) + "\n");
                }
                showMessage ("Data ", buffer.toString());
            }
        });

        donorDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDonor.getAllDataDonor();
                if(res.getCount() == 0) {
                    showMessage ("error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append( "Type : " + res.getString(1) + "\n");
                    buffer.append("Business Name : " + res.getString(2) + "\n");
                    buffer.append( "Phone Number : " + res.getString(3) + "\n");
                    buffer.append( "Address : " + res.getString(4) + "\n");
                    buffer.append( "Zip Code : " + res.getString(5) + "\n");
                    buffer.append( "City : " + res.getString(6) + "\n");
                    buffer.append( "Province : " + res.getString(7) + "\n");
                    buffer.append( "Country : " + res.getString(8) + "\n");
                }
                showMessage ("Data ", buffer.toString());
            }
        });

        bankDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myBank.getAllDataBank();
                if(res.getCount() == 0) {
                    showMessage ("error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Business Name : " + res.getString(1) + "\n");
                    buffer.append( "Phone Number : " + res.getString(2) + "\n");
                    buffer.append( "Address : " + res.getString(3) + "\n");
                    buffer.append( "Zip Code : " + res.getString(4) + "\n");
                    buffer.append( "City : " + res.getString(5) + "\n");
                    buffer.append( "Province : " + res.getString(6) + "\n");
                    buffer.append( "Country : " + res.getString(7) + "\n");
                }
                showMessage ("Data ", buffer.toString());

            }
        });
    }

    private void showMessage(String title, String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }
}