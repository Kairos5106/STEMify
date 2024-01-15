package com.example.stemify.ui.moduleA;

import static androidx.browser.customtabs.CustomTabsClient.getPackageName;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    public void setListOfQuestions(List<Question> listOfQuestions) {
        this.listOfQuestions = listOfQuestions;
    }

    @NonNull
    @Override
    public QuestionAdapter.QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(context).inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.QuestionViewHolder holder, int position) { // not done
        Question question = listOfQuestions.get(position);
        holder.questionNumber.setText("Question " + (position + 1));
        holder.questionDesc.setText(question.getQuestionDesc());

        // If the question does not have a diagram defined, remove the imageview and textview associated with the diagram to not take up UI space
        if(question.getDiagramName().equalsIgnoreCase("")){
            holder.questionDiagram.setVisibility(View.GONE);
            holder.diagramDesc.setVisibility(View.GONE);
        }
        else{
            holder.questionDiagram.setImageResource(context.getResources().getIdentifier(question.getDiagramName(), "drawable", context.getPackageName()));
            holder.diagramDesc.setText(question.getDiagramDesc());
        }

        if(question instanceof MultipleChoice){ // Implementation for MCQ features: Answer selection and validation
            MCQAnswerAdapter mcqAnswerAdapter = new MCQAnswerAdapter(context, ((MultipleChoice) question).getListOfAnswers());
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            holder.recyclerView.setAdapter(mcqAnswerAdapter);
            mcqAnswerAdapter.notifyDataSetChanged();
            mcqAnswerAdapter.setOnItemClickListener(new MCQAnswerAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    // Reset all answer boxes from being selected
                    for (int i = 0; i < ((MultipleChoice) question).getListOfAnswers().size(); i++) {
                        MCQAnswerAdapter.MCQAnswerViewHolder viewHolder = (MCQAnswerAdapter.MCQAnswerViewHolder) holder.recyclerView.findViewHolderForAdapterPosition(i);
                        viewHolder.resetColor();
                    }
                    MCQAnswerAdapter.MCQAnswerViewHolder viewHolder = (MCQAnswerAdapter.MCQAnswerViewHolder) holder.recyclerView.findViewHolderForAdapterPosition(position);
                    if(viewHolder != null){
                        String questionCorrectAns = question.getCorrectAnswer();
                        String selectedAns = ((MultipleChoice) question).getListOfAnswers().get(position);

                        // Change color of answer box to green when the answer selected is wrong
                        if(selectedAns.equals(questionCorrectAns)){
                            viewHolder.selectCorrect();
                        }
                        else{ // Change color of answer box to red when the answer selected is wrong
                            viewHolder.selectWrong();
                        }
                    }
                }
            });
        }
        else if (question instanceof FillBlank) { // Specific implementation for FillBlank features: Answer validation
            FillBlankAdapter fillBlankAdapter = new FillBlankAdapter(context, (FillBlank) question);
            holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            holder.recyclerView.setAdapter(fillBlankAdapter);
            fillBlankAdapter.notifyDataSetChanged();
        }

        this.position++;
    }
    @Override
    public int getItemCount() {
        return listOfQuestions.size();
    }

    public class QuestionViewHolder extends RecyclerView.ViewHolder{
        TextView questionNumber, diagramDesc, questionDesc;
        ImageView questionDiagram;
        RecyclerView recyclerView;
        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            this.questionNumber = itemView.findViewById(R.id.TVQuestionNumber);
            this.diagramDesc = itemView.findViewById(R.id.TVQuestionDiagramDesc);
            this.questionDesc = itemView.findViewById(R.id.TVQuestionDescription);
            this.questionDiagram = itemView.findViewById(R.id.IVQuestionDiagram);
            this.recyclerView = itemView.findViewById(R.id.RVAnswer);
        }
    }
}
