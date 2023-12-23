package com.example.stemify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.GenericLifecycleObserver;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeworkHelp_NewQuestion extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference userRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_help_new_question);

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBNewQuestion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("New Question");

        // Binding
        ImageView IVProfilePic = findViewById(R.id.IVProfilePic);
        TextView TVUsername = findViewById(R.id.TVUsername);
        EditText ETQuestion = findViewById(R.id.ETQuestion);
        EditText ETDescription = findViewById(R.id.ETDescription);
        TextInputLayout TagsLayout = findViewById(R.id.TagsLayout);
        TextInputEditText ETTags = findViewById(R.id.ETTags);
        Button BtnPost = findViewById(R.id.BtnPost);

        // Load current user profile pic from Realtime Database
        if (currentUser != null) {
            String userId = currentUser.getUid();

            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            String photoUrl = user.getPhotoUrl();
                            String username = user.getUsername();

                            // Load profile pic using Picasso
                            Picasso.get().load(photoUrl).into(IVProfilePic);

                            // Set username
                            TVUsername.setText(username);
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Error handling
                    Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }


        // BtnPost onClickListener
        BtnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Thank you for your post";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });

    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Finish the current activity to navigate back
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}
