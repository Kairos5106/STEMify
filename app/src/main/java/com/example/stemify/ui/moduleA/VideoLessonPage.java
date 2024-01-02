package com.example.stemify.ui.moduleA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lesson_page);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBSectionLibrary);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Sections");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);

        // Setup VideoView
        VideoView videoView = findViewById(R.id.VVVideoLesson);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.samplevideo));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
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