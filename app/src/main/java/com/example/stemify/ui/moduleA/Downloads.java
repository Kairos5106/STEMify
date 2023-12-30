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
    List<DownloadItem> listOfItems;

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
        listOfItems = new ArrayList<DownloadItem>();

        // Populate list with download items
        listOfItems.add(new DownloadItem("Test1", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test2", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test3", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test4", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test5", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test6", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test7", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test8", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test9", R.drawable.sampleimage));
        listOfItems.add(new DownloadItem("Test10", R.drawable.sampleimage)); // not showing 10th
    }
}