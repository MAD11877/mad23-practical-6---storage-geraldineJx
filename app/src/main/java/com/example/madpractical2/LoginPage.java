package com.example.madpractical2;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {
    final String TITLE = "Login Page";

    private DatabaseReference usersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        Log.v(TITLE, "On Create!");

        // Initialize Firebase Realtime Database reference
        usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Initialize views
        EditText editTextUsername = findViewById(R.id.editTextText);
        EditText editTextPassword = findViewById(R.id.editTextText2);
        Button login = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(editTextUsername.getText());
                String password = String.valueOf(editTextPassword.getText());

                // Validate input fields
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Query the database for the user with the entered email
                usersRef.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        boolean userFound = false;
                        UserLogin user = null;

                        // Iterate through the snapshot to find the user
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            user = snapshot.getValue(UserLogin.class);
                            if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {
                                userFound = true;
                                break;
                            }
                        }

                        if (userFound) {
                            // Login success
                            Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();

                            // You can proceed to the next activity or perform other actions here
                        } else {
                            // Login failed
                            Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle database error, if any
                    }
                });
            }
        });
    }
}