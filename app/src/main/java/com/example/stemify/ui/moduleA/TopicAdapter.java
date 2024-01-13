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

import com.example.stemify.EmptyTestActivity;
import com.example.stemify.R;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder>{
    Context context;
    List<ResourceTopic> list;
    OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
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
        holder.topicImage.setImageResource(context.getResources().getIdentifier(resourceTopic.getTopicImageName(), "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView topicImage;
        public TopicViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVDownloadTitle);
            this.topicImage = itemView.findViewById(R.id.IVItemImage);
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
