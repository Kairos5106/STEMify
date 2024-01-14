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
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class SubtopicLibrary extends AppCompatActivity {
    SubtopicAdapter subtopicAdapter;
    RecyclerView recyclerView;
    List<Subtopic> listOfItems;
    String selectedSubjectTitle;
    String selectedGrade;
    String selectedTopic;
    DatabaseReference database;
    TextView TVGradeLevel, TVSubjectWithTopic;
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
        subtopicAdapter.setOnItemClickListener(new SubtopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToSectionLibrary = new Intent(SubtopicLibrary.this, SectionLibrary.class);

                // Get selected subject title and grade level
                goToSectionLibrary.putExtra("selectedSubject", selectedSubjectTitle);
                goToSectionLibrary.putExtra("selectedGrade", selectedGrade);
                goToSectionLibrary.putExtra("selectedTopic", selectedTopic);

                // Get selected subtopic
                Subtopic selectedSubtopic = listOfItems.get(position);

                // Pack title of selected subtopic
                String subtopicTitle = selectedSubtopic.getTitle();
                goToSectionLibrary.putExtra("subtopicTitle", subtopicTitle);

                startActivity(goToSectionLibrary);
            }
        });
        recyclerView.setAdapter(subtopicAdapter);
        subtopicAdapter.notifyDataSetChanged();

        // Adapt subtopic information on top of page
        TVGradeLevel = findViewById(R.id.TVGradeLevel);
        TVGradeLevel.setText(selectedGrade);

        TVSubjectWithTopic = findViewById(R.id.TVSubjectWithTopic);
        TVSubjectWithTopic.setText(selectedSubjectTitle + ": " + selectedTopic);
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
        // Get data from previous activity's intent
        Intent prevActivityData = getIntent();
        selectedSubjectTitle = prevActivityData.getStringExtra("selectedSubject");
        selectedGrade = prevActivityData.getStringExtra("selectedGrade");
        selectedTopic = prevActivityData.getStringExtra("selectedTopic");

        // Initializing list of subtopic items
        listOfItems = new ArrayList<Subtopic>();

        // Populate list with subtopics from selected topic
        database = FirebaseDatabase.getInstance().getReference("Subjects")
                .child(selectedSubjectTitle)
                .child(selectedGrade)
                .child(selectedTopic);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){ // Addition
                    if(dataSnapshot.getValue() instanceof String){
                        continue;
                    }

                    // Retrieve subtopic and add it to listOfItems to be displayed by RecyclerView
                    Subtopic subtopic = dataSnapshot.getValue(Subtopic.class);

                    // Retrieve sections of the current subtopic
                    ArrayList<Section> listOfSections = new ArrayList<>();
                    for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) { // One Digit, Two Digit
                        if(childSnapshot.getValue() instanceof String){
                            continue;
                        }

                        // Retrieve each section of the current subtopic
                        Section section = childSnapshot.getValue(Section.class);
                        listOfSections.add(section);
                    }
                    subtopic.setListOfSections(listOfSections);
                    listOfItems.add(subtopic);
                }
                subtopicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}