package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Quiz_ScoreActivity extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    DatabaseReference userRef;
    private String userPhotoUrl;
    private String username;

    TextView TVScoreDigit;
    TextView TVTotalDigit;
    TextView TVTitleYourScore;
    int score;
    int total;

    FirebaseUser user;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_score);

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBYourScore);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("Your Score");

        // Binding UI elements
        TVScoreDigit = findViewById(R.id.TVScoreDigit);
        TVTotalDigit = findViewById(R.id.TVTotalDigit);
        TVTitleYourScore = findViewById(R.id.TVTitleYourScore);

        // Load current user profile pic from Realtime Database
        if (currentUser != null) {
            String userId = currentUser.getUid();

            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            userPhotoUrl = user.getPhotoUrl();
                            username = user.getDisplayName();
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

        // Display score and total
        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);
        TVScoreDigit.setText(String.valueOf(score));
        TVTotalDigit.setText(String.valueOf(total));

        // Set title of score screen according to student score
        if (score == total) {
            TVTitleYourScore.setText("Congratulations!");
        } else if (score == 0) {
            TVTitleYourScore.setText("Don't give up, try again!");
        } else {
            TVTitleYourScore.setText("Almost there!");
        }

        // Store the score (cumulative) in database so that can display in leaderboard later
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference();

        DatabaseReference quizScoresRef = reference.child("QuizScores").child(user.getUid());

        // Get the reference to the "result" child
        DatabaseReference resultRef = quizScoresRef.child("result");

        // Add new score to database (cumulatively)
        resultRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Get score history from database
                    score += Integer.parseInt(dataSnapshot.getValue().toString());
                }

                // Update the "result" child with the new score
                dataSnapshot.getRef().setValue(score);

                // Store username and userPhotoUrl
                quizScoresRef.child("username").setValue(username);
                quizScoresRef.child("userPhotoUrl").setValue(userPhotoUrl);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
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