package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link to the Splash 2 after clicking the button
        Button StartJourney = findViewById(R.id.BtnStartJourney);
        StartJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //check if the user has logged in recently (didn't log out)
                //if yes, direct to home page
                FirebaseUser currentUser = mAuth.getCurrentUser();
                if(currentUser != null){
                    Intent nextScreen = new Intent(getApplicationContext(), Welcome.class);
                    startActivity(nextScreen);
                    finish();
                }else {
                    Intent nextScreen = new Intent(getApplicationContext(), Splash2.class);
                    startActivity(nextScreen);
                    finish();
                }
            }
        });


    }
}