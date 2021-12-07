package com.example.teamproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    private ArrayList<String> images;
    private ArrayList<String> businessNames;
    private ArrayList<String> emails;
    private ArrayList<String> phones;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        Context context = this.getActivity();

        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);

        if (getArguments() != null) {
            images = getArguments().getStringArrayList("images");
            businessNames = getArguments().getStringArrayList("businessNames");
            emails = getArguments().getStringArrayList("emails");
            phones = getArguments().getStringArrayList("phones");
        }

        ListAdapter adapter = new ListAdapter(context, images, businessNames, emails, phones);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        return view;
    }

}
