package com.example.stemify.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

public class CreateCommunityResource extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community_resource);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBCreateCommunityResource);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Create Your Resource");

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
            Intent goToAddResourcePage = new Intent(CreateCommunityResource.this, TestActivity.class); // chg ltr
            startActivity(goToAddResourcePage);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Setup add resource button in app bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_create_resource, menu);
        return true;
    }
}