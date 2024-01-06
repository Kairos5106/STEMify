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

public class ResourceSectionAdapter extends RecyclerView.Adapter<ResourceSectionAdapter.ResourceSectionViewHolder> {
    Context context;
    List<Section> listOfSections;
    OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public ResourceSectionAdapter(Context context, List<Section> listOfSections) {
        this.context = context;
        this.listOfSections = listOfSections;
    }

    @NonNull
    @Override
    public ResourceSectionAdapter.ResourceSectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResourceSectionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resource_subtopic_details_alt, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceSectionAdapter.ResourceSectionViewHolder holder, int position) {
        Section subtopic = listOfSections.get(position);

        // Change hint inside of EditText
        holder.sectionTitle.setHint("Give a section title");
        // Setup button actions
        holder.sectionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSectionEditing = new Intent(context, EditResourceSection.class);
                context.startActivity(goToSectionEditing);
            }
        });

        holder.sectionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup delete button logic here
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfSections.size();
    }

    public class ResourceSectionViewHolder extends RecyclerView.ViewHolder {
        EditText sectionTitle;
        ImageButton sectionDelete;
        ImageButton sectionEdit;

        public ResourceSectionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.sectionTitle = itemView.findViewById(R.id.ETSubtopicTitle);
            this.sectionDelete = itemView.findViewById(R.id.BtnSubtopicDelete);
            this.sectionEdit = itemView.findViewById(R.id.BtnSubtopicEdit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
