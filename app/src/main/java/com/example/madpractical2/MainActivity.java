package com.example.madpractical2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final String TITLE = "Main Activity";
    //User myUser = new User();
    private MyDBHandler myDBHandler;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create!");

        //recieve intent from adapter
        Intent myIntent = getIntent();
        String myName = myIntent.getStringExtra("username");
        String myDescription = myIntent.getStringExtra("description");
        int myID = myIntent.getExtras().getInt("id");
        Boolean myFollowed = myIntent.getExtras().getBoolean("followed");

        // Initialize the database handler and retrieve the current user from the database
        myDBHandler = new MyDBHandler(this,null);
        currentUser =  myDBHandler.getUsersId(myID);
        //Toast.makeText(getApplicationContext(), "Follow"+ currentUser.getFollowed(), Toast.LENGTH_SHORT).show();

        //Text to show MAD2435324368(etc)
        TextView numText = findViewById(R.id.textView);
        numText.setText(myName);
        TextView descriptionText = findViewById(R.id.textView2);
        descriptionText.setText(myDescription);

        // Set the text of the follow button based on the "followed" variable
        final Button followButton = findViewById(R.id.button2);
        if (currentUser.getFollowed()) {
            followButton.setText("Unfollow");
        } else {
            followButton.setText("Follow");
        }
//        //For Follow button to change from follow to unfollow and vise versa
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TITLE, "Follow button is pressed");
                if (followButton.getText().equals("Follow")) {
                    followButton.setText("Unfollow");
                    currentUser.setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                } else {
                    followButton.setText("Follow");
                    currentUser.setFollowed(false);
                }
                // Update the user in the database
                myDBHandler.updateUser(currentUser);
            }
        });

        // Restore the followed state from savedInstanceState, if available
        //if (savedInstanceState != null) {
        //    myUser = (User) savedInstanceState.get("myUser");
        //}

        //Message button
        Button messageButton = findViewById(R.id.button3);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TITLE, "Message button is pressed");
                Intent myIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myIntent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("myUser", currentUser);
        super.onSaveInstanceState(savedInstanceState);
        Log.v(TITLE, "On SaveInstanceState!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TITLE, "On Start!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v(TITLE, "On Pause!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v(TITLE, "On Resume!");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TITLE, "On Stop!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TITLE, "On Destroy!");
    }

}

