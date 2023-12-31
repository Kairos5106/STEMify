package com.example.stemify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HealthGuide extends AppCompatActivity {

    CardView health, mental, fitness, clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_page_d);

        //first cardview to new page
        health = findViewById(R.id.card_health1);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthGuide.this, Lifestyle.class);
                startActivity(intent);
            }
        });

        //second cardview to new page
        mental = findViewById(R.id.card_health2);
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthGuide.this, MentalHealth.class);
                startActivity(intent);
            }
        });

        //third cardview to new page
        fitness = findViewById(R.id.card_health3);
        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthGuide.this, HealthFitness.class);
                startActivity(intent);
            }
        });

        //forth carview to new page
        clean = findViewById(R.id.card_health4);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthGuide.this, Cleanliness.class);
                startActivity(intent);
            }
        });
    }
}
