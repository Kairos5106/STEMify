package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;
import com.example.stemify.TestActivity;

import java.util.List;

public class ResourceQuestionAdapter extends RecyclerView.Adapter<ResourceQuestionAdapter.ResourceQuestionViewHolder> {
    Context context;
    List<Question> listOfQuestions;

    public ResourceQuestionAdapter(Context context, List<Question> listOfQuestions) {
        this.context = context;
        this.listOfQuestions = listOfQuestions;
    }

    @NonNull
    @Override
    public ResourceQuestionAdapter.ResourceQuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ResourceQuestionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question_detail, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ResourceQuestionAdapter.ResourceQuestionViewHolder holder, int position) {
        Question question = listOfQuestions.get(position);

        // Change question number
        holder.questionNumber.setText("Question " + (position+1));
        // Setup button actions
        holder.questionEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToQuestionEditing = new Intent(context, EditResourceQuestion.class);
                context.startActivity(goToQuestionEditing);
            }
        });

        holder.questionDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Setup delete button logic here
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfQuestions.size();
    }

    public class ResourceQuestionViewHolder extends RecyclerView.ViewHolder {
        TextView questionNumber;
        ImageButton questionDelete;
        ImageButton questionEdit;

        public ResourceQuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.questionNumber = itemView.findViewById(R.id.ETQuestionNumber);
            this.questionDelete = itemView.findViewById(R.id.BtnQuestionDelete);
            this.questionEdit = itemView.findViewById(R.id.BtnQuestionEdit);
        }
    }
}
