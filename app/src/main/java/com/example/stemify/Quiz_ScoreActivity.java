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

public class Quiz_ScoreActivity extends AppCompatActivity {

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

        reference.child("QuizScores").child(user.getUid()).child("result").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    // Get score history from database
                    score += Integer.parseInt(dataSnapshot.getValue().toString());

                }

                // Add new score to database (cumulatively)
                dataSnapshot.getRef().setValue(score);
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