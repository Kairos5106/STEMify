package com.example.stemify.ui.moduleA;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SubtopicAdapter extends RecyclerView.Adapter<SubtopicAdapter.SubtopicViewHolder> {
    @NonNull
    @Override
    public SubtopicAdapter.SubtopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SubtopicAdapter.SubtopicViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class SubtopicViewHolder extends RecyclerView.ViewHolder{
        TextView title;

        public SubtopicViewHolder(@NonNull View itemView) {
            super(itemView);
            // Add title here
        }
    }
}
