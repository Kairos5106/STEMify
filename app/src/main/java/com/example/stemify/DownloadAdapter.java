package com.example.stemify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.ui.moduleA.CommunityResourceItem;
import com.example.stemify.ui.moduleA.SubtopicLibrary;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder> {
    Context context;
    List<CommunityResourceItem> list;

    public DownloadAdapter(Context context, List<CommunityResourceItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DownloadAdapter.DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DownloadAdapter.DownloadViewHolder(LayoutInflater.from(context).inflate(R.layout.item_community_resource, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadAdapter.DownloadViewHolder holder, int position) {
        CommunityResourceItem communityResourceItem = list.get(position);
        holder.title.setText(communityResourceItem.getTitle());
        holder.description.setText(communityResourceItem.getDescription());
        holder.author.setText(communityResourceItem.getAuthor());
        holder.authorImage.setImageResource(communityResourceItem.getAuthorImageId());
        holder.resourceImage.setImageResource(communityResourceItem.getResourceImageId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DownloadViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, author;
        ImageView authorImage, resourceImage;

        public DownloadViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVResourceTitle);
            this.description = itemView.findViewById(R.id.TVResourceDesc);
            this.author = itemView.findViewById(R.id.TVResourceAuthor);
            this.authorImage = itemView.findViewById(R.id.IVAuthorImage);
            this.resourceImage = itemView.findViewById(R.id.IVResourceImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goToSubtopicsPage = new Intent(context, SubtopicLibrary.class);
                    context.startActivity(goToSubtopicsPage);
                }
            });
        }
    }
}
