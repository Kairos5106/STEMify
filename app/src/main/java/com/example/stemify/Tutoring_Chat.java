package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Tutoring_Chat extends AppCompatActivity {

    TextView tutorUsername;
    ImageView tutorPfp;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    EditText ETMessage;
    ImageButton ImgBtnSendMessage;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_chat);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBChatConversation);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        // The name of the tutor you're chatting with
        String tutorName = getIntent().getExtras().getString("tutorName");
        setTitle("Chat with " + tutorName);

        // Binding
        ETMessage = findViewById(R.id.ETMessage);
        ImgBtnSendMessage = findViewById(R.id.ImgBtnSendMessage);

        // Get tutor info
        String tutor = getIntent().getExtras().getString("email");

        // Get current user info
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser(); // retrieve the currently signed in user

        ImgBtnSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageContent = ETMessage.getText().toString();

                if (!messageContent.equals("")) {
                    sendMessage(firebaseUser.getUid(), tutor, messageContent);
                } else {
                    Toast.makeText(Tutoring_Chat.this, "You can't send empty message.", Toast.LENGTH_SHORT).show();
                }

                ETMessage.setText("");
            }
        });




    }

    public void sendMessage(String sender, String receiver, String message) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

        reference.child("users").orderByChild("email").equalTo(receiver).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String tutorUID = snapshot.getKey();

                    // Create the message and push it to the "Chats" node
                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("sender", sender);
                    hashMap.put("receiver", tutorUID);
                    hashMap.put("message", message);
                    reference.child("TutoringChats").push().setValue(hashMap);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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