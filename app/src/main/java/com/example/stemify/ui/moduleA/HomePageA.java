package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.User;
import com.example.stemify.VPAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomePageA extends Fragment {
    Handler handler;
    String userIdentity;
    VPAdapter vpAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;
    SharedPreferences userPrefs;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get user identity from a shared preference defined earlier in MainActivity.java
        userIdentity = "";
        userPrefs = getActivity().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        userIdentity = userPrefs.getString("userIdentity", "Student");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_a, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.modA_TLHomePageA);
        viewPager = (ViewPager) view.findViewById(R.id.modA_VPHomePageA);

        tabLayout.setupWithViewPager(viewPager);

        // Assign fragments to tabs
        // For the community page, there are two different layouts available:
        // (1) Community Resources Page: For students
        // (2) Community Resources Page with View Resources button: For tutors
        vpAdapter = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ResourceLibrary(), "LIBRARY");

        // Determining which community page to add depending on user identity
//        if(userIdentity.equalsIgnoreCase("Tutor")){
//            vpAdapter.addFragment(new ResourceCommunityEducator(), "COMMUNITY");
//        }
//        else{
//            vpAdapter.addFragment(new ResourceCommunity(), "COMMUNITY");
//        }
        viewPager.setAdapter(vpAdapter);
        vpAdapter.notifyDataSetChanged();

        return view;
    }
}