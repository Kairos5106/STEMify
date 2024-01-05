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

public class EducatorResourcesAdapter extends RecyclerView.Adapter<EducatorResourcesAdapter.EducatorResourcesViewHolder> {
    Context context;
    List<CommunityResourceItem> list;

    public EducatorResourcesAdapter(Context context, List<CommunityResourceItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EducatorResourcesAdapter.EducatorResourcesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EducatorResourcesViewHolder(LayoutInflater.from(context).inflate(R.layout.item_community_resource, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull EducatorResourcesAdapter.EducatorResourcesViewHolder holder, int position) {
        CommunityResourceItem communityResourceItem = list.get(position);
        holder.title.setText(communityResourceItem.title);
        holder.description.setText(communityResourceItem.description);
        holder.author.setText(communityResourceItem.author);
        holder.authorImage.setImageResource(communityResourceItem.authorImageId);
        holder.resourceImage.setImageResource(communityResourceItem.resourceImageId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class EducatorResourcesViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, author;
        ImageView authorImage, resourceImage;

        public EducatorResourcesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVResourceTitle);
            this.description = itemView.findViewById(R.id.TVResourceDesc);
            this.author = itemView.findViewById(R.id.TVResourceAuthor);
            this.authorImage = itemView.findViewById(R.id.IVAuthorImage);
            this.resourceImage = itemView.findViewById(R.id.IVResourceImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToEditResources = new Intent(context, EditEducatorResources.class);
                    context.startActivity(goToEditResources);
                }
            });
        }
    }
}
