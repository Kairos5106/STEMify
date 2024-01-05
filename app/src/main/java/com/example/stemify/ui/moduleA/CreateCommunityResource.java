package com.example.stemify.ui.moduleA;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class CreateCommunityResource extends AppCompatActivity {
    private static final int SELECT_IMAGE = 100;
    Button BtnSelectIcon;
    Button BtnCreateResource;
    ImageView IVIconPreview;
    RecyclerView recyclerView;
    ResSubtopicAdapter resSubtopicAdapter;
    List<ResSubtopicItem> listOfItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community_resource);
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBCreateCommunityResource);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Create Resource");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup button for icon selection
        BtnSelectIcon = (Button) findViewById(R.id.BtnSelectIcon);
        IVIconPreview = (ImageView) findViewById(R.id.IVIconPreview);
        BtnSelectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickImage = new Intent(Intent.ACTION_GET_CONTENT);
                pickImage.setType("image/*");
                startActivityForResult(Intent.createChooser(pickImage, "Select an icon"), SELECT_IMAGE);
            }
        });

        // Setup button to finish resource creation and save
        BtnCreateResource = (Button) findViewById(R.id.BtnCreateResource);
        BtnCreateResource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CreateCommunityResource.this, "Resource Successfully Created!", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Setup RecyclerView in subtopics list
        recyclerView = findViewById(R.id.RVResSubtopic);
        recyclerView.setLayoutManager(new LinearLayoutManager(CreateCommunityResource.this));
        resSubtopicAdapter = new ResSubtopicAdapter(CreateCommunityResource.this, listOfItems);
        recyclerView.setAdapter(resSubtopicAdapter);
        resSubtopicAdapter.notifyDataSetChanged();
    }

    private void initializeData() {
        listOfItems = new ArrayList<>();

        ResSubtopicItem item1 = new ResSubtopicItem();
        ResSubtopicItem item2 = new ResSubtopicItem();
        ResSubtopicItem item3 = new ResSubtopicItem();

        listOfItems.add(item1);
        listOfItems.add(item2);
        listOfItems.add(item3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_IMAGE && null != data){
            Uri uri = data.getData();
            IVIconPreview.setImageURI(uri);
        }
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