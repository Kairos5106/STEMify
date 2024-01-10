package com.example.stemify.ui.moduleD;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stemify.CounselorAdapter;
import com.example.stemify.CounselorData;
import com.example.stemify.CounselorLists;
import com.example.stemify.R;

public class Counseling extends Fragment {

    Button BtnViewBookings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_counseling, container, false);

        BtnViewBookings = rootView.findViewById(R.id.BtnViewBook);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewCounseling);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CounselorData[] counselorData = new CounselorData[]{
                new CounselorData("Dr. Kevina Ho", "Specialist in treating mental health issues with young adults", "Experience: 12 Years", "kevinhotzewan@gmail.com", R.drawable.female),
                new CounselorData("Dr.Adam Carlson", "Specialist in treating insomnia among young adults aged 13-22", "Experience: 7 Years", "dradamcarlson@gmail.com", R.drawable.male1),
                new CounselorData("Dr.H.J. Kamal", "Specialist in helping young adults with ADHD", "Experience: 5 Years", "hjkamal@gmail.com", R.drawable.male1),

        };

        CounselorAdapter counselorAdapter = new CounselorAdapter(counselorData, getContext());
        recyclerView.setAdapter(counselorAdapter);


        //on view appointments
        BtnViewBookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CounselorLists.class);
                startActivity(intent);
            }
        });


        return rootView;
    }
}