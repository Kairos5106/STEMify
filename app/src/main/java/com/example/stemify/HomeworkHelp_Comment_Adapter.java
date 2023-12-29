package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

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
        holder.TVComment.setText(mData.get(position).getContent());
        Picasso.get().load(mData.get(position).getCommenterPfp()).into(holder.IVCommenterPfp);
        holder.TVUsernameCommenter.setText(mData.get(position).getCommenterUsername());

        // Calculate the duration since the post was first posted
        long timestamp = (long) mData.get(position).getCommenterTimestamp();
        String duration = calculateDuration(timestamp);
        holder.TVTimeCommenter.setText(duration);
    }

    // Helper method to calculate duration
    private String calculateDuration(long timestamp) {
        long currentMillis = System.currentTimeMillis();
        long diffMillis = currentMillis - timestamp;

        long seconds = diffMillis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return days + "d ago";
        } else if (hours > 0) {
            return hours + "h ago";
        } else if (minutes > 0) {
            return minutes + "m ago";
        } else {
            return seconds + "s ago";
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView TVComment;
        TextView TVTimeCommenter;
        TextView TVUsernameCommenter;
        ImageView IVCommenterPfp;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            TVComment = itemView.findViewById(R.id.TVComment);
            TVTimeCommenter = itemView.findViewById(R.id.TVTimeCommenter);
            TVUsernameCommenter = itemView.findViewById(R.id.TVUsernameCommenter);
            IVCommenterPfp = itemView.findViewById(R.id.IVCommenterPfp);

        }
    }

}
