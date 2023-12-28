package com.example.stemify;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.GenericLifecycleObserver;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.stemify.HomeworkHelp_TagsInputEditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeworkHelp_NewQuestion extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    DatabaseReference userRef;
    DataManager postDataManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_help_new_question);

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");
        postDataManager = DataManager.getPostInstance();

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
        HomeworkHelp_TagsInputEditText ETTags = findViewById(R.id.ETTags);
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
                            String username = user.getDisplayName();

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

                // Test all input fields (Title, description, tags), if all fields are filled:

                List<String> tags = ETTags.getTags();

                if (!ETQuestion.getText().toString().isEmpty() &&
                        !ETDescription.getText().toString().isEmpty() /*&&
                        ETTags != null &&
                        tags.size() >= 1*/) {

                    // Save data to DataManager for later use
                    postDataManager.putPostData("question", ETQuestion.getText().toString());
                    postDataManager.putPostData("description", ETDescription.getText().toString());
                    postDataManager.putPostData("tags", tags);

                    // Create post object
                    HomeworkHelp_Post post = new HomeworkHelp_Post(ETQuestion.getText().toString(),
                            ETDescription.getText().toString(),
                            currentUser.getUid(),
                            currentUser.getPhotoUrl());

                    // Add post object to Firebase database
                    addPost(post);

                } else {
                    String messageN = "Please fill in all fields before posting.";
                    Toast.makeText(HomeworkHelp_NewQuestion.this, messageN, Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    private void addPost(HomeworkHelp_Post post) {

        // Get an instance of the Firebase Realtime Database
        // If a db instance doesn't alrd exist, it will create one
        // If a db instance alrd exists, it will return the existing instance
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        // Create a reference to the "Posts" node and generate a unique key using push()
        DatabaseReference postsRef = database.getReference("Posts").push();

        // Get the key generated by push() and set it in the post object
        String postKey = postsRef.getKey();
        post.setPostKey(postKey);

        // Add post data to firebase database
        Log.d("Debug", "addPost method called");
        postsRef.setValue(post)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("Debug", "Post added successfully");
                        String messageOnSuccess = "Post added successfully.";
                        Toast.makeText(HomeworkHelp_NewQuestion.this, messageOnSuccess, Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Error", "Error adding post: " + e.getMessage());
                        // Handle the failure case, e.g., log the error or show an error message
                        String errorMessage = "Error adding post: " + e.getMessage();
                        Toast.makeText(HomeworkHelp_NewQuestion.this, errorMessage, Toast.LENGTH_LONG).show();
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
