package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //return to Splash 2 if cancel button is clicked
        //no data/information will be saved to database
        Button BtnCancelLogin = findViewById(R.id.BtnCancelLogin);
        BtnCancelLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                finish();
            }
        });

        //go to forgot password activity
        TextView TVForgotPassword = findViewById(R.id.TVForgotPassword);
        TVForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), ForgotPassword.class);
                startActivity(nextScreen);
                finish();
            }
        });

        //go to sign up activity
        TextView TVAccSU = findViewById(R.id.TVAccSU);
        TVAccSU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), SignUp.class);
                startActivity(nextScreen);
                finish();
            }
        });

        //go to Welcome page after successfully login
        Button BtnLogin = findViewById(R.id.BtnLogin);
        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasInput = true;

                //check if all inputs (email + password) are filled
                EditText ETLoginEmail = findViewById(R.id.ETLoginEmail);
                EditText ETLoginPassword = findViewById(R.id.ETLoginPassword);
                if(isEmail(ETLoginEmail) == false){
                    ETLoginEmail.setError("Enter valid email");
                    hasInput = false;
                }
                if(isEmpty(ETLoginPassword)){
                    ETLoginPassword.setError("Enter a password");
                    hasInput = false;
                }
                //check if the inputs are valid (match the data)
                if(hasInput){
                    boolean isValid = true;
                    int counter = 0;

                    //check email
                    //true if match, false if unmatch
                    //check password
                    //true if match, false if unmatch
                    //if isValid = false, increase counter by 1
                    //Toast.makeText(this, "Wrong Email or Password!", Toast.LENGTH_SHORT).show();
                    //if counter == 3 , go to lock account page
                    //if correct, go to Welcome page
                    Intent nextScreen = new Intent(getApplicationContext(), Welcome.class);
                    nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(nextScreen);
                    finish();
                }
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