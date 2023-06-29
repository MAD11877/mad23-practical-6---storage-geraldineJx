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
    User myUser = new User();

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

        //Text to show MAD2435324368(etc)
        TextView numText = findViewById(R.id.textView);
        numText.setText(myName);
        TextView descriptionText = findViewById(R.id.textView2);
        descriptionText.setText(myDescription);

        // Set the text of the follow button based on the "followed" variable
        final Button followButton = findViewById(R.id.button2);
        if (myUser.getFollowed()) {
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
                    myUser.setFollowed(true);
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();
                } else {
                    followButton.setText("Follow");
                    myUser.setFollowed(false);
                }
            }
        });

        // Restore the followed state from savedInstanceState, if available
        if (savedInstanceState != null) {
            myUser = (User) savedInstanceState.get("myUser");
        }

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
        savedInstanceState.putSerializable("myUser", myUser);
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

