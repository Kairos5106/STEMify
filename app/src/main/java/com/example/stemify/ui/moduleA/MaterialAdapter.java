package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {
    Context context;
    List<Material> listOfMaterial;

    public MaterialAdapter(Context context, List<Material> listOfMaterial) {
        this.context = context;
        this.listOfMaterial = listOfMaterial;
    }

    @NonNull
    @Override
    public MaterialAdapter.MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MaterialViewHolder(LayoutInflater.from(context).inflate(R.layout.item_material, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialAdapter.MaterialViewHolder holder, int position) {
        Material material = listOfMaterial.get(position);

        // Bind View objects
        holder.title.setText(material.getTitle());
        holder.masteryPoints.setText(material.getMasteryPoints());
        holder.icon.setImageResource(R.drawable.sampleimage); // change to material.getIconId() later
        holder.setNavigationToNext(material.getType());
    }

    @Override
    public int getItemCount() {
        return listOfMaterial.size();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        Intent goToMaterialPage;
        TextView title, masteryPoints;
        ImageView icon;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVMaterialTitle);
            this.masteryPoints = itemView.findViewById(R.id.TVMaterialMasteryPoints);
            this.icon = itemView.findViewById(R.id.IVMaterialIcon);
        }

        // Sets an onClickListener for the viewholder depending on the type of material passed
        public void setNavigationToNext(String materialType){
            if(materialType.equalsIgnoreCase("VideoLesson")){
                goToMaterialPage = new Intent(context, VideoLessonPage.class);
            } else if (materialType.equalsIgnoreCase("Practice")) {
                goToMaterialPage = new Intent(context, VideoLessonPage.class); // change later
            } else if (materialType.equalsIgnoreCase("Quiz")) {
                goToMaterialPage = new Intent(context, VideoLessonPage.class); // change later
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(goToMaterialPage);
                }
            });
        }
    }
}
