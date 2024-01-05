package com.example.stemify.ui.moduleC;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
}