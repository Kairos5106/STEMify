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

public class Downloads extends Fragment {
    DownloadAdapter downloadAdapter;
    RecyclerView recyclerView;
    List<CommunityResourceItem> listOfItems;

    public Downloads() {
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
        return inflater.inflate(R.layout.fragment_downloads, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Setup RecyclerView
        recyclerView = view.findViewById(R.id.RVDownloads);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        downloadAdapter = new DownloadAdapter(getContext(), listOfItems);
        recyclerView.setAdapter(downloadAdapter);
        downloadAdapter.notifyDataSetChanged();
    }

    public void initalizeData(){
        // Initializing list of download items
        listOfItems = new ArrayList<CommunityResourceItem>();

        // Populate list with download items
        listOfItems.add(new CommunityResourceItem("Test1", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test2", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test3", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test4", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test5", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test6", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test7", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test8", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test9", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
        listOfItems.add(new CommunityResourceItem("Test10", "Sample description", "Kairos5106", R.drawable.pfp, R.drawable.sampleimage));
    }
}