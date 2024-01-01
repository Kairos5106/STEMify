package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class HomeworkHelp_PostDetail extends AppCompatActivity {
    TextView TVQuestionPostDetail;
    TextView TVTag1PostDetail;
    TextView TVTag2PostDetail;
    TextView TVTag3PostDetail;
    TextView TVDescriptionPostDetail;
    ImageView IVPfpPosterPostDetail;
    TextView TVUsernamePosterPostDetail;
    TextView TVTimePosterPostDetail;
    ImageView IVPfpYouPostDetail;
    EditText ETAddCommentPostDetail;
    Button BtnSendComment;
    String PostKey;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    private String userPhotoUrl;

    FirebaseDatabase firebaseDatabase;
    RecyclerView RVComment;
    HomeworkHelp_Comment_Adapter commentAdapter;
    List<HomeworkHelp_Comment> listComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homework_help_post_detail);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBPostDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("Discussion");

        // Binding
        TVQuestionPostDetail = findViewById(R.id.TVQuestionPostDetail);
        TVTag1PostDetail = findViewById(R.id.TVTag1PostDetail);
        TVTag2PostDetail = findViewById(R.id.TVTag2PostDetail);;
        TVTag3PostDetail = findViewById(R.id.TVTag3PostDetail);;
        TVDescriptionPostDetail = findViewById(R.id.TVDescriptionPostDetail);
        IVPfpPosterPostDetail = findViewById(R.id.IVPfpPosterPostDetail);
        TVUsernamePosterPostDetail = findViewById(R.id.TVUsernamePosterPostDetail);
        TVTimePosterPostDetail = findViewById(R.id.TVTimePosterPostDetail);

        IVPfpYouPostDetail = findViewById(R.id.IVPfpYouPostDetail);
        ETAddCommentPostDetail = findViewById(R.id.ETAddCommentPostDetail);
        BtnSendComment = findViewById(R.id.BtnSendComment);

        RVComment = findViewById(R.id.RVComments);

        // Load current user pfp
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("users");
        if (firebaseUser != null) {
            String userId = firebaseUser.getUid();

            databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            userPhotoUrl = user.getPhotoUrl();

                            // Load profile pic using Picasso
                            Picasso.get().load(userPhotoUrl).into(IVPfpYouPostDetail);
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

        // Get post detail data from firebase realtime database
        // Based on post position on adapter that was clicked
        // Then load it into the placeholder
        String postTitle = getIntent().getExtras().getString("title");
        TVQuestionPostDetail.setText(postTitle);

        String description = getIntent().getExtras().getString("description");
        TVDescriptionPostDetail.setText(description);

        String posterPfp = getIntent().getExtras().getString("userPfp");
        Picasso.get().load(posterPfp).into(IVPfpPosterPostDetail);

        String username = getIntent().getExtras().getString("username");
        TVUsernamePosterPostDetail.setText(username);

        PostKey = getIntent().getExtras().getString("postKey");

        String date = timestampToString(getIntent().getExtras().getLong("postDate"));
        TVTimePosterPostDetail.setText("Posted on " + date);

        // Comment section
        firebaseDatabase = FirebaseDatabase.getInstance();

        // BtnSendComment click listener
        BtnSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BtnSendComment.setVisibility(View.INVISIBLE);

                // Add the 1 particular comment to the "Comment" node in firebase
                // Also add a child node "PostKey" referring to the specific postkey in which the comment was posted
                // Note: 1 post (1 postkey) can have many comments while one particular comment can be under 1 post only
                DatabaseReference commentReference = firebaseDatabase.getReference("Comments").child(PostKey).push();
                String commentContent = ETAddCommentPostDetail.getText().toString();
                String commenterID = firebaseUser.getUid();
                String commenterUsername = firebaseUser.getDisplayName();
                String commenterPfp = userPhotoUrl;
                HomeworkHelp_Comment comment = new HomeworkHelp_Comment(commentContent, commenterID, commenterUsername, commenterPfp);

                commentReference.setValue(comment)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                String messageOnSuccess = "Comment added.";
                                Toast.makeText(HomeworkHelp_PostDetail.this, messageOnSuccess, Toast.LENGTH_LONG).show();
                                ETAddCommentPostDetail.setText("");
                                BtnSendComment.setVisibility(View.VISIBLE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("Error", "Error adding comment: " + e.getMessage());
                                // Handle the failure case, e.g., log the error or show an error message
                                String errorMessage = "Fail to add comment: " + e.getMessage();
                                Toast.makeText(HomeworkHelp_PostDetail.this, errorMessage, Toast.LENGTH_LONG).show();
                            }
                        });

            }
        });

        // Initialise RecyclerView for comments
        initRVComment();

    }

    private String timestampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH );
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
    }

    private void initRVComment() {

        RVComment.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference("Comments").child(PostKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listComment = new ArrayList<>();

                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    HomeworkHelp_Comment comment = snap.getValue(HomeworkHelp_Comment.class);
                    listComment.add(comment);
                }

                Collections.reverse(listComment); // to make the most recent comment appear at top, just add this one line of code

                commentAdapter = new HomeworkHelp_Comment_Adapter(getApplicationContext(), listComment);
                RVComment.setAdapter(commentAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
}