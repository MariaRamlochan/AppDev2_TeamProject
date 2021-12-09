package com.example.teamproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonationsFragment extends Fragment {

    FloatingActionButton open, past, post, present;
    TextView pastText, postText, presentText, title;
    Boolean isFabVisible;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donations, container, false);

        open = view.findViewById(R.id.openFab);
        past = view.findViewById(R.id.pastFab);
        post = view.findViewById(R.id.postFab);
        present = view.findViewById(R.id.presentFab);
        pastText = view.findViewById(R.id.textViewPastD);
        postText = view.findViewById(R.id.textViewPostD);
        presentText = view.findViewById(R.id.textViewPresentD);
        title = view.findViewById(R.id.donationTitle);

        title.setText("Your Current Donations");
        past.setVisibility(View.GONE);
        post.setVisibility(View.GONE);
        present.setVisibility(View.GONE);
        pastText.setVisibility(View.GONE);
        postText.setVisibility(View.GONE);
        presentText.setVisibility(View.GONE);

        isFabVisible = false;

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFabVisible){
                    past.setVisibility(View.VISIBLE);
                    post.setVisibility(View.VISIBLE);
                    present.setVisibility(View.VISIBLE);
                    pastText.setVisibility(View.VISIBLE);
                    postText.setVisibility(View.VISIBLE);
                    presentText.setVisibility(View.VISIBLE);

                    isFabVisible = true;
                } else {
                    past.setVisibility(View.GONE);
                    post.setVisibility(View.GONE);
                    present.setVisibility(View.GONE);
                    pastText.setVisibility(View.GONE);
                    postText.setVisibility(View.GONE);
                    presentText.setVisibility(View.GONE);

                    isFabVisible = false;
                }
            }
        });


        if (getArguments() != null) {
            String email = getArguments().getString("email");
            String userType = getArguments().getString("userType");
        }


        return view;
    }
}
