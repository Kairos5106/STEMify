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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        TextView TVHelloPass = findViewById(R.id.TVHelloPass);
        Intent intent = getIntent();
        if (intent != null) {
            String receivedData = intent.getStringExtra("fullname");
            String text = "Hello " + receivedData;
            TVHelloPass.setText(text);
        }

        //return to Splash 2 if cancel button is clicked
        Button BtnCancelChangePass = (Button) findViewById(R.id.BtnCancelChangePass);
        BtnCancelChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnBack = new Intent(getApplicationContext(), Splash2.class);
                startActivity(returnBack);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);
                finish();
            }
        });


        Button BtnSubmitNewPass = (Button) findViewById(R.id.BtnSubmitNewPass);
        BtnSubmitNewPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasInput = true;

                //check if all inputs (email + password) are filled
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
                if(hasInput){
                    //override the old password with the new password in the database
                    String newPassword = ETNewPassword.getText().toString();
                    if(newPassword.equals(ETConfirmNewPassword.getText().toString())){
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        FirebaseUser currentUser = mAuth.getCurrentUser();

                        if (currentUser != null) {
                            currentUser.updatePassword(newPassword)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Password updated successfully
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
                            Toast.makeText(getApplicationContext(), "Error in searching existing user", Toast.LENGTH_SHORT).show();
                        }
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
}