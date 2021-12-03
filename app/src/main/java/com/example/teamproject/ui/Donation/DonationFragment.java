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

public class DonationFragment extends Fragment {
    private FragmentDonationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDonationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textViewDonation = binding.textDonations;

        textViewDonation.setText("Testing completed");

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}