package com.example.stemify.ui.moduleA;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stemify.DownloadAdapter;
import com.example.stemify.DownloadItem;
import com.example.stemify.R;

import java.util.ArrayList;
import java.util.List;

public class ResourceLibrary extends Fragment {
    SubjectAdapter subjectAdapter;
    RecyclerView recyclerView;
    List<Subject> listOfItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initalizeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_resource_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.RVLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        subjectAdapter = new SubjectAdapter(getContext(), listOfItems);
        recyclerView.setAdapter(subjectAdapter);
        subjectAdapter.notifyDataSetChanged();
    }

    public void initalizeData(){
        // Initializing list of download items
        listOfItems = new ArrayList<Subject>();

        // Populate list with download items
        listOfItems.add(new Subject("Test1"));
        listOfItems.add(new Subject("Test2"));
        listOfItems.add(new Subject("Test3"));
        listOfItems.add(new Subject("Test4"));
        listOfItems.add(new Subject("Test5"));
        listOfItems.add(new Subject("Test6"));
        listOfItems.add(new Subject("Test7"));
        listOfItems.add(new Subject("Test8"));
        listOfItems.add(new Subject("Test9"));
        listOfItems.add(new Subject("Test10"));
    }
}