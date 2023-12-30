package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder>{
    Context context;
    List<ResourceTopic> list;

    public TopicAdapter(Context context, List<ResourceTopic> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public TopicAdapter.TopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TopicViewHolder(LayoutInflater.from(context).inflate(R.layout.row_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TopicAdapter.TopicViewHolder holder, int position) {
        ResourceTopic resourceTopic = list.get(position);
        holder.title.setText(resourceTopic.title);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVDownloadTitle);
        }
    }
}
