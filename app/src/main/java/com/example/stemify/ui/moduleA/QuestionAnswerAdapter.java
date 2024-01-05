package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.List;

public class QuestionAnswerAdapter extends RecyclerView.Adapter<QuestionAnswerAdapter.QuestionAnswerViewHolder> {
    Context context;
    List<String> listOfAnswers;

    public QuestionAnswerAdapter(Context context, List<String> listOfAnswers) {
        this.context = context;
        this.listOfAnswers = listOfAnswers;
    }

    @NonNull
    @Override
    public QuestionAnswerAdapter.QuestionAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionAnswerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_resource_subtopic_details, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAnswerAdapter.QuestionAnswerViewHolder holder, int position) {
        String subtopic = listOfAnswers.get(position);

        holder.answer.setHint("Answer " + (position+1));
        // Setup button actions
        holder.answerDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup delete button logic here
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfAnswers.size();
    }

    public class QuestionAnswerViewHolder extends RecyclerView.ViewHolder {
        EditText answer;
        ImageButton answerDelete;

        public QuestionAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.answer = itemView.findViewById(R.id.ETSubtopicTitle);
            this.answerDelete = itemView.findViewById(R.id.BtnSubtopicDelete);
        }
    }
}
