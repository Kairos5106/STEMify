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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import com.example.stemify.R;
import com.example.stemify.TestActivity;
import com.google.android.material.badge.BadgeUtils;

import java.util.ArrayList;
import java.util.List;

public class EducatorResources extends AppCompatActivity {
    EducatorResourcesAdapter educatorResourcesAdapter;
    RecyclerView recyclerView;
    List<CommunityResourceItem> listOfItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_educator_resources);
        initalizeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBEducatorResources);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Your Resources");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    // Give action to options in app bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            finish();
            return true;
        }
        if(item.getItemId() == R.id.BtnAddResource){
            Intent goToAddResourcePage = new Intent(EducatorResources.this, CreateCommunityResource.class);
            startActivity(goToAddResourcePage);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Setup add resource button in app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_educator_resource, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVEducatorResources);
        recyclerView.setLayoutManager(new LinearLayoutManager(EducatorResources.this));
        educatorResourcesAdapter = new EducatorResourcesAdapter(EducatorResources.this, listOfItems);
        recyclerView.setAdapter(educatorResourcesAdapter);
        educatorResourcesAdapter.notifyDataSetChanged();
    }

    public void initalizeData(){
        // Initializing list of download items
        listOfItems = new ArrayList<CommunityResourceItem>();

        // Populate list with download items
        listOfItems.add(new CommunityResourceItem("Test1", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test2", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test3", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test4", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test5", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test6", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test7", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test8", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test9", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test10", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
    }
}