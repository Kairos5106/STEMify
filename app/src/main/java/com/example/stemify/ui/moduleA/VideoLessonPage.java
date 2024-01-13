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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.stemify.R;

public class VideoLessonPage extends AppCompatActivity {
    TranscriptAdapter transcriptAdapter;
    RecyclerView recyclerView;
    VideoView videoView;
    VideoLesson videoLesson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lesson_page);

        // Get data from Section page
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBVideoLesson);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Video Lesson");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    private void initializeData() {
        // Get data about selected video lesson object from SectionLibrary activity
        Intent prevActivityData = getIntent();
        String selectedMaterial = prevActivityData.getStringExtra("selectedMaterial");

        // Obtain Video Lesson data from database
        
        // Get video data and setup VideoView
        videoView = findViewById(R.id.VVVideoLesson);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/R.raw." + videoLesson.getVideoName()));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup transcript adapter to fit in transcript
        recyclerView = findViewById(R.id.RVTranscript);
        recyclerView.setLayoutManager(new LinearLayoutManager(VideoLessonPage.this));
        transcriptAdapter = new TranscriptAdapter(VideoLessonPage.this, videoLesson.getTranscript());
        recyclerView.setAdapter(transcriptAdapter);
        transcriptAdapter.notifyDataSetChanged();
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
}