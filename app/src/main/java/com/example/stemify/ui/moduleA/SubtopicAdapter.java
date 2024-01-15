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

import org.w3c.dom.Text;

import java.util.List;

public class SubtopicAdapter extends RecyclerView.Adapter<SubtopicAdapter.SubtopicViewHolder> {
    Context context;
    List<Subtopic> listOfSubtopics;
    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public SubtopicAdapter(Context context, List<Subtopic> listOfSubtopics) {
        this.context = context;
        this.listOfSubtopics = listOfSubtopics;
    }

    @NonNull
    @Override
    public SubtopicAdapter.SubtopicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubtopicViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subtopic, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubtopicAdapter.SubtopicViewHolder holder, int position) {
        Subtopic subtopic = listOfSubtopics.get(position);
        holder.title.setText(subtopic.title);
        holder.sectionList.setText(subtopic.getSectionTitleList());
        holder.image.setImageResource(context.getResources().getIdentifier(subtopic.getSubtopicImageName(), "drawable", context.getPackageName()));
    }

    @Override
    public int getItemCount() {
        return listOfSubtopics.size();
    }

    public class SubtopicViewHolder extends RecyclerView.ViewHolder{
        TextView title, sectionList;
        ImageView image;

        public SubtopicViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVSubtopicTitle);
            this.sectionList = itemView.findViewById(R.id.TVSectionList);
            this.image = itemView.findViewById(R.id.IVSubtopicImage);

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
