package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//temporary My Profile activity for testing purposes
//to retrieve current user's email, password and profile picture, use FireBaseUser
//to retrieve other user's information, use DatabaseReference

public class MyProfile extends AppCompatActivity {

    String fullname;
    String username;
    String email;
    String identity;
    String organization;
    String securityQ;
    String answer;

    Uri profilePic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        TextView TVUSER = findViewById(R.id.TVUSER);
        ImageView ProfileP = findViewById(R.id.ProfileP);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {

            // Read from the database
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        User user = dataSnapshot.getValue(User.class);

                        fullname = user.getFullname();
                        username = user.getUsername();
                        identity = user.getIdentity();
                        organization = user.getOrganization();
                        securityQ = user.getSecurityques();
                        answer = user.getAnswer();
                        email = currentUser.getEmail();
                        profilePic = Uri.parse(user.getPhotoUrl());
                        TVUSER.setText(email);
                        ProfileP.setImageURI(profilePic);
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Handle errors
                    Toast.makeText(getApplication(), "error", Toast.LENGTH_SHORT).show();
                }
            });

        }

        Button BtnLogOut = findViewById(R.id.BtnLogOut);
        BtnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent nextScreen = new Intent(getApplicationContext(), Splash2.class);
                startActivity(nextScreen);
                finish();
            }
        });
    }
}
