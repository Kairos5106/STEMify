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

public class SubtopicLibrary extends AppCompatActivity {
    SubtopicAdapter subtopicAdapter;
    RecyclerView recyclerView;
    List<Subtopic> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtopic_library);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBSubtopicLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Subtopics");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVSubtopicLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(SubtopicLibrary.this));
        subtopicAdapter = new SubtopicAdapter(SubtopicLibrary.this, listOfItems);
        recyclerView.setAdapter(subtopicAdapter);
        subtopicAdapter.notifyDataSetChanged();
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
        // Initializing list of topic items
        listOfItems = new ArrayList<Subtopic>();

        // Populate list with grade items
        Subtopic subtopic1 = new Subtopic("Subtopic 1");
        Section section1a = new Section("Section 1");
        Section section1b = new Section("Section 2");
        Section section1c = new Section("Section 3");
        subtopic1.addSection(section1a);
        subtopic1.addSection(section1b);
        subtopic1.addSection(section1c);

        listOfItems.add(subtopic1);
    }
}