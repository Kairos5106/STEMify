package com.example.stemify.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stemify.Counselor;
import com.example.stemify.HealthGuide;
import com.example.stemify.Lifestyle;
import com.example.stemify.R;
import com.example.stemify.ui.moduleD.Counseling;
import com.example.stemify.ui.moduleD.HealthGuidance;

import java.text.DateFormat;
import java.util.Calendar;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

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

        //button for feature 1: healthy lifestyle and initiatives
        Button lifestyle = rootView.findViewById(R.id.BtnInitiate);

        lifestyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an instance of HealthGuidance fragment
                HealthGuidance healthGuidanceFragment = new HealthGuidance();

                // to replace HomeFragment with HealthGuidance fragment
                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_home, healthGuidanceFragment)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        // button for feature 2: counseling session
        Button counseling = rootView.findViewById(R.id.BtnCounseling);

        counseling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Counseling.class);
                startActivity(intent);
            }
        });

        //button for feature 3: community initiatives and health events
        Button health = rootView.findViewById(R.id.BtnCommunity);

        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), HealthGuidance.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

}