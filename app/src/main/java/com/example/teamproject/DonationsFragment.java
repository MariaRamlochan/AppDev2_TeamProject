package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DonationsFragment extends Fragment {

    FloatingActionButton open, past, post, present;
    TextView pastText, postText, presentText, title;
    Boolean isFabVisible;
    String userId, email;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donations, container, false);

        Context context = this.getActivity();

        RecyclerView recyclerView = view.findViewById(R.id.donationRecyclerView);
        FragmentManager fragmentManager = getChildFragmentManager();


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

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                addPostFragment.getView().setVisibility(View.VISIBLE);
//                recyclerView.setVisibility(View.GONE);
                title.setText("Add A Food Donation");
                Intent intent = new Intent(getContext(), DatabaseViewActivity.class);
                startActivity(intent);
            }
        });

        past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);



            }
        });

        present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.setVisibility(View.VISIBLE);

//                ListAdapter adapter = new ListAdapter(context, images, businessNames, emails, phones);
//                recyclerView.setAdapter(adapter);
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            }
        });


//        if (getArguments() != null) {
//            images = getArguments().getStringArrayList("images");
//            businessNames = getArguments().getStringArrayList("businessNames");
//            emails = getArguments().getStringArrayList("emails");
//            phones = getArguments().getStringArrayList("phones");
//            String userType = getArguments().getString("userType");
//        }




        return view;
    }
}
