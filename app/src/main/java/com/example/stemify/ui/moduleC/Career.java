package com.example.stemify.ui.moduleC;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stemify.R;

import java.util.ArrayList;
import java.util.List;

public class Career extends Fragment {

    Context mContext;
    List<ResourceData> mList;
    RecyclerView mRecyclerView;
    ResourceAdapter mResourceAdapter;
    FragmentManager fragmentManager;
    View rootView;

    public Career() {}

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mContext = getActivity().getApplicationContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_career, container, false);

        mRecyclerView = rootView.findViewById(R.id.RVCareer);
        mList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        mList.add(new ResourceData("Doctor", "Science", R.drawable.doctor, R.string.doctor));
        mList.add(new ResourceData("Chemist", "Science", R.drawable.chemist2, R.string.chemist));
        mList.add(new ResourceData("Software Engineer", "Technology", R.drawable.software_engineer, R.string.software_engineer));
        mList.add(new ResourceData("Web Developer", "Technology", R.drawable.web_dev, R.string.web_developer));
        mList.add(new ResourceData("Computer System Analyst", "Technology", R.drawable.cs_analyst, R.string.computer_system_analyst));
        mList.add(new ResourceData("Cost Estimator", "Mathematics", R.drawable.cost_estimator, R.string.cost_estimator));
        mList.add(new ResourceData("Industrial Engineer", "Engineering", R.drawable.industrial_engineering, R.string.industrial_engineer));
        mList.add(new ResourceData("Civil Engineer", "Engineering", R.drawable.civil_engineering, R.string.civil_engineer));
        mList.add(new ResourceData("Mechanical Engineer", "Engineering", R.drawable.mechanical_engineer, R.string.mechanical_engineer));
        mList.add(new ResourceData("Statistician", "Mathematics", R.drawable.career, R.string.statistician));

        mResourceAdapter = new ResourceAdapter(getContext(), mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setAdapter(mResourceAdapter);

        return rootView;
    }
}