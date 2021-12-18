package com.example.teamproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    Context context;
    ArrayList<String> images, desc, email, phone, date, ids, businessName;
    String status, color;
    DatabaseHelper databaseHelper;

    public DonationAdapter(Context context, ArrayList<String> ids, ArrayList<String> images,
                           ArrayList<String> desc, ArrayList<String> email, ArrayList<String> phone,
                           ArrayList<String> date, ArrayList<String> businessName,String status, String color) {
        this.context = context;
        this.ids = ids;
        this.images = images;
        this.desc = desc;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.businessName = businessName;
        this.status = status;
        this.color = color;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public void onBindViewHolder(@NonNull DonationViewHolder holder, int position) {
        holder.donationImageView.setImageURI(Uri.parse(images.get(position)));
        holder.donationDescTextView.setText(desc.get(position));
        holder.donationListEmail.setText(email.get(position));
        holder.donationListPhone.setText(phone.get(position));
        holder.donationBusinessName.setText(businessName.get(position));
        holder.donationDate.setText(date.get(position));
        holder.donationDeleteButton.setText(status);
        holder.donationDeleteButton.setBackgroundColor(Color.parseColor(color));
        holder.donationId.setText(ids.get(position));
        holder.donationDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                boolean isUpdated = databaseHelper.updateStatusPost(holder.donationId.getText().toString());
                if(isUpdated) {
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    HomeFragment homeFragment = new HomeFragment();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            homeFragment).commit();
                }
            }
        });

        holder.donationListPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + holder.donationListPhone.getText()));
                view.getContext().startActivity(intent);
            }
        });
    }

    @NonNull
    @Override
    public DonationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_post, parent, false);
        return new DonationViewHolder(view);
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
        public TextView donationBusinessName;
        public TextView donationId;
        public Button donationDeleteButton;

        public DonationViewHolder(@NonNull View itemView) {
            super(itemView);
            donationImageView = itemView.findViewById(R.id.donationImageView);
            donationDescTextView = itemView.findViewById(R.id.donationDescTextView);
            donationListEmail = itemView.findViewById(R.id.donationListEmail);
            donationListPhone = itemView.findViewById(R.id.donationListPhone);
            donationDate = itemView.findViewById(R.id.donationDate);
            donationDeleteButton = itemView.findViewById(R.id.donationDeleteButton);
            donationId = itemView.findViewById(R.id.postIDTextView);
            donationBusinessName = itemView.findViewById(R.id.postDonationBusinessName);

        }
    }
}
