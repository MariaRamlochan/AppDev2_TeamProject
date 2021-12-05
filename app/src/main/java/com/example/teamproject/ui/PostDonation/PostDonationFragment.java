package com.example.teamproject.ui.PostDonation;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamproject.activity.MainActivity;
import com.example.teamproject.activity.PictureSetupActivity;
import com.example.teamproject.activity.TestingPage;
import com.example.teamproject.database.DatabaseHelper;
import com.example.teamproject.databinding.FragmentHomeBinding;
import com.example.teamproject.databinding.FragmentListBinding;
import com.example.teamproject.databinding.FragmentPostDonationBinding;

import java.io.ByteArrayOutputStream;

public class PostDonationFragment extends Fragment {

    private FragmentPostDonationBinding binding;

    public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;

    ImageView postDonationImageView;
    ImageButton postDonationImageButton;
    Button postDonationBtn;
    TextView postDonationDescTextView;
    EditText postDonationDescEditText;
    DatabaseHelper databaseHelper;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPostDonationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        postDonationImageView = binding.imageViewPostDonation;
        postDonationImageButton = binding.postDonationImageButton;
        postDonationDescTextView = binding.textViewPostDonationDesc;
        postDonationDescEditText = binding.postDonationDescEditText;
        postDonationBtn = binding.postDonationButton;
        databaseHelper = new DatabaseHelper(getContext());
        Cursor res = databaseHelper.getAllDataUser();


        postDonationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                final Bundle bundle = intent.getExtras();
                final String email = bundle.getString("email");
                String donationDesc = postDonationDescEditText.getText().toString().trim();
                String user_id = databaseHelper.getUserId(email).toString();


                if (donationDesc.equals("")) {
                    Toast.makeText(getContext(), "All Fields must be filled", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean insert = databaseHelper.insertDataPost(donationDesc, Integer.parseInt(user_id));
                    if (insert) {
                        Toast.makeText(getContext(), "Post Added", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        postDonationImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap bmp = (Bitmap) data.getExtras().get("data");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();

                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();

                Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0,
                        byteArray.length);

                postDonationImageView.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}