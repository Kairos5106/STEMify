package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class GradeAdapter extends RecyclerView.Adapter<GradeAdapter.GradeViewHolder>{
    Context context;
    List<Grade> listOfGrades;

    public GradeAdapter(Context context, List<Grade> listOfGrades) {
        this.context = context;
        this.listOfGrades = listOfGrades;
    }

    @NonNull
    @Override
    public GradeAdapter.GradeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GradeAdapter.GradeViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter.GradeViewHolder holder, int position) {
        Grade grade = listOfGrades.get(position);
        holder.title.setText(grade.gradeTitle);
    }

    @Override
    public int getItemCount() {
        return listOfGrades.size();
    }

    public class GradeViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public GradeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVSubjectTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Upon clicking a grade, user will be redirected to a page listing the topics of the grade
                    Intent goToTopicLibrary = new Intent(context, TopicLibrary.class);
                    int position = getAdapterPosition();

                    context.startActivity(goToTopicLibrary);
                }
            });
        }
    }
}
