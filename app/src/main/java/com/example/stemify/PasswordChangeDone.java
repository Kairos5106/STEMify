package com.example.stemify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PasswordChangeDone extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change_done);

        //inform the user that their password has changed and return to login page
        Button BtnBackToLogin = findViewById(R.id.BtnBackToLogin);
        BtnBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent returnBack = new Intent(getApplicationContext(), Login.class);
                startActivity(returnBack);
                finish();
            }
        });
    }
}