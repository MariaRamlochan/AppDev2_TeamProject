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
    ArrayList<String> images, businessNames, phones;
    DatabaseHelper databaseHelper;

    public ListAdapter(Context context, ArrayList<String> images, ArrayList<String> businessNames, ArrayList<String> phones) {
        this.context = context;
        this.images = images;
        this.businessNames = businessNames;
        this.phones = phones;
        databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ListViewHolder lvh = new ListViewHolder(view);
        return lvh;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.listImageView.setImageURI(Uri.parse(images.get(position)));
        holder.textView.setText(businessNames.get(position));
        holder.textView.setText(phones.get(position));
    }

    @Override
    public int getItemCount() {
        return businessNames.size();
    }

    public  static class ListViewHolder extends RecyclerView.ViewHolder {

        public ImageView listImageView;
        public TextView textView;
        public TextView textView2;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            listImageView = itemView.findViewById(R.id.listImageView);
            textView = itemView.findViewById(R.id.listTextView);
            textView2 = itemView.findViewById(R.id.listTextView2);
        }
    }

}
