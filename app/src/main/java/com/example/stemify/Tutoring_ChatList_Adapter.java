package com.example.stemify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Tutoring_ChatList_Adapter extends RecyclerView.Adapter<Tutoring_ChatList_Adapter.ChatListViewHolder>{

    private Context mContext;
    private List<User> mData;

    public Tutoring_ChatList_Adapter(Context mContext, List<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Tutoring_ChatList_Adapter.ChatListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.tutoring_tutorlist_row, parent, false);
        return new Tutoring_ChatList_Adapter.ChatListViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Tutoring_ChatList_Adapter.ChatListViewHolder holder, int position) {
        Picasso.get().load(mData.get(position).getPhotoUrl()).into(holder.IVTutorPfpRow);
        holder.TVTutorUsernameRow.setText(mData.get(position).getDisplayName());
        holder.TVTutorOrganisationRow.setText(mData.get(position).getOrganization());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder {

        ImageView IVTutorPfpRow;
        TextView TVTutorUsernameRow;
        TextView TVTutorOrganisationRow;
        ImageButton ImgBtnTutorProfile;
        ImageButton ImgBtnChat;

        public ChatListViewHolder(@NonNull View itemView) {
            super(itemView);

            IVTutorPfpRow = itemView.findViewById(R.id.IVTutorPfpRow);
            TVTutorUsernameRow = itemView.findViewById(R.id.TVTutorUsernameRow);
            TVTutorOrganisationRow = itemView.findViewById(R.id.TVTutorOrganisationRow);
            ImgBtnTutorProfile = itemView.findViewById(R.id.ImgBtnTutorProfile);
            ImgBtnChat = itemView.findViewById(R.id.ImgBtnChat);

            // Set click listener for ImgBtnTutorProfile
            ImgBtnTutorProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start Tutoring_TutorDetail activity
                    Intent tutorDetailActivity = new Intent(mContext, Tutoring_TutorDetail.class);
                    int position = getAdapterPosition();

                    tutorDetailActivity.putExtra("tutorName", mData.get(position).getFullname());
                    tutorDetailActivity.putExtra("organisation", mData.get(position).getOrganization());
                    tutorDetailActivity.putExtra("email", mData.get(position).getEmail());
                    tutorDetailActivity.putExtra("tutorPfp", mData.get(position).getPhotoUrl());
                    tutorDetailActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(tutorDetailActivity);
                }
            });

            // Set click listener for ImgBtnChat
            ImgBtnChat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Create an Intent to start Tutoring_Message activity
                    Intent chatActivity = new Intent(mContext, Tutoring_Message.class);
                    int position = getAdapterPosition();

                    chatActivity.putExtra("userId", mData.get(position).getId());
                    chatActivity.putExtra("tutorName", mData.get(position).getFullname());
                    chatActivity.putExtra("organisation", mData.get(position).getOrganization());
                    chatActivity.putExtra("email", mData.get(position).getEmail());
                    chatActivity.putExtra("tutorPfp", mData.get(position).getPhotoUrl());
                    chatActivity.putExtra("tutorUsername", mData.get(position).getDisplayName());
                    chatActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(chatActivity);
                }
            });

        }
    }
}
