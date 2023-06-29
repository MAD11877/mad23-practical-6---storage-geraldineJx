package com.example.madpractical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    String TITTLE = "ListActivity";
    ImageView image;
    TextView userName, userDesc;

    public MyViewHolder(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userNameTextView);
        userDesc = itemView.findViewById(R.id.userDesc1);
        image = itemView.findViewById(R.id.imageView2);
    }
}

