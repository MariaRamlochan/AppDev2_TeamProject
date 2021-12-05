package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class SetUpPictureActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE_GALLERY = 1000;
    public static final int PERMISSION_CODE_CAMERA = 1001;
    public static final int CAMERA_PICK_CODE = 1002;
    public static final int GALLERY_PICK_CODE = 1003;
    DatabaseHelper databaseHelper;
    ImageView selectedImage;
    ImageButton camera;
    Button gallery, setPic;
    Uri image_uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_picture);

        camera = findViewById(R.id.imageButton);
        gallery = findViewById(R.id.galleryButton);
        setPic = findViewById(R.id.buttonConfirmSetPic);
        selectedImage = findViewById(R.id.urlPictureBusiness);
        databaseHelper = new DatabaseHelper(this);

        setPic.setOnClickListener(new View.OnClickListener() {

            final Bundle bundle = getIntent().getExtras();
            final String userType = bundle.getString("userType");
            final String email = bundle.getString("email");
            final String password = bundle.getString("password");
            final String business = bundle.getString("businessName");
            final String phone = bundle.getString("phone");
            final String address = bundle.getString("address");
            final String zip = bundle.getString("zip");
            final String city = bundle.getString("city");
            final String province = bundle.getString("province");
            final String country = bundle.getString("country");


            @Override
            public void onClick(View v) {
                Boolean insert = databaseHelper.insertDataUser(userType, email, password, business,
                        phone, address, zip, city, province, country, selectedImage.toString());
                if (insert) {
                    Toast.makeText(SetUpPictureActivity.this, "Registration completed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SetUpPictureActivity.this, ProfileActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);
                }
            }
        });

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                            || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE_CAMERA);
                    } else {
                        openCamera();
                    }
                } else {
                    openCamera();
                }

            }
        });

        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE_GALLERY);
                    } else {
                        openGallery();
                    }
                } else {
                    openGallery();
                }
            }
        });
    }

    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera");
        image_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, CAMERA_PICK_CODE);
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(gallery, GALLERY_PICK_CODE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else {
                Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSION_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
            else {
                Toast.makeText(this, "Permission denied...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == CAMERA_PICK_CODE) {
            selectedImage.setImageURI(image_uri);

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICK_CODE) {
            selectedImage.setImageURI(data.getData());
        }
    }

}