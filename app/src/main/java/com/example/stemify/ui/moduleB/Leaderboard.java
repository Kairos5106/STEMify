package com.example.stemify.ui.moduleB;

import static org.webrtc.ContextUtils.getApplicationContext;

import android.content.Intent;
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

import com.example.stemify.HomeworkHelp_NewQuestion;
import com.example.stemify.Leaderboard_Ranking_Adapter;
import com.example.stemify.Leaderboard_ScoreData;
import com.example.stemify.Quiz_StartQuiz;
import com.example.stemify.R;
import com.example.stemify.User;
import com.example.stemify.ui.moduleA.Downloads;
import com.example.stemify.ui.moduleA.QuizPage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    List<Leaderboard_ScoreData> listScoreData;

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
        databaseReference = firebaseDatabase.getReference("QuizScores"); // go into QuizScores node

        // Display the leaderboard rankings
        listScoreData = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listScoreData.clear(); // Clear the list before adding new data

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String userUid = userSnapshot.getKey();

                    if (userUid != null) {
                        String username = userSnapshot.child("username").getValue(String.class);
                        String userPfp = userSnapshot.child("userPhotoUrl").getValue(String.class);
                        Long score = userSnapshot.child("result").getValue(Long.class);

                        if (username != null && userPfp != null && score != null) {
                            Leaderboard_ScoreData data = new Leaderboard_ScoreData(username, userPfp, score);
                            listScoreData.add(data);
                        }
                    }
                }

                // Sort the list by score in descending order
                Collections.sort(listScoreData, new Comparator<Leaderboard_ScoreData>() {
                    @Override
                    public int compare(Leaderboard_ScoreData o1, Leaderboard_ScoreData o2) {
                        return Long.compare(o2.getScore(), o1.getScore());
                    }
                });

                leaderboardRankingAdapter = new Leaderboard_Ranking_Adapter(getActivity(), listScoreData);
                leaderboardRankRecyclerView.setAdapter(leaderboardRankingAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getActivity(), "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        // Binding - BtnEarnXP: navigate student to forum page
        Button BtnEarnXP = rootView.findViewById(R.id.BtnEarnXP);

        // Set OnClickListener for the button
        BtnEarnXP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Start Quiz", Toast.LENGTH_SHORT).show();

                // Create an Intent to start HomeworkHelp_NewQuestion activity
                Intent intent = new Intent(getActivity(), Quiz_StartQuiz.class);
                //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });

        return rootView;


    }

}