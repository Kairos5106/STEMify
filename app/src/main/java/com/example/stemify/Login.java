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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    SharedPreferences userPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Instantiate shared preferences to store user identity
        // Will be used in the definition of certain activities for different users
        userPrefs = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = userPrefs.edit();

        //return to Splash 2 if cancel button is clicked
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
            long attemptTime;
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
                                        // Sign in success, check if the account is currently locked
                                        //if locked, sign the user out
                                        FirebaseUser currentUser = mAuth.getCurrentUser();
                                        // Read from the database
                                        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid());
                                        if (currentUser != null) {
                                            userRef.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        User user = dataSnapshot.getValue(User.class);
                                                        attemptTime = user.getAttemptTime();

                                                        // Add user info to shared preferences
                                                        prefEditor.putString("userIdentity", user.getIdentity());
                                                        prefEditor.putString("userName", user.getDisplayName());
                                                        prefEditor.putString("userEmail", user.getEmail());
                                                        prefEditor.putString("userPhotoUrl", user.getPhotoUrl());
                                                        prefEditor.commit();

                                                        if((System.currentTimeMillis()-attemptTime)>3600000){
                                                            Intent nextScreen = new Intent(getApplicationContext(), Welcome.class);
                                                            nextScreen.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                            startActivity(nextScreen);
                                                            finish();
                                                        }else{
                                                            Toast.makeText(Login.this, "Your account is locked. Try again later", Toast.LENGTH_SHORT).show();
                                                            FirebaseAuth.getInstance().signOut();
                                                        }
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(DatabaseError error) {
                                                    // Handle errors
                                                    Toast.makeText(getApplication(), "Error in Retrieving Account Data", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    } else {
                                        // If sign in fails (wrong password or email), increase the loginTrial counter
                                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
                                        usersRef.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                if (dataSnapshot.exists()) {
                                                    //user with the provided email exists
                                                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                                                        String userId = userSnapshot.getKey();
                                                        int loginTrial = userSnapshot.child("loginTrial").getValue(Integer.class);

                                                        //if either of them match, increase the loginTrial counter
                                                        Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                                                        if (loginTrial > 2) {
                                                            //if the attempt is more than three times, record the time to lock the account
                                                            loginTrial = 0;
                                                            Map<String, Object> updates = new HashMap<>();
                                                            updates.put("loginTrial", loginTrial);
                                                            updates.put("attemptTime", System.currentTimeMillis());
                                                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                                                            userRef.updateChildren(updates);
                                                            Intent nextScreen = new Intent(getApplicationContext(), LoginFailed.class);
                                                            startActivity(nextScreen);
                                                            finish();
                                                        }else{
                                                            loginTrial++;
                                                            //update at Firebase Realtime Database the number of loginTrial
                                                            Map<String, Object> updates = new HashMap<>();
                                                            updates.put("loginTrial", loginTrial);
                                                            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
                                                            userRef.updateChildren(updates);
                                                        }
                                                    }
                                                } else {
                                                    //if the email does not exist at all, inform the user
                                                    Toast.makeText(Login.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {
                                                System.err.println("Error searching for user by email: " + databaseError.getMessage());
                                            }
                                        });
                                    }
                                }
                            });
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