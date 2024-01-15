package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class FillBlankAdapter extends RecyclerView.Adapter<FillBlankAdapter.FillBlankViewHolder> {
    Context context;
    FillBlank question;

    public FillBlankAdapter(Context context, FillBlank question) {
        this.context = context;
        this.question = question;
    }

    @NonNull
    @Override
    public FillBlankAdapter.FillBlankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FillBlankViewHolder(LayoutInflater.from(context).inflate(R.layout.item_fill_blank_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FillBlankAdapter.FillBlankViewHolder holder, int position) {
        holder.answerBox.setText("Answer for slot " + (position + 1));

        // Set each answer box (EditText) to have a TextWatcher to detect any user input and validate their answers
        holder.answerBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // Logic to validate user's answer to question's correct answer
                String correctAnswer = ((FillBlank) question).getCorrectAnswers().get(position);
                if(validateAnswer(holder.answerBox.getText().toString().trim(), correctAnswer)){
                    holder.selectCorrect();
                }
                else{
                    holder.selectWrong();
                }
            }
        });
    }

    private boolean validateAnswer(String userAnswer, String correctAnswer) {
        if (!userAnswer.isEmpty()) {
            if (userAnswer.equalsIgnoreCase(correctAnswer)) {
                // User's answer is correct
                showToast("Correct Answer!");
                return true;
            } else {
                // User's answer is incorrect
                return false;
            }
        } else {
            // Empty input
            showToast("Please enter an answer");
            return false;
        }
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return question.getNumberOfAnswers();
    }

    public class FillBlankViewHolder extends RecyclerView.ViewHolder {
        EditText answerBox;
        FrameLayout FLChoiceBox;

        public FillBlankViewHolder(@NonNull View itemView) {
            super(itemView);
            this.answerBox = itemView.findViewById(R.id.ETAnswer);
            this.FLChoiceBox = itemView.findViewById(R.id.FLChoiceBox);
        }

        public void selectWrong() {
            this.FLChoiceBox.setBackgroundColor(Color.RED);
        }

        public void selectCorrect() {
            this.FLChoiceBox.setBackgroundColor(Color.GREEN);
        }

        public void resetColor() {
            int color = Color.parseColor("#E3D0D0");
            this.FLChoiceBox.setBackgroundColor(color);
        }
    }
}