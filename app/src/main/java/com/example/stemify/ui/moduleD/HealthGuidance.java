package com.example.stemify.ui.moduleD;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.example.stemify.Cleanliness;
import com.example.stemify.HealthFitness;
import com.example.stemify.HealthGuide;
import com.example.stemify.Lifestyle;
import com.example.stemify.MentalHealth;
import com.example.stemify.R;

public class HealthGuidance extends Fragment{

    CardView health, mental, fitness, clean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home_page_d, container, false);

        //first cardview to new page
        health = rootView.findViewById(R.id.card_health1);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Lifestyle.class);
                startActivity(intent);
            }
        });

        //second cardview to new page
        mental = rootView.findViewById(R.id.card_health2);
        mental.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MentalHealth.class);
                startActivity(intent);
            }
        });

        //third cardview to new page
        fitness = rootView.findViewById(R.id.card_health3);
        fitness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthFitness.class);
                startActivity(intent);
            }
        });

        //forth carview to new page
        clean = rootView.findViewById(R.id.card_health4);
        clean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Cleanliness.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}