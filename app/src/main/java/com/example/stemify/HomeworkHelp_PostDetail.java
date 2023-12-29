package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
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
import com.squareup.picasso.Picasso;

import java.util.Calendar;
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

    }

    private String timestampToString(long time) {
        Calendar calendar = Calendar.getInstance(Locale.ENGLISH );
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy", calendar).toString();
        return date;
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