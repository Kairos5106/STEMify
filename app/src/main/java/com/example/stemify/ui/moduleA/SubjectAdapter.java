package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.HomeworkHelp_PostDetail;
import com.example.stemify.R;

import java.util.List;
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    Context context;
    List<Subject> list;

    public SubjectAdapter(Context context, List<Subject> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectAdapter.SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
        Subject subject = list.get(position);
        holder.title.setText(subject.subjectTitle);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVSubjectTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Upon clicking a subject, user will be redirected to a page listing the topics of the subject
                    Intent goToTopicLibrary = new Intent(context, TopicLibrary.class);
                    int position = getAdapterPosition();

                    goToTopicLibrary.putExtra("title", list.get(position).getTitle());
                    goToTopicLibrary.putExtra("description", list.get(position).getDescription());
                    goToTopicLibrary.putExtra("postKey", list.get(position).getPostKey());
                    goToTopicLibrary.putExtra("userPfp", list.get(position).getUserPfp());
                    goToTopicLibrary.putExtra("username", list.get(position).getUsername());
                    long timestamp = (long) list.get(position).getTimeStamp();
                    goToTopicLibrary.putExtra("postDate", timestamp);
                    context.startActivity(goToTopicLibrary);

                }
            });
        }
    }
}
