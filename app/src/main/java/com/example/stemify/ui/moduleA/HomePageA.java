package com.example.stemify.ui.moduleA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

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
    String userIdentity = "";
    VPAdapter vpAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get user identity from database;
        getUserIdentity();
        Toast.makeText(getActivity(), "onCreate userIdentity: " + userIdentity, Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_a, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.modA_TLHomePageA);
        viewPager = (ViewPager) view.findViewById(R.id.modA_VPHomePageA);

        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Assign fragments to tabs
        vpAdapter = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ResourceLibrary(), "LIBRARY");
        vpAdapter.addFragment(getAppropriateLayout(), "COMMUNITY");
        vpAdapter.addFragment(new Downloads(), "DOWNLOADS");
        viewPager.setAdapter(vpAdapter);
        vpAdapter.notifyDataSetChanged();
    }

    public void getUserIdentity(){
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User user = snapshot.getValue(User.class);
                    userIdentity = user.getIdentity();
                    Toast.makeText(getActivity(), "in userIdentity: " + userIdentity, Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    // Adding appropriate resource community layout for users of different identities
    public Fragment getAppropriateLayout(){
        if(userIdentity.equalsIgnoreCase("Tutor")){ // if user is an Educator, give student fragment
            Toast.makeText(getActivity(), "Educator View", Toast.LENGTH_SHORT).show();
            return new ResourceCommunityEducator();
        }
        else { // if user is anything but an Educator, give Student fragment
            Toast.makeText(getActivity(), "Student View", Toast.LENGTH_SHORT).show();
            return new ResourceCommunity();
        }
    }
}