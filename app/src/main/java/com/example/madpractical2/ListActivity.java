package com.example.madpractical2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    String TITLE = "ListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Log.v(TITLE, "On Create!");

        createUsers(); //call to make userlist

        //Recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        MyAdapter myAdapter = new MyAdapter(readList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
    }
    private void createUsers() {
        MyDBHandler myDBHandler = new MyDBHandler(this,null,null,0);
        for (int i = 0; i <= 20; i++) {
            User user = new User("Name" + randomNum(), "Description" + randomNum()
                    , null, randomBoolean());
            myDBHandler.addUsers(user);
        }
    }
    private List<User> readList() {
        MyDBHandler myDBHandler = new MyDBHandler(this,null,null,0);
        return myDBHandler.getUsers();
    }

    //Generate num for name
    private int randomNum() {
        Random ran = new Random();
        int myNumber = ran.nextInt(999999999);
        return myNumber;
    }
    public boolean randomBoolean() {
        Random rd = new Random(); // creating Random object
        boolean myBoolean = rd.nextBoolean();
        return myBoolean;
    }
}















