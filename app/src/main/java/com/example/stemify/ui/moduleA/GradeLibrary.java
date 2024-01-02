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

public class GradeLibrary extends AppCompatActivity {
    GradeAdapter gradeAdapter;
    RecyclerView recyclerView;
    List<Grade> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_library);

        initalizeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBGradeLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Grades");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVGradeLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(GradeLibrary.this));
        gradeAdapter = new GradeAdapter(GradeLibrary.this, listOfItems);
        recyclerView.setAdapter(gradeAdapter);
        gradeAdapter.notifyDataSetChanged();
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

    public void initalizeData(){
        // Initializing list of topic items
        listOfItems = new ArrayList<Grade>();

        // Populate list with grade items
        listOfItems.add(new Grade("Grade1"));
        listOfItems.add(new Grade("Grade2"));
        listOfItems.add(new Grade("Grade3"));
        listOfItems.add(new Grade("Grade4"));
        listOfItems.add(new Grade("Grade5"));
    }
}