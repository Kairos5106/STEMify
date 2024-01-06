package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.HomeworkHelp_PostDetail;
import com.example.stemify.R;

import java.util.List;
public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.SubjectViewHolder> {
    Context context;
    List<Subject> listOfSubjects;
    OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public SubjectAdapter(Context context, List<Subject> listOfSubjects) {
        this.context = context;
        this.listOfSubjects = listOfSubjects;
    }

    @NonNull
    @Override
    public SubjectAdapter.SubjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SubjectAdapter.SubjectViewHolder(LayoutInflater.from(context).inflate(R.layout.item_subject, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.SubjectViewHolder holder, int position) {
        Subject subject = listOfSubjects.get(position);
        holder.title.setText(subject.subjectTitle);
    }

    @Override
    public int getItemCount() {
        return listOfSubjects.size();
    }

    public class SubjectViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        public SubjectViewHolder(@NonNull View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.TVSubjectTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
