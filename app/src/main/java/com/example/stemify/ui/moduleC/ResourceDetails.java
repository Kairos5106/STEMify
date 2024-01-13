package com.example.stemify.ui.moduleC;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stemify.R;

public class ResourceDetails extends AppCompatActivity {

    TextView cardName;
    TextView cardDetails;
    ImageView cardImg;

    Bundle extras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_details);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Details");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);



        cardName = findViewById(R.id.TVCardNameDetails);
        cardImg = findViewById(R.id.IVCardDetails);
        cardDetails = findViewById(R.id.TVCardDetails);

        extras = getIntent().getExtras();

        if (extras != null){

            cardName.setText(extras.getString("Name"));
            cardImg.setImageResource(extras.getInt("ImageData"));
            cardDetails.setText(extras.getInt("Details"));
        }

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