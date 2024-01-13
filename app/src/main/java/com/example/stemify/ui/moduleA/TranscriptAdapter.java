package com.example.stemify.ui.moduleA;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stemify.R;

import java.util.List;

public class TranscriptAdapter extends RecyclerView.Adapter<TranscriptAdapter.TranscriptViewHolder> {
    Context context;
    String videoTranscript; // Contains full video transcript

    public TranscriptAdapter(Context context, String videoTranscript) {
        this.context = context;
        this.videoTranscript = videoTranscript;
    }

    public void setVideoTranscript(String videoTranscript) {
        this.videoTranscript = videoTranscript;
    }

    @NonNull
    @Override
    public TranscriptAdapter.TranscriptViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TranscriptAdapter.TranscriptViewHolder(LayoutInflater.from(context).inflate(R.layout.textbox_transcript, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TranscriptAdapter.TranscriptViewHolder holder, int position) {
        holder.transcript.setText(videoTranscript);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public String formatTranscript(String transcript){ // work on the logic here later
        return transcript;
    }

    public class TranscriptViewHolder extends RecyclerView.ViewHolder{
        TextView transcript;
        public TranscriptViewHolder(@NonNull View itemView) {
            super(itemView);
            this.transcript = itemView.findViewById(R.id.TVTranscript);
        }
    }
}
