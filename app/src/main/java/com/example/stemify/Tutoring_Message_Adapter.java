package com.example.stemify;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Tutoring_Message_Adapter extends RecyclerView.Adapter<Tutoring_Message_Adapter.ChatViewHolder> {

    public static final int MSG_TYPE_LEFT = 0;
    public static final int MSG_TYPE_RIGHT = 1;
    private Context mContext;
    private List<Tutoring_Chat> mChat;
    private String imageUrl;
    FirebaseUser firebaseUser;

    public Tutoring_Message_Adapter(Context mContext, List<Tutoring_Chat> mChat, String imageUrl) {
        this.mContext = mContext;
        this.mChat = mChat;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public Tutoring_Message_Adapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == MSG_TYPE_RIGHT) {
            View row = LayoutInflater.from(mContext).inflate(R.layout.tutoring_chat_item_right, parent, false);
            return new Tutoring_Message_Adapter.ChatViewHolder(row);
        } else {
            View row = LayoutInflater.from(mContext).inflate(R.layout.tutoring_chat_item_left, parent, false);
            return new Tutoring_Message_Adapter.ChatViewHolder(row);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull Tutoring_Message_Adapter.ChatViewHolder holder, int position) {
        Tutoring_Chat chat = mChat.get(position);

        holder.TVMessage.setText(chat.getMessage());

        if (imageUrl.equals("default")) {
            holder.IVPfp.setImageResource(R.mipmap.ic_launcher);
        } else {
            Picasso.get().load(imageUrl).into(holder.IVPfp);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        ImageView IVPfp;
        TextView TVMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            IVPfp = itemView.findViewById(R.id.IVPfp);
            TVMessage = itemView.findViewById(R.id.TVMessage);

        }
    }

    @Override
    public int getItemViewType(int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(firebaseUser.getUid())) {
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}
