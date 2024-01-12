package com.example.stemify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.stemify.HomeworkHelp_Post;
import com.squareup.picasso.Picasso;

public class HomeworkHelp_Post_Adapter extends RecyclerView.Adapter<HomeworkHelp_Post_Adapter.MyViewHolder> {

    Context mContext;
    List<HomeworkHelp_Post> mData;

    public HomeworkHelp_Post_Adapter(Context mContext, List<HomeworkHelp_Post> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View row = LayoutInflater.from(mContext).inflate(R.layout.homeworkhelp_postlist_row, parent, false);
        return new MyViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.TVQuestionPostList.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getUserPfp()).into(holder.IVPfpPosterPostListRow);
        holder.TVUsernamePosterPostListRow.setText(mData.get(position).getUsername());

        // Calculate the duration since the post was first posted
        long timestamp = (long) mData.get(position).getTimeStamp();
        String duration = calculateDuration(timestamp);
        holder.TVPostTimePostListRow.setText(duration);
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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView TVQuestionPostList;
        TextView TVPostTimePostListRow;
        TextView TVUsernamePosterPostListRow;
        ImageView IVPfpPosterPostListRow;


        public MyViewHolder(View itemView) {
            super(itemView);

            TVQuestionPostList = itemView.findViewById(R.id.TVQuestionPostList);
            TVPostTimePostListRow = itemView.findViewById(R.id.TVPostTimePostListRow);
            TVUsernamePosterPostListRow = itemView.findViewById(R.id.TVUsernamePosterPostListRow);
            IVPfpPosterPostListRow = itemView.findViewById(R.id.IVPfpPosterPostListRow);

            // When user click on one of the post, will show up new activity (post detail)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start HomeworkHelp_PostDetail activity
                    Intent postDetailActivity = new Intent(mContext, HomeworkHelp_PostDetail.class);
                    int position = getAdapterPosition();

                    postDetailActivity.putExtra("title", mData.get(position).getTitle());
                    postDetailActivity.putExtra("description", mData.get(position).getDescription());
                    postDetailActivity.putExtra("postKey", mData.get(position).getPostKey());
                    postDetailActivity.putExtra("userPfp", mData.get(position).getUserPfp());
                    postDetailActivity.putExtra("username", mData.get(position).getUsername());
                    long timestamp = (long) mData.get(position).getTimeStamp();
                    postDetailActivity.putExtra("postDate", timestamp);
                    mContext.startActivity(postDetailActivity);

                }
            });
        }

    }


}
