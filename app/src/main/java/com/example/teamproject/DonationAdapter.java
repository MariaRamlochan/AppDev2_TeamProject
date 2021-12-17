package com.example.teamproject;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonationAdapter extends RecyclerView.Adapter<DonationAdapter.DonationViewHolder> {

    Context context;
    ArrayList<String> images, desc, email, phone, date, ids;
    String status, color;
    DatabaseHelper databaseHelper;

    public DonationAdapter(Context context, ArrayList<String> ids, ArrayList<String> images,
                           ArrayList<String> desc, ArrayList<String> email, ArrayList<String> phone,
                           ArrayList<String> date, String status, String color) {
        this.context = context;
        this.ids = ids;
        this.images = images;
        this.desc = desc;
        this.email = email;
        this.phone = phone;
        this.date = date;
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
        holder.donationDate.setText(date.get(position));
        holder.donationDeleteButton.setText(status);
        holder.donationDeleteButton.setBackgroundColor(Color.parseColor(color));
        holder.donationId.setText(ids.get(position));
//        holder.donationDeleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String userId = "";
//                Cursor cursor = databaseHelper.getUserId(holder.donationListEmail.getText().toString());
//                if (cursor.moveToNext()) {
//                    int userTypeColumn = cursor.getColumnIndex("user_id");
//                    userId = cursor.getString(userTypeColumn);
//                }
//                boolean isUpdated = databaseHelper.updateDataPost(userId, businessName.getText().toString(), userPic);
//                if(isUpdated) {
//                    Toast.makeText(getContext(), "Update successful", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

//        holder.donationListPhone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
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

        }
    }
}
