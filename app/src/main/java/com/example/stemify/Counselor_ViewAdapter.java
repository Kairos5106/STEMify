package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Counselor_ViewAdapter extends RecyclerView.Adapter<Counselor_ViewAdapter.ViewHolder>{

    List<Counselor_ViewData> bookingList;
    Context context;

    public Counselor_ViewAdapter(List<Counselor_ViewData> bookingList, Context context) {
        this.bookingList = bookingList;
        this.context = context;
    }

    @NonNull
    @Override
    public Counselor_ViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.counselor_list_view_all, parent, false);
        Counselor_ViewAdapter.ViewHolder viewHolder = new Counselor_ViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Counselor_ViewAdapter.ViewHolder holder, int position){
        final Counselor_ViewData bookingDataList = bookingList.get(position);
        holder.TVName.setText(bookingDataList.getBooking_dr_name());
        holder.TVTime.setText(bookingDataList.getBooking_time());
        holder.TVDate.setText(bookingDataList.getBooking_date());

    }

    public int getItemCount(){
        return bookingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView TVName, TVTime, TVDate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            TVName = itemView.findViewById(R.id.textName);
            TVTime = itemView.findViewById(R.id.textTime);
            TVDate = itemView.findViewById(R.id.textDate);
        }
    }


}
