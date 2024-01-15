package com.example.stemify.ui.moduleE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.stemify.R;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EventDetailActivity extends AppCompatActivity {

    TextView eventDetailName, eventDetailDesc, eventDetailLocation, eventDetailDate, eventDetailTime;
    ImageView eventDetailImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.toolbar4);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Event Detail");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);



        eventDetailName = findViewById(R.id.eventDetailName);
        eventDetailDesc = findViewById(R.id.eventDetailDesc);
        eventDetailLocation = findViewById(R.id.eventDetailLocation);
        eventDetailDate = findViewById(R.id.eventDetailDate);
        eventDetailTime = findViewById(R.id.eventDetailTime);
        eventDetailImage = findViewById(R.id.eventDetailImage);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            eventDetailName.setText(bundle.getString("Name"));
            eventDetailDesc.setText(bundle.getString("Description"));
            eventDetailLocation.setText(bundle.getString("Location"));
            eventDetailDate.setText(bundle.getString("Date"));
            eventDetailTime.setText(bundle.getString("Time"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(bundle.getString("Image")).into(eventDetailImage);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Events");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(EventDetailActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), HomePageE.class));
                        finish();
                    }
                });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetailActivity.this, EventUpdateActivity.class)
                        .putExtra("Name", eventDetailName.getText().toString())
                        .putExtra("Description", eventDetailDesc.getText().toString())
                        .putExtra("Location", eventDetailLocation.getText().toString())
                        .putExtra("Date", eventDetailDate.getText().toString())
                        .putExtra("Time", eventDetailTime.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }

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