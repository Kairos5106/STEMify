package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.ui.moduleB.Leaderboard;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.example.stemify.User;

import java.util.List;

public class Leaderboard_Ranking_Adapter extends RecyclerView.Adapter<Leaderboard_Ranking_Adapter.RankingViewHolder> {

    private Context mContext;
    private List<Leaderboard_ScoreData> mData;

    public Leaderboard_Ranking_Adapter(Context mContext, List<Leaderboard_ScoreData> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public Leaderboard_Ranking_Adapter.RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(mContext).inflate(R.layout.leaderboard_rank_row, parent, false);
        return new RankingViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull Leaderboard_Ranking_Adapter.RankingViewHolder holder, int position) {

        Leaderboard_ScoreData currentItem = mData.get(position);

        holder.TVRankNumber.setText(String.valueOf(position + 1));
        holder.TVUsernameRankRow.setText(mData.get(position).getUsername());
        Picasso.get().load(mData.get(position).getUserPfp()).into(holder.IVUserPfpRankRow);
        holder.TVDigitXPRankRow.setText(String.valueOf(currentItem.getScore()));

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class RankingViewHolder extends RecyclerView.ViewHolder {

        TextView TVRankNumber;
        TextView TVUsernameRankRow;
        TextView TVDigitXPRankRow;
        ImageView IVUserPfpRankRow;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);

            TVRankNumber = itemView.findViewById(R.id.TVRankNumber);
            TVUsernameRankRow = itemView.findViewById(R.id.TVUsernameRankRow);
            TVDigitXPRankRow = itemView.findViewById(R.id.TVDigitXPRankRow);
            IVUserPfpRankRow = itemView.findViewById(R.id.IVUserPfpRankRow);

        }
    }


}
