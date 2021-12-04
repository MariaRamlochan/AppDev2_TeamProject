package com.example.teamproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //vars
    ArrayList<String> imageNames = new ArrayList<>();
    ArrayList<String> images = new ArrayList<>();
    Context mycontext;
    LayoutInflater minflator;

    public RecyclerViewAdapter(ArrayList<String> imageNames, ArrayList<String> images, Context mycontext) {
        this.imageNames = imageNames;
        this.images = images;
        this.mycontext = mycontext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = minflator.from(parent.getContext()).inflate(R.layout.child_view,parent,false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(mycontext)
                .asBitmap()
                .load(images.get(position))
                .into(holder.image);
        holder.imageName.setText(imageNames.get(position));

    }

    @Override
    public int getItemCount() {
        return imageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView imageName;
        LinearLayout parent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            image = itemView.findViewById(R.id.image1);
//            imageName = itemView.findViewById(R.id.image_name);
//            parent = itemView.findViewById(R.id.paremt_layout);

        }
    }
}
