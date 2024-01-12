package com.example.stemify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.ui.moduleD.Community;

public class CommunityEventAdapter extends RecyclerView.Adapter<CommunityEventAdapter.ViewHolder> {

    Context context;
    CommunityEventData[] communityEventData;

    public CommunityEventAdapter(CommunityEventData[] communityEventData, Context context){
        this.context = context;
        this.communityEventData = communityEventData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.communityevents_custom_view, parent, false); //only one events page.
        CommunityEventAdapter.ViewHolder viewHolder = new CommunityEventAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position){
        final CommunityEventData communityEventDataList = communityEventData[position];
        holder.textTitle.setText(communityEventDataList.getTitle());
        holder.textDate.setText(communityEventDataList.getDate());
        holder.textTime.setText(communityEventDataList.getTime());
        holder.textPlace.setText(communityEventDataList.getPlace());
        holder.imageView.setImageResource(communityEventDataList.getImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommunityEventDetails.class);
                //pass details to detailed page in community events detail

                intent.putExtra("Title", communityEventDataList.getTitle());
                intent.putExtra("Date", communityEventDataList.getDate());
                intent.putExtra("Time", communityEventDataList.getTime());
                intent.putExtra("Place", communityEventDataList.getPlace());
                intent.putExtra("Image", communityEventDataList.getImage());
                intent.putExtra("Detail", communityEventDataList.getDetails());
                intent.putExtra("Image",communityEventDataList.getImage());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return communityEventData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textTitle, textDate, textTime, textPlace;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.textTitle);
            textDate = itemView.findViewById(R.id.textDate);
            textTime = itemView.findViewById(R.id.textTime);
            textPlace = itemView.findViewById(R.id.textPlace);
            imageView = itemView.findViewById(R.id.imageViewEvent);
            cardView = itemView.findViewById(R.id.cardViewEvent);

        }
    }

}
