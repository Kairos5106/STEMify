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
//        recyclerView.setAdapter(new DownloadAdapter(getContext(), listOfItems));
    }

    public void initalizeData(){
        // Populate list with download items
        listOfItems.add(new DownloadItem("Test1"));
    }
}