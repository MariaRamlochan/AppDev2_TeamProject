package com.example.teamproject;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class AddPostFragment extends Fragment implements View.OnClickListener {

    public static final int PERMISSION_CODE_CAMERA = 1001;
    public static final int CAMERA_PICK_CODE = 1002;
    EditText addPostDesc;
    ImageView addPostImage;
    ImageButton addPostImageButton;
    Button addPostButton;
    DatabaseHelper databaseHelper;
    Uri image_uri;
    String userId, userEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        addPostButton = getActivity().findViewById(R.id.addPostButton);
        addPostImageButton = getActivity().findViewById(R.id.addPostImageButton);
        addPostImage = getActivity().findViewById(R.id.addPostImageView);
        addPostDesc = getActivity().findViewById(R.id.addPostEditText);


    }

    @Override
    public void onClick(View v) {
        addPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getArguments() != null) {
                    userEmail = getArguments().getString("email");
                }
                Cursor cursor = databaseHelper.getUserId(userEmail);
                if (cursor.moveToNext()) {
                    int userTypeColumn = cursor.getColumnIndex("user_id");
                    userId = cursor.getString(userTypeColumn);
                }
                Boolean insert = databaseHelper.insertDataPost(addPostDesc.toString(), image_uri.toString(), userId);
                if (insert) {
                    Toast.makeText(getContext(), "Post Added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addPostImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_DENIED
                            && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_DENIED) {
                                openCamera();
                            } else {
                        String[] permission = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(permission, PERMISSION_CODE_CAMERA);
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
        image_uri = getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);


        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri);
        startActivityForResult(cameraIntent, CAMERA_PICK_CODE);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_CODE_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else {
                Toast.makeText(getContext(), "Permission denied...", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CAMERA_PICK_CODE) {
            addPostImage.setImageURI(image_uri);

        }
    }

}