package com.example.stemify.ui.moduleA;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
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

public class TopicLibrary extends AppCompatActivity {
    TopicAdapter topicAdapter;
    RecyclerView recyclerView;
    List<ResourceTopic> listOfTopics;
    String selectedSubjectTitle;
    String selectedGrade;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_library);
        initalizeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBTopicLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Topics");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVTopicLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(TopicLibrary.this));
        topicAdapter = new TopicAdapter(TopicLibrary.this, listOfTopics);
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();

        // Upon clicking a topic, user will be redirected to a page listing the subtopics of the subject
        topicAdapter.setOnItemClickListener(new TopicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToSubtopicLibrary = new Intent(TopicLibrary.this, SubtopicLibrary.class);

                // Insert information to be transferred to next Activity
                goToSubtopicLibrary.putExtra("selectedGrade", selectedGrade);
                goToSubtopicLibrary.putExtra("selectedSubject", selectedSubjectTitle);
                goToSubtopicLibrary.putExtra("selectedTopic", listOfTopics.get(position).getTitle());

                startActivity(goToSubtopicLibrary);
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
        // Get data from previous activity's intent
        Intent prevActivityData = getIntent();
        selectedSubjectTitle = prevActivityData.getStringExtra("selectedSubjectTitle");
        selectedGrade = prevActivityData.getStringExtra("selectedGrade");

        // Initializing list of topic items
        listOfTopics = new ArrayList<ResourceTopic>();

        // Populate list with topics from selected subject and grade
        database = FirebaseDatabase.getInstance().getReference("Subjects")
                .child(selectedSubjectTitle)
                .child(selectedGrade);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ResourceTopic resourceTopic = dataSnapshot.getValue(ResourceTopic.class);
                    listOfTopics.add(resourceTopic);
                }
                topicAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}