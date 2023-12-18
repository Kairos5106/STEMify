package com.example.stemify.ui.moduleD;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stemify.R;
import com.example.stemify.VPAdapter;
import com.example.stemify.ui.moduleA.Downloads;
import com.example.stemify.ui.moduleA.ResourceCommunity;
import com.example.stemify.ui.moduleA.ResourceLibrary;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomePageD#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageD extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page_d, container, false);

        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.modD_TLHomePageD);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.modD_VPHomePageD);

        tabLayout.setupWithViewPager(viewPager);

        VPAdapter vpAdapter = new VPAdapter(getParentFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vpAdapter.addFragment(new HealthGuidance(), "HEALTH");
        vpAdapter.addFragment(new Community(),  "COMMUNITY");
        vpAdapter.addFragment(new Counseling(), "COUNSELING");
        viewPager.setAdapter(vpAdapter);

        return view;
    }
}