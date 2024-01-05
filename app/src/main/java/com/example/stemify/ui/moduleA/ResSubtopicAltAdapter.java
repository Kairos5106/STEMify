package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.List;

public class ResSubtopicAltAdapter extends RecyclerView.Adapter<ResSubtopicAltAdapter.SubtopicItemAltViewHolder> {
    Context context;
    List<ResSubtopicItem> listOfSubtopicItemsAlt;

    public ResSubtopicAltAdapter(Context context, List<ResSubtopicItem> listOfSubtopicItemsAlt) {
        this.context = context;
        this.listOfSubtopicItemsAlt = listOfSubtopicItemsAlt;
    }

    @NonNull
    @Override
    public ResSubtopicAltAdapter.SubtopicItemAltViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubtopicItemAltViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resource_subtopic_details_alt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResSubtopicAltAdapter.SubtopicItemAltViewHolder holder, int position) {
        ResSubtopicItem subtopic = listOfSubtopicItemsAlt.get(position);

        // Setup button actions
        holder.subtopicEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSubtopicEditing = new Intent(context, EditResourceSubtopic.class);
                context.startActivity(goToSubtopicEditing);
            }
        });

        holder.subtopicDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup delete button logic here
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfSubtopicItemsAlt.size();
    }

    public class SubtopicItemAltViewHolder extends RecyclerView.ViewHolder {
        EditText subtopicTitle;
        ImageButton subtopicDelete;
        ImageButton subtopicEdit;

        public SubtopicItemAltViewHolder(@NonNull View itemView) {
            super(itemView);
            this.subtopicTitle = itemView.findViewById(R.id.ETSubtopicTitle);
            this.subtopicDelete = itemView.findViewById(R.id.BtnSubtopicDelete);
            this.subtopicEdit = itemView.findViewById(R.id.BtnSubtopicEdit);
        }
    }
}
