package com.example.teamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class DonationsFragment extends Fragment {

    private String email;
    private String userType;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donations, container, false);
        TextView textView = view.findViewById(R.id.donations_textView_fragment);

        if (getArguments() != null) {
            email = getArguments().getString("email");
            userType = getArguments().getString("userType");
        }

        textView.setText(email + "......." + userType);

        return view;
    }
}
