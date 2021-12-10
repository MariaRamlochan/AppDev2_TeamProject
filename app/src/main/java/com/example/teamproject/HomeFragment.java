package com.example.teamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment {

    ImageView donationOne, donationTwo;
    TextView donorOne, donorTwo, addressOne, addressTwo, phoneOne, phoneTwo, stopWasting;
    EditText descOne, descTwo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);

        donationOne = view.findViewById(R.id.donationImage1);
        donationTwo = view.findViewById(R.id.donationImage2);
        donorOne = view.findViewById(R.id.textViewBName1);
        donorTwo = view.findViewById(R.id.textViewBName2);
        descOne = view.findViewById(R.id.multiLineDesc1);
        descTwo = view.findViewById(R.id.multiLineDesc2);
        stopWasting = view.findViewById(R.id.textViewStopWasting);

        return view;
    }
}
