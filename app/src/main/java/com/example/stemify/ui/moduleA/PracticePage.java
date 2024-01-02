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
import java.util.List;

public class PracticePage extends AppCompatActivity {
    QuestionAdapter questionAdapter;
    RecyclerView recyclerView;
    List<Question> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_page);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBPracticePage);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Practice");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVPracticePage);
        recyclerView.setLayoutManager(new LinearLayoutManager(PracticePage.this));
        questionAdapter = new QuestionAdapter(PracticePage.this, listOfItems);
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
    }
}