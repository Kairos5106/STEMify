package com.example.stemify.ui.moduleD;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stemify.CommunityEventAdapter;
import com.example.stemify.CommunityEventData;
import com.example.stemify.R;

public class Community extends Fragment {

    RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_community, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerViewEvents);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        CommunityEventData[] communityEventData = new CommunityEventData[]{
                new CommunityEventData("World Health Conference", "27 Nov 2023", "9:00 A.M - 13:00 P.M", "Online - Zoom", getString(R.string.event1), R.drawable.event1),
                new CommunityEventData("Medical Check-Up By MedicHealth clinic", "12 Jan 2024", "2:00 P.M - 4:00 P.M", "SMK Satu Dua", getString(R.string.event2), R.drawable.event2),
                new CommunityEventData("How To Brush", "25 Feb 2024", "8:00 A.M - 9:00 A.M", "SMK Padang Besar", getString(R.string.event3), R.drawable.event3),

        };

        CommunityEventAdapter eventsAdapter = new CommunityEventAdapter(communityEventData, getContext());
        recyclerView.setAdapter(eventsAdapter);
        return rootView;
    }
}