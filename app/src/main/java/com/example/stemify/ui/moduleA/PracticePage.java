package com.example.stemify.ui.moduleA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.stemify.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PracticePage extends AppCompatActivity {
    QuestionAdapter questionAdapter;
    RecyclerView recyclerView;
    List<Question> listOfQuestions;
    Practice practice;
    SelectHistory selectHistory;
    String selectedSection, selectedMaterial;
    DatabaseReference practiceRef;
    TextView TVGradeLevel, TVSubjectWithTopic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_page);

        // Assign data for RecyclerView usage
        initializeData();

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBPracticePage);
        toolbar.bringToFront(); // brings toolbar to the top-most layer
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Set the title for the app bar for this particular page
        getSupportActionBar().setTitle("Practice");

        // Set the back button at app bar to be white
        Drawable arrow = AppCompatResources.getDrawable(this, R.drawable.ic_arrow_back);
        DrawableCompat.setTint(arrow, Color.WHITE);
        getSupportActionBar().setHomeAsUpIndicator(arrow);
    }
    public void initializeData(){
        // Instantiate required variables to prevent the use of null values
        practice = new Practice();
        selectHistory = new SelectHistory();
        listOfQuestions = new ArrayList<>();

        // Gets data from previous activity intent
        Intent prevActivityData = getIntent();
        selectedSection = prevActivityData.getStringExtra("selectedSection");
        selectedMaterial = prevActivityData.getStringExtra("selectedMaterial");

        // Get user select history information
        DatabaseReference userSelectHistoryRef = FirebaseDatabase.getInstance().getReference("UserSelectHistory").child(getUserId());
        userSelectHistoryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                selectHistory = snapshot.getValue(SelectHistory.class);

                // Obtain Practice page data from database
                practiceRef = FirebaseDatabase.getInstance().getReference("Subjects")
                        .child(selectHistory.getSelectedSubject())
                        .child(selectHistory.getSelectedGrade())
                        .child(selectHistory.getSelectedTopic())
                        .child(selectHistory.getSelectedSubtopic())
                        .child(selectedSection)
                        .child(selectedMaterial);

                // Change the information on Practice page
                TVGradeLevel = findViewById(R.id.TVGradeLevel);
                TVGradeLevel.setText(selectHistory.selectedGrade);
                TVSubjectWithTopic = findViewById(R.id.TVSubjectWithTopic);
                TVSubjectWithTopic.setText(selectHistory.getSelectedSubject() + ": " + selectHistory.getSelectedTopic());


                practiceRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // Get questions and insert into Practice object
                        for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                            if(dataSnapshot.getValue() instanceof String){
                                continue;
                            }
                            Question question = dataSnapshot.getValue(Question.class);
                            if(question.getQuestionType().equalsIgnoreCase("MCQ")){
                                MultipleChoice multipleChoice = dataSnapshot.getValue(MultipleChoice.class);
                                listOfQuestions.add(multipleChoice);
                            }
                            else{
                                FillBlank fillBlank = dataSnapshot.getValue(FillBlank.class);
                                listOfQuestions.add(fillBlank);
                            }
                        }
                        questionAdapter.setListOfQuestions(listOfQuestions);
                        questionAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getUserId(){
        String currentUserId = null;
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser != null){
            currentUserId = currentUser.getUid();
        }
        return currentUserId;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Setup RecyclerView
        recyclerView = findViewById(R.id.RVPracticePage);
        recyclerView.setLayoutManager(new LinearLayoutManager(PracticePage.this));
        questionAdapter = new QuestionAdapter(PracticePage.this, listOfQuestions);
        recyclerView.setAdapter(questionAdapter);
        questionAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle the back button click
        if (item.getItemId() == android.R.id.home) {
            // Navigate back to the previous fragment or activity
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}