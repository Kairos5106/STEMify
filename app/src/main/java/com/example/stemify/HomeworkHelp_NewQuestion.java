package com.example.stemify;

import android.os.Bundle;
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
import com.squareup.picasso.Picasso;

public class HomeworkHelp_NewQuestion extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_help_new_question);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBNewQuestion);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("New Question");

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        ImageView IVProfilePic = findViewById(R.id.IVProfilePic);
        TextView TVUsername = findViewById(R.id.TVUsername);
        EditText ETQuestion = findViewById(R.id.ETQuestion);
        EditText ETDescription = findViewById(R.id.ETDescription);
        TextInputLayout TagsLayout = findViewById(R.id.TagsLayout);
        TextInputEditText ETTags = findViewById(R.id.ETTags);
        Button BtnPost = findViewById(R.id.BtnPost);

        // Load current user profile pic
        if (currentUser != null) {
            String photoUrl = String.valueOf(currentUser.getPhotoUrl());
            String username = currentUser.getDisplayName();

            // Load profile picture using Picasso
            Picasso.get().load(photoUrl).placeholder(R.drawable.pfp).into(IVProfilePic);

            // Set the username
            TVUsername.setText(username);
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
