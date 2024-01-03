package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Tutoring_TutorDetail extends AppCompatActivity {

    ImageView IVTutorPfpTutorDetail;
    TextView TVTutorUsernameTutorDetail;
    TextView TVOrganisationTutorDetail;
    TextView TVAboutDescriptionTutorDetail;
    Button BtnBookASession;
    Button BtnStartVideoCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_tutor_detail);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBTutorDetail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("Tutor Profile");

        // Binding
        IVTutorPfpTutorDetail = findViewById(R.id.IVTutorPfpTutorDetail);
        TVTutorUsernameTutorDetail = findViewById(R.id.TVTutorUsernameTutorDetail);
        TVOrganisationTutorDetail = findViewById(R.id.TVOrganisationTutorDetail);
        TVAboutDescriptionTutorDetail = findViewById(R.id.TVAboutDescriptionTutorDetail);
        BtnBookASession = findViewById(R.id.BtnBookASession);
        BtnStartVideoCall = findViewById(R.id.BtnStartVideoCall);

        // Get tutor detail data from firebase realtime database
        // Based on tutor position on adapter that was clicked
        // Then load it into the placeholder
        String tutorName = getIntent().getExtras().getString("tutorName");
        TVTutorUsernameTutorDetail.setText(tutorName);

        String organisation = getIntent().getExtras().getString("organisation");
        TVOrganisationTutorDetail.setText(organisation);

        String tutorPfp = getIntent().getExtras().getString("tutorPfp");
        Picasso.get().load(tutorPfp).into(IVTutorPfpTutorDetail);

        String email = getIntent().getExtras().getString("email");
        TVAboutDescriptionTutorDetail.setText(email);
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