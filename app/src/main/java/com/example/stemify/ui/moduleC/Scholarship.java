package com.example.stemify.ui.moduleC;

import android.content.Context;
import android.os.Bundle;

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

public class Scholarship extends Fragment {

    Context mContext;
    List<ResourceData> mList;
    RecyclerView mRecyclerView;
    ResourceAdapter mRecyclerViewAdapter;
    FragmentManager fragmentManager;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_scholarship, container, false);

        mRecyclerView = rootView.findViewById(R.id.RVScholarship);
        mList = new ArrayList<>();

        fragmentManager = getActivity().getSupportFragmentManager();

        mList.add(new ResourceData("Khazanah Global Scholarship", "Full Scholarship", R.drawable.career, R.string.khazanah));
        mList.add(new ResourceData("MyBrainSc Scholarship", "Full Scholarship", R.drawable.career, R.string.mybrainsc));
        mList.add(new ResourceData("Bank Negara Scholarship", "Full Scholarship", R.drawable.career, R.string.bank_negara));
        mList.add(new ResourceData("JPA Scholarship", "Loan", R.drawable.career, R.string.jpa));
        mList.add(new ResourceData("Fullbright Program", "Partial Scholarship", R.drawable.career, R.string.fullbright));
        mList.add(new ResourceData("British Council GREAT Scholarship", "Partial Scholarship", R.drawable.career, R.string.british_council));

        mRecyclerViewAdapter = new ResourceAdapter(getContext(), mList);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        return rootView;
    }
}