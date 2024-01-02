package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.ArrayList;
import java.util.List;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    Context context;
    List<Section> listOfSection;

    List<Material> listOfMaterial;

    public SectionAdapter(Context context, List<Section> listOfSection) {
        this.context = context;
        this.listOfSection = listOfSection;
        this.listOfMaterial = new ArrayList<>();
    }

    @NonNull
    @Override
    public SectionAdapter.SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SectionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_section, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SectionAdapter.SectionViewHolder holder, int position) {
        Section section = listOfSection.get(position);

        // Bind View objects
        holder.title.setText(section.getTitle());
        holder.masteryPoints.setText(section.getMasteryPoints());


    }

    @Override
    public int getItemCount() {
        return listOfSection.size();
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder{
        TextView title, masteryPoints;
        RecyclerView materialRV;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVSectionTitle);
            this.masteryPoints = itemView.findViewById(R.id.TVSectionMasteryPoints);
            this.materialRV = itemView.findViewById(R.id.RVMaterialList);
        }
    }
}
