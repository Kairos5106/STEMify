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
import android.os.ProxyFileDescriptorCallback;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SectionLibrary extends AppCompatActivity {
    SectionAdapter sectionAdapter;
    RecyclerView recyclerView;
    List<Section> listOfItems;
    TextView TVSubtopicTitle, TVGradeLevelWithSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_section_library);

        // Assign sample data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBSectionLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Sections");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVSectionLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(SectionLibrary.this));
        sectionAdapter = new SectionAdapter(SectionLibrary.this, listOfItems);
        recyclerView.setAdapter(sectionAdapter);
        sectionAdapter.notifyDataSetChanged();
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
        // Get section items to be displayed
        listOfItems = new ArrayList<Section>();
        listOfItems = Parcels.unwrap(getIntent().getParcelableExtra("listOfSections"));

        // Update the section information on top of the activity
        Intent prevActivityData = getIntent();

        String subtopicTitle = prevActivityData.getStringExtra("subtopicTitle");
        TVSubtopicTitle = findViewById(R.id.TVSubtopicTitle);
        TVSubtopicTitle.setText(subtopicTitle);

        String selectedSubject = prevActivityData.getStringExtra("selectedSubject");
        String selectedGrade = prevActivityData.getStringExtra("selectedGrade");
        TVGradeLevelWithSubject = findViewById(R.id.TVGradeLevelWithSubject);
        TVGradeLevelWithSubject.setText(selectedGrade + ": " + selectedSubject);
    }
}