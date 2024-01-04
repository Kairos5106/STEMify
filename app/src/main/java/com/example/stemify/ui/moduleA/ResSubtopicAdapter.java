package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class ResSubtopicAdapter extends RecyclerView.Adapter<ResSubtopicAdapter.SubtopicItemViewHolder> {
    Context context;
    List<ResSubtopicItem> listOfSubtopicItems;

    public ResSubtopicAdapter(Context context, List<ResSubtopicItem> listOfSubtopicItems) {
        this.context = context;
        this.listOfSubtopicItems = listOfSubtopicItems;
    }

    @NonNull
    @Override
    public ResSubtopicAdapter.SubtopicItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubtopicItemViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resource_subtopic_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResSubtopicAdapter.SubtopicItemViewHolder holder, int position) {
        ResSubtopicItem subtopic = listOfSubtopicItems.get(position);

        // May add extra logic here
    }

    @Override
    public int getItemCount() {
        return listOfSubtopicItems.size();
    }

    public class SubtopicItemViewHolder extends RecyclerView.ViewHolder {
        EditText subtopicTitle;
        ImageButton subtopicDelete;

        public SubtopicItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.subtopicTitle = itemView.findViewById(R.id.ETSubtopicTitle);
            this.subtopicDelete = itemView.findViewById(R.id.BtnSubtopicDelete);
        }
    }
}
