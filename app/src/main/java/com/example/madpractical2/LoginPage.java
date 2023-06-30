package com.example.madpractical2;

import android.content.Intent;
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
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://practical-6-1e9c8-default-rtdb.asia-southeast1.firebasedatabase.app");
        usersRef = database.getReference("Users");

        // Initialize views
        EditText editTextUsername = findViewById(R.id.editTextText);
        EditText editTextPassword = findViewById(R.id.editTextText2);
        Button login = findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = String.valueOf(editTextUsername.getText());

                // Validate input fields
                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(getApplicationContext(), "Enter username", Toast.LENGTH_SHORT).show();
                    return;
                }

                String passwordString = editTextPassword.getText().toString().trim();
                if (TextUtils.isEmpty(passwordString)) {
                    Toast.makeText(getApplicationContext(), "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!TextUtils.isDigitsOnly(passwordString)) {
                    Toast.makeText(getApplicationContext(), "Invalid password format", Toast.LENGTH_SHORT).show();
                    return;
                }

                Long password = Long.valueOf(passwordString);

                // Query the database for the user with the entered email
                usersRef.child("mad").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            String dbusername = dataSnapshot.child("username").getValue(String.class);
                            Long dbpassword = dataSnapshot.child("password").getValue(Long.class);

                            if (username != null && password != null && username.equals(dbusername) && password.equals(dbpassword)) {
                                // Login success
                                Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                                Intent myIntent = new Intent(LoginPage.this, ListActivity.class);
                                startActivity(myIntent);
                                finish();

                                // Proceed to the next activity or perform other actions here
                            } else {
                                // Login failed
                                Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "username: " + dbusername, Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getApplicationContext(), "Password: " + dbpassword, Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            // User not found
                            Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
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