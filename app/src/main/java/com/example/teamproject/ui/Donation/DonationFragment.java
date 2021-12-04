package com.example.teamproject.ui.Donation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.teamproject.databinding.FragmentDonationsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonationFragment extends Fragment {
    private FragmentDonationsBinding binding;
    FloatingActionButton post, past, present, open;
    TextView postD, pastD, presentD, donationTextView;
    Boolean isFabvisible;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDonationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        post = binding.postFab;
        past = binding.pastFab;
        present = binding.donationFab;
        open = binding.openFab;
        postD = binding.textViewPost;
        pastD = binding.textViewPast;
        presentD = binding.textViewDonationList;
        donationTextView = binding.donationTextView;

        isFabvisible = false;

        post.setVisibility(View.GONE);
        past.setVisibility(View.GONE);
        present.setVisibility(View.GONE);
        postD.setVisibility(View.GONE);
        pastD.setVisibility(View.GONE);
        presentD.setVisibility(View.GONE);

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isFabvisible){
                    post.setVisibility(View.VISIBLE);
                    past.setVisibility(View.VISIBLE);
                    present.setVisibility(View.VISIBLE);
                    postD.setVisibility(View.VISIBLE);
                    pastD.setVisibility(View.VISIBLE);
                    presentD.setVisibility(View.VISIBLE);
                    isFabvisible = true;
                } else {
                    post.setVisibility(View.GONE);
                    past.setVisibility(View.GONE);
                    present.setVisibility(View.GONE);
                    postD.setVisibility(View.GONE);
                    pastD.setVisibility(View.GONE);
                    presentD.setVisibility(View.GONE);
                    isFabvisible = false;
                }
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationTextView.setText("Post Donation");
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationTextView.setText("Past Donation");
            }
        });

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                donationTextView.setText("Donations");
            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}