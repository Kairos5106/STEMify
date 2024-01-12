package com.example.stemify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePassword extends AppCompatActivity {
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");

        //return to Splash 2 if cancel button is clicked (user decided not to proceed with changing password)
        Button BtnCancelChangePass = findViewById(R.id.BtnCancelChangePass);
        BtnCancelChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                finish();
            }
        });

        //update the user's password with new password
        Button BtnSubmitNewPass = findViewById(R.id.BtnSubmitNewPass);
        BtnSubmitNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasInput = true;

                //check if all inputs (new password) are filled
                EditText ETNewPassword = findViewById(R.id.ETNewPassword);
                EditText ETConfirmNewPassword = findViewById(R.id.ETConfirmNewPassword);
                if(isEmpty(ETNewPassword)){
                    ETNewPassword.setError("Enter a new password");
                    hasInput = false;
                }

                if(isEmpty(ETConfirmNewPassword)){
                    ETConfirmNewPassword.setError("Confirm your password");
                    hasInput = false;
                }

                if(!isEmpty(ETNewPassword)){
                    //check if the password has more than 6 characters
                    if(ETNewPassword.getText().toString().length()>=6){
                        boolean checker = hasTwoNonAlphaCharacters(ETNewPassword.getText().toString());
                        if(!checker){
                            ETNewPassword.setError("Requirements not fulfilled!");
                        }
                        hasInput = checker;
                    }else{
                        hasInput = false;
                        TextView TVWeakNewPass = findViewById(R.id.TVWeakNewPass);
                        TVWeakNewPass.setTextColor(Color.RED);
                    }
                }

                if(hasInput){
                    //override the old password with the new password in the database
                    String newPassword = ETNewPassword.getText().toString();

                    if(newPassword.equals(ETConfirmNewPassword.getText().toString())){
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        mAuth.signInWithEmailAndPassword(email, password)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            FirebaseUser user = mAuth.getCurrentUser();
                                            user.updatePassword(newPassword)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                //password updated successfully (at Firebase User Authentication)
                                                                //update at Firebase Realtime Database too
                                                                Map<String, Object> updates = new HashMap<>();
                                                                updates.put("password", newPassword);
                                                                DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid());
                                                                userRef.updateChildren(updates);

                                                                //move to password change done page to inform the user that their password has changed
                                                                Intent nextScreen = new Intent(getApplicationContext(), PasswordChangeDone.class);
                                                                startActivity(nextScreen);
                                                                finish();
                                                            } else {
                                                                // If updating the password fails, display a message to the user
                                                                Toast.makeText(getApplicationContext(), "Failed to update password", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Failed to retrieve account", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                        FirebaseAuth.getInstance().signOut();
                    }else{
                        TextView TVIncorrectPass = findViewById(R.id.TVIncorrectPass);
                        TVIncorrectPass.setText("Password Mismatch");
                        TVIncorrectPass.setTextColor(Color.RED);
                    }
                }
            }
        });
    }
    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean hasTwoNonAlphaCharacters(String inputString) {
        Pattern pattern = Pattern.compile("[^a-zA-Z]");
        Matcher matcher = pattern.matcher(inputString);

        int nonAlphaCount = 0;
        while (matcher.find()) {
            nonAlphaCount++;
            if (nonAlphaCount >= 2) {
                return true;
            }
        }
        return false;
    }
}