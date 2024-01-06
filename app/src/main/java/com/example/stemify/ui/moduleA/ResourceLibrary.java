package com.example.stemify.ui.moduleA;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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


        // Upon clicking a subject, user will be redirected to a page listing the topics of the subject
        subjectAdapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToGradeLibrary = new Intent(getContext(), GradeLibrary.class);
                startActivity(goToGradeLibrary);
            }
        });
    }

    public void initalizeData(){
        // Initializing list of subject items
        listOfItems = new ArrayList<Subject>();

        // Populate list with download items
        Subject subject1 = new Subject("Test1");
        Grade topic1a = new Grade("Form 1");
        Grade topic1b = new Grade("Form 2");
        Grade topic1c = new Grade("Form 3");
        subject1.addGrade(topic1a);
        subject1.addGrade(topic1b);
        subject1.addGrade(topic1c);

        listOfItems.add(subject1);
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