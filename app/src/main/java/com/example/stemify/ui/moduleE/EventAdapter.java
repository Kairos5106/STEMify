package com.example.stemify.ui.moduleE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.stemify.R;

import java.util.ArrayList;
import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<EventDataClass> eventDataList;

    public EventAdapter(Context context, List<EventDataClass> eventDataList) {
        this.context = context;
        this.eventDataList = eventDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_recycler_item, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Glide.with(context).load(eventDataList.get(position).getEventImage()).into(holder.eventRecImage);
        holder.eventRecName.setText(eventDataList.get(position).getEventName());
        holder.eventRecDate.setText(eventDataList.get(position).getEventDate());
        holder.eventRecLocation.setText(eventDataList.get(position).getEventLocation());

        holder.eventRecCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailActivity.class);
                intent.putExtra("Image", eventDataList.get(holder.getAdapterPosition()).getEventImage());
                intent.putExtra("Date", eventDataList.get(holder.getAdapterPosition()).getEventDate());
                intent.putExtra("Name", eventDataList.get(holder.getAdapterPosition()).getEventName());
                intent.putExtra("Location",eventDataList.get(holder.getAdapterPosition()).getEventLocation());
                intent.putExtra("Time", eventDataList.get(holder.getAdapterPosition()).getEventTime());
                intent.putExtra("Description", eventDataList.get(holder.getAdapterPosition()).getEventDesc());
                intent.putExtra("Key", eventDataList.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return eventDataList.size();
    }

    public void searchEventList(ArrayList<EventDataClass> eventSearchList){
        eventDataList = eventSearchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    ImageView eventRecImage;
    TextView eventRecDate, eventRecName, eventRecLocation;
    CardView eventRecCard;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        eventRecImage = itemView.findViewById(R.id.eventCardImage);
        eventRecName = itemView.findViewById(R.id.eventCardName);
        eventRecDate = itemView.findViewById(R.id.eventCardDate);
        eventRecLocation = itemView.findViewById(R.id.eventCardLocation);
        eventRecCard = itemView.findViewById(R.id.eventCard);

    }
}
