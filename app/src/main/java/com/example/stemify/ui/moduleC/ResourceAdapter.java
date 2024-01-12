package com.example.stemify.ui.moduleC;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class ResourceAdapter extends RecyclerView.Adapter<ResourceAdapter.MyViewHolder> {

    Context mContext;
    List<ResourceData> mResourceDataList;

    public ResourceAdapter(Context mContext, List<ResourceData> mCareerScholarshipDataList){
        this.mContext = mContext;
        this.mResourceDataList = mCareerScholarshipDataList;
    }

    @NonNull
    @Override
    public ResourceAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.item_career_resource,parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ResourceAdapter.MyViewHolder holder, int position) {

        holder.cardName.setText(mResourceDataList.get(position).getCardName());
        holder.cardDesc.setText(mResourceDataList.get(position).getCardDesc());
        holder.cardImg.setImageResource(mResourceDataList.get(position).getCardImg());

    }

    @Override
    public int getItemCount() {
        return mResourceDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView cardName;
        private TextView cardDesc;
        private ImageView cardImg;
        private CardView mainCard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            cardName = itemView.findViewById(R.id.TVCardTitle);
            cardDesc = itemView.findViewById(R.id.TVCardDesc);
            cardImg = itemView.findViewById(R.id.IVCard);
            mainCard = itemView.findViewById(R.id.mainCard);

        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            ResourceData item = mResourceDataList.get(position);

            Intent detailsScreenData = new Intent(mContext, ResourceDetails.class);

            detailsScreenData.putExtra("Name", item.getCardName());
            detailsScreenData.putExtra("ImageData", item.getCardImg());
            detailsScreenData.putExtra("Details", item.getCardDetails());

            //detailsScreenData.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            mContext.startActivity(detailsScreenData);

        }
    }
}
