package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class AddPostActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE_GALLERY = 1000;
    public static final int PERMISSION_CODE_CAMERA = 1001;
    public static final int CAMERA_PICK_CODE = 1002;
    public static final int GALLERY_PICK_CODE = 1003;
    EditText addPostDesc;
    ImageView addPostImage;
    ImageButton addPostImageButton, back;
    Button addPostButton;
    DatabaseHelper databaseHelper;
    Uri image_uri;
    String userId, userEmail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        addPostImage = findViewById(R.id.addPostImageView);
        addPostDesc = findViewById(R.id.addPostEditText);
        addPostImageButton = findViewById(R.id.addPostImageButton);
        addPostButton = findViewById(R.id.addPostButton);
        back = findViewById(R.id.backImageButton);
        databaseHelper = new DatabaseHelper(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddPostActivity.this, DonationsFragment.class);
                startActivity(intent);
            }
        });


        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = getIntent().getExtras();
                String email = bundle.getString("email");
                Cursor cursor = databaseHelper.getUserId(email);
                String desc = addPostDesc.getText().toString();
                String userId = "";
                String postUserId = "";

                if (cursor.moveToNext()) {
                    int userTypeColumn = cursor.getColumnIndex("user_id");
                    userId = cursor.getString(userTypeColumn);
                }
                Boolean insert = databaseHelper.insertDataPost(desc, image_uri.toString(), userId);

                Cursor cursor1 = databaseHelper.getAllDataUserPost(userId);
                if (cursor1.moveToNext()) {
                    int userIdColumn = cursor1.getColumnIndex("user_id");
                    postUserId = cursor1.getString(userIdColumn);
                }

                if (insert) {
                    Toast.makeText(AddPostActivity.this, "Registration completed", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddPostActivity.this, ProfileActivity.class);
                    intent.putExtra("postPic", image_uri.toString());
                    intent.putExtra("postUserID", postUserId);
                    startActivity(intent);
                }
            }
        });

        addPostImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            addPostImage.setImageURI(image_uri);

        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICK_CODE) {
            image_uri = data.getData();
            addPostImage.setImageURI(image_uri);
        }
    }

}