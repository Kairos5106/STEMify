package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Tutoring_Message extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    EditText ETMessage;
    ImageButton ImgBtnSendMessage;

    Tutoring_Message_Adapter tutoringMessageAdapter;
    List<Tutoring_Chat> mChat;
    RecyclerView RVConversations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_message);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBChatConversation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        // The name of the tutor you're chatting with
        String tutorUsername = getIntent().getExtras().getString("tutorUsername");
        setTitle("Chat with " + tutorUsername);

        // Binding
        ETMessage = findViewById(R.id.ETMessage);
        ImgBtnSendMessage = findViewById(R.id.ImgBtnSendMessage);

        // Set up recycler view for conversation items
        RVConversations = findViewById(R.id.RVConversations);
        RVConversations.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setStackFromEnd(true);
        RVConversations.setLayoutManager(linearLayoutManager);

        // Get receiver info
        String otherUser = getIntent().getExtras().getString("email");
        String tutorPfp = getIntent().getExtras().getString("tutorPfp");

        // Get current user info
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser(); // retrieve the currently signed in user

        ImgBtnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = ETMessage.getText().toString();

                if (!messageContent.equals("")) {
                    sendMessage(firebaseUser.getUid(), otherUser, messageContent);

                } else {
                    Toast.makeText(Tutoring_Message.this, "You can't send empty message.", Toast.LENGTH_SHORT).show();
                }

                ETMessage.setText("");
            }
        });

        initRVConversations(firebaseUser.getUid(), otherUser, tutorPfp);

    }

    public void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("users").orderByChild("email").equalTo(receiver).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String receiver = snapshot.getKey(); // fetch tutor

                    // Create the message and push it to the "Chats" node
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("sender", sender);
                    hashMap.put("receiver", receiver);
                    hashMap.put("message", message);
                    reference.child("TutoringChats").push().setValue(hashMap);

                    Toast.makeText(Tutoring_Message.this, "Message sent.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle any errors
            }
        });
    }

    private void initRVConversations(String myID, String userID, String imageUrl) {
        mChat = new ArrayList<>();

        // Query to get the UID based on the email address
        DatabaseReference userReference = FirebaseDatabase.getInstance().getReference("users");
        userReference.orderByChild("email").equalTo(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String otherUserID = snapshot.getKey(); // Retrieve the UID of the other user

                    // Continue with your existing logic to fetch messages
                    databaseReference = FirebaseDatabase.getInstance().getReference("TutoringChats");
                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            mChat.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Tutoring_Chat chat = snapshot.getValue(Tutoring_Chat.class);
                                if (chat != null && ((chat.getReceiver().equals(myID) && chat.getSender().equals(otherUserID)) ||
                                        (chat.getReceiver().equals(otherUserID) && chat.getSender().equals(myID)))) {
                                    mChat.add(chat);
                                }
                            }

                            tutoringMessageAdapter = new Tutoring_Message_Adapter(Tutoring_Message.this, mChat, imageUrl);
                            RVConversations.setAdapter(tutoringMessageAdapter);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle any errors
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle any errors
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}