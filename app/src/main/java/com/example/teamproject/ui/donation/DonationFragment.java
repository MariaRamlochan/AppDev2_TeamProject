package com.example.teamproject.ui.donation;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.teamproject.databinding.FragmentDonationsBinding;

public class DonationFragment extends Fragment {

    private DonationViewModel donationViewModel;
    private FragmentDonationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        donationViewModel =
                new ViewModelProvider(this).get(DonationViewModel.class);

        binding = FragmentDonationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        //final RecyclerView recyclerView = binding.recyclerViewDonation;

        //final TextView textView = binding.textDonations;
        final TextView textView = binding.textView8;
        donationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
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