package com.example.stemify.ui.moduleA;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stemify.DownloadAdapter;
import com.example.stemify.DownloadItem;
import com.example.stemify.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ResourceLibrary extends Fragment {
    SubjectAdapter subjectAdapter;
    RecyclerView recyclerView;
    List<Subject> listOfSubjects;
    DatabaseReference database;
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
        subjectAdapter = new SubjectAdapter(getContext(), listOfSubjects);
        recyclerView.setAdapter(subjectAdapter);
        subjectAdapter.notifyDataSetChanged();


        // Upon clicking a subject, user will be redirected to a page listing the topics of the subject
        subjectAdapter.setOnItemClickListener(new SubjectAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent goToGradeLibrary = new Intent(getContext(), GradeLibrary.class);
                goToGradeLibrary.putExtra("subjectSelected", listOfSubjects.get(position).getSubjectTitle());
                startActivity(goToGradeLibrary);
            }
        });
    }

    public void initalizeData(){
        database = FirebaseDatabase.getInstance().getReference("Subjects");
        listOfSubjects = new ArrayList<Subject>();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Subject subject = dataSnapshot.getValue(Subject.class);
                    listOfSubjects.add(subject);
                }
                subjectAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}