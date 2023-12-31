package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class ModuleHealthMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_d_main);

        //calender for daily checking

        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView txtViewDate = findViewById(R.id.TVDate);
        txtViewDate.setText(currentDate);

        //emoticon picker for user to choose
        ImageView emoticon1 = findViewById(R.id.BtnSad);
        ImageView emoticon2 = findViewById(R.id.BtnNormal);
        ImageView emoticon3 = findViewById(R.id.BtnHappy);
        ImageView emoticon4 = findViewById(R.id.BtnExcited);

        emoticon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Hope you feel better soon", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon1.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Cheer up a little! ", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon1.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Glad to see you feeling good", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon1.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Great! Keep smiling! ", Toast.LENGTH_SHORT).show();
            }
        });

        //button for feature 1: healthy lifestyle and initiatives
        Button lifestyle = (Button)findViewById(R.id.BtnInitiate);

        lifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLifestyle();
            }
        });

        // button for feature 2: counseling session
        Button counseling = (Button)findViewById(R.id.BtnCounseling);

        counseling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCounselor();
            }
        });

        //button for feature 3: community initiatives and health events
        Button health = (Button) findViewById(R.id.BtnCommunity);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCommunity();
            }
        });
    }
    public void openCounselor(){
        Intent intent = new Intent(this, Counselor.class);
        startActivity(intent);
    }

    public void openLifestyle(){
        Intent intent = new Intent(this, HealthGuide.class);
        startActivity(intent);
    }

    public void openCommunity(){
        Intent intent = new Intent(this, Lifestyle.class);
        startActivity(intent);
    }
}