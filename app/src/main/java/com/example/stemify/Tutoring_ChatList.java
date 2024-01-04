package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tutoring_ChatList extends AppCompatActivity {

    private RecyclerView RVChatList;
    private Tutoring_Tutor_Adapter userAdapter;
    private List<User> mUsers;

    FirebaseUser fUser;
    DatabaseReference reference;

    private List<String> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoring_chat_list);

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBChatList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("Your Chats");

        // Set up recycler view
        RVChatList = findViewById(R.id.RVChatList);
        RVChatList.setHasFixedSize(true);
        RVChatList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        fUser = FirebaseAuth.getInstance().getCurrentUser();

        usersList = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("TutoringChats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                usersList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Tutoring_Chat chat = snapshot.getValue(Tutoring_Chat.class);

                    if (chat.getSender().equals(fUser.getUid())) {
                        usersList.add(chat.getReceiver());
                    }

                    if (chat.getReceiver().equals(fUser.getUid())) {
                        usersList.add(chat.getSender());
                    }

                }

                readChats();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void readChats() {
        mUsers = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    if (user != null) {
                        String userId = snapshot.getKey(); // Get the user ID directly from the snapshot key

                        user.setId(userId); // Set the user ID using the setter method

                        for (String id : usersList) {
                            if (userId.equals(id)) {
                                if (mUsers.size() != 0) {
                                    boolean userExists = false;
                                    for (User user1 : mUsers) {
                                        if (userId.equals(user1.getId())) {
                                            userExists = true;
                                            break;
                                        }
                                    }
                                    if (!userExists) {
                                        mUsers.add(user);
                                    }
                                } else {
                                    mUsers.add(user);
                                }
                            }
                        }
                    }
                }


                userAdapter = new Tutoring_Tutor_Adapter(getApplicationContext(), mUsers);
                RVChatList.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
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