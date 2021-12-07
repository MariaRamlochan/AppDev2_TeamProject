package com.example.teamproject;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    Context context;
    ArrayList<String> images, businessNames, email, phones;
    DatabaseHelper databaseHelper;

    public ListAdapter(Context context, ArrayList<String> images, ArrayList<String> businessNames,
                       ArrayList<String> email, ArrayList<String> phones) {
        this.context = context;
        this.images = images;
        this.businessNames = businessNames;
        this.email = email;
        this.phones = phones;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.listImageView.setImageURI(Uri.parse(images.get(position)));
        holder.businessName.setText(businessNames.get(position));
        holder.email.setText(email.get(position));
        holder.phoneNum.setText(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return businessNames.size();
    }

    public  static class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView listImageView;
        public TextView businessName;
        public TextView email;
        public TextView phoneNum;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            listImageView = itemView.findViewById(R.id.listImageView);
            businessName = itemView.findViewById(R.id.listTextView);
            email = itemView.findViewById(R.id.textViewEmail);
            phoneNum = itemView.findViewById(R.id.textViewPhone);
        }
    }

}
