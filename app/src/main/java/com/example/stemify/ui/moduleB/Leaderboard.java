package com.example.stemify.ui.moduleB;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.stemify.Leaderboard_Ranking_Adapter;
import com.example.stemify.R;
import com.example.stemify.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Leaderboard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Leaderboard extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView leaderboardRankRecyclerView;
    Leaderboard_Ranking_Adapter leaderboardRankingAdapter;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public Leaderboard() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Leaderboard.
     */
    // TODO: Rename and change types and number of parameters
    public static Leaderboard newInstance(String param1, String param2) {
        Leaderboard fragment = new Leaderboard();
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

            mAuth = FirebaseAuth.getInstance();
            currentUser = mAuth.getCurrentUser();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        // RecyclerView for posts list
        leaderboardRankRecyclerView = rootView.findViewById(R.id.RVLiveConferenceList);
        leaderboardRankRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        firebaseDatabase = FirebaseDatabase.getInstance(); // get instance of the realtime database in firebase
        databaseReference = firebaseDatabase.getReference("users"); // go into users node


        // Binding - BtnEarnXP: navigate student to forum page
        Button BtnEarnXP = rootView.findViewById(R.id.BtnEarnXP);

        // Set OnClickListener for the button
        BtnEarnXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Button clicked", Toast.LENGTH_SHORT).show();

            }
        });

        return rootView;


    }

    @Override
    public void onStart() {
        super.onStart();

        // Load users data from Firebase Realtime Database
        // Order the users by their display names
        databaseReference.orderByChild("displayName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<User> userList = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    userList.add(user);
                }

                // Set up and attach the adapter to RecyclerView
                // i.e. these lines of code establish the connection between the custom adapter (HomeworkHelp_Post_Adapter) and the RecyclerView (postRecyclerView)
                leaderboardRankingAdapter = new Leaderboard_Ranking_Adapter(getActivity(), userList);
                leaderboardRankRecyclerView.setAdapter(leaderboardRankingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error if needed
            }
        });
    }
}