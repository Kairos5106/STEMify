package com.example.stemify.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.stemify.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuizPage extends AppCompatActivity {
    QuestionAdapter questionAdapter;
    RecyclerView recyclerView;
    List<Question> listOfItems;
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVQuizPage);
        recyclerView.setLayoutManager(new LinearLayoutManager(QuizPage.this));
        questionAdapter = new QuestionAdapter(QuizPage.this, listOfItems);
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
        listOfItems = new ArrayList<Question>();

        // Populate listOfItems with sample question objects
        MultipleChoice question1 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question1.addAnswer("Answer 1a");
        question1.addAnswer("Answer 1b");
        question1.addAnswer("Answer 1c");
        question1.addAnswer("Answer 1d");
        question1.setDiagramId(R.drawable.sampleimage);
        question1.setCorrectAnswer("Answer 1a");
        question1.setDiagramDesc("Sample Diagram Description");
        listOfItems.add(question1);

        FillBlank question5 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        HashMap<Integer, String> answer5 = new HashMap<Integer, String>();
        answer5.put(0, "Lorem");
        answer5.put(1, "ipsum");
        question5.setCorrectAnswers(answer5);
        listOfItems.add(question5);

        MultipleChoice question2 = new MultipleChoice("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question2.addAnswer("Answer 2a");
        question2.addAnswer("Answer 2b");
        question2.addAnswer("Answer 2c");
        question2.addAnswer("Answer 2d");
        question2.setDiagramId(R.drawable.sampleimage);
        question2.setCorrectAnswer("Answer 2a");
        question2.setDiagramDesc("Sample Diagram Description");
        listOfItems.add(question2);

        FillBlank question3 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question3.addAnswer("Lorem");
        question3.addAnswer("ipsum");
        listOfItems.add(question3);

        FillBlank question4 = new FillBlank("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        question4.addAnswer("Lorem");
        question4.addAnswer("ipsum");
        listOfItems.add(question4);
    }
}