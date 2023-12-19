package com.example.stemify.ui.moduleA;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stemify.R;
import com.example.stemify.VPAdapter;
import com.google.android.material.tabs.TabLayout;

public class HomePageA extends Fragment {
    VPAdapter vpAdapter;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_a, container, false);

        tabLayout = (TabLayout) view.findViewById(R.id.modA_TLHomePageA);
        viewPager = (ViewPager) view.findViewById(R.id.modA_VPHomePageA);

        tabLayout.setupWithViewPager(viewPager);

        vpAdapter = new VPAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new ResourceLibrary(), "LIBRARY");
        vpAdapter.addFragment(new ResourceCommunity(),  "COMMUNITY");
        vpAdapter.addFragment(new Downloads(), "DOWNLOADS");
        viewPager.setAdapter(vpAdapter);

        Toast.makeText(getContext().getApplicationContext(), "Item count: " + vpAdapter.getCount(), Toast.LENGTH_LONG);

        return view;
    }
}