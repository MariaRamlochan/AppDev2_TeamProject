package com.example.teamproject.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.teamproject.R;
import com.example.teamproject.database.DatabaseHelper;

public class ChildView extends AppCompatActivity {

    TextView deleteId;
    Button userDbBtn, deleteDbBtn;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_database);

        deleteId = findViewById(R.id.deleteIdEditText);
        userDbBtn = findViewById(R.id.userDbButton);
        deleteDbBtn = findViewById(R.id.deleteButton);
        databaseHelper = new DatabaseHelper(this);

        userDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = databaseHelper.getAllDataUser();
                if (res.getCount() == 0) {
                    showMessage("error", "Nothing found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("ID : " + res.getString(0) + "\n");
                    buffer.append("Type : " + res.getString(1) + "\n");
                    buffer.append("Email : " + res.getString(2) + "\n");
                    buffer.append("Password : " + res.getString(3) + "\n");
                    buffer.append("Business Name : " + res.getString(4) + "\n");
                    buffer.append("Phone Number : " + res.getString(5) + "\n");
                    buffer.append("Address : " + res.getString(6) + "\n");
                    buffer.append("Zip Code : " + res.getString(7) + "\n");
                    buffer.append("City : " + res.getString(8) + "\n");
                    buffer.append("Province : " + res.getString(9) + "\n");
                    buffer.append("Country : " + res.getString(10) + "\n");
                    buffer.append("Image URL : " + res.getString(11) + "\n");
                }
                showMessage("Data ", buffer.toString());
            }
        });

        deleteDbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(deleteId.getText().toString());
                databaseHelper.deleteData(id);
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