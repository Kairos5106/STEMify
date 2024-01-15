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
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.stemify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VideoLessonPage extends AppCompatActivity {
    TranscriptAdapter transcriptAdapter;
    RecyclerView recyclerView;
    VideoView videoView;
    VideoLesson videoLesson;
    SelectHistory selectHistory;
    String selectedSection, selectedMaterial;
    DatabaseReference videoLessonRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_lesson_page);

        // Get data from Section page
        initializeData();

        // Setup videoview
        setupVideoView(videoLesson.getVideoName());

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBVideoLesson);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle(selectedMaterial);

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }

    private void initializeData() {
        // Instantiate required variables to prevent the use of null values
        videoLesson = new VideoLesson();
        selectHistory = new SelectHistory();

        // Gets data from previous activity intent
        Intent prevActivityData = getIntent();
        selectedSection = prevActivityData.getStringExtra("selectedSection");
        selectedMaterial = prevActivityData.getStringExtra("selectedMaterial");

        // Get user select history information
        DatabaseReference userSelectHistoryRef = FirebaseDatabase.getInstance().getReference("UserSelectHistory").child(getUserId());
        userSelectHistoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                selectHistory = snapshot.getValue(SelectHistory.class);

                // Obtain Video Lesson data from database
                videoLessonRef = FirebaseDatabase.getInstance().getReference("Subjects")
                        .child(selectHistory.getSelectedSubject())
                        .child(selectHistory.getSelectedGrade())
                        .child(selectHistory.getSelectedTopic())
                        .child(selectHistory.getSelectedSubtopic())
                        .child(selectedSection)
                        .child(selectedMaterial);

                videoLessonRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        videoLesson = snapshot.getValue(VideoLesson.class);
                        setupVideoView(videoLesson.getVideoName());
                        transcriptAdapter.setVideoTranscript(videoLesson.getTranscript());
                        transcriptAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void setupVideoView(String videoName){
        videoView = findViewById(R.id.VVVideoLesson);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/" + videoName));
        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);
    }

    public String getUserId(){
        String currentUserId = null;
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            currentUserId = currentUser.getUid();
        }
        return currentUserId;
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