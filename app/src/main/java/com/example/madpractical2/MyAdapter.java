package com.example.madpractical2;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<User> users;
    String title = "Adapter";

    public MyAdapter(List<User> users) {
        this.users = users;
    }


    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout2, parent, false);

            return new MyViewHolder(item);
        } else {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_itms, parent, false);
            return new MyViewHolder(item);
        }
    }

    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getName());
        holder.userDesc.setText(users.get(position).getDescription());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenAlertBox(position, v);
            } //calling OpenAlertBox dialog
        });
    }

    public int getItemCount() {
        return users.size();
    }

    public int getItemViewType(int position) {
        if (users.get(position).getName().endsWith("7")) {
            return 0;
        } else return 1;
    }

    private void OpenAlertBox(int position, View v) { //Alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setTitle("Profile");
        builder.setMessage(users.get(position).getName());
        builder.setCancelable(false);
        builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Initialize and define myIntent here
                Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                myIntent.putExtra("username", users.get(position).getName());
                myIntent.putExtra("description", users.get(position).getDescription());
                myIntent.putExtra("id", users.get(position).getId());
                myIntent.putExtra("followed", users.get(position).getFollowed());
                Log.v(title, "start");
                v.getContext().startActivity(myIntent);
            }

        });
        builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}




