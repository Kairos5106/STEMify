package com.example.stemify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Counselor extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_counselor_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        CounselorData[] counselorData = new CounselorData[]{
                new CounselorData("Dr.Melissa Ho", "Specialist in treating mental health issues with young adults", "Expereince: 12 Years", "Patients: 216", R.drawable.confused),
                new CounselorData("Dr.Adam Carlson", "Specialist in treating insomnia among young adults aged 13-22", "Expereince: 7 Years", "Patients: 110", R.drawable.confused),
                new CounselorData("Dr.H.J. Kamal", "Specialist in helping young adults with ADHD", "Expereince: 5 Years", "Patients: 96", R.drawable.confused),

        };

        //CounselorAdapter counselorAdapter = new CounselorAdapter(counselorData, Counseling.class);
        //recyclerView.setAdapter(counselorAdapter);

    }

}
