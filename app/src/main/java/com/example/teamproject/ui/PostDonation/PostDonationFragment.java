package com.example.teamproject.ui.PostDonation;

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

import com.example.teamproject.databinding.FragmentHomeBinding;
import com.example.teamproject.databinding.FragmentListBinding;
import com.example.teamproject.databinding.FragmentPostDonationBinding;

public class PostDonationFragment extends Fragment {

    private FragmentPostDonationBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPostDonationBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textViewPostDonation;
        textView.setText("Post Donation!");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}