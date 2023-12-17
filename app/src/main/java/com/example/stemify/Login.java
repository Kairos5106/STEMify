package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    public int counter = 0;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();


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

        //check if the account is lock previously
        SharedPreferences pref = this.getSharedPreferences("Sample", Context.MODE_PRIVATE);
        long time = pref.getLong("ATTEMPT_Time", 0);
        if(time>(System.currentTimeMillis()-3600000)) {
            //disable login button
            BtnLogin.setClickable(false);
        }else {
            BtnLogin.setClickable(true);
            BtnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean hasInput = true;

                    //check if all inputs (email + password) are filled
                    EditText ETLoginEmail = findViewById(R.id.ETLoginEmail);
                    EditText ETLoginPassword = findViewById(R.id.ETLoginPassword);
                    if (isEmail(ETLoginEmail) == false) {
                        ETLoginEmail.setError("Enter valid email");
                        hasInput = false;
                    }
                    if (isEmpty(ETLoginPassword)) {
                        ETLoginPassword.setError("Enter a password");
                        hasInput = false;
                    }
                    //check if the inputs are valid (match the data)
                    if (hasInput) {
                        String email = ETLoginEmail.getText().toString();
                        String password = ETLoginPassword.getText().toString();

                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            // Sign in success, go to Welcome page
                                            Intent nextScreen = new Intent(getApplicationContext(), Welcome.class);
                                            nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                            startActivity(nextScreen);
                                            finish();
                                        } else {
                                            // If sign in fails, display a message to the user, counter increment by 1
                                            counter++;
                                            Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                                            //unsuccessful attempts for three times, go to Login Fail page
                                            if (counter == 3) {
                                                Intent nextScreen = new Intent(getApplicationContext(), LoginFailed.class);
                                                startActivity(nextScreen);
                                                finish();
                                            }
                                        }
                                    }
                                });
                    }
                }
            });
        }
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