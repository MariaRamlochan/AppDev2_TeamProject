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

public class DonationAdapter  extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    Context context;
    ArrayList<String> images, desc, email, phone, date;
    DatabaseHelper databaseHelper;

    public DonationAdapter(Context context, ArrayList<String> images, ArrayList<String> desc,
                           ArrayList<String> email, ArrayList<String> phone, ArrayList<String> date) {
        this.context = context;
        this.images = images;
        this.desc = desc;
        this.email = email;
        this.phone = phone;
        this.date = date;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        holder.donationImageView.setImageURI(Uri.parse(images.get(position)));
        holder.donationDescTextView.setText(desc.get(position));
        holder.donationListEmail.setText(email.get(position));
        holder.donationListPhone.setText(phone.get(position));
        holder.donationDate.setText(date.get(position));
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false);
        return new DonationAdapter.DonationViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return desc.size();
    }

    public static class DonationViewHolder extends RecyclerView.ViewHolder {

        public ImageView donationImageView;
        public TextView donationDescTextView;
        public TextView donationListEmail;
        public TextView donationListPhone;
        public TextView donationDate;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            donationImageView = itemView.findViewById(R.id.donationImageView);
            donationDescTextView = itemView.findViewById(R.id.donationDescTextView);
            donationListEmail = itemView.findViewById(R.id.donationListEmail);
            donationListPhone = itemView.findViewById(R.id.donationListPhone);
            donationDate = itemView.findViewById(R.id.donationDate);
        }
    }
}
