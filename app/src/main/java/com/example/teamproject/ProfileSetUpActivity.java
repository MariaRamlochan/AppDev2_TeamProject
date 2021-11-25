package com.example.teamproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileSetUpActivity extends AppCompatActivity {

    Button setPic;
    ImageView profile;
    ImageButton choosePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        setPic = findViewById(R.id.buttonConfirmSetPic);
        profile = findViewById(R.id.imageView);
        choosePic = findViewById(R.id.imageButton);


    }
}