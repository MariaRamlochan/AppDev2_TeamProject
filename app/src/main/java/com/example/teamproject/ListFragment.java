package com.example.teamproject;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListFragment extends Fragment {

    RecyclerView.LayoutManager layoutManager;

    ArrayList<String> image, businessName, phone;
    DatabaseHelper databaseHelper;
    private String userType;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        image = new ArrayList<>();
        businessName = new ArrayList<>();
        phone = new ArrayList<>();
        Cursor cursor;

        if (getArguments() != null) {
            userType = getArguments().getString("userType");
        }

        if (userType.equals("Donor")) {
            cursor = databaseHelper.getListBanks("Food Bank");
        } else {
            cursor = databaseHelper.getListDonors("Donor");
        }
        if (cursor.getCount() == 0) {
            Toast.makeText(getContext(),"No data",Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                image.add(cursor.getString(2));
                businessName.add(cursor.getString(0));
                phone.add(cursor.getString(1));
            }

        }

        RecyclerView recyclerView = view.findViewById(R.id.listRecyclerView);
        ListAdapter adapter = new ListAdapter(getContext(), image, businessName, phone);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);

        return view;
    }

}
