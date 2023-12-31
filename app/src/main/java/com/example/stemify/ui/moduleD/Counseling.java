package com.example.stemify.ui.moduleD;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stemify.Counselor;
import com.example.stemify.CounselorAdapter;
import com.example.stemify.CounselorData;
import com.example.stemify.R;

public class Counseling extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_counseling, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        CounselorData[] counselorData = new CounselorData[]{
                new CounselorData("Dr.Melissa Ho", "Specialist in treating mental health issues with young adults", "Experience: 12 Years", "Patients: 216", R.drawable.confused),
                new CounselorData("Dr.Adam Carlson", "Specialist in treating insomnia among young adults aged 13-22", "Experience: 7 Years", "Patients: 110", R.drawable.confused),
                new CounselorData("Dr.H.J. Kamal", "Specialist in helping young adults with ADHD", "Experience: 5 Years", "Patients: 96", R.drawable.confused),

        };

        CounselorAdapter counselorAdapter = new CounselorAdapter(counselorData, Counseling.this);
        recyclerView.setAdapter(counselorAdapter);

        return rootView;
    }
}