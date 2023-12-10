package com.example.stemify;

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

public class ForgotPassword extends AppCompatActivity {

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
                if(hasInput){
                    boolean isValid = true;
                    int counter = 0;

                    //check email
                    //true if match, false if unmatch
                    //check answer
                    //true if match, false if unmatch
                    //if isValid = false, increase counter by 1
                    //Toast.makeText(this, "Wrong Email or Answer!", Toast.LENGTH_SHORT).show();
                    //if counter == 3 , go to lock account page
                    //if correct, go to Change Password page
                    Intent nextScreen = new Intent(getApplicationContext(), ChangePassword.class);
                    startActivity(nextScreen);
                    finish();
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