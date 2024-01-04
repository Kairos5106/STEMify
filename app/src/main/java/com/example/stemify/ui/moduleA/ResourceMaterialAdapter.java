package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.List;

public class ResourceMaterialAdapter extends RecyclerView.Adapter<ResourceMaterialAdapter.ResourceMaterialViewHolder> implements AdapterView.OnItemSelectedListener{
    Context context;
    List<Material> listOfMaterials;
    public ResourceMaterialAdapter(Context context, List<Material> listOfMaterials) {
        this.context = context;
        this.listOfMaterials = listOfMaterials;
    }

    @NonNull
    @Override
    public ResourceMaterialAdapter.ResourceMaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResourceMaterialViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resource_material_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceMaterialAdapter.ResourceMaterialViewHolder holder, int position) {
        Material material = listOfMaterials.get(position);

        // Change hint inside of EditText
        holder.materialTitle.setHint("Give a material title");

        // Setup edit button action
        if("Video Lesson".equalsIgnoreCase(material.getType())){
            holder.materialEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToMaterialEditing = new Intent(context, EditResourceVideo.class);
                    context.startActivity(goToMaterialEditing);
                }
            });
        }
        else if ("Practice".equalsIgnoreCase(material.getType())) {
            holder.materialEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToMaterialEditing = new Intent(context, TestActivity.class); // change to edit material page
                    context.startActivity(goToMaterialEditing);
                }
            });
        }
        else if ("Quiz".equalsIgnoreCase(material.getType())) {
            holder.materialEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToMaterialEditing = new Intent(context, TestActivity.class); // change to edit material page
                    context.startActivity(goToMaterialEditing);
                }
            });
        }

        // Setup delete button action
        holder.materialDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup delete button logic here
            }
        });

        // Setup spinner options
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(context, R.array.materialType, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.materialType.setAdapter(spinnerAdapter);
        holder.materialType.setOnItemSelectedListener(this);
    }

    @Override
    public int getItemCount() {
        return listOfMaterials.size();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String materialTypeString = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Type: " + materialTypeString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class ResourceMaterialViewHolder extends RecyclerView.ViewHolder {
        EditText materialTitle;
        ImageButton materialDelete;
        ImageButton materialEdit;
        Spinner materialType;

        public ResourceMaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialTitle = itemView.findViewById(R.id.ETSubtopicTitle);
            this.materialDelete = itemView.findViewById(R.id.BtnSubtopicDelete);
            this.materialEdit = itemView.findViewById(R.id.BtnSubtopicEdit);
            this.materialType = itemView.findViewById(R.id.SpinMaterialType);

        }
    }
}
