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

import com.example.stemify.R;

import java.util.ArrayList;
import java.util.List;

public class TopicLibraryTemp extends Fragment {
    TopicAdapter topicAdapter;
    RecyclerView recyclerView;
    List<ResourceTopic> listOfItems;
    public TopicLibraryTemp() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initalizeData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_topic_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.RVTopicLibrary);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topicAdapter = new TopicAdapter(getContext(), listOfItems);
        recyclerView.setAdapter(topicAdapter);
        topicAdapter.notifyDataSetChanged();
    }

    public void initalizeData(){
        // Initializing list of topic items
        listOfItems = new ArrayList<ResourceTopic>();

        // Populate list with download items
        listOfItems.add(new ResourceTopic("Test1"));
        listOfItems.add(new ResourceTopic("Test2"));
        listOfItems.add(new ResourceTopic("Test3"));
        listOfItems.add(new ResourceTopic("Test4"));
        listOfItems.add(new ResourceTopic("Test5"));
        listOfItems.add(new ResourceTopic("Test6"));
        listOfItems.add(new ResourceTopic("Test7"));
        listOfItems.add(new ResourceTopic("Test8"));
        listOfItems.add(new ResourceTopic("Test9"));
        listOfItems.add(new ResourceTopic("Test10"));
    }
}