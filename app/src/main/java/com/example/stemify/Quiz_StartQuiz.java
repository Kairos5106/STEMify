package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class Quiz_StartQuiz extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser currentUser = mAuth.getCurrentUser();
    DatabaseReference userRef;

    private String userPhotoUrl;
    private String username;

    private TextView TVQuizQuestion;
    private TextView TVIndicator;
    private MaterialButton BtnAns1;
    private MaterialButton BtnAns2;
    private MaterialButton BtnAns3;
    private MaterialButton BtnAns4;
    private Button BtnNextQuestionQuiz;
    private LinearLayout LinearLayoutOptions;

    private int score = 0;
    private int position = 0;
    private int count = 0;

    DatabaseReference reference;
    private List<Quiz_QuestionData> listQuestionData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_start_quiz);

        // Initiate Firebase components
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userRef = FirebaseDatabase.getInstance().getReference().child("users");

        // Enable back button in the action bar
        Toolbar toolbar = findViewById(R.id.TBStartQuiz);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Set the title for the app bar for this particular page
        setTitle("Quiz");

        // Binding
        TVQuizQuestion = findViewById(R.id.TVQuizQuestion);
        TVIndicator = findViewById(R.id.TVIndicator);
        BtnAns1 = findViewById(R.id.BtnAns1);
        BtnAns2 = findViewById(R.id.BtnAns2);
        BtnAns3 = findViewById(R.id.BtnAns3);
        BtnAns4 = findViewById(R.id.BtnAns4);
        BtnNextQuestionQuiz = findViewById(R.id.BtnNextQuestionQuiz);
        LinearLayoutOptions = findViewById(R.id.LinearLayoutOptions);

        // Load current user info from Realtime Database
        if (currentUser != null) {
            String userId = currentUser.getUid();

            userRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        User user = snapshot.getValue(User.class);

                        if (user != null) {
                            userPhotoUrl = user.getPhotoUrl();
                            username = user.getDisplayName();
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Error handling
                    Toast.makeText(getApplicationContext(), "Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        }

        // Play quiz
        listQuestionData = new ArrayList<>();
        reference = FirebaseDatabase.getInstance().getReference(); // get instance of the database so that can perform read/write operation

        reference.child("QuizQuestions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                // Get question set from database
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String question = snapshot.child("question").getValue().toString();
                    String option1 = snapshot.child("option1").getValue().toString();
                    String option2 = snapshot.child("option2").getValue().toString();
                    String option3 = snapshot.child("option3").getValue().toString();
                    String option4 = snapshot.child("option4").getValue().toString();
                    String correctAnswer = snapshot.child("answer").getValue().toString();

                    // Make the particular question set object
                    listQuestionData.add(new Quiz_QuestionData(option1, option2, option3, option4, question, correctAnswer));
                }

                // Load the question that was retrieved from the database into the activity screen
                if (listQuestionData.size() > 0) {
                    loadQuestion(TVQuizQuestion, 0, listQuestionData.get(position).getQuestion());

                    // When student click on one of the mcq options
                    for (int i = 0 ; i < 4 ; i++) {
                        LinearLayoutOptions.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                checkAnswer((Button)view);
                            }
                        });
                    }

                    // Button to go to next question
                    BtnNextQuestionQuiz.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            BtnNextQuestionQuiz.setEnabled(false);
                            BtnNextQuestionQuiz.setAlpha(0.7f);
                            enabled(true);
                            position++;

                            if (position == listQuestionData.size()) {
                                Intent intent = new Intent(Quiz_StartQuiz.this, Quiz_ScoreActivity.class);
                                intent.putExtra("score", score);
                                intent.putExtra("total", listQuestionData.size());
                                startActivity(intent);
                                finish();
                                return;
                            }

                            count = 0;
                            loadQuestion(TVQuizQuestion, 0, listQuestionData.get(position).getQuestion());

                        }
                    });

                } else {
                    Toast.makeText(Quiz_StartQuiz.this, "No data found.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Quiz_StartQuiz.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadQuestion(View view, int value, String data) {

        for (int i = 0 ; i < 4 ; i++) {
            LinearLayoutOptions.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
        }
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animation) {
                        if (value == 0 && count < 4) {
                            String option = "";

                            switch (count) {
                                case 0:
                                    option = listQuestionData.get(position).getOption1();
                                    break;
                                case 1:
                                    option = listQuestionData.get(position).getOption2();
                                    break;
                                case 2:
                                    option = listQuestionData.get(position).getOption3();
                                    break;
                                case 3:
                                    option = listQuestionData.get(position).getOption4();
                                    break;
                            }

                            loadQuestion(LinearLayoutOptions.getChildAt(count), 0, option);
                            count++;

                        }
                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animation) {
                        if (value == 0) {

                            try {
                                ((TextView)view).setText(data);
                                TVIndicator.setText((position + 1) + "/" + listQuestionData.size());
                            } catch (ClassCastException e) {
                                ((Button)view).setText(data);
                            }

                            view.setTag(data);
                            loadQuestion(view, 1, data);
                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animation) {

                    }
                });

    }

    private void checkAnswer(Button selectedOption) {
        enabled(false);
        BtnNextQuestionQuiz.setEnabled(true);
        BtnNextQuestionQuiz.setAlpha(1);

        if (selectedOption.getText().toString().equals(listQuestionData.get(position).getAnswer())) {
            score++;
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        } else {
            selectedOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
            Button correctOption = LinearLayoutOptions.findViewWithTag(listQuestionData.get(position).getAnswer());
            correctOption.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#4CAF50")));
        }


    }

    private void enabled(Boolean enable) {
        for (int i = 0 ; i < 4 ; i++) {
            LinearLayoutOptions.getChildAt(i).setEnabled(enable);

            if (enable) {
                LinearLayoutOptions.getChildAt(i).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#989898")));
            }
        }
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