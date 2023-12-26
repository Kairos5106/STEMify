package com.example.stemify;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CounselorAdapter extends RecyclerView.Adapter<CounselorAdapter.ViewHolder> {

    CounselorData[] counselorData;
    Context context;

    public CounselorAdapter(CounselorData[] counselorData, Counselor counselor){
        this.counselorData = counselorData;
        this.context = counselor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.counselor_listview, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        final CounselorData counselorDataList = counselorData[position];
        holder.textViewName.setText(counselorDataList.getName());
        holder.textViewExp.setText(counselorDataList.getExperience());
        holder.textViewInfo.setText(counselorDataList.getInformation());
        holder.textViewPat.setText(counselorDataList.getPatients());
        holder.drImage.setImageResource(counselorDataList.getImagedr());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, counselorDataList.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, BookingCounselor.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return counselorData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView drImage;
        TextView textViewName, textViewExp, textViewInfo, textViewPat;
        CardView cardView;



        public ViewHolder(@NonNull View itemView){
            super(itemView);
            drImage = itemView.findViewById(R.id.imageview);
            textViewName = itemView.findViewById(R.id.textName);
            textViewExp = itemView.findViewById(R.id.textExp);
            textViewInfo = itemView.findViewById(R.id.textInfo);
            textViewPat = itemView.findViewById(R.id.textPatients);
            cardView = itemView.findViewById(R.id.cardViewCounselor);
        }

    }
}
