package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Tutoring_TutorDetail extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    DatabaseReference userRef;
    private String userPhotoUrl;
    private String username;
    ImageView IVTutorPfpTutorDetail;
    TextView TVTutorUsernameTutorDetail;
    TextView TVOrganisationTutorDetail;
    TextView TVAboutDescriptionTutorDetail;
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
        setTitle("Profile");

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Binding
        IVTutorPfpTutorDetail = findViewById(R.id.IVTutorPfpTutorDetail);
        TVTutorUsernameTutorDetail = findViewById(R.id.TVTutorUsernameTutorDetail);
        TVOrganisationTutorDetail = findViewById(R.id.TVOrganisationTutorDetail);
        TVAboutDescriptionTutorDetail = findViewById(R.id.TVAboutDescriptionTutorDetail);
        BtnStartVideoCall = findViewById(R.id.BtnStartVideoCall);

        // Load current user info
        if (currentUser != null) {
            String userId = currentUser.getUid();

            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            userPhotoUrl = user.getPhotoUrl();
                            username = user.getDisplayName();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Error handling
                    Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

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

        // BtnStartVideoCall onClickListener
        BtnStartVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Tutoring_TutorDetail.this, "Button clicked", Toast.LENGTH_SHORT).show();
                //onVideoCallClicked(username);
            }
        });
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