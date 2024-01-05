package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class CommunityResourceAdapter extends RecyclerView.Adapter<CommunityResourceAdapter.CommunityResourceViewHolder> {
    Context context;
    List<CommunityResourceItem> list;

    public CommunityResourceAdapter(Context context, List<CommunityResourceItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CommunityResourceAdapter.CommunityResourceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CommunityResourceViewHolder(LayoutInflater.from(context).inflate(R.layout.item_community_resource, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityResourceAdapter.CommunityResourceViewHolder holder, int position) {
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

    public class CommunityResourceViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, author;
        ImageView authorImage, resourceImage;

        public CommunityResourceViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVResourceTitle);
            this.description = itemView.findViewById(R.id.TVResourceDesc);
            this.author = itemView.findViewById(R.id.TVResourceAuthor);
            this.authorImage = itemView.findViewById(R.id.IVAuthorImage);
            this.resourceImage = itemView.findViewById(R.id.IVResourceImage);
        }
    }
}
