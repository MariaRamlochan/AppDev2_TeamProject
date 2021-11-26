package com.example.teamproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerView extends androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<String> businessName = new ArrayList<>();
    ArrayList<String> businessAddress = new ArrayList<>();
    ArrayList<String> businessPhone = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    Context mycontext;
    LayoutInflater minflator;

    public RecyclerView(ArrayList<String> businessName, ArrayList<String> businessAddress, ArrayList<String> businessPhone, ArrayList<String> images, Context mycontext) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.businessPhone = businessPhone;
        this.images = images;
        this.mycontext = mycontext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = minflator.from(parent.getContext()).inflate(R.layout.activity_child_view,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        Glide.with(mycontext)
//                .asBitmap()
//                .load(images.get(position))
//                .into(holder.image);
        holder.bName.setText(businessName.get(position));
        holder.bAddress.setText(businessAddress.get(position));
        holder.bPhone.setText(businessPhone.get(position));

    }

    @Override
    public int getItemCount() {
        return businessName.size();
    }

    public class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {

        CircleImageView image;
        TextView bName, bAddress, bPhone;
        ConstraintLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.urlPictureBusiness);
            bName = itemView.findViewById(R.id.textViewBusinessNameD);
            bAddress = itemView.findViewById(R.id.textViewBAddress);
            bPhone = itemView.findViewById(R.id.textViewBPhone);
            parent = itemView.findViewById(R.id.parent_layout);

        }
    }
}
