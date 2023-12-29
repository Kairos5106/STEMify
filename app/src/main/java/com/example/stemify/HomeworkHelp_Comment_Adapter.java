package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HomeworkHelp_Comment_Adapter extends RecyclerView.Adapter<HomeworkHelp_Comment_Adapter.CommentViewHolder> {

    private Context mContext;
    private List<HomeworkHelp_Comment> mData;

    public HomeworkHelp_Comment_Adapter(Context mContext, List<HomeworkHelp_Comment> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.homeworkhelp_comment_row, parent, false);
        return new CommentViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
