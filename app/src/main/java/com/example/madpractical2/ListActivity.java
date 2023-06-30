package com.example.madpractical2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    String TITLE = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TITLE, "On Create!");

        //Recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        MyAdapter myAdapter = new MyAdapter(readList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }

    private List<User> readList() {
        try(MyDBHandler myDBHandler = new MyDBHandler(this, null)) {
            List<User> users = myDBHandler.getUsers();
            if(users.isEmpty()){
                myDBHandler.createUsers(20);
                users = myDBHandler.getUsers();
            }
            return users;
        }
    }
}















