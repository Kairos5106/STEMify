package com.example.stemify.ui.moduleB;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.stemify.HomeworkHelp_Post;
import com.example.stemify.HomeworkHelp_Post_Adapter;
import com.example.stemify.R;
import com.example.stemify.HomeworkHelp_NewQuestion;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Forum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Forum extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView postRecyclerView;
    HomeworkHelp_Post_Adapter homeworkhelpPostAdapter;

    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    List<HomeworkHelp_Post> postList;

    public Forum() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Forum.
     */
    // TODO: Rename and change types and number of parameters
    public static Forum newInstance(String param1, String param2) {
        Forum fragment = new Forum();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_forum, container, false);

        // RecyclerView for posts list
        postRecyclerView = rootView.findViewById(R.id.modB_RVPostsList);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseDatabase = FirebaseDatabase.getInstance(); // get instance of the realtime database in firebase
        databaseReference = firebaseDatabase.getReference("Posts"); // go into Posts node


        // Binding - BtnAskAQuestion: for student to post a new question in the homework forum
        Button BtnAskAQuestion = rootView.findViewById(R.id.BtnAskAQuestion);

        // Set OnClickListener for the button
        BtnAskAQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Debug
                Log.d("ForumFragment", "Button clicked. Starting PostANewQuestion activity.");

                // Create an Intent to start HomeworkHelp_NewQuestion activity
                Intent intent = new Intent(getActivity(), HomeworkHelp_NewQuestion.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Load list of posts from Firebase Realtime Database [based on: databaseReference = firebaseDatabase.getReference("Posts");]
        // orderByChild("timeStamp) to order the posts by timestamp in descending order (most recent post will have highest timestamp)
        databaseReference.orderByChild("timeStamp").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                postList = new ArrayList<>();

                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {
                    HomeworkHelp_Post post = postsnap.getValue(HomeworkHelp_Post.class);
                    postList.add(post);
                }

                // Reverse the order of the postList so that the most recent post appear at top
                Collections.reverse(postList);

                // Set up and attach the adapter to RecyclerView
                // i.e. these lines of code establish the connection between the custom adapter (HomeworkHelp_Post_Adapter) and the RecyclerView (postRecyclerView)
                homeworkhelpPostAdapter = new HomeworkHelp_Post_Adapter(getActivity(), postList);
                postRecyclerView.setAdapter(homeworkhelpPostAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}