package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {
    Context context;
    List<Question> listOfQuestions;
    static int position = 0;

    public QuestionAdapter(Context context, List<Question> listOfQuestions) {
        this.context = context;
        this.listOfQuestions = listOfQuestions;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(listOfQuestions.get(this.position) instanceof MultipleChoice){
            return new QuestionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question, parent, false)); // change to inflate different layout item later
        }
//        else if () {
//        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionViewHolder holder, int position) { // not done
        Question question = listOfQuestions.get(position);
        holder.questionNumber.setText("Question " + (position + 1));
        holder.diagramDesc.setText(question.getDiagramDesc());
        holder.questionDesc.setText(question.getQuestionDesc());
        holder.questionDiagram.setImageResource(question.getDiagramId());

        if(question instanceof MultipleChoice){ // Implementation for MCQ features
            MCQAnswerAdapter mcqAnswerAdapter = new MCQAnswerAdapter(context, ((MultipleChoice) question).getListOfAnswers());
            holder.multipleAnswerRV.setLayoutManager(new LinearLayoutManager(context));
            holder.multipleAnswerRV.setAdapter(mcqAnswerAdapter);
            mcqAnswerAdapter.notifyDataSetChanged();
        }
//        else if () { // Specific implementation for FillBlank features
//            // Add code for FillBlank question
//        }

        this.position++;
    }

    @Override
    public int getItemCount() {
        return listOfQuestions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber, diagramDesc, questionDesc;
        ImageView questionDiagram;
        RecyclerView multipleAnswerRV;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.questionNumber = itemView.findViewById(R.id.TVQuestionNumber);
            this.diagramDesc = itemView.findViewById(R.id.TVQuestionDiagramDesc);
            this.questionDesc = itemView.findViewById(R.id.TVQuestionDescription);
            this.questionDiagram = itemView.findViewById(R.id.IVQuestionDiagram);
            this.multipleAnswerRV = itemView.findViewById(R.id.RVMultipleAnswer);
        }
    }
}
