package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Quiz_ScoreActivity extends AppCompatActivity {

    TextView TVScoreDigit;
    TextView TVTotalDigit;
    TextView TVTitleYourScore;
    int score;
    int total;

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

        score = getIntent().getIntExtra("score", 0);
        total = getIntent().getIntExtra("total", 0);
        TVScoreDigit.setText(String.valueOf(score));
        TVTotalDigit.setText(String.valueOf(total));

        if (score == total) {
            TVTitleYourScore.setText("Congratulations!");
        } else if (score == 0) {
            TVTitleYourScore.setText("Don't give up, try again!");
        } else {
            TVTitleYourScore.setText("Almost there!");
        }


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