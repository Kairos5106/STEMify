package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class FillBlankAdapter extends RecyclerView.Adapter<FillBlankAdapter.FillBlankViewHolder>{
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
    }

    @Override
    public int getItemCount() {
        return question.getNumberOfAnswers();
    }

    public class FillBlankViewHolder extends RecyclerView.ViewHolder{
        EditText answerBox;
        public FillBlankViewHolder(@NonNull View itemView) {
            super(itemView);
            this.answerBox = itemView.findViewById(R.id.ETAnswer);
            }
        }
}
