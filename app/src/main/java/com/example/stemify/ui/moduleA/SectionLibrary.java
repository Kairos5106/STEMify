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

public class SectionLibrary extends AppCompatActivity {
    SectionAdapter sectionAdapter;
    RecyclerView recyclerView;
    List<Section> listOfItems;
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
        // Initializing list of topic items
        listOfItems = new ArrayList<Section>();

        // Populate list with grade items
        Section section1 = new Section("Section 1");
        Material material1a = new Material("Material 1a: Video Lesson");
        Material material1b = new Material("Material 1b: Practice");
        Material material1c = new Material("Material 1c: Quiz");
        material1a.setType("VideoLesson");
        material1b.setType("Practice");
        material1c.setType("Quiz");
        section1.addMaterial(material1a);
        section1.addMaterial(material1b);
        section1.addMaterial(material1c);
        listOfItems.add(section1);

        Section section2 = new Section("Section 2");
        Material material2a = new Material("Material 2a: Video Lesson");
        Material material2b = new Material("Material 2b: Practice");
        Material material2c = new Material("Material 2c: Quiz");
        material2a.setType("VideoLesson");
        material2b.setType("Practice");
        material2c.setType("Quiz");
        section2.addMaterial(material2a);
        section2.addMaterial(material2b);
        section2.addMaterial(material2c);
        listOfItems.add(section2);

        Section section3 = new Section("Section 3");
        Material material3a = new Material("Material 3a: Video Lesson");
        Material material3b = new Material("Material 3b: Practice");
        Material material3c = new Material("Material 3c: Quiz");
        material2a.setType("VideoLesson");
        material2b.setType("Practice");
        material2c.setType("Quiz");
        section3.addMaterial(material3a);
        section3.addMaterial(material3b);
        section3.addMaterial(material3c);
        listOfItems.add(section3);
    }
}