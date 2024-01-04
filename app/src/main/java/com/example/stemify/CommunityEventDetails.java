package com.example.stemify;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CommunityEventDetails extends AppCompatActivity {
    TextView textTitle, textDate, textPlace, textTime, textDetails;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communityevents_detail_view);

        //toolbar

        Toolbar toolbar = findViewById(R.id.TBCommunityEvent);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Community Events");

        //pass details to go to detailed page
        textTitle = findViewById(R.id.txtTitle);
        textDate = findViewById(R.id.txtDate);
        textTime = findViewById(R.id.txtTime);
        textPlace = findViewById(R.id.txtPlace);
        textDetails = findViewById(R.id.txtDetails);
        image = findViewById(R.id.detailed_image);


        Intent intent = getIntent();
        if (intent != null){
            String title = intent.getStringExtra("Title");
            String date = intent.getStringExtra("Date");
            String time = intent.getStringExtra("Time");
            String place = intent.getStringExtra("Place");
            String details = intent.getStringExtra("Detail");
            int imageID = intent.getIntExtra("Image", R.drawable.event1);


            textTitle.setText(title);
            textDate.setText(date);
            textTime.setText(time);
            textPlace.setText(place);
            textDetails.setText(details);
            image.setImageResource(imageID);


        }


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
}
