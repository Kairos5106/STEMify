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
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.ArrayList;
import java.util.List;

public class EditResourceVideo extends AppCompatActivity {
    private static int SELECT_VIDEO = 100;
    Button BtnSelectVideo;
    Button BtnSaveChanges;
    VideoView VVVideoPreview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_resource_video);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBEditResourceVideo);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Edit Video Lesson");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup button: save changes to video lesson edits
        BtnSaveChanges = (Button) findViewById(R.id.BtnSaveChanges);
        BtnSaveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EditResourceVideo.this, "Changes applied", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        // Set up button: pick video file for lesson
        VVVideoPreview = findViewById(R.id.VVVideoPreview);
        BtnSelectVideo = findViewById(R.id.BtnSelectVideo);
        BtnSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickVideo = new Intent(Intent.ACTION_GET_CONTENT);
                pickVideo.setType("video/*");
                startActivityForResult(Intent.createChooser(pickVideo, "Select an icon"), SELECT_VIDEO);
            }
        });

        // Add video player to preview
        MediaController mediaController = new MediaController(this);
        VVVideoPreview.setMediaController(mediaController);
        mediaController.setAnchorView(VVVideoPreview);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == SELECT_VIDEO && null != data){
            Uri uri = data.getData();
            VVVideoPreview.setVideoURI(uri);
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
        if(item.getItemId() == R.id.BtnCollaborate){
            Intent goToAddResourcePage = new Intent(EditResourceVideo.this, TestActivity.class); // chg ltr
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
        menuInflater.inflate(R.menu.menu_edit_resource, menu); // change later to add collaboration
        return true;
    }
}