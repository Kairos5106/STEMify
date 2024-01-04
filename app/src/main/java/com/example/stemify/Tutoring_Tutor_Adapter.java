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

public class Tutoring_Tutor_Adapter extends RecyclerView.Adapter<Tutoring_Tutor_Adapter.TutorListViewHolder> {

    private Context mContext;
    private List<User> mData;

    public Tutoring_Tutor_Adapter(Context mContext, List<User> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Tutoring_Tutor_Adapter.TutorListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.tutoring_tutorlist_row, parent, false);
        return new TutorListViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Tutoring_Tutor_Adapter.TutorListViewHolder holder, int position) {
        Picasso.get().load(mData.get(position).getPhotoUrl()).into(holder.IVTutorPfpRow);
        holder.TVTutorUsernameRow.setText(mData.get(position).getDisplayName());
        holder.TVTutorOrganisationRow.setText(mData.get(position).getOrganization());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TutorListViewHolder extends RecyclerView.ViewHolder {

        ImageView IVTutorPfpRow;
        TextView TVTutorUsernameRow;
        TextView TVTutorOrganisationRow;
        ImageButton ImgBtnTutorProfile;
        ImageButton ImgBtnChat;

        public TutorListViewHolder(@NonNull View itemView) {
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

                    chatActivity.putExtra("tutorName", mData.get(position).getFullname());
                    chatActivity.putExtra("organisation", mData.get(position).getOrganization());
                    chatActivity.putExtra("email", mData.get(position).getEmail());
                    chatActivity.putExtra("tutorPfp", mData.get(position).getPhotoUrl());
                    chatActivity.putExtra("tutorUsername", mData.get(position).getDisplayName());

                    /*// Get the user ID from Firebase Authentication
                    String tutorUID = mData.get(position).getUid();
                    chatActivity.putExtra("tutorUID", tutorUID);*/

                    mContext.startActivity(chatActivity);
                }
            });

        }
    }
}
