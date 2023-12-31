package com.example.stemify.ui.moduleA;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.ActionBar;
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

public class TopicLibrary extends AppCompatActivity {
    TopicAdapter topicAdapter;
    RecyclerView recyclerView;
    List<ResourceTopic> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic_library);
        initalizeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBTopicLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        topicAdapter = new TopicAdapter(getApplicationContext(), listOfItems);
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void initalizeData(){
        // Initializing list of topic items
        listOfItems = new ArrayList<ResourceTopic>();

        // Populate list with download items
        listOfItems.add(new ResourceTopic("Test1"));
        listOfItems.add(new ResourceTopic("Test2"));
        listOfItems.add(new ResourceTopic("Test3"));
        listOfItems.add(new ResourceTopic("Test4"));
        listOfItems.add(new ResourceTopic("Test5"));
        listOfItems.add(new ResourceTopic("Test6"));
        listOfItems.add(new ResourceTopic("Test7"));
        listOfItems.add(new ResourceTopic("Test8"));
        listOfItems.add(new ResourceTopic("Test9"));
        listOfItems.add(new ResourceTopic("Test10"));
        listOfItems.add(new ResourceTopic("Test11"));
        listOfItems.add(new ResourceTopic("Test12"));
    }
}