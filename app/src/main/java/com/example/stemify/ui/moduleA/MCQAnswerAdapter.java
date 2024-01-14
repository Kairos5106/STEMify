package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class MCQAnswerAdapter extends RecyclerView.Adapter<MCQAnswerAdapter.MCQAnswerViewHolder> {
    Context context;
    List<String> listOfAnswers;
    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public MCQAnswerAdapter(Context context, List<String> listOfAnswers) {
        this.context = context;
        this.listOfAnswers = listOfAnswers;
    }

    @NonNull
    @Override
    public MCQAnswerAdapter.MCQAnswerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MCQAnswerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_choice_box, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MCQAnswerAdapter.MCQAnswerViewHolder holder, int position) {
        String answer = listOfAnswers.get(position);
        holder.answer.setText(answer);
    }

    @Override
    public int getItemCount() {
        return listOfAnswers.size();
    }

    public class MCQAnswerViewHolder extends RecyclerView.ViewHolder{
        TextView answer;
        FrameLayout answerBox;
        public MCQAnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            this.answer = itemView.findViewById(R.id.TVAnswer);
            this.answerBox = itemView.findViewById(R.id.FLChoiceBox);

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

        public void selectWrong(){
            this.answerBox.setBackgroundColor(Color.RED);
        }

        public void selectCorrect(){
            this.answerBox.setBackgroundColor(Color.RED);
        }

        public void resetColor(){
            int color = Color.parseColor("#E3D0D0");
            this.answerBox.setBackgroundColor(color);
        }
    }
}
