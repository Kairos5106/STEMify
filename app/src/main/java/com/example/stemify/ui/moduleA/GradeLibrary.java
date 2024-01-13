package com.example.stemify.ui.moduleA;

import androidx.annotation.NonNull;
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
import android.view.MenuItem;
import android.widget.Toast;

import com.example.stemify.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GradeLibrary extends AppCompatActivity {
    GradeAdapter gradeAdapter;
    RecyclerView recyclerView;
    List<Grade> listOfGrades;
    DatabaseReference database;
    String selectedSubjectTitle;
    String selectedGrade;
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
        gradeAdapter = new GradeAdapter(GradeLibrary.this, listOfGrades);
        recyclerView.setAdapter(gradeAdapter);
        gradeAdapter.notifyDataSetChanged();

        // Upon the click of a grade level, send selected subject title and grade level to the next activity
        gradeAdapter.setOnItemClickListener(new GradeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToTopicLibrary = new Intent(GradeLibrary.this, TopicLibrary.class);
                goToTopicLibrary.putExtra("selectedSubjectTitle", selectedSubjectTitle);
                goToTopicLibrary.putExtra("selectedGrade", listOfGrades.get(position).getGradeTitle());
                startActivity(goToTopicLibrary);
            }
        });
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
        Intent prevActivityData = getIntent();
        selectedSubjectTitle = prevActivityData.getStringExtra("subjectSelected");

        // Instantiate list of grade items
        listOfGrades = new ArrayList<Grade>();

        listOfGrades.add(new Grade("Form 1"));
        listOfGrades.add(new Grade("Form 2"));
        listOfGrades.add(new Grade("Form 3"));
        listOfGrades.add(new Grade("Form 4"));
        listOfGrades.add(new Grade("Form 5"));
    }
}