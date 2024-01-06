package com.example.stemify.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.stemify.R;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizPage extends AppCompatActivity {
    QuestionAdapter questionAdapter;
    RecyclerView recyclerView;
    CustomTimer timer;
    TextView countdownTimer;
    List<Question> listOfQuestions;
    int quizDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBQuizPage);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Quiz");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup timer in page
        countdownTimer = findViewById(R.id.TVTimer);
        timer = new CustomTimer(quizDuration, countdownTimer);
        timer.updateCountDownText();
        timer.startTimer();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVQuizPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuizPage.this));
        questionAdapter = new QuestionAdapter(QuizPage.this, listOfQuestions);
        recyclerView.setAdapter(questionAdapter);
        questionAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initializeData(){
        Intent intent = getIntent();

        // Populate listOfItems with sample question objects
        Parcelable quizQuestions = intent.getParcelableExtra("quizQuestions");
        listOfQuestions = new ArrayList<>();
        listOfQuestions = Parcels.unwrap(quizQuestions);

        Parcelable quizDuration = intent.getParcelableExtra("quizDuration");
        this.quizDuration = Parcels.unwrap(quizDuration);
    }
}