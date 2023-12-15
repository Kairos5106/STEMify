package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ForgotPassword extends AppCompatActivity {

    public int counter = 0;
    FirebaseAuth mAuth;
    boolean selectionChecker = false;
    String chosenSecurityQuestion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        //dropdown for security questions
        String[] SECURITYQ = new String[]{
                "Security Question", "What is the name of your favourite teacher?", "What is your favourite food?", "What is your favourite colour?", "What is your favourite city?"
        };

        Spinner SPSecurityQ = (Spinner) findViewById(R.id.SPSecurityQ);
        ArrayAdapter<String> arrAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, SECURITYQ);
        arrAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SPSecurityQ.setAdapter(arrAdapter);
        SPSecurityQ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //check if the selection is "Security Question"
                if (parent.getItemAtPosition(position).equals("Security Question")){
                    TextView TVSelectSQ = findViewById(R.id.TVSelectSQ);
                    TVSelectSQ.setText("Please Select A Question");
                    TVSelectSQ.setTextColor(Color.RED);
                }else{
                    chosenSecurityQuestion = (String) parent.getItemAtPosition(position);
                    selectionChecker = true;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                TextView TVSelectSQ = findViewById(R.id.TVSelectSQ);
                TVSelectSQ.setText("Please Select A Question");
                TVSelectSQ.setTextColor(Color.RED);
            }
        });

        //move to new password page if the identity matched
        Button BtnSubmitSecAns = findViewById(R.id.BtnSubmitSecAns);
        BtnSubmitSecAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean hasInput = true;

                //check if all inputs (email, security question and answer) are filled
                EditText ETEmailAdd = findViewById(R.id.ETEmailAdd);
                EditText ETAnswer = findViewById(R.id.ETAnswer);
                if(isEmail(ETEmailAdd) == false){
                    ETEmailAdd.setError("Enter valid email");
                    hasInput = false;
                }
                if(isEmpty(ETAnswer)){
                    ETAnswer.setError("Enter an answer");
                    hasInput = false;
                }
                //check if the inputs are valid (match data)
                if(hasInput && selectionChecker){
                    String enteredEmail = ETEmailAdd.getText().toString();
                    String answer = ETAnswer.getText().toString();

                    mAuth = FirebaseAuth.getInstance();
                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    if(currentUser != null){
                        String uid = currentUser.getUid();

                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("users").child(uid);
                        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String email = currentUser.getEmail();
                                    String securityQuestion = dataSnapshot.child("securityques").getValue(String.class);
                                    String securityAnswer = dataSnapshot.child("answer").getValue(String.class);

                                    if(enteredEmail.equals(email) && chosenSecurityQuestion.equals(securityQuestion) && answer.equals(securityAnswer)){
                                        Intent nextScreen = new Intent(getApplicationContext(), ChangePassword.class);
                                        nextScreen.putExtra("fullname", dataSnapshot.child("fullname").getValue(String.class));
                                        startActivity(nextScreen);
                                        finish();
                                    }else{
                                        counter++;
                                        Toast.makeText(ForgotPassword.this, "Wrong inputs", Toast.LENGTH_SHORT).show();
                                        //unsuccessful attempts for three times, go to Login Fail page
                                        if (counter == 3) {
                                            Intent nextScreen = new Intent(getApplicationContext(), LoginFailed.class);
                                            startActivity(nextScreen);
                                            finish();
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(getApplicationContext(), "Error encountered during data retrieval", Toast.LENGTH_SHORT).show();                            }
                        });

                    }
                }
            }
        });

        //return to Splash 2 if cancel button is clicked
        Button BtnCancelSubmitSecAns = findViewById(R.id.BtnCancelSubmitSecAns);
        BtnCancelSubmitSecAns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                finish();
            }
        });

    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text){
        CharSequence str = text.getText().toString();
        return (!TextUtils.isEmpty(str) && Patterns.EMAIL_ADDRESS.matcher(str).matches());
    }
}