package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.stemify.ui.moduleA.Subject;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser;
    SharedPreferences userPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Instantiate shared preferences to store user identity
        // Will be used in the definition of certain activities for different users
        userPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = userPrefs.edit();
        currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(getUserId());
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    prefEditor.putString("userIdentity", user.getIdentity());
                    prefEditor.putString("userName", user.getDisplayName());
                    prefEditor.putString("userEmail", user.getEmail());
                    prefEditor.putString("userPhotoUrl", user.getPhotoUrl());
                    prefEditor.commit();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        //link to the Splash 2 after clicking the button
        Button StartJourney = findViewById(R.id.BtnStartJourney);
        StartJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if a user has logged in recently (didn't log out)
                //if yes, use back the same account and go to welcome page (don't have to login again)
                //if no, go to splash 2 page where the user can login/sign up
//                FirebaseUser currentUser = mAuth.getCurrentUser();
                if (currentUser != null) {
                    Intent nextScreen = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(nextScreen);
                    finish();
                } else {
                    Intent nextScreen = new Intent(getApplicationContext(), Splash2.class);
                    startActivity(nextScreen);
                    finish();
                }
            }
        });
    }

    public String getUserId(){
        String currentUserId = null;
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            currentUserId = currentUser.getUid();
        }
        return currentUserId;
    }
}