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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.stemify.Cleanliness;
import com.example.stemify.HealthFitness;
import com.example.stemify.HealthGuide;
import com.example.stemify.Lifestyle;
import com.example.stemify.MentalHealth;
import com.example.stemify.R;

import java.text.DateFormat;
import java.util.Calendar;

public class HealthGuidance extends Fragment{

    CardView health, mental, fitness, clean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_health_guidance, container, false);


        //calender for daily checking
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView txtViewDate = rootView.findViewById(R.id.TVDate);
        txtViewDate.setText(currentDate);

        //emoticon picker for user to choose
        ImageView emoticon1 = rootView.findViewById(R.id.BtnSad);
        ImageView emoticon2 = rootView.findViewById(R.id.BtnNormal);
        ImageView emoticon3 = rootView.findViewById(R.id.BtnHappy);
        ImageView emoticon4 = rootView.findViewById(R.id.BtnExcited);

        emoticon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Hope you feel better soon", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon1.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Cheer up a little! ", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon1.setVisibility(View.GONE);
                emoticon4.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Glad to see you feeling good", Toast.LENGTH_SHORT).show();
            }
        });

        emoticon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emoticon2.setVisibility(View.GONE);
                emoticon3.setVisibility(View.GONE);
                emoticon1.setVisibility(View.GONE);
                Toast.makeText(getActivity().getApplicationContext(), "Great! Keep smiling! ", Toast.LENGTH_SHORT).show();
            }
        });

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