package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DownloadAdapter extends RecyclerView.Adapter<DownloadAdapter.DownloadViewHolder> {
    Context context;
    List<DownloadItem> list;

    public DownloadAdapter(Context context, List<DownloadItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public DownloadAdapter.DownloadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DownloadAdapter.DownloadViewHolder(LayoutInflater.from(context).inflate(R.layout.row_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DownloadAdapter.DownloadViewHolder holder, int position) {
        DownloadItem downloadItem = list.get(position);
        holder.text.setText(downloadItem.text);
        holder.image.setImageResource(downloadItem.imageId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class DownloadViewHolder extends RecyclerView.ViewHolder {
        TextView text;
        ImageView image;

        public DownloadViewHolder(@NonNull View itemView) {
            super(itemView);
            this.text = itemView.findViewById(R.id.TVTest);
            this.image = itemView.findViewById(R.id.IVTest);
        }
    }
}
